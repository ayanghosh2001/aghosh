package Hotel;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(publish = true)
public class Book {
	private WebDriver driver;
	static ExtentReports extent;
	@Before
	public void setup() {
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Result");
		reporter.config().setDocumentTitle("Automation");
		 extent= new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ayan Ghosh");
		extent.createTest("Initial Test");
		System.setProperty("webdriver.firefox.driver",
				"C:\\Users\\ayanghosh\\Documents\\BrowserDrivers\\geckodriver.exe");
		//WebDriverManager.chromiumdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://www.ixigo.com/");
		driver.manage().window().maximize();
	}

	@Given("Go to website")
	public void Go_to_website() {
		
		try {
			WebElement elementonpopup = driver.findElement(By.id("wzrk-cancel"));
			if (elementonpopup.isDisplayed()) {
				elementonpopup.click();// checking if popup coming or not

			}
		} catch (Exception e) {
			System.out.println("alert is not displayed!");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
	}

	@Given("assert for first page")
	public void assert_for_first_page() {
		String actual = driver.findElement(By.cssSelector("#content > div > div.offer-section > div > div > div > h2"))
				.getText();// assert for first page
		String expected = "Best Flight Booking Offers";
		Assert.assertEquals(actual, expected);
		System.out.println(" HotelBooking Started");
		driver.findElement(By.linkText("Hotels")).click();
	}

	@When("^Hotel booking started (.+)$")
	public void Hotel_booking_started(String name) {
		
		Actions actions = new Actions(driver);
		driver.findElement(By.id("ss")).sendKeys(name);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.xpath(
				"//span[@class='sb-date-field__icon sb-date-field__icon-btn bk-svg-wrapper calendar-restructure-sb'][contains(@aria-hidden,'true')]"))
				.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.findElement(By.xpath("//td[@class='bui-calendar__date'][contains(@data-date,'2022-11-25')]")).click();// first
																														// date

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));

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
				"//select[@id='hprt_nos_select_282701104_269950795_2_41_0']"));
		Select drop = new Select(staticDrop);
		drop.selectByIndex(1);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("(//button[@data-tooltip-class='submit_holder_button_tooltip'])[2]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@And("^I enter (.+) (.+) (.+) (.+)$")
	public void I_enter(String fname, String lname, String email, String phone) {
		//driver.findElement(By.xpath("//button[@class='txp-bui-main-pp bui-button bui-button--primary  hp_rt_input js-reservation-button px--fw-cta'][contains(@type,'submit')]").click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id("firstname")).sendKeys(fname);
		driver.findElement(By.id("lastname")).sendKeys(lname);
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("email_confirm")).sendKeys(email);
		driver.findElement(By.xpath("(//span[@class='bui-radio__label'])[3]")).click();
		WebElement staticdropr = driver.findElement(By.id("checkin_eta_hour"));
		
		Select dropd = new Select(staticdropr);
		dropd.selectByVisibleText("I don't know");
		driver.findElement(By.xpath("//button[@type='submit'][1]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.findElement(By.id("phone")).sendKeys(phone);
		extent.flush();
	}
}


