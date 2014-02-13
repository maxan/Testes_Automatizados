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
	
	@Test
	public void testValidarCamposObrigatorios() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		
		// Cenário 1: Salvamento com sucesso.
		entidadeEntrada = getEntidadeValida();
		
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setId((long) 1);
		
		EasyMock.reset(persistencia);
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
		
		EasyMock.verify(persistencia);
		
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
		
		EasyMock.verify(persistencia);
		
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
		
		EasyMock.verify(persistencia);
		
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
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testValidarRegras() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		Calendar calendario;
		
		// Cenário 1: Salvamento com sucesso.
		entidadeEntrada = getEntidadeValida();
		
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setId((long) 1);
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(true);
		EasyMock.expect(persistencia.salvar(entidadeEntrada)).andReturn(entidadeExpected);
		EasyMock.replay(persistencia);
		
		entidadeActual = classeNegocio.salvar(entidadeEntrada);
		
		assertNotNull("Cenário 1: Salvamento com sucesso.", entidadeActual.getId());
		
		EasyMock.verify(persistencia);
		
		// Cenário 2: Tenta salvar com campo nome com nome contendo mais do que 30 caracteres (erro).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome("Anderson Marinho da Silva e Silva");
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 2: Tenta salvar com campo nome com nome contendo mais do que 30 caracteres. O campo nome não deve ter mais que 30 caracteres.");
		} catch (Exception e) {
			assertEquals("Cenário 2: Tenta salvar com campo nome com nome contendo mais do que 30 caracteres.", "O nome não pode ter mais que 30 caracteres", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 3: Tenta salvar com campo nome com nome contendo menos do que 5 caracteres (erro).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNome("Ana");
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 3: Tenta salvar com campo nome com nome contendo menos do que 5 caracteres. O campo nome não deve ter menos que 5 caracteres.");
		} catch (Exception e) {
			assertEquals("Cenário 3: Tenta salvar com campo nome com nome contendo menos do que 5 caracteres.", "O nome não pode ter menos que 5 caracteres", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 4: Tenta salvar com campo número do documento menor ou igual a zero (erro).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setNumeroDocumento((long)-1);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 4: Tenta salvar com campo número do documento menor ou igual a zero (erro). O campo número do documento deve ser menor ou igual a zero.");
		} catch (Exception e) {
			assertEquals("Cenário 4: Tenta salvar com campo número do documento menor ou igual a zero (erro).", "O número do documento deve ser maior que zero", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 5: Tenta salvar com campo data inicial menor que a data atual (erro).
		entidadeEntrada = getEntidadeValida();
		calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		entidadeEntrada.setDataInicial(calendario.getTime());
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 5: Tenta salvar com campo data inicial menor que a data atual (erro). O campo data inicial deve ser menor que a data atual.");
		} catch (Exception e) {
			assertEquals("Cenário 5: Tenta salvar com campo data inicial menor que a data atual (erro).", "A data inicial não pode ser menor que a data atual", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 6: Tenta salvar com campo data final menor que a data inicial (erro).
		entidadeEntrada = getEntidadeValida();
		calendario = Calendar.getInstance();
		calendario.add(Calendar.DAY_OF_MONTH, -1);
		entidadeEntrada.setDataFinal(calendario.getTime());
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 6: Tenta salvar com campo data final menor que a data inicial (erro). O campo data final deve ser menor que a data inicial.");
		} catch (Exception e) {
			assertEquals("Cenário 6: Tenta salvar com campo data final menor que a data inicial (erro).", "A data final não pode ser menor que a data inicial", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 7: Tenta salvar com campo tipo do documento inválido (erro).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setTipoDocumento(4);
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 7: Tenta salvar com campo tipo do documento inválido (erro). O campo tipo do documento deve ser inválido.");
		} catch (Exception e) {
			assertEquals("Cenário 7: Tenta salvar com campo tipo do documento inválido (erro).", "Tipo de documento inválido", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 8: Tenta salvar com campo e-mail inválido (erro).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setEmail("meuemailcom");
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 8: Tenta salvar com campo e-mail inválido (erro). O campo e-mail deve ser inválido.");
		} catch (Exception e) {
			assertEquals("Cenário 8: Tenta salvar com campo e-mail inválido (erro).", "Endereço de email inválido", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testSalvar() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		
		testValidarCamposObrigatorios();
		testValidarRegras();
		
		// Cenário 1: Tenta salvar, mas unidicidade retorna false.
		entidadeEntrada = getEntidadeValida();
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(false);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.salvar(entidadeEntrada);
			
			fail("Cenário 1: Tenta salvar, mas unidicidade retorna false. Deveria retornar uma exceção.");
		} catch(Exception e) {
			assertEquals("Cenário 1: Tenta salvar, mas unidicidade retorna false.", "Já existe entidade cadastrada com este nome.", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
		
		// Cenário 2: Tenta salvar, salvamento ocorre com sucesso.
		entidadeEntrada = getEntidadeValida();
		
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setId((long) 1);
		
		EasyMock.reset(persistencia);
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
