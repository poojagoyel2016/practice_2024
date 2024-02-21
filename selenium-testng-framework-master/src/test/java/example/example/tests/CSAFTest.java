package example.example.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * The Class FaceBookLoginTest.
 *
 */
@Test(testName = "Facebook login test", description = "Facebook login test")
public class CSAFTest {

	@Test
	public void facebookLoginTest() {

		//System.setProperty("webdriver.chrome.driver","");

	   WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();

		try {
			driver.get("https://sqo-b2b-e2e1.apps.orion.sys.comcast.net/B2B/SelectCustomer");
			Thread.sleep(15000);
			WebElement username = driver.findElement(By.id("i0116"));
			WebElement loginId = driver.findElement(By.id("i0118"));
			WebElement btnNext = driver.findElement(By.id("idSIButton9"));


			username.sendKeys("svc-orione2edcp@cable.comcast.com");
			btnNext.click();
			loginId.sendKeys("HMcSzOaExQbdIaVq4W5lLDWxYtDPN15b");
			Thread.sleep(3000);
			JavascriptExecutor js = (JavascriptExecutor)driver ;
			WebElement btnLogin = driver.findElement(By.xpath("//input[@type='submit']"));
			js.executeScript("arguments[0].click();", btnLogin);


			System.out.println("abc");
			WebElement gearIcon = driver.findElement(By.xpath("//div[@class='st-title__wrapper']//h1[text()='Business VoiceEdge']/ancestor::div[@class='st-title__wrapper']//button[@class='btn-gear']"));

			//Keep a debug point and navigate to the BVE and click on congiure
			gearIcon.click();

			Thread.sleep(20000);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Number of Unified Communication Seats:']//ancestor::div[@class='st-row-item__slide']//input[@class='st-form__input text']")));
			WebElement communicationSeats = driver.findElement(By.xpath("//h1[text()='Number of Unified Communication Seats:']//ancestor::div[@class='st-row-item__slide']//input[@class='st-form__input text']"));
			WebElement directoryListing = driver.findElement(By.xpath("//h1[text()='Number of Directory Listings:']//ancestor::div[@class='st-row-item__slide']//input"));
			WebElement btnUpdate = driver.findElement(By.xpath("//button[contains(@class,'btn btn-primary') and text()='Update']"));
			WebElement btnSave = driver.findElement(By.xpath(".//button[text()='Save']"));

			communicationSeats.clear();
			communicationSeats.sendKeys("2");
			js = (JavascriptExecutor)driver ;
			js.executeScript("arguments[0].scrollIntoView(true);", directoryListing);
			directoryListing.clear();
			directoryListing.sendKeys("2");

			wait = new WebDriverWait(driver,Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(btnUpdate));
			btnUpdate.click();
			wait.until(ExpectedConditions.elementToBeClickable(btnSave));

			//driver.quit();
		}
		catch(Exception e){
			System.out.println("Exception Occured" + e);
			driver.quit();
		}


	}
}
