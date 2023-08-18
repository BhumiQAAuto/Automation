package basePackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.Parameters;

import pomPackage.Pom_Login;

public class BaseClass 
{
   public static Properties prop=new Properties();
   public static WebDriver driver;
   
   public void initbrowser(String browser) throws InterruptedException
   {	
	        if (browser.equalsIgnoreCase("chrome")){
	        	driver = new ChromeDriver();}
		    else if (browser.equalsIgnoreCase("firefox")){
				driver = new FirefoxDriver();}
		    else if (browser.equalsIgnoreCase("edge")){
				driver = new EdgeDriver();}
			driver.manage().window().maximize();
		    driver.get(prop.getProperty("url"));
			Thread.sleep(3000);
	}
   
    public BaseClass() {
		try {
			FileInputStream file = new FileInputStream(
					"C:\\Users\\n\\eclipse-workspace\\Luma\\src\\test\\java\\utilities\\Config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
