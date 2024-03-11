package SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	
	WebDriver driver;

	
	public ProductCatalogue(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css=".mb-3")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastMessage=By.cssSelector("#toast-container");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	
	}
	
	public WebElement getProductByName(String productname) {
	
		WebElement prod=	getProductList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productname) {
		
		WebElement prod=getProductByName(productname);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		//waitForElementToDisappear(spinner);
		System.out.println("Product added to the cart successfully");
		//wait.until(ExpectedConditions.invisibilityOf);
		
	}
	
	
	

}
