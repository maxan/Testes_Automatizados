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
	private EntidadeDAOInterface persistencia;
	private  final static String TABELA_ENTIDADE = "entidade";

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
		
		// A linha abaixo garante que vou conhecer o estado do banco após cada testes, já
		// que executa um DELETE_ALL depois de cada teste realizado.
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}
	
	public void testVerificarUnicidadeNome() throws Exception {
		//
	}
}
