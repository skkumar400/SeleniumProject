package gautamacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import gautamacademy.abstractComponents.abstractComponent;

public class LandingPage extends abstractComponent {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//WebElement usermail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement usermail;
	
	@FindBy(id="userPassword")
	WebElement userpass;
	
	@FindBy(id="login")
	WebElement submit;
	
	public void loginApplication(String email, String password) {
		usermail.sendKeys(email);
		userpass.sendKeys(password);
		submit.click();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
			
	
}
