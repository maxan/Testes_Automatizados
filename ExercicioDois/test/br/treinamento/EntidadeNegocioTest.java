package br.treinamento;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EntidadeNegocioTest {
	private EntidadeNegocio classeNegocio;
	private EntidadeDAOInterface persistencia;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando o teste da classe de negócio.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Concluindo teste da classe de negócio.");
	}

	@Before
	public void setUp() throws Exception {
		persistencia = EasyMock.createMock(EntidadeDAOInterface.class);
		classeNegocio = new EntidadeNegocio();
		classeNegocio.setPersistencia(persistencia);
	}

	@After
	public void tearDown() throws Exception {
		EasyMock.reset(persistencia);
	}

	@Test
	public void testSalvar() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		
		// Cenário 1: Salvamento com sucesso.
		entidadeEntrada = getEntidadeValida();
		
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setId((long) 1);
		
		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(true);
		EasyMock.expect(persistencia.salvar(entidadeEntrada)).andReturn(entidadeExpected);
		EasyMock.replay(persistencia);
		
		entidadeActual = classeNegocio.salvar(entidadeEntrada);
		
		assertNotNull("Cenário 1: Salvamento com sucesso.", entidadeActual.getId());
		
		EasyMock.verify(persistencia);
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
