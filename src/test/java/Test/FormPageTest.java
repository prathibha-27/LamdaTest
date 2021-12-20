package Test;

import java.awt.AWTException;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Point;
import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Base.BaseTest;
import Pages.FormPage;
import Pages.LoginPage;

public class FormPageTest extends BaseTest{
	
	
	@Test
	public void testing() throws IOException, AWTException, InterruptedException
	{
		LoginPage lp= new LoginPage(driver);
		lp.acceptCookies();
		lp.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));
		FormPage fp= new FormPage(driver);
		fp.enteringMail(prop.getProperty("email"));
		driver.switchTo().alert().accept();
		
		//clicking on radio button
		fp.selectRadioBtn();
		
		//clicking on checkbox
		fp.selectCheckBox();
		
		//selecting payment option
		fp.selectPaymentOption();
		
		//clicking on terms and conditions checkbox
		fp.selectTermsAndCondition();
		
		//validating slider is displayed
		boolean flag=fp.sliderIsDisplayed();
		Assert.assertTrue(flag);
		
		
		 Point p2=fp.getSliderPosition();
		 System.out.println(p2);
		 Point p1=fp.checkSliderPosition();
		 System.out.println(p1);
		 Assert.assertNotEquals(p2, p1);
		 
		 fp.enterFeedback();
		 String parentTab=driver.getWindowHandle();
		 
		 //opening the new url in new tab
	     driver.switchTo().newWindow(WindowType.TAB);
	     driver.get(prop.getProperty("secondURL"));
	   
	     //wait for all the elements to be present
	     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	     try {
	          fp.downloadImage();
	     }
	     catch(Exception e){
	    	 e.printStackTrace();
	     }
	     finally
	     {
	       driver.close();
	     }
	     //switching back to parent tab
		 driver.switchTo().window(parentTab);
		 String text=fp.uploadImage();
			
		 //checking if image uploaded successfully
		 SoftAssert sa=new SoftAssert();
		 sa.assertEquals(text, "your image upload sucessfully!!");
			
		 String form=fp.clickOnSubmitBtn();
		 //checking form has successfully submitted
		 Assert.assertEquals(form, "You have successfully submitted the form.");
	}
	
	

}
