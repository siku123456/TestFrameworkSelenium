package SeleniumFrameworkDesign;

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

import SeleniumFrameworkDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		String productname="ZARA COAT 3";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingpage=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("AarviDehuri@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Aarvi@6481");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		Thread.sleep(10000);
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod=products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		
		//Thread.sleep(10000);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Thread.sleep(10000);
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*='cart']")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		WebElement element = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.print("clicked on the cart button");
		
		//driver.findElement(By.xpath("(//button[@class='btn btn-custom'])[1]")).click();
		
		List <WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a =new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"India").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		//driver.findElement(By.cssSelector(".action__submit")).click();
		
		WebElement PlaceOrderelement = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", PlaceOrderelement);
		System.out.print("clicked on the place Order button");
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		driver.close();
		

}
}
