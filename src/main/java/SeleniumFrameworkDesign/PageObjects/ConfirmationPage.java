package SeleniumFrameworkDesign.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponent.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {
	
	WebDriver driver;
	
public ConfirmationPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

@FindBy(css=".hero-primary")
WebElement confirmationMessage;


public String getConfirmationMessage() {
	
	String confText=confirmationMessage.getText();
	System.out.println(confText);
	return confirmationMessage.getText();
}
	

}
