package SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	
	WebDriver driver;

	
	public CartPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productTitles;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutElement;
	
	
	public Boolean verifyProductDisplay(String productname) {
		Boolean match=productTitles.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		return match;
	}
	
	//driver.findElement(By.cssSelector(".totalRow button")).click();
	
	public CheckOutPage goToCheckOut() {
		
		checkOutElement.click();
		CheckOutPage checkoutpage=new CheckOutPage(driver);
		return checkoutpage;
		
	}
	
	
	

}
