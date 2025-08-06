package com.gqt.test;

import java.io.FileInputStream;
import java.util.Properties;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class shadiTestCases {

	Properties prop = new Properties();

	@BeforeClass
	public void loadData() throws Exception {
		FileInputStream fis = new FileInputStream("src/com/gqt/utilities/shadiLogin.properties");
		prop.load(fis);
	}

	public void login(WebDriver driver, String email, String password) throws Exception {
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("//a[@alt='Member login']")).click();
		driver.findElement(By.xpath("//input[@name='user_email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='txtPassword2']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submit2']")).click();
		Thread.sleep(3000);
	}

	@Test
	public void validLogin() throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		login(driver, prop.getProperty("valid_email"), prop.getProperty("valid_password"));
		driver.quit();
	}

	@Test
	public void invalidLogin() throws Exception {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		login(driver, prop.getProperty("invalid_email"), prop.getProperty("invalid_password"));
		driver.quit();
	}
}