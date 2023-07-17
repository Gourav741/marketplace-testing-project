package com.onlinemarketplace.qa.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.onlinemarketplace.qa.base.Base;

public class Login {
	
	WebDriver driver;
	
	Base baseClass = new Base();
	
	@BeforeMethod
	public void setupBrower() {
		driver = baseClass.setUpBrowserAndLaunchApp();
		
		driver.findElement(By.linkText("My Account")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void closeBrowser() {
		baseClass.endBrowser();
	}
	

	@Test
	public void verifyLoginWithValidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys("test.tony@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Boolean accountDisplayed = driver.findElement(By.linkText("Edit your account information")).isDisplayed();
		Assert.assertTrue(accountDisplayed);
		
	}
	
	@Test
	public void verifyLoginWithInvalidCredentials() {
		
		driver.findElement(By.id("input-email")).sendKeys("xyzabc123@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("xyzabc123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String warningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();
		Assert.assertEquals(warningMsg, "Warning: No match for E-Mail Address and/or Password.","The warning message did not match for invalid credentials. Hence test failed.");
		
	}
	
	
	@Test
	public void verifyLoginWithOnlyInvalidEmail() {
		
		driver.findElement(By.id("input-email")).sendKeys("xyzabc123@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		String warningMsg = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]")).getText();
		Assert.assertEquals(warningMsg, "Warning: No match for E-Mail Address and/or Password.","The warning message did not match for invalid email & valid password. Hence test failed.");

	}
}
