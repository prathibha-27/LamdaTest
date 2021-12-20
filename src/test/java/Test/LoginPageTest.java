package Test;

import org.testng.Assert;
import org.testng.annotations.Test;

import Base.BaseTest;
import Pages.LoginPage;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void ValidatingLoginCredentials() throws InterruptedException
	{
		LoginPage lp=new LoginPage(driver);
		lp.acceptCookies();
		Thread.sleep(1000);
		boolean flag=lp.enterCredentials(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(flag);
	}
	
	

}
