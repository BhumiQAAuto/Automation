package TestLayer;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import basePackage.BaseClass;
import pomPackage.Pom_Login;


public class LoginTest extends BaseClass
{
    Pom_Login pom;
    public LoginTest()
	{
		super();
	}
    
    @Parameters("browser")
    @BeforeMethod
    public void initsetup(String browser) throws InterruptedException
    {	 
       initbrowser(browser);
       pom = new Pom_Login();
	}
	
	@Test(dataProvider = "Credentials", priority = 1)
	public void verifyLoginCredentials(String scenario,String id,String psw) throws InterruptedException
	{
		driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);

		pom.SignInNav();
		pom.Email(id);
		pom.Password(psw);
		Thread.sleep(2000);
		pom.SignInBtn();
		
		Thread.sleep(2000);
		//Assertions
		if(scenario.equals("Valid") || scenario.equals("Case_Sensitivity")){
		   WebElement gettext = driver.findElement(By.xpath("(//li[@class='customer-welcome'])[1]"));
		   Assert.assertTrue(gettext.isDisplayed(), "Login Not Sucess");
		}
		else if(scenario.equals("Invalid_Email")|| scenario.equals("Non_Domain")) {
			String Emailtxt = driver.findElement(By.cssSelector("div[role='alert']")).getText();
			Assert.assertTrue(Emailtxt.contains("account sign-in was incorrect"));
		}
        else if(scenario.equals("InValid_PSW")) {
        	String pswtxt = driver.findElement(By.cssSelector("div[role='alert']")).getText();
			Assert.assertTrue(pswtxt.contains("account sign-in was incorrect"));
		}
        else if(scenario.equals("Empty")) {
        	String Emailtxt2 = driver.findElement(By.xpath("//div[@id='email-error']")).getText();
        	String pswtxt2 = driver.findElement(By.xpath("//div[@id='pass-error']")).getText();
			Assert.assertTrue(Emailtxt2.contains("This is a required field") || pswtxt2.contains("This is a required field"));
		}
	    else{
	    	Assert.assertTrue(false);
	    }
	}
	
	@Test(priority = 2)
	//For signout
	public void verifyForgot_Psw() {
		pom.SignInNav();
		pom.Forgot_Psw();
		String ForgotPassword = driver.findElement(By.cssSelector("label[for='email_address'] span")).getText();
		Assert.assertTrue(ForgotPassword.contains("Email"));
		
	}
	
	
	@DataProvider(name="Credentials") 
	public Object[][] getData(){
		return new Object[][] {
			{"Valid","Bhumishamahida96@gmail.com","Bhumisha@1234"},
			{"Invalid_Email","Bhumisha@gmail.com","Bhumisha@1234"},
			{"InValid_PSW","Bhumishamahida96@gmail.com","1234"},
			{"Empty","",""},
			{"Case_Sensitivity","amitMAHIDA49@gmail.com","Amit@4567"},
			{"Non_Domain","maulipandya145@123.com","Mauli@145"}
		};
	}
	
	@AfterMethod
    public void quit()
	{
		if (driver != null) {
            driver.quit();
        }
     }
}
