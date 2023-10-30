package sample;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Layout2 {

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
		driver.get(prop.getProperty("url"));

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test(priority = 1)
	public void Tectfield() {

		driver.findElement(By.id("fname")).sendKeys(prop.getProperty("firstname"));
		driver.findElement(By.id("lname")).sendKeys(prop.getProperty("lastname"));

	}

	@Test(priority = 2)

	public void Gendercheckbox() {

		WebElement checkbox = driver.findElement(By.name("gender"));
		checkbox.click();
		Boolean chk = checkbox.isSelected();

		Assert.assertTrue(chk);

	}

	@Test(priority = 3)

	public void Dropdown() { // static dropdown by using select class

		WebElement dropdwn = driver.findElement(By.id("option"));
		Select sel = new Select(dropdwn);
		sel.selectByValue("option 1");
	}

	@Test(priority = 4)
	public void Multidrop() { // clicking multiple options using for loop

		List<WebElement> list = driver.findElements(By.xpath("//input[@id='moption']"));

		for (WebElement li : list) {
			li.click();
		}

	}

	@Test(priority = 5)
	public void Search() {

		driver.findElement(By.xpath("//input[@name='Options']")).sendKeys("Vanilla");
		driver.findElement(By.id("day")).sendKeys("10312023");

	}

	@Test(priority = 6)
	public void Slider() {

		WebElement slide = driver.findElement(By.id("a"));
		Actions ac = new Actions(driver);

		int offset = 20;

		ac.dragAndDropBy(slide, offset, 0).perform();

	}

	@Test(priority = 7)
	public void Uploadfile() throws Throwable { // using rob class to upload the files

//		JavascriptExecutor js=(JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,350)");

		driver.findElement(By.xpath("//label[@for='myfile']")).click();

		Robot robo = new Robot();
		robo.delay(2000);

		StringSelection upload = new StringSelection("C:\\Users\\Jakkam Sairam\\Downloads\\download.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(upload, null);
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_V);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		robo.keyRelease(KeyEvent.VK_V);
		robo.keyPress(KeyEvent.VK_ENTER);
		robo.keyRelease(KeyEvent.VK_ENTER);

	}

	@Test(priority = 8)
	public void LongText() throws Throwable {

		WebElement text = driver.findElement(By.name("message"));
		text.clear();
		Thread.sleep(1000);
		text.sendKeys("There was a boy sitting in cafeteria");

		WebElement submitbtn = driver.findElement(By.xpath("//button[text()=' Submit']"));
		Thread.sleep(5000);
		// submitbtn.click();
	}

}
