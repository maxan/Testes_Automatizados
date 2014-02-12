package br.treinamento;

import java.io.FileInputStream;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.Test;

public class EntidadeDAODBUnitTest extends DatabaseTestCase {
	private EntidadeDAOInterface persistencia;
	private  final static String TABELA_ENTIDADE = "entidade";

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(ConnectionFactory.getConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("dados.xml"));
	}
	
	public void testVerificarUnicidadeNome() throws Exception {
		//
	}
}
