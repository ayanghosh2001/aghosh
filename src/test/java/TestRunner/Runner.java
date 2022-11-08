package TestRunner;





import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features=".//Feature/Book.feature",
		glue="Hotel",
		dryRun = false,
		monochrome = true,
		plugin = {"json:target/cucumber.json"}
		
		)
public class Runner{
//empty classs
}
