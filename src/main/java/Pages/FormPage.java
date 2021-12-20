package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v85.input.Input.DispatchKeyEventType;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class FormPage {

	Robot robot;
	JavascriptExecutor js;
	
	WebDriver driver;
	@FindBy(name="email") WebElement email;
	
	@FindBy(id="populate") WebElement populateBtn;
	
	@FindBy(xpath="(//div[@class='radio-button pb-20']//p/label/input)[4]") WebElement option4;
	
	@FindBy(xpath="(//div[@class='checkbox pb-20']//p/label/input)[1]") WebElement option2;
	
	@FindBy(name="preferred-payment") WebElement paymentDropdown;
	
	@FindBy(name="tried-ecom") WebElement checkbox;
	
	@FindBy(xpath="//div[@style='transform: scale(1); cursor: pointer; height: 36px; display: flex; width: 100%;']") WebElement slider;
	
	@FindBy(xpath="//div[@class='relative progress_bar progress9']") WebElement target;
	
	@FindBy(name="comments") WebElement comment;
	
	@FindBy(xpath="//img[@title='Jenkins']") WebElement img;
	
	@FindBy(xpath="//*[local-name()='svg']") WebElement imaging;
	
	@FindBy(xpath="//label[@id='img']") WebElement uploadBtn;
	
	@FindBy(xpath="//button[@id='submit-button']") WebElement submitBtn;
	
	@FindBy(xpath="//div[@role='slider']") WebElement sliderPosition;
	
	@FindBy(xpath="//p[contains(text(),'You have successfully')]") WebElement successMessage;
	
	public FormPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enteringMail(String ema)
	{
		email.sendKeys(ema);
		populateBtn.click();
	}
	
	public void selectRadioBtn()
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(option4));
		option4.click();
	}
	
	public void selectCheckBox()
	{
		option2.click();
	}
	
	public void selectPaymentOption()
	{
		Select sel=new Select(paymentDropdown);
		sel.selectByVisibleText("Cash on delivery");
	}
	
	public void selectTermsAndCondition()
	{
		checkbox.click();
	}
	
	public boolean sliderIsDisplayed()
	{
		 return slider.isDisplayed();
	}
	
	public Point getSliderPosition()
	{
		return sliderPosition.getLocation();
	}
	public Point checkSliderPosition()
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,200)");
		Actions act= new Actions(driver);
	    act.dragAndDrop(slider,target).build().perform();
		return sliderPosition.getLocation();
	}
	
	public void enterFeedback()
	{
		 comment.sendKeys("feedback");
		
	}
	
	public void downloadImage() throws IOException, AWTException, InterruptedException
	{
		 js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView(true);", img);
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(40));
		 wait.until(ExpectedConditions.visibilityOf(img));	
		 Actions action= new Actions(driver);
		 Thread.sleep(1000);
		 //to right click on image
		 action.contextClick(img).build().perform();
		 
		 robot = new Robot();
		 for(int i=0; i<8;i++)
		 {
			 robot.keyPress(KeyEvent.VK_DOWN);
			 robot.keyRelease(KeyEvent.VK_DOWN);
			 
		 }
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		 Thread.sleep(2000);
		 robot.keyPress(KeyEvent.VK_ALT);
		 robot.keyPress(KeyEvent.VK_S);
		 Thread.sleep(3000);
		 robot.keyRelease(KeyEvent.VK_S);
		 robot.keyRelease(KeyEvent.VK_ALT);
		 Thread.sleep(3000);
		 robot.keyPress(KeyEvent.VK_ENTER);
		 robot.keyRelease(KeyEvent.VK_ENTER);
		
            
	}
	
	public String uploadImage() throws AWTException, InterruptedException
	{
		        String filePath="C:\\Users\\dckap\\Downloads\\jenkins.svg";
		
		        robot = new Robot();
				js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollBy(0,300)");
				js = (JavascriptExecutor)driver;
				js.executeScript("document.getElementById('img').click()");
		    	uploadBtn.click();
				Thread.sleep(3000);
				
				//copy to clipboard
				StringSelection ss = new StringSelection(filePath);
			    Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

			     robot.setAutoDelay(2000);
		   	     robot.keyPress(KeyEvent.VK_CONTROL);
		   	     robot.keyRelease(KeyEvent.VK_V);
				 robot.keyPress(KeyEvent.VK_V);

				 Thread.sleep(3000);
				 robot.keyRelease(KeyEvent.VK_V);
				 robot.keyRelease(KeyEvent.VK_CONTROL);
				 Thread.sleep(2000);
				 robot.keyPress(KeyEvent.VK_ENTER);
			
		         robot.keyRelease(KeyEvent.VK_ENTER);
		         Thread.sleep(3000);
		         
		         //switch to alert popup
		         Alert a= driver.switchTo().alert();
		         
		         //get text of popup
		         String text= a.getText();
		         System.out.println(text);
		         
		         //accept the popup
		         a.accept();
		         return text;       
	}
	
	public String clickOnSubmitBtn()
	{
		submitBtn.click();
		return successMessage.getText();
	}
}
