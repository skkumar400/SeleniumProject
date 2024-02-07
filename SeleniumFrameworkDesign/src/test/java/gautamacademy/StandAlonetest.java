package gautamacademy;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import gautamacademy.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAlonetest {

	public static void main(String[] args) {
		
		String productName="ZARA COAT 3";
		//chromedriver steup
		WebDriverManager.chromedriver().setup();
		
		//object creation
		WebDriver driver = new ChromeDriver();
		
		//implicityWait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//websiteLanuch
		driver.get("https://rahulshettyacademy.com/client/");
		
		LandingPage landingpage = new LandingPage(driver);
		
		//maximizeWindow
		driver.manage().window().maximize();
		
		//login into account
		driver.findElement(By.id("userEmail")).sendKeys("gtm.sh@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Ss169349667@");
		driver.findElement(By.id("login")).click();
		
		//ExciplitedWait
		 WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		 
		//Store All Product
		 List<WebElement> products= driver.findElements(By.cssSelector(".mb-3"));
		 
		 //store Deseired Product
		 WebElement prod = products.stream().filter(product->
		 product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		
		 //click on AddToCart button
		 prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		 
		 
		 
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		 
		 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		 
		 //clickonCart
		 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[routerlink='/dashboard/cart']")));
		 driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		 
		 //CheckProductInCartSection
		 List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		 
		 //checkCartProduct
		  Boolean match=  cartProducts.stream().anyMatch(cartproduct->
		  cartproduct.getText().equalsIgnoreCase(productName));
		  
		  //True|False
		  Assert.assertTrue(match);
		  
		  //checkout
		  driver.findElement(By.xpath("//*[text()='Checkout']")).click();
		  
		  //selectCountry
		  
		  Actions a = new Actions(driver);
		  a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		  
		  wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
		  //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-item:nth-of-type(2)")));
		  
		  driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		  
		  //clickOnPlaceOrderButton
		  driver.findElement(By.cssSelector(".action__submit")).click();
		  
		  //confirmation
		 String confirmMessage=  driver.findElement(By.cssSelector(".hero-primary")).getText();
		 Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		  
		  
		 
		 //closeWindow
		 driver.close();
		 
	}

}
