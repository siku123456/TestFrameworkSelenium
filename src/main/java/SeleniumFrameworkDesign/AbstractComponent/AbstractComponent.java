package SeleniumFrameworkDesign.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.PageObjects.CartPage;
import SeleniumFrameworkDesign.PageObjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderheader;

	public void waitForElementToAppear(By FindBy) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
public void waitForWebElementToAppear(WebElement FindBy) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(FindBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele) {
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	
	public CartPage goToCartPage() {
		
		//cartHeader.click();
		
		WebElement element = driver.findElement(By.cssSelector("[routerlink*='cart']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);
		System.out.print("clicked on the cart button");
		CartPage cartpage=new CartPage(driver);
		return cartpage;
		
	}
	
   public OrderPage goToOrderPagePage() {
	 
	orderheader.click();
	OrderPage orderpage=new OrderPage(driver);
	return orderpage;
			
	}
	

}
