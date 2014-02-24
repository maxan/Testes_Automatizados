package br.treinamento;

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
