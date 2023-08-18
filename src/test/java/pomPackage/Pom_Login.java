package pomPackage;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import basePackage.BaseClass;

public class Pom_Login extends BaseClass
{
	//object repository
   @FindBy(xpath ="(//a[contains(text(),'Sign In')])[1]") WebElement SignInNav;
   @FindBy(id = "email") WebElement E_id;
   @FindBy(id ="pass") WebElement psw;
   @FindBy(id ="send2") WebElement SignInbtn;
   @FindBy(css ="a[class='action remind'] span") WebElement FRPsw;
  
   //Initiate Page Elements
   public Pom_Login()
   {
 	 PageFactory.initElements(driver,this);
   }
   
   public void SignInNav() {
	   SignInNav.click();}
   
   public void Email(String Email) {
	   E_id.sendKeys(Email);}
   
   public void Password(String Password) {
	   psw.sendKeys(Password);}
   
   public void SignInBtn() {
	   SignInbtn.click();}
   
   public void Forgot_Psw() {
	   FRPsw.click();}
}
