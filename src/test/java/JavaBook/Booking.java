package JavaBook;

import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Booking {
	private static WebDriver driver;
	static ExtentReports extent;
	private static ExtentTest test;
	@BeforeTest
	public void config() {
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Automation");
		 extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ayan Ghosh");
		reporter.config().setTheme(Theme.DARK);
		extent.setSystemInfo("Browser", "firefox");
		extent.setSystemInfo("os", "win10");
		extent.setSystemInfo("Hostname", "localhost");
		return ;
	}
	@Test
	public static void initial(){
		extent.createTest("Initial Test");
		System.setProperty("webdriver.firefox.driver",
				"C:\\Users\\ayanghosh\\Documents\\BrowserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://www.ixigo.com/");
		driver.manage().window().maximize();

		try {
			WebElement elementonpopup = driver.findElement(By.id("wzrk-cancel"));
			if (elementonpopup.isDisplayed()) {
				elementonpopup.click();// checking if popup coming or not

			}
		} catch (Exception e) {
			System.out.println("alert is not displayed!");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	
		String actual = driver.findElement(By.cssSelector("#content > div > div.offer-section > div > div > div > h2"))
				.getText();// assert for first page
		String expected = "Best Flight Booking Offers";
		Assert.assertEquals(actual, expected);
		System.out.println(" HotelBooking Started");
		driver.findElement(By.linkText("Hotels")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		/*WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));// explicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//*[@id=\"indexsearch\"]/div[1]/div/div/div/div/div/header/h1/span")));*/
		//Actions actions = new Actions(driver);

//		actions.moveToElement(driver.findElement(By.id("ss")).sendKeys("Hyderabad").perform();
		
		driver.findElement(By.id("ss")).sendKeys("Hyderabad");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("(//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb'][contains(@aria-hidden,'true')])[1]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath("//td[@class='bui-calendar__date'][contains(@data-date,'2022-11-25')]")).click();// first
																														// date

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		driver.findElement(By.xpath("//td[@class='bui-calendar__date'][contains(@data-date,'2022-11-30')]")).click();// last
																														// date
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		System.out.println("Date Selected");
		driver.findElement(By.className("xp__guests__count")).click();

		driver.findElement(By.xpath(
				"//button[@class='bui-button bui-button--secondary bui-stepper__add-button '][contains(@aria-describedby,'group_children_desc')]"))
				.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		WebElement staticDropdown = driver
				.findElement(By.xpath("//select[@name='age']"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText("6 years old");
		driver.findElement(By.cssSelector(
				"#frm > div.xp__fieldset.js--sb-fieldset.accommodation > div.xp__button > div.sb-searchbox-submit-col.-submit-button > button > span.js-sb-submit-text.b-button__text"))
				.click();// search
		String url = driver
				.findElement(By.xpath("//div[@class='a68bfa09c2'][contains(@data-testid,'availability-cta')]/a"))
				.getAttribute("href");
		System.out.println(url);
		System.out.println("Hotel Selected");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(url);
		driver.findElement(By.xpath("//button[@id='hcta']"))
				.click();
		WebElement staticDrop = driver.findElement(By.xpath(
				"//select[@id='hprt_nos_select_282701103_269950795_1_41_0']"));
		Select drop = new Select(staticDrop);
		drop.selectByIndex(1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.findElement(By.xpath("(//button[@data-tooltip-class='submit_holder_button_tooltip'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	
		//driver.findElement(By.xpath("//button[@class='txp-bui-main-pp bui-button bui-button--primary  hp_rt_input js-reservation-button px--fw-cta'][contains(@type,'submit')]").click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id("firstname")).sendKeys("Ayan");
		driver.findElement(By.id("lastname")).sendKeys("Ghosh");
		driver.findElement(By.id("email")).sendKeys("ayanghosh13237@gmail.com");
		driver.findElement(By.id("email_confirm")).sendKeys("ayanghosh13237@gmail.com");
		//driver.findElement(By.xpath("(//span[@class='bui-radio__label'])[3]")).click();
		WebElement staticdropr = driver.findElement(By.id("checkin_eta_hour"));
		
		Select dropd = new Select(staticdropr);
		dropd.selectByVisibleText("I don't know");
		driver.findElement(By.xpath("//button[@type='submit'][1]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id("phone")).sendKeys("9475325911");
		extent.flush();
	}
	@AfterMethod
	  public void tearDown(ITestResult result) throws IOException {
		  if(result.getStatus() == ITestResult.FAILURE) {
			  test.log(Status.FAIL, "test case fail is" +result.getName());
			  test.log(Status.FAIL, "test case fail is" +result.getThrowable());
			  String screenpath =Booking.getscreenhot(driver, result.getName());
			  test.addScreenCaptureFromPath(screenpath);
		  }
		  else if (result.getStatus() == ITestResult.SKIP) {
			  test.log(Status.SKIP, "test case skip is" +result.getName());
		}
		  else if (result.getStatus() == ITestResult.SUCCESS) {
			  test.log(Status.PASS, "test case passed is" +result.getName());
		}
		driver.quit();  
			  
		  }
	public void afterTest() {
	  }
	  public static String getscreenhot(WebDriver driver, String ScreenshotName) throws IOException{
		  String dataname = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		  TakesScreenshot ts = (TakesScreenshot) driver;
		  File source = ts.getScreenshotAs(OutputType.FILE);
		  String destination = System.getProperty("User.dir") + "/screenshot/" + ScreenshotName + dataname + ".png";
		  File FinalDestination = new File(destination);
		  FileUtils.copyFile(source, FinalDestination);
		  return destination;
		
}
}

