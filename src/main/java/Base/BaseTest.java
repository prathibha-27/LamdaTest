package Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest{

	public static Properties prop;
//	public static WebDriver driver;
	
	public static ChromeOptions options;
	
	public String username = "prathibhap027";
    public String accessKey = "b5I5OzLs9SIrbGROK1u1TtOli287v3fTX8ja4HE41lMYpXsuYu";
    public static RemoteWebDriver driver;


	
	//launching browser
	@BeforeMethod
	//@Parameters({"BrowserName", "version"})
	public void LaunchBrowser() throws IOException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("build", "Third build");
		capabilities.setCapability("name", "test");
		capabilities.setCapability("platform", "Windows 10");
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version","96.0");
		capabilities.setCapability("video",true);
		capabilities.setCapability("console",true);
		capabilities.setCapability("visual",true);
		capabilities.setCapability("network",true);
		
			  try {

			    driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), capabilities);

			  } catch (MalformedURLException e) {

			    System.out.println("Invalid grid URL");

			  }

			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				
				prop = new Properties();
			    FileInputStream ip = new FileInputStream("./src/main/java/Configuations/configuration.properties");
			    prop.load(ip);
				driver.get(prop.getProperty("url"));
	}
		
	

	//quitting browser
	@AfterMethod
	public void QuitBrowser() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
