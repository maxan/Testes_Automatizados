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

// Este exercício deve ter 100% de cobertura de código, mas não é necessário cobrir 100% dos branches.

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

//	@Test
//	public void testSalvar() throws Exception {
//		Entidade entidadeActual;
//		Entidade entidadeExpected;
//		Entidade entidadeEntrada;
//		
//	}
	
	@Test
	public void testValidarCamposObrigatorios() throws Exception {
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
		
		// Cenário 2: Tenta salvar com campo nome não preenchido.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 2: Tenta salvar com campo nome não preenchido. O campo nome não deve estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 2: Tenta salvar com campo nome não preenchido.", "O nome é obrigatório", e.getMessage());
		}
		
		// Cenário 3: Tenta salvar com campo número do documento não preenchido.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNumeroDocumento(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 3: Tenta salvar com campo número do documento não preenchido. O campo número do documento não deve estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 3: Tenta salvar com campo número do documento não preenchido.", "O número do documento é obrigatório", e.getMessage());
		}
		
		// Cenário 4: Tenta salvar com campo tipo do documento não preenchido.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setTipoDocumento(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 4: Tenta salvar com campo tipo do documento não preenchido. O campo tipo do documento não deve estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 4: Tenta salvar com campo tipo do documento não preenchido.", "O tipo do documento é obrigatório", e.getMessage());
		}
		
		// Cenário 5: Tenta salvar com data inicial preenchida e data final não preenchida (período incompleto).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setDataFinal(null);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 5: Tenta salvar com data inicial preenchida e data final não preenchida (período incompleto). O campo data final não deve estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 5: Tenta salvar com data inicial preenchida e data final não preenchida (período incompleto).", "O período deve ser informado por completo", e.getMessage());
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
