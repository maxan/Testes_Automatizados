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
	
	@Test
	public void testGetById() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		
		testValidarCamposObrigatorios();
		testValidarRegras();
		
		// Cenário 1: Usuário retornado com suceso.
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setId((long)10);
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.getById((long)10)).andReturn(entidadeExpected);
		EasyMock.replay(persistencia);
		
		entidadeActual = classeNegocio.getById((long)10);
		
		assertEquals("Cenário 1: Usuário retornado com suceso. Usuário deveria ser encontrado.", entidadeExpected.getNome(), entidadeActual.getNome());
		
		EasyMock.verify(persistencia);
		
		// Cenário 2: Usuário não encontrado.
		entidadeExpected = null;
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.getById((long)11)).andReturn(entidadeExpected);
		EasyMock.replay(persistencia);
		
		entidadeActual = classeNegocio.getById((long)11);
		
		assertNull("Cenário 2: Usuário não encontrado. Usuário não deveria ser encontrado.", entidadeActual);
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testAlterar() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		Entidade entidadeExpectedGetById;
		
		testValidarCamposObrigatorios();
		testValidarRegras();
		testGetById();
		
		// Cenário 1: Usuário alterado com sucesso.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setEmail("outro@email.com");
		entidadeEntrada.setId((long)12);
		
		entidadeExpectedGetById = getEntidadeValida();
		entidadeExpectedGetById.setId((long)12);
		
		entidadeExpected = getEntidadeValida();
		entidadeExpected.setEmail("outro@email.com");
		entidadeExpected.setId((long)12);
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.getById((long)12)).andReturn(entidadeExpectedGetById);
		EasyMock.expect(persistencia.alterar(entidadeEntrada)).andReturn(entidadeExpected);
		EasyMock.replay(persistencia);
		
		entidadeActual = classeNegocio.alterar(entidadeEntrada);
		
		assertNotNull("Cenário 1: Usuário alterado com sucesso. Usuário alterado está retornando sem ID.", entidadeActual.getId());
		
		EasyMock.verify(persistencia);
		
		// Cenário 2: Usuário com o nome alterado (erro).
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setEmail("outro@email.com");
		entidadeEntrada.setNome("Anderson Silva da Silva");
		entidadeEntrada.setId((long)12);
		
		entidadeExpectedGetById = getEntidadeValida();
		entidadeExpectedGetById.setId((long)12);
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.getById((long)12)).andReturn(entidadeExpectedGetById);
		EasyMock.replay(persistencia);
		
		try {
			entidadeActual = classeNegocio.alterar(entidadeEntrada);
			
			fail("Cenário 2: Usuário com o nome alterado (erro). Deveria ter sido lançada uma exceção, pois o nome da entidade mudou.");
		} catch (Exception e) {
			assertSame("Cenário 2: Usuário com o nome alterado (erro).", "Não é possível alterar o nome da entidade", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testExcluir() throws Exception {
		Entidade entidadeActual;
		Entidade entidadeExpected;
		Entidade entidadeEntrada;
		Entidade entidadeExpectedGetById;
		
		// Cenário 1: Exclui com sucesso.
		entidadeEntrada = getEntidadeValida();
		entidadeEntrada.setTipoDocumento(2);
		
		EasyMock.reset(persistencia);
		persistencia.excluir(entidadeEntrada);
		EasyMock.expectLastCall().once();
		EasyMock.replay(persistencia);
		
		classeNegocio.excluir(entidadeEntrada);
		
		EasyMock.verify(persistencia);
		
		// Cenário 2: Tenta excluir entidade com tipo de documento CPF (erro).
		entidadeEntrada = getEntidadeValida();
		
		EasyMock.reset(persistencia);
		EasyMock.replay(persistencia);
		
		try {
			classeNegocio.excluir(entidadeEntrada);
			
			fail("Cenário 2: Tenta excluir entidade com tipo de documento CPF (erro). Deveria lançar uma exceção por estar tentando excluir uma entidade com tipo de documento CPF.");
		} catch (Exception e) {
			assertEquals("Cenário 2: Tenta excluir entidade com tipo de documento CPF (erro).", "Não é possível excluir entidades com CPF", e.getMessage());
		}
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testGetQuantidadeRegistros() throws Exception {
		int quantidadeRegistrosActual;
		int quantidadeRegistrosExpected = 10;
		
		// Cenário único: Retorna a quantidade de registos.
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.getQuantidadeRegistros()).andReturn(quantidadeRegistrosExpected);
		EasyMock.replay(persistencia);
		
		quantidadeRegistrosActual = classeNegocio.getQuantidadeRegistros();
		
		assertEquals("Cenário único: Retorna a quantidade de registos.", quantidadeRegistrosExpected, quantidadeRegistrosActual);
		
		EasyMock.verify(persistencia);
	}
	
	@Test
	public void testVerificarUnicidadeNome() throws Exception {
		Entidade entidadeEntrada;
		boolean respostaActual;
		
		// Cenário 1: Entidade é única.
		entidadeEntrada = getEntidadeValida();
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(true);
		EasyMock.replay(persistencia);
		
		respostaActual = classeNegocio.verificarUnicidadeNome(entidadeEntrada);
		
		assertTrue("Cenário 1: Entidade é única.", respostaActual);
		
		// Cenário 2: Entidade não é única.
		entidadeEntrada = getEntidadeValida();
		
		EasyMock.reset(persistencia);
		EasyMock.expect(persistencia.verificarUnicidadeNome(entidadeEntrada)).andReturn(false);
		EasyMock.replay(persistencia);
		
		respostaActual = classeNegocio.verificarUnicidadeNome(entidadeEntrada);
		
		assertFalse("Cenário 2: Entidade não é única.", respostaActual);
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
