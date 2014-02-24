package br.treinamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntidadeDAODBUnitTest extends DatabaseTestCase {
	private EntidadeNegocio classeNegocio;
	private EntidadeDAOInterface persistencia;
	private final static String TABELA_ENTIDADE = "entidade";

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		IDatabaseConnection dataBaseConnection = new DatabaseConnection(ConnectionFactory.getConnection());
		dataBaseConnection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new HsqldbDataTypeFactory());
		
		return dataBaseConnection;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("dados.xml"));
		
		// Adiciona alguns termos especiais para facilitar a inserção de dados.
		ReplacementDataSet replacement = new ReplacementDataSet(dataSet);
		Calendar calendario = Calendar.getInstance();
		
		replacement.addReplacementObject("null", null);
		replacement.addReplacementObject("hoje", new Date());
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		replacement.addReplacementObject("ontem", calendario.getTime());
		
		return replacement;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("INICIANDO o teste da classe de negócio.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("CONCLUÍDO teste da classe de negócio.");
	}
	
	@Before
	public void setUp() throws Exception {
		persistencia = new EntidadeDAO();
		classeNegocio = new EntidadeNegocio();
		classeNegocio.setPersistencia(persistencia);
		
		// A linha abaixo garante que vou conhecer o estado do banco após cada testes, já
		// que executa um DELETE_ALL depois de cada teste realizado.
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}
	
	public void testVerificarUnicidadeNome() throws Exception {
		Entidade entidadeEntrada;
		boolean respostaActual;
		
		// Cenário 1: já existe a pessoa cadastrada.
		entidadeEntrada = getEntidadeValida();
		
		respostaActual = classeNegocio.verificarUnicidadeNome(entidadeEntrada);
		
		assertTrue("Cenário 1: já existe a pessoa cadastrada.", respostaActual);
		
		// Cenário 2: não existe a pessoa cadastrada.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome("Alessandra da Silva");
		entidadeEntrada.setNumeroDocumento(88666113529L);
		
		respostaActual = classeNegocio.verificarUnicidadeNome(entidadeEntrada);
		
		assertFalse("Cenário 2: não existe a pessoa cadastrada.", respostaActual);
	}
	
	public void testGetQuantidadeRegistros() throws Exception {
		int quantidadeRetrieveAll;
		int quantidadeGetQuantidade;
		
		// Cenário único: retorna a quantidade de registos.
		quantidadeGetQuantidade = classeNegocio.getQuantidadeRegistros();
		quantidadeRetrieveAll = persistencia.retrieveAll().size();
		
		assertEquals(quantidadeRetrieveAll, quantidadeGetQuantidade);
	}
	
	public void testExcluir() throws Exception {
		Entidade entidadeEntrada;
		int quantidadeRegistrosExpected;
		int quantidadeRegistrosActual;
		
		// Cenário 1: exclusão realizada com sucesso.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setTipoDocumento(2);
		entidadeEntrada.setId(new Long(4));
		
		quantidadeRegistrosExpected = classeNegocio.getQuantidadeRegistros() - 1;
		
		classeNegocio.excluir(entidadeEntrada);
		
		quantidadeRegistrosActual = classeNegocio.getQuantidadeRegistros();
		
		assertEquals("Cenário 1: exclusão realizada com sucesso.", quantidadeRegistrosExpected, quantidadeRegistrosActual);
		
		// Cenário 2: tenta excluir entidade com tipo de documento CPF (erro).
		entidadeEntrada = classeNegocio.getById(new Long(1));
		
		try {
			classeNegocio.excluir(entidadeEntrada);
			
			fail("Cenário 2: tenta excluir entidade com tipo de documento CPF (erro). Deveria lançar uma exceção por estar tentando excluir uma entidade com tipo de documento CPF.");
		} catch (Exception e) {
			assertEquals("Cenário 2: tenta excluir entidade com tipo de documento CPF (erro).", "Não é possível excluir entidades com CPF", e.getMessage());
		}
	}
	
	/**
	 * Gera um objeto de Entidade válido e corretamente preenchido.
	 * 
	 * @return Entidade
	 */
	private Entidade getEntidadeValida() {
		Calendar dataCalendario = Calendar.getInstance();
		
		Entidade entidade = new Entidade();
		entidade.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidade.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidade.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidade.setTipoDocumento(1);
		entidade.setNumeroDocumento(new Long(01123435456));
		
		return entidade;
	}
}
