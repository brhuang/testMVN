package artOfTesting.test;


import java.io.File;
import java.net.URL;
import java.net.MalformedURLException;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.*;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;



import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class googleCalcStepDefinition {
	
	
	protected WebDriver driver = null;
	DesiredCapabilities capabilities = DesiredCapabilities.chrome();


	
	 @Before
	    public void setup()  throws MalformedURLException{
		 System.setProperty("webdriver.chrome.driver", "/Users/brianhuang/Documents/QA/WebDriver/chromedriver");
		 //driver = new ChromeDriver(capabilities);
		  driver = new RemoteWebDriver(new URL("http://localhost:9515"), capabilities);
	}
		
	@Given("^I open google$")
	public void I_open_google() {
		//Set implicit wait of 10 seconds and launch google
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.google.com.tw");
	}
	
	@When("^I enter \"([^\"]*)\" in search textbox$")
	public void I_enter_in_search_textbox(String additionTerms) {
		//Write term in google textbox
		WebElement googleTextBox = driver.findElement(By.id("lst-ib"));
		googleTextBox.sendKeys(additionTerms);
					
		//Click on searchButton
		WebElement searchButton = driver.findElement(By.name("btnK"));
		searchButton.submit();
		 
	}
	
	@Then("^I should get result as \"([^\"]*)\"$")
	public void I_should_get_correct_result(String expectedResult) {
		//Get result from calculator
		//WebDriverWait wait = new WebDriverWait(driver,10);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cwos")));
		WebElement calculatorTextBox = driver.findElement(By.id("cwos"));
		String result = calculatorTextBox.getText();
	
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Verify that result of 2+2 is 4
		Assert.assertEquals(result, expectedResult);	
		
		driver.close();
	}
	
	 @After
	    public void closeBrowser() {
	        driver.quit();
	 }

}


