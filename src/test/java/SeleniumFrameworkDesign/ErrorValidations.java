package SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.CheckOutPage;
import SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.PageObjects.LandingPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidations extends BaseTest{

	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		
		//String productname="ZARA COAT 3";
		//String path=System.getProperty("user.dir")+"reports//index.html";
		//ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		//reporter.config().setReportName("Web Automation Result");
		ProductCatalogue productCatalogue=landingpage.loginApplication("AarviDehuri1@gmail.com", "Aarvi@6481");
		Assert.assertEquals("Incorrect email  password.", landingpage.getErrorMessage());
		
}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		
		String productname="ZARA COAT 3";
		
		//LandingPage landingpage=launchApplication();
		ProductCatalogue productCatalogue=landingpage.loginApplication("AarviDehuri@gmail.com", "Aarvi@6481");
		
		/*driver.findElement(By.id("userEmail")).sendKeys("AarviDehuri@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aarvi@6481");
		driver.findElement(By.id("login")).click();*/
		
		//ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productname);
		Thread.sleep(10000);
		CartPage cartpage=productCatalogue.goToCartPage();
		Boolean match=	cartpage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
}
}