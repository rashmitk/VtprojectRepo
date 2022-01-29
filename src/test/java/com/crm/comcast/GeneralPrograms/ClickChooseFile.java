package com.crm.comcast.GeneralPrograms;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class ClickChooseFile {

	public static void main(String[] args) throws Throwable {
		// get the data from property file
		String filePath="./src/test/resources/VtigercrmCommomData.properties";
		FileInputStream fis=new FileInputStream(filePath);
		Properties property=new Properties();
		property.load(fis);
		String browserName=property.getProperty("browser");
		String chromekey=property.getProperty("chromekey");
		String chromepath=property.getProperty("chromepath");
		String firefoxkey=property.getProperty("firefoxkey");
		String firefoxpath=property.getProperty("firefoxpath");
		String expectedurl=property.getProperty("url");
		String username=property.getProperty("username");
		String password=property.getProperty("password");
		String expectedTitle=property.getProperty("title");
		
		//how to launch browser
		WebDriver driver=null;
		if(browserName.equals("chrome")) {
			System.setProperty(chromekey, chromepath);
			driver=new ChromeDriver();
			System.out.println("PASS::Chrome Browser launched");
		}else if(browserName.equals("firefox")) {
			System.setProperty(firefoxkey, firefoxpath);
			driver=new FirefoxDriver();
			System.out.println("PASS::Firefox Browser launched");
	}else {
		System.out.println("FAIL::Browser not supported");
	}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20 ,TimeUnit.SECONDS);
		//1.navigating to application and verifying Url
		driver.get(expectedurl);
		String actualUrl=driver.getCurrentUrl();
		if(actualUrl.equals(expectedurl)) {
			System.out.println("PASS::Login Page is displayed");
		}else {
			System.out.println("PASS::Login Page is not displayed");
		}
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		WebElement contactsModulelLink = driver.findElement(By.linkText("Contacts"));
		contactsModulelLink.click();
		driver.findElement(By.cssSelector("img[title='Import Contacts']")).click();
		WebElement chooseFileButton = driver.findElement(By.xpath("//input[@type='file']"));
		//JavascriptExecutor jse= (JavascriptExecutor) driver;
		//jse.executeScript("arguments[0].click", chooseFileButton);
		Actions action=new Actions(driver);
		action.doubleClick(chooseFileButton).perform();

	}

}
