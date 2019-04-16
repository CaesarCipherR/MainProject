package Application;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import resources.Base;

public class HomePage extends Base {
	
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	@Test(dataProvider="getData")
	public void baseNavigation(String Username, String Password, String text) throws IOException {
	driver=initializeDriver();
	driver.get(prop.getProperty("url"));	
	LandingPage lp = new LandingPage(driver);
	Assert.assertEquals(lp.getTitle().getText(), "Sign Up");
	lp.getEmail().sendKeys(Username);
	lp.getPassword().sendKeys(Password);
	log.info(text);
	LoginPage l = new LoginPage(driver);
	l.getLogin().click();
		
	}
	
	@AfterTest
	public void teardown() {
		driver.close();
	}

	
	@DataProvider
	public Object[][] getData() {
		Object[][] data = new Object[2][3];
		data[0][0]="nonerestrictiveuser@facebook.com";
		data[0][1]="12345";
		data[0][2]="Non Restrictive User";
		
		data[1][0]="restrictiveuser@facebook.com";
		data[1][1]="678910";
		data[1][2]="Restrictive User";
		
		return data;
	}

}
