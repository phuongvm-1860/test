package stepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pageObjects.login.AppointmentPage;
import pageObjects.login.LoginPage;
import utility.GetPropertiesValue;
import utility.ScenarioContext;
import utility.TestBase;

public class LoginSteps extends TestBase {

    private GetPropertiesValue getPropertiesValue = new GetPropertiesValue();

    public LoginSteps(ScenarioContext context) {
        scenarioContext = context;
    }

    @When("^Admin navigates to Admin site again$")
    public void admin_navigates_to_Admin_site_again() {
        driver.get(getPropertiesValue.getPropValues("Base_URL.properties", "ADMIN_BASE_URL"));
    }

    @When("^user sets username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
    public void userSetsUsernameAsAndPasswordAs(String username, String password) throws Throwable {
        LoginPage.username().sendKeys(username);
        LoginPage.password().sendKeys(password);
    }

    @Given("^user is in Katalon login page$")
    public void userIsInKatalonLoginPage() {
        driver.get(getPropertiesValue.getPropValues("Base_URL.properties", "BASE_URL")+"/profile.php#login");
    }

    @And("^user clicks on login button$")
    public void userClicksOnLoginButton() {
        LoginPage.loginButton().click();
    }

    @Then("^user sees appointment page$")
    public void userSeesAppointmentPage() {
        Assert.assertTrue(AppointmentPage.facilityBox().isDisplayed());
    }
}
