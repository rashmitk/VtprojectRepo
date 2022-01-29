package com.crm.comcast.GeneralPrograms;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

public class RightClick {
	
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "./src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.edureka.co");
        String parentWid=driver.getWindowHandle();
        System.out.println(driver.getTitle());
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(By.partialLinkText("Corporate Training"));

 action.contextClick(element).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();

        Set<String> winid = driver.getWindowHandles();
        Iterator<String> I1= winid.iterator();
		while(I1.hasNext())
		{

		String child_window=I1.next();
		if(!parentWid.equals(child_window))
		{
		driver.switchTo().window(child_window);

		System.out.println(driver.switchTo().window(child_window).getTitle());
		}}
		driver.close();

        driver.quit();
    }
}

	

