package com.crm.comcast.createContact;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class createContactFromImportTest {

	public static void main(String[] args) throws Throwable{
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
				//2.verifying title
				String actualTitle = driver.getTitle();
				if(actualTitle.equals(expectedTitle)) {
					System.out.println("PASS::Home Page is displayed");
				}else {
					System.out.println("FAIL::Home Page is not displayed");
				}
				
				WebElement contactsModulelLink = driver.findElement(By.linkText("Contacts"));
				contactsModulelLink.click();
				String actualTitle1 = driver.getTitle();
				System.out.println(actualTitle1);
				String expectedTitle1="Administrator - Contacts";
				if(actualTitle1.contains(expectedTitle1)) {
					System.out.println("PASS::Contact list Page is displayed");
				}else {
					System.out.println("FAIL::Contact list Page is not displayed");
				}
				
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				//String parentWid=driver.getWindowHandle();
				String actualPageName = driver.findElement(By.className("lvtHeaderText")).getText();
				//System.out.println(actualPageName);
				String expectedPageName="Creating New Contact";
				if(actualPageName.contains(expectedPageName)) {
					System.out.println("PASS::Contact New Page is displayed");
				}else {
					System.out.println("FAIL::Contact New Page is not displayed");
				}
				driver.findElement(By.name("firstname")).sendKeys("aaj");
				driver.findElement(By.name("lastname")).sendKeys("singh");
				WebElement exportContactsLink = driver.findElement(By.xpath("//img[@title='Export Contacts']"));
				exportContactsLink.click();
				driver.findElement(By.cssSelector("input[value='Export Contacts ']")).click();
				//Actions action=new Actions(driver);
				//action.contextClick(exportContactsLink).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
			
				String actualPageName1 = driver.findElement(By.xpath("//td[@class='mailClientBg  genHeaderSmall']")).getText();
				String expectedPageName1="Contacts >> Export";
				if(actualPageName1.contains(expectedPageName1)) {
					System.out.println("PASS::Export contact Page is displayed");
				}else {
					System.out.println("FAIL::Export contact Page is not displayed");
				}
				Thread.sleep(1000);
				driver.findElement(By.cssSelector("a[class='hdrLink']")).click();
				driver.findElement(By.cssSelector("img[title='Import Contacts']")).click();
				WebElement chooseFileButton = driver.findElement(By.id("import_file"));
				Actions action=new Actions(driver);
				action.click(chooseFileButton).perform();
				Runtime.getRuntime().exec("\"D:\\SELENIUM\\SOFTWARE\\AUTOIT Pgms\\vtigerContactimport.exe\"");
				Thread.sleep(2000);
				driver.findElement(By.name("next")).click();
			
			
				
				//driver.quit();
	}

}
