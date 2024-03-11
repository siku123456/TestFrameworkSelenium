package SeleniumFrameworkDesign.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumFrameworkDesign.AbstractComponent.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;

	
	public CheckOutPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;
	
	By results=By.cssSelector(".ta-results");
	
	public void selectCountry(String CountryName) {
		Actions a =new Actions(driver);
		a.sendKeys(country,"India").build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage SubmitOrder() {
		WebElement PlaceOrderelement = driver.findElement(By.cssSelector(".action__submit"));
		JavascriptExecutor executor1 = (JavascriptExecutor)driver;
		executor1.executeScript("arguments[0].click();", PlaceOrderelement);
		System.out.println("clicked on the place Order button");
		ConfirmationPage confirmationpage=new ConfirmationPage(driver);
		return confirmationpage;
		
	}
	

	
	
	

}
