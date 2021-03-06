package br.treinamento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.util.Calendar;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.InputSource;

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
	
	public void testGetById() throws Exception {
		Entidade entidadeExpected;
		Entidade entidadeActual;
		Calendar data = Calendar.getInstance();
		
		// Cenário 1: registro encontrado com sucesso.
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setNome("Anderson Marinho");
		entidadeExpected.setId(new Long(5));
		data.set(2014, 2, 28);
		entidadeExpected.setDataGravacao(data.getTime());
		
		entidadeActual = classeNegocio.getById(new Long(5));
		
		assertEquals("Cenário 1: registro encontrado com sucesso.", entidadeExpected.getId(), entidadeActual.getId());
		
		// Cenário 2: registro não encontrado.
		entidadeActual = classeNegocio.getById(new Long(10));
		
		assertNull("Cenário 2: registro não encontrado.", entidadeActual);
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
	
	public void testValidarCamposObrigatorios() throws Exception {
		Entidade entidadeEntrada;
		Entidade entidadeActual;
		Entidade entidadeExpected;
		
		// Cenário 1: todos os campos válidos.
		entidadeEntrada = classeNegocio.getById(new Long(5));
		entidadeEntrada.setEmail("meunovo@email.com");
		entidadeEntrada.setNumeroDocumento(81888833017L);
		
		entidadeActual = classeNegocio.alterar(entidadeEntrada);
		
		entidadeExpected = classeNegocio.getById(new Long(5));
		
		assertTrue("Cenário 1: todos os campos válidos.", entidadeExpected.equals(entidadeActual));
		
		// Cenário 2: nome não preenchido.
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setNome(null);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 2: nome não preenchido. O campo nome não deve estar preenchido (nulo).");
		} catch (Exception e) {
			assertEquals("Cenário 2: nome não preenchido.", "O nome é obrigatório", e.getMessage());
		}
		
		// Cenário 3: número do documento não preenchido.
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setNumeroDocumento(null);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 3: número do documento não preenchido. O campo número do documento não deve estar preenchido (nulo).");
		} catch (Exception e) {
			assertEquals("Cenário 3: número do documento não preenchido.", "O número do documento é obrigatório", e.getMessage());
		}
		
		// Cenário 4: tipo do documento não preenchido.
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setTipoDocumento(null);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 4: tipo do documento não preenchido. O campo tipo do documento não deve estar preenchido (nulo).");
		} catch (Exception e) {
			assertEquals("Cenário 4: tipo do documento não preenchido.", "O tipo do documento é obrigatório", e.getMessage());
		}
		
		// Cenário 5: período não informado por completo.
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setDataFinal(null);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 5: período não informado por completo. O campo data final não deve estar preenchido (nulo).");
		} catch (Exception e) {
			assertEquals("Cenário 5: período não informado por completo.", "O período deve ser informado por completo", e.getMessage());
		}
	}
	
	public void testValidarRegras() throws Exception {
		Entidade entidadeEntrada;
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Calendar calendario = Calendar.getInstance();
		
		// Cenário 1: todos os campos válidos.
		entidadeEntrada = classeNegocio.getById(new Long(5));
		entidadeEntrada.setEmail("meunovo@email.com");
		entidadeEntrada.setNumeroDocumento(81888833017L);
		
		entidadeActual = classeNegocio.alterar(entidadeEntrada);
		
		entidadeExpected = classeNegocio.getById(new Long(5));
		
		assertTrue("Cenário 1: todos os campos válidos.", entidadeExpected.equals(entidadeActual));
		
		// Cenário 2: tenta alterar o campo nome com um nome contendo mais do que 30 caracteres (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setNome("Camila Alves da Silva Marinho e Silva");
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 2: tenta alterar o campo nome com um nome contendo mais do que 30 caracteres (erro). O campo nome não deve ter mais que 30 caracteres.");
		} catch (Exception e) {
			assertEquals("Cenário 2: tenta alterar o campo nome com um nome contendo mais do que 30 caracteres (erro).", "O nome não pode ter mais que 30 caracteres", e.getMessage());
		}
		
		// Cenário 3: tenta alterar o campo nome com um nome contendo menos do que 5 caracteres (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setNome("Ana");
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 3: tenta alterar o campo nome com um nome contendo menos do que 5 caracteres (erro). O campo nome não deve ter menos que 5 caracteres.");
		} catch (Exception e) {
			assertEquals("Cenário 3: tenta alterar o campo nome com um nome contendo menos do que 5 caracteres (erro).", "O nome não pode ter menos que 5 caracteres", e.getMessage());
		}
		
		// Cenário 4: tenta alterar com campo número do documento menor ou igual a zero (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setNumeroDocumento(0L);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 4: tenta alterar com campo número do documento menor ou igual a zero (erro). O campo número do documento deve ser menor ou igual a zero.");
		} catch (Exception e) {
			assertEquals("Cenário 4: tenta alterar com campo número do documento menor ou igual a zero (erro).", "O número do documento deve ser maior que zero", e.getMessage());
		}
		
		// Cenário 5: tenta alterar com campo data inicial menor que a data atual (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		entidadeEntrada.setDataInicial(calendario.getTime());
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 5: tenta alterar com campo data inicial menor que a data atual (erro). O campo data inicial deve ser menor que a data atual.");
		} catch (Exception e) {
			assertEquals("Cenário 5: tenta alterar com campo data inicial menor que a data atual (erro).", "A data inicial não pode ser menor que a data atual", e.getMessage());
		}
		
		// Cenário 6: tenta alterar com campo data final menor que a data inicial (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		entidadeEntrada.setDataFinal(calendario.getTime());
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 6: tenta alterar com campo data final menor que a data inicial (erro). O campo data final deve ser menor que a data inicial.");
		} catch (Exception e) {
			assertEquals("Cenário 6: tenta alterar com campo data final menor que a data inicial (erro).", "A data final não pode ser menor que a data inicial", e.getMessage());
		}
		
		// Cenário 7: tenta alterar com campo tipo do documento inválido (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setTipoDocumento(4);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 7: tenta alterar com campo tipo do documento inválido (erro). O campo tipo do documento deve ser inválido.");
		} catch (Exception e) {
			assertEquals("Cenário 7: tenta alterar com campo tipo do documento inválido (erro).", "Tipo de documento inválido", e.getMessage());
		}
		
		// Cenário 8: tenta salvar com campo e-mail inválido (erro).
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setEmail("meuemailcom");
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 8: tenta salvar com campo e-mail inválido (erro). O campo e-mail deve ser inválido.");
		} catch (Exception e) {
			assertEquals("Cenário 8: tenta salvar com campo e-mail inválido (erro).", "Endereço de email inválido", e.getMessage());
		}
	}
	
	public void testAlterar() throws Exception {
		testValidarCamposObrigatorios();
		testValidarRegras();
		
		Entidade entidadeEntrada;
		Entidade entidadeActual;
		Entidade entidadeExpected;
		
		// Cenário 1: alteração realizada com sucesso.
		entidadeEntrada = classeNegocio.getById(new Long(5));
		entidadeEntrada.setEmail("meunovo@email.com");
		entidadeEntrada.setNumeroDocumento(81888833017L);
		
		entidadeActual = classeNegocio.alterar(entidadeEntrada);
		
		entidadeExpected = classeNegocio.getById(new Long(5));
		
		assertTrue("Cenário 1: alteração realizada com sucesso.", entidadeExpected.equals(entidadeActual));
		
		// Cenário 2: tenta alterar com um nome diferente.
		entidadeEntrada = classeNegocio.getById(new Long(6));
		entidadeEntrada.setNome("Camila Silva");
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 2: tenta alterar com um nome diferente. Deveria ter sido lançada uma exceção, pois o nome da entidade mudou.");
		} catch (Exception e) {
			assertEquals("Cenário 2: tenta alterar com um nome diferente.", "Não é possível alterar o nome da entidade", e.getMessage());
		}
	}
	
	public void testSalvar() throws Exception {
		testValidarCamposObrigatorios();
		testValidarRegras();
		
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		
		// Cenário 1: salva novo registro com sucesso.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome("Armando Paiva");
		entidadeEntrada.setNumeroDocumento(44792377552L);
		
		IDataSet dadosEsperados = new FlatXmlDataSet(new FlatXmlProducer(new InputSource("massa_dados_salvar.xml")));
		ITable tabelaEsperada = dadosEsperados.getTable(TABELA_ENTIDADE);
		
		entidadeActual = classeNegocio.salvar(entidadeEntrada);
		
		assertNotNull("Cenário 1: salva novo registro com sucesso. Teste do ID retornado.", entidadeActual.getId());
		assertEquals("Cenário 1: salva novo registro com sucesso. Teste das quantidades.", tabelaEsperada.getRowCount(), classeNegocio.getQuantidadeRegistros());
		
		// Cenário 2: tenta salvar novo registro com o mesmo nome de outro já presente no banco de dados.
		entidadeEntrada = getEntidadeValida();
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 2: tenta salvar novo registro com o mesmo nome de outro já presente no banco de dados. Deveria retornar uma exceção.");
		} catch (Exception e) {
			assertEquals("Cenário 2: tenta salvar novo registro com o mesmo nome de outro já presente no banco de dados.", "Já existe entidade cadastrada com este nome.", e.getMessage());
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
		entidade.setDataInicial(dataCalendario.getTime());
		dataCalendario.set(2014, 5, 31);
		entidade.setDataFinal(dataCalendario.getTime());
		entidade.setTipoDocumento(1);
		entidade.setNumeroDocumento(new Long(01123435456));
		
		return entidade;
	}
}
