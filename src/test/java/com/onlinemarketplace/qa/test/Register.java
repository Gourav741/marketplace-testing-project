package com.onlinemarketplace.qa.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.onlinemarketplace.qa.base.Base;

public class Register {
	
	WebDriver driver;
	
	Base baseClass = new Base();
	
	@BeforeMethod
	public void startScenario() {
		driver = baseClass.setUpBrowserAndLaunchApp();
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	
	@AfterMethod
	public void closeBrowser() {
		baseClass.endBrowser();
	}

	@Test
	public void registerUsingOnlyMandatoryField() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Harry");
		driver.findElement(By.id("input-lastname")).sendKeys("Bruce"); 
		driver.findElement(By.id("input-email")).sendKeys("harry.bruce123@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("3456789012"); 
		driver.findElement(By.id("input-password")).sendKeys("123456");
		driver.findElement(By.id("input-confirm")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String accountSuccess = driver.findElement(By.xpath("//div[@id='content']/child::h1")).getText();
		Assert.assertEquals(accountSuccess, "Your Account Has Been Created!", "The text for account success did not match");
		
	}
	
	
	@Test
	public void registerUsingAllFields() {
		
		driver.findElement(By.id("input-firstname")).sendKeys("Tom");
		driver.findElement(By.id("input-lastname")).sendKeys("Cruise"); 
		driver.findElement(By.id("input-email")).sendKeys("tom.cruise982@gmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("4567890122"); 
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.id("input-confirm")).sendKeys("12345");
		
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='0']")).click();
		
		driver.findElement(By.xpath("//input[@name='agree']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		String accountSuccess = driver.findElement(By.xpath("//div[@id='content']/child::h1")).getText();
		Assert.assertEquals(accountSuccess, "Your Account Has Been Created!", "The text for account success did not match for all field scenario");
	}
}
