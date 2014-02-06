package br.treinamento;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import br.treinamento.Basico;

public class BasicoTest {
	private Basico negocio;
	private static int contadorBeforeClass = 0;
	private static int contadorBefore = 0;
	
	@Rule
	public ExpectedException thrown= ExpectedException.none();
	
	@BeforeClass
	public static void setUpBeforeClass(){
		System.out.println("SetUp do BeforeClass: uma única vez | " + ++contadorBeforeClass);
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		System.out.println("Tear Down do AfterClass: uma única vez");
	}
	
	@Before
	public void setUpBefore(){
		System.out.println("SetUp do Before | " + ++contadorBefore);
		negocio = new Basico();
	}
	
	@After
	public void tearDownAfter(){
		System.out.println("Tear Down After");
		negocio = null;
	}

	@Test
	public void testSomar(){
		assertEquals(negocio.somar(1, 1), 2);
		assertThat(negocio.somar(1, 1), is(2));
		assertThat(negocio.somar(1, 1), is(not(3)));
	}
	
	@Test
	public void testDividir() throws Exception{
		assertEquals(((double)10 / 3), 3.33, 0.009);
	}
	
	@Test(expected = Exception.class)
	public void testDividirPorZero_elegante() throws Exception{
		//negocio.dividir("10", "0");
		negocio.dividir("10", "2");
	}
	
	@Test
	public void testDividirPorZero_robusto(){
		try{
			assertEquals(negocio.dividir("10", "0"), 10d, 0d);
			fail("Não deveria chegar nesta linha");
		} catch (Exception e) {
			assertEquals("/ by zero", e.getMessage());
		}
	}
	
	@Test
	public void testDividirPorZero_regra(){
		thrown.expect(Exception.class);
		thrown.expectMessage(is("/ by zero"));
		
		assertEquals(negocio.dividir("10", "0"), 10d, 0d);
	}
	
	@Ignore("Não lembro como faz isso")
	@Test
	public void testMDC(){
		assertEquals(negocio.mdc(1, 2, 3), 100);
	}
	
	@Test(timeout = 1000)
	public void infinito() {
		while (true);
	}
	
	@Test
	public void testLancarExcecao() {
		try {
			negocio.lancarExcecao();
			fail("Uma exceção deveria ter sido lançada.");
		} catch (Exception e) {
			//e.printStackTrace();
			assertEquals(e.getMessage(), "Exceção no método com erro!");
		}
	}
}
