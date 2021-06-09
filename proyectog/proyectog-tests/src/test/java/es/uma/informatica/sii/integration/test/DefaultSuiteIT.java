package es.uma.informatica.sii.integration.test;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;

public class DefaultSuiteIT {
	
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  // TODO: No reconoce la unidad de persistencia
  
  @Before
  public void setUp() {
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
    
    BaseDatos bd = new BaseDatos();
    bd.init();
  }
  
  @After
  public void tearDown() {
    driver.quit();
  }
  
  @Test
  public void primerTest() {
    driver.get("http://localhost:8080/proyectog-war/");
    driver.manage().window().setSize(new Dimension(1936, 1066));
    assertThat(driver.findElement(By.cssSelector("h3")).getText(), is("proyectog ETSII website"));
    assertThat(driver.findElement(By.cssSelector(".d-flex > .text-light")).getText(), is("Universidad de Málaga"));
  }
}
