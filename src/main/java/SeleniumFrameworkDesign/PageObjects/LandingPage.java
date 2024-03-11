package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(id="userEmail")
	WebElement userEmail;
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//driver.findElement(By.id("userPassword"))
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	//driver.findElement(By.id("login"))
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public ProductCatalogue loginApplication(String email,String password) {
		
	userEmail.sendKeys(email);
	passwordEle.sendKeys(password);
	submit.click();
	ProductCatalogue productCatalogue=new ProductCatalogue(driver);
	return productCatalogue;
	}
	
	public String getErrorMessage() {
		
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
		
	}

	public void goToURL() {
		// TODO Auto-generated method stub
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	

}
