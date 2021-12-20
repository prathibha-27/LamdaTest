package Pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	
	WebDriver driver;
	@FindBy(name="name") WebElement email;
	
	@FindBy(id="password") WebElement password;
	
	@FindBy(xpath="//span[contains(text(),'Got it')]") WebElement gotItBtn;
	
	@FindBy(xpath="//button[@class='applynow bg-black text-white w-full rounded-sm h-36 mt-20 font-bold']") WebElement loginBtn;
	
	@FindBy(xpath="//div[@class='automation__toast toast jam']") WebElement successMessage;
	
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void acceptCookies()
	{
		gotItBtn.click();
	}
	
	public boolean enterCredentials(String em, String pass)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(email));
		email.sendKeys(em);
		password.sendKeys(pass);
		loginBtn.submit();
		return successMessage.isDisplayed();
	}

}
