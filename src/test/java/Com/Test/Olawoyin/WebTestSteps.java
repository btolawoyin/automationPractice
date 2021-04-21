package Com.Test.Olawoyin;

import Com.Test.Utilities.ChromeWebDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebTestSteps {

    WebDriver driver;
//    String title;

    @Given("I am on the automation practice website")
    public void iAmOnAutomationPracticeWebsite() {
        driver = ChromeWebDriver.getDriver();

    //Launch Browser
        driver.get("http://automationpractice.com/");
        driver.manage().window().maximize();
        System.out.println("Browser successfully launched");
    }

    @When("I login to my customer account")
    public void iLoginToMyCustomerAccount() {
        //Click the 'Sign in' button
            WebElement btnSignIn = driver.findElement(By.linkText("Sign in"));
            if (btnSignIn.getSize() != null){
                btnSignIn.click();
                System.out.println("The 'Sign in' button successfully displayed");
            } else {
                System.out.println("The 'Sign in' button was NOT displayed");
            }
        //Enter the user's Email Address
            driver.findElement(By.xpath("//*[@id='email']")).sendKeys("bukky@olawoyin.net");
        //Enter the user's Password
            driver.findElement(By.xpath("//*[@id='passwd']")).sendKeys("Qu4l1t3st!");
        //Click 'Sign in'
            driver.findElement(By.xpath("//*[@id='SubmitLogin']/span")).click();
        //Confirm Landing Page
        WebElement objMyStore = driver.findElement(By.xpath("//*[@id=\"header_logo\"]/a/img"));
        if (objMyStore.getSize() != null){
            System.out.println("The user successfully logged in and expected landing page was displayed");
        } else {
            System.out.println("The expected landing page was NOT displayed");
        }
    }

    @When("I order a t-shirt")
    public void iOrderATShirt() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='My account']")).isDisplayed());

        driver.findElement(By.xpath("//*[@id='block_top_menu']/ul/li[3]/a")).click();

        driver.navigate().to("http://automationpractice.com/index.php?id_product=1&controller=product");

        driver.findElement(By.xpath("//*[@id='add_to_cart']/button/span")).click();

        driver.findElement(By.partialLinkText("Proceed to checkout")).click();

        driver.findElement(By.linkText("Proceed to checkout")).click();
        driver.findElement(By.xpath("//*[@id='center_column']//span[text()='Proceed to checkout']")).click();

        driver.findElement(By.xpath("//*[@id='cgv']")).click();
        driver.findElement(By.xpath("//button[@name='processCarrier']/span")).click();

        driver.findElement(By.xpath("//a[@title='Pay by bank wire']")).click();

        driver.findElement(By.xpath("//span[text()='I confirm my order']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Order confirmation']")).isDisplayed());

        driver.findElement(By.xpath("//a[@title='Back to orders']")).click();
    }


    @Then("I see ordered t-shirt in the order history")
    public void iSeeOrderedTShirtInTheOrderHistory() {
        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Order history']")).isDisplayed());

        String orderRef = driver.findElement(By.xpath("//tr[contains(@class, 'first_item')]/td[contains(@class, 'history_link')]/a")).getText();

        Assert.assertEquals(driver.findElement(By.xpath("//tr[contains(@class, 'first_item')]/td[contains(@class, 'history_date')]")).getText(), "04/21/2021");

        Assert.assertEquals(driver.findElement(By.xpath("//tr[contains(@class, 'first_item')]/td[contains(@class, 'history_price')]")).getAttribute("data-value"), "18.51");

        Assert.assertEquals(driver.findElement(By.xpath("//tr[contains(@class, 'first_item')]/td[contains(@class, 'history_method')]")).getText(), "Bank wire");

        System.out.println("\nT-Shirt Order reference: " + orderRef);

        driver.findElement(By.xpath("//*[@id='center_column']//a[@href='http://automationpractice.com/index.php?controller=my-account']/span")).click();
    }

    @And("I click on My Personal Information page")
    public void iAmOnMyPersonalInformationPage() {
    //Click 'My Personal Information' page button
        driver.findElement(By.xpath("//*[@id=\"center_column\"]//*[text()='My personal information']")).click();
    //Confirm the Personal Information Page
        WebElement objPIP = driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/h1"));
        if (objPIP.getSize() != null){
            System.out.println("The Personal Information page was successfully displayed");
        } else {
            System.out.println("The Personal Information page was NOT displayed");
        }

    }

    @And("I update my first name")
    public void iUpdatePersonalInformation() {
    //Edit the First Name
        WebElement objFirstname = driver.findElement(By.xpath("//*[@id=\"firstname\"]"));
        objFirstname.click();
        objFirstname.clear();
        objFirstname.sendKeys("BukkyEDITED");
        System.out.println("Firstname updated successfully");

    //Enter the Current Password
        driver.findElement(By.xpath("//*[@id=\"old_passwd\"]")).sendKeys("Qu4l1t3st!");
        System.out.println("Current Password successfully entered");
    //Enter the New Password
        driver.findElement(By.xpath("//*[@id=\"passwd\"]")).sendKeys("Qu4l1t3st!");
        System.out.println("New Password successfully entered");
    //Confirm the New Password
        driver.findElement(By.xpath("//*[@id=\"confirmation\"]")).sendKeys("Qu4l1t3st!");
        System.out.println("New Password successfully confirmed");
    //Click the 'Save' button
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/div/form//*[text()='Save']")).click();
        System.out.println("Save button successfully clicked");
    //Go to the Account Page
        driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[1]")).click();
    //Click 'My Personal Information' page button
        driver.findElement(By.xpath("//*[@id=\"center_column\"]//*[text()='My personal information']")).click();
    }

    @Then("I confirm name change")
    public void iConfirmNameChange() throws InterruptedException {
    //Confirm new Firstname
        WebElement objFirstname = driver.findElement(By.xpath("//*[@id=\"firstname\"]"));
        String expected = "Bukkyedited";
        String actual = objFirstname.getText();
        System.out.println(actual);
            if(expected.equals(actual)){
                System.out.println("Pass");}
            else {System.out.println("Fail");}
    }
}
