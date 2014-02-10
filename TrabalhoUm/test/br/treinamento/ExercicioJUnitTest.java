package br.treinamento;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExercicioJUnitTest {
	private ExercicioJUnit classe;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Iniciando o teste da classe.");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Teste da classe concluído.");
	}

	@Before
	public void setUp() throws Exception {
		classe = new ExercicioJUnit();
	}

	@After
	public void tearDown() throws Exception {
		classe = null;
	}

	@Test
	public void testValidarCamposObrigatorios() throws Exception {
		Entidade entidadeActual;
		boolean respostaActual;
		
		// Cenário 1: todos os campos corretos.
		Calendar dataCalendario = Calendar.getInstance();
		
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 1: todos os campos corretos.", respostaActual);
		} catch (Exception e) {
			fail("Cenário 1: todos os campos corretos. Erro retornado: " + e.getMessage());
		}
		
		// Cenário 2: nome não preenchido.
		entidadeActual = new Entidade();
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 2: nome não preenchido. Campo nome não deveria estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 2: nome não preenchido.", "O nome é obrigatório", e.getMessage());
		}
		
		// Cenário 3: número do documento não preenchido.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 3: número do documento não preenchido. Campo número do documento não deveria estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 3: número do documento não preenchido.", "O número do documento é obrigatório", e.getMessage());
		}
		
		// Cenário 4: tipo do documento não preenchido.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 4: tipo do documento não preenchido. Campo tipo do documento não deveria estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 4: tipo do documento não preenchido.", "O tipo do documento é obrigatório", e.getMessage());
		}
		
		// Cenário 5: data final não preenchida.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		entidadeActual.setTipoDocumento(1);
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 5: data final não preenchida. Campo data final não deveria estar preenchido.");
		} catch (Exception e) {
			assertEquals("Cenário 5: data final não preenchida.", "O período deve ser informado por completo", e.getMessage());
		}
		
		// Cenário 6: data inicial não preenchida.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		entidadeActual.setTipoDocumento(1);
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 6: data inicial não preenchida.", respostaActual);
		} catch (Exception e) {
			fail("Cenário 6: data inicial não preenchida. Erro retornado: " + e.getMessage());
		}
	}
	
	@Test
	public void testValidarRegras() throws Exception {
		Entidade entidadeActual;
		boolean respostaActual;
		
		// Cenário 1: todos os campos corretos.
		Calendar dataCalendario = Calendar.getInstance();
		
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 1: todos os campos corretos.", respostaActual);
		} catch (Exception e) {
			fail("Cenário 1: todos os campos corretos. Erro retornado: " + e.getMessage());
		}
		
		/// Cenário 1.1: todos os campos corretos (tipo de documento = 2)
		entidadeActual.setTipoDocumento(2);
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 1.1: todos os campos corretos (tipo de documento = 2).", respostaActual);
		} catch (Exception e) {
			fail("Cenário 1.1: todos os campos corretos (tipo de documento = 2). Erro retornado: " + e.getMessage());
		}
		
		// Cenário 2: nome muito pequeno.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Ana");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 2: nome muito pequeno. Campo nome deve ter menos que 5 caracteres.");
		} catch (Exception e) {
			assertEquals("Cenário 2: nome muito pequeno.", "O nome não pode ter menos que 5 caracteres", e.getMessage());
		}
		
		// Cenário 3: nome muito grande.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Marinho da Silva e Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 3: nome muito grande. Campo nome deve ter mais que 30 caracteres.");
		} catch (Exception e) {
			assertEquals("Cenário 3: nome muito grande.", "O nome não pode ter mais que 30 caracteres", e.getMessage());
		}
		
		// Cenário 4: data inicial menor que a data atual.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2013, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 4: data inicial menor que a data atual. Campo data inicial deve ser menor que a data atual.");
		} catch (Exception e) {
			assertEquals("Cenário 4: data inicial menor que a data atual.", "A data inicial não pode ser menor que a data atual", e.getMessage());
		}
		
		/// Cenário 4.1: data inicial menor que a data atual / data final menor que a data inicial (data inicial NULL).
		entidadeActual.setDataInicial(null);
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 4.1: data inicial menor que a data atual / data final menor que a data inicial (data inicial NULL).", respostaActual);
		} catch (Exception e) {
			fail("Cenário 4.1: data inicial menor que a data atual / data final menor que a data inicial (data inicial NULL). Erro retornado: " + e.getMessage());
		}
		
		// Cenário 5: data final menor que a data inicial.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 5: data final menor que a data inicial. Campo data final deve ser menor que a data inicial.");
		} catch (Exception e) {
			assertEquals("Cenário 5: data final menor que a data inicial.", "A data final não pode ser menor que a data inicial", e.getMessage());
		}
		
		// Cenário 6: tipo do documento inválido.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(3);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 6: tipo do documento inválido. Campo tipo do documento deve ser inválido.");
		} catch (Exception e) {
			assertEquals("Cenário 6: tipo do documento inválido.", "Tipo de documento inválido", e.getMessage());
		}
		
		// Cenário 7: e-mail inválido.
		entidadeActual = new Entidade();
		entidadeActual.setNome("Anderson Silva");
		dataCalendario.set(2014, 5, 1);
		entidadeActual.setDataInicial(new Date(dataCalendario.getTimeInMillis()));
		dataCalendario.set(2014, 5, 31);
		entidadeActual.setDataFinal(new Date(dataCalendario.getTimeInMillis()));
		entidadeActual.setTipoDocumento(1);
		entidadeActual.setNumeroDocumento(new Long(01123435456));
		entidadeActual.setEmail("meuemailcom");
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			fail("Cenário 7: e-mail inválido. Campo e-mail deve ser inválido.");
		} catch (Exception e) {
			assertEquals("Cenário 7: e-mail inválido.", "Endereço de email inválido", e.getMessage());
		}
		
		/// Cenário 7.1: e-mail inválido (e-mail NULL).
		entidadeActual.setEmail(null);
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 7.1: e-mail inválido (e-mail NULL).", respostaActual);
		} catch (Exception e) {
			fail("Cenário 7.1: e-mail inválido (e-mail NULL). Erro retornado: " + e.getMessage());
		}
		
		/// Cenário 7.2: e-mail inválido (e-mail sem @).
		entidadeActual.setEmail("meuemail.com");
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 7.2: e-mail inválido (e-mail sem @).", respostaActual);
		} catch (Exception e) {
			fail("Cenário 7.2: e-mail inválido (e-mail sem @). Erro retornado: " + e.getMessage());
		}
		
		/// Cenário 7.3: e-mail inválido (e-mail sem .).
		entidadeActual.setEmail("meu@emailcom");
		
		try {
			respostaActual = classe.verificarEntidadeValida(entidadeActual);
			
			assertTrue("Cenário 7.3: e-mail inválido (e-mail sem .).", respostaActual);
		} catch (Exception e) {
			fail("Cenário 7.3: e-mail inválido (e-mail sem .). Erro retornado: " + e.getMessage());
		}
	}

	@Test
	public void testVerificarEntidadeValida() throws Exception {
		testValidarCamposObrigatorios();
		testValidarRegras();
	}
	
	@Test
	public void testCalcDigVerif() throws Exception {
		String cpfActual;
		boolean respostaActual;
		
		// Cenário 1: dígito calculado correto.
		cpfActual = "96257426111";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertTrue("Cenário 1: dígito calculado correto.", respostaActual);
		
		/// Cenário 1.1: dígito calculado correto.
		cpfActual = "01353074307";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertTrue("Cenário 1.1: dígito calculado correto.", respostaActual);
		
		/// Cenário 1.2: dígito calculado correto.
		cpfActual = "15484140005";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertTrue("Cenário 1.2: dígito calculado correto.", respostaActual);
		
		/// Cenário 1.3: dígito calculado correto.
		cpfActual = "16754518340";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertTrue("Cenário 1.3: dígito calculado correto.", respostaActual);
		
		/// Cenário 1.4: dígito calculado correto.
		cpfActual = "62152465300";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertTrue("Cenário 1.4: dígito calculado correto.", respostaActual);
		
		// Cenário 2: dígito calculado incorreto.
		cpfActual = "96257426101";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 2: dígito calculado incorreto.", respostaActual);
	}
	
	@Test
	public void testValidaCPF() throws Exception {
		String cpfActual;
		boolean respostaActual;
		
		testCalcDigVerif();
		
		// Cenário 1: CPF incorreto.
		cpfActual = "12345678901";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1: CPF incorreto.", respostaActual);
		
		// Cenário 1.1: CPF incorreto (00000000000).
		cpfActual = "00000000000";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.1: CPF incorreto (00000000000).", respostaActual);
		
		// Cenário 1.2: CPF incorreto (tamanho menor que 11 dígitos).
		cpfActual = "123";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.2: CPF incorreto (tamanho menor que 11 dígitos).", respostaActual);
		
		// Cenário 1.3: CPF incorreto (11111111111).
		cpfActual = "11111111111";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.3: CPF incorreto (11111111111).", respostaActual);
		
		// Cenário 1.4: CPF incorreto (22222222222).
		cpfActual = "22222222222";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.4: CPF incorreto (22222222222).", respostaActual);
		
		// Cenário 1.5: CPF incorreto (33333333333).
		cpfActual = "33333333333";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.5: CPF incorreto (33333333333).", respostaActual);
		
		// Cenário 1.6: CPF incorreto (44444444444).
		cpfActual = "44444444444";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.6: CPF incorreto (44444444444).", respostaActual);
		
		// Cenário 1.7: CPF incorreto (55555555555).
		cpfActual = "55555555555";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.7: CPF incorreto (55555555555).", respostaActual);
		
		// Cenário 1.8: CPF incorreto (66666666666).
		cpfActual = "66666666666";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.8: CPF incorreto (66666666666).", respostaActual);
		
		// Cenário 1.9: CPF incorreto (77777777777).
		cpfActual = "77777777777";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.9: CPF incorreto (77777777777).", respostaActual);
		
		// Cenário 1.10: CPF incorreto (88888888888).
		cpfActual = "88888888888";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.10: CPF incorreto (88888888888).", respostaActual);
		
		// Cenário 1.11: CPF incorreto (99999999999).
		cpfActual = "99999999999";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertFalse("Cenário 1.11: CPF incorreto (99999999999).", respostaActual);
		
		// Cenário 2: CPF correto.
		cpfActual = "96257426111";
		
		respostaActual = classe.validaCPF(cpfActual);
		assertTrue("Cenário 2: CPF correto.", respostaActual);
	}

}
