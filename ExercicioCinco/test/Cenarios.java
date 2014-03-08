import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Cenarios {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://www2.camara.leg.br/";
    
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testCenarioUm() throws Exception {
    driver.get(baseUrl);
    assertTrue(isElementPresent(By.linkText("Conheça os Deputados")));
    
    driver.findElement(By.linkText("Conheça os Deputados")).click();
    assertEquals("Pesquisa de Deputados", driver.findElement(By.xpath("//div[@id='content']/div/div[2]/h6")).getText());
    assertEquals("Pesquisar", driver.findElement(By.id("Pesquisa2")).getAttribute("value"));
    
    driver.findElement(By.id("nome")).clear();
    driver.findElement(By.id("nome")).sendKeys("Tiririca");
    
    new Select(driver.findElement(By.id("uf"))).selectByVisibleText("SP");
    
    driver.findElement(By.id("Pesquisa2")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA".equals(driver.findElement(By.cssSelector("ul.visualNoMarker > li")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("Projetos de sua autoria")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60)
    		fail("timeout");
    	
    	try {
    		if ("Data de apresentação: 7/6/2011\nEmenta: Altera a Lei nº 10.753, de 30 de outubro de 2003, que institui a Política Nacional do Livro, para dispor sobre a criação do Vale-Livro.".equals(driver.findElement(By.xpath("//form[@id='frmListaProp']/table/tbody[21]/tr[2]/td[2]/p[2]")).getText()))
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("próxima")).click();
    assertTrue(driver.getCurrentUrl().matches("^http://www\\.camara\\.gov\\.br/internet/sileg/Prop_lista\\.asp[\\s\\S]Pagina=2&Autor=530193&Limite=N$"));
    
    driver.navigate().back();
    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA".equals(driver.findElement(By.cssSelector("ul.visualNoMarker > li")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("Discursos em plenário")).click();
    
    try {
      assertEquals("Discursos e Notas Taquigráficas", driver.getTitle());
    } catch (Error e) {
      verificationErrors.append(e.toString());
    }
    
    driver.findElement(By.linkText("Retorna a pesquisa.")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome civil: FRANCISCO EVERARDO OLIVEIRA SILVA".equals(driver.findElement(By.cssSelector("ul.visualNoMarker > li")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("Biografia")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60)
    		fail("timeout");
    	
    	try { 
    		if ("SILVA, Francisco Everardo Oliveira (Co-autor). As Piadas fantárdigas do Tiririca. São Paulo: Matrix, 2006.".equals(driver.findElement(By.xpath("//div[@id='bioDeputado']/div[9]/div[2]")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.cssSelector("a[title=\"Proposições Transformadas em Norma Jurídica\"] > b")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("PL 4682/2012".equals(driver.findElement(By.cssSelector("span.nomeProposicao")).getText())) break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.xpath("(//a[contains(text(),'Atividade Legislativa')])[2]")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Agenda".equals(driver.findElement(By.cssSelector("a[alt=\"Agenda\"]")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Comissões".equals(driver.findElement(By.cssSelector("a[alt=\"Comissões\"]")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60)
    		fail("timeout");
    	
    	try { 
    		if ("Conheça o Processo Legislativo".equals(driver.findElement(By.cssSelector("a[alt=\"Conheça o Processo Legislativo\"]")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }
  }
  
  @Test
  public void testCenarioDois() throws Exception {
    driver.get(baseUrl);
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Conheça os Deputados".equals(driver.findElement(By.linkText("Conheça os Deputados")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.findElement(By.linkText("Conheça os Deputados")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("TIRIRICA");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome Parlamentar: TIRIRICA - PR/SP".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("JEAN WYLLYS");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome Parlamentar: JEAN WYLLYS - PSOL/RJ".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("JOÃO ARRUDA");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome Parlamentar: JOÃO ARRUDA - PMDB/PR".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("ZOINHO");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome Parlamentar: ZOINHO - PR/RJ".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("ANTHONY GAROTINHO");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome Parlamentar: ANTHONY GAROTINHO - PR/RJ".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("ARIOSTO HOLANDA");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { if ("Nome Parlamentar: ARIOSTO HOLANDA - PROS/CE".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    		break; 
    	} catch (Exception e) {
    		//
    	}
    	
    	Thread.sleep(1000);
    }

    driver.navigate().back();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Legislatura Atual - Deputados em exercício".equals(driver.findElement(By.cssSelector("h6.header")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Pesquisar".equals(driver.findElement(By.id("Pesquisa")).getAttribute("value"))) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }

    new Select(driver.findElement(By.id("deputado"))).selectByVisibleText("ARTUR BRUNO");
    
    driver.findElement(By.id("rbDeputado6")).click();
    driver.findElement(By.id("Pesquisa")).click();
    
    for (int second = 0;; second++) {
    	if (second >= 60) 
    		fail("timeout");
    	
    	try { 
    		if ("Nome Parlamentar: ARTUR BRUNO - PT/CE".equals(driver.findElement(By.cssSelector("#content > h3")).getText())) 
    			break; 
		} catch (Exception e) {
			//
		}
    	
    	Thread.sleep(1000);
    }
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    
    String verificationErrorString = verificationErrors.toString();
    
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
