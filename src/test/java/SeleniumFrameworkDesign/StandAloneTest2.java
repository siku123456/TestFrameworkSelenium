package SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.CheckOutPage;
import SeleniumFrameworkDesign.PageObjects.ConfirmationPage;
import SeleniumFrameworkDesign.PageObjects.LandingPage;
import SeleniumFrameworkDesign.PageObjects.OrderPage;
import SeleniumFrameworkDesign.PageObjects.ProductCatalogue;
import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest2 extends BaseTest{
	
	String productname="ZARA COAT 3";
	
	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		
		
		//LandingPage landingpage=launchApplication();
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"), input.get("Password"));
		
		/*driver.findElement(By.id("userEmail")).sendKeys("AarviDehuri@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aarvi@6481");
		driver.findElement(By.id("login")).click();*/
		
		//ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productname"));
		Thread.sleep(10000);
		CartPage cartpage=productCatalogue.goToCartPage();
		Boolean match=	cartpage.verifyProductDisplay(input.get("productname"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage=cartpage.goToCheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage=checkoutpage.SubmitOrder();
		String confirmaMessage=confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmaMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		System.out.println("Test passed");
		
}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderhistory() {
		
		ProductCatalogue productCatalogue=landingpage.loginApplication("AarviDehuri@gmail.com", "Aarvi@6481");
		OrderPage orderpage=productCatalogue.goToOrderPagePage();
		orderpage.verifyOrderDisplay(productname);
		Assert.assertTrue(orderpage.verifyOrderDisplay(productname));
		
	}
	

	@DataProvider
	public Object[][] getData() throws IOException {
		
		/*HashMap<String,String> map=new HashMap<String,String>();
		map.put("email", "AarviDehuri@gmail.com");
		map.put("Password", "Aarvi@6481");
		map.put("productname", "ZARA COAT 3");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email", "xyz06448@gmail.com");
		map1.put("Password", "Aarvi@6481");
		map1.put("productname", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SeleniumFrameworkDesign//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		 
	}
	
	
}
