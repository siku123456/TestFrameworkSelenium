package SeleniumFrameworkDesign.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumFrameworkDesign.PageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingpage;
	
	public WebDriver initializeDriver() throws IOException {
		//Properties class
		Properties prop=new Properties();
		FileInputStream fs=new FileInputStream(System.getProperty("user.dir")+"/src/main/java/SeleniumFrameworkDesign/resources/GlobalData.properties");
		prop.load(fs);
		String browsername=System.getProperty("browser")!=null ? 	System.getProperty("browser") : prop.getProperty("browser");
		//String browsername=prop.getProperty("browser");
		if(browsername.equalsIgnoreCase("chrome")) {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();

		}
		
		else if(browsername.equalsIgnoreCase("firefox")){
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			//firefox
		}
		
		else if(browsername.equalsIgnoreCase("edge")){
			
			//edge
			
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
	}
	
public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		
		//FileUtils.readFileToString(new File(System.getProperty("user.dir"))+"//src//test//java//SeleniumFrameworkDesign//data//PurchaseOrder.json"));
		String jsonContent=FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//String to hashmap
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;
		
		
		
	}

public String getScreenshot(String testCaseName,WebDriver driver) throws IOException {
	
	TakesScreenshot ts=(TakesScreenshot)driver;
	File source=ts.getScreenshotAs(OutputType.FILE);
	File file=new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
	//FileUtils.copyFile(null, null);
	FileUtils.copyFile(source, file);
	return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	
}

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		
		driver=initializeDriver();
		landingpage= new LandingPage(driver);
		landingpage.goToURL();
		return landingpage;
	}
	@AfterMethod(alwaysRun=true)
	public void TearDown() {
		
		driver.close();	}

}
