package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Layout1 {

	public static WebDriver driver;
	Properties prop;
	Actions ac;

	@BeforeClass   

	public void setup() throws Throwable {

		prop = new Properties();
		FileInputStream file = new FileInputStream("./src/test/java/configprop/Properties"); 
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Jakkam Sairam\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		prop.load(file);
		driver = new ChromeDriver();
		driver.get(prop.getProperty("url")); //url is taken in properties file 

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test

	public void Alert() throws Throwable {

		driver.findElement(By.xpath("//button[@onclick='alertfunction()']")).click();
		Thread.sleep(2000);
		driver.switchTo().alert().accept();
	}

	@Test
	public void Gettext() {

		String sampletext = driver.findElement(By.xpath("//div[@class='tooltip']")).getText();
		Assert.assertEquals(sampletext, "This is your Sample Tooltip Option");

	}
	
	@Test
	public void Doubleclick() {

		ac = new Actions(driver);
		WebElement dclick = driver.findElement(By.xpath("//button[@ondblclick='myFunction()']"));
		ac.doubleClick(dclick).build().perform();
	}

	@Test
	public void DragDrop() {

		WebElement destination = driver.findElement(By.id("drag1"));
		WebElement source = driver.findElement(By.id("div1"));
		ac.dragAndDrop(source, destination).build().perform();
	}

	@Test
	public void LoginFunctionality() {

		driver.findElement(By.id("uname")).sendKeys(prop.getProperty("username"));
		driver.findElement(By.id("pwd")).sendKeys(prop.getProperty("password"));
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String title = driver.getTitle();
		Assert.assertEquals("Login Success", title);
	}
}
