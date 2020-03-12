package org.example.steps;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.HomePage;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.testng.Assert.assertTrue;

public class StepDefinitions {
    public static final String SAUCE_HUB_URL = "https://ondemand.saucelabs.com:443/wd/hub";
    private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    private HomePage homePage;

    private WebDriver getDriver() {
        return drivers.get();
    }

    public void setDriver(WebDriver driver) {
        drivers.set(driver);
    }

    private WebDriver createDriver(final String browserName, final String browserVersion, final String platform)
            throws MalformedURLException {
        String sauceUserName = System.getenv("SAUCE_USERNAME");
        String sauceAccessKey = System.getenv("SAUCE_ACCESS_KEY");

        MutableCapabilities sauceOpts = new MutableCapabilities();
        sauceOpts.setCapability("username", sauceUserName);
        sauceOpts.setCapability("accessKey", sauceAccessKey);

        MutableCapabilities browserOpts = new MutableCapabilities();
        browserOpts.setCapability(CapabilityType.PLATFORM_NAME, platform);
        browserOpts.setCapability(CapabilityType.BROWSER_VERSION, browserVersion);
        browserOpts.setCapability(CapabilityType.BROWSER_NAME, browserName);
        browserOpts.setCapability("sauce:options", sauceOpts);

        URL hubUrl = new URL(SAUCE_HUB_URL);
        return new RemoteWebDriver(hubUrl, browserOpts);
    }

    @Given("a user opens a browser with specific configuration {string}, {string} y {string}")
    public void aUserOpensABrowserWithSpecificConfigurationY(final String browserName, final String browserVersion, final String platform)
            throws MalformedURLException {
        setDriver(createDriver(browserName, browserVersion, platform));
        homePage = new HomePage(getDriver());
    }

    @When("a user goes to home page")
    public void aUserGoesToHomePage() {
        homePage.goHome();
    }

    @Then("the user sees the home page loaded")
    public void theUserSeesTheHomePageLoaded() {
        assertTrue(homePage.isLoaded(), "this is not home page");
    }

    @After
    public void quit() {
        getDriver().quit();
    }
}
