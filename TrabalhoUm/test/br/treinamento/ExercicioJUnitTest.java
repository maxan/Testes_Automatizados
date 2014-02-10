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
	
//	@Test
//	public void testCalcDigVerif() throws Exception {
//		Entidade entidadeActual;
//		boolean respostaActual;
//	}

}
