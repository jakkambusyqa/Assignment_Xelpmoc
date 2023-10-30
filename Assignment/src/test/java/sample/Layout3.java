package sample;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Layout3 {

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

	@Test
	public void WebTable() {

		List<WebElement> rows = driver.findElements(By.xpath("//table//tr"));
		int rowsnumbers = rows.size();

		Assert.assertEquals(7, rowsnumbers); // verify the row numbers

		List<WebElement> tabledata = driver.findElements(By.tagName("td"));

		boolean datapresent = false;

		for (WebElement data : tabledata) {

			System.out.println(data.getText()); // Retrieving data from table using td tag

			if (data.getText().equals("Monika")) { // verifying the data is present in table

				datapresent = true;
			}

		}
		Assert.assertEquals(true, datapresent);

	}

}
