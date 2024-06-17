import lv.acodemy.page_object.LoginPage;
import lv.acodemy.page_object.ProductsPage;
import lv.acodemy.utils.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTest {
    private static final Logger log = LoggerFactory.getLogger(SauceDemoTest.class);
    Config config = Config.readConfig();

    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;


    @BeforeMethod
    public void beforeTest() {
        //Initialize driver;
        driver = new EdgeDriver();

        //Initialize pages
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);

        //TODO: Get URL from config.yaml file
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void testSauceDemoSuccessLogin() {

        loginPage.authorize(config.getCredentials().getLogin(), config.getCredentials().getPassword());
        Assert.assertEquals(driver.findElement(By.xpath("//span[@data-test='title']")).getText(), "Products");

    }

    @AfterMethod
    public void afterTest() {
        //Close driver
        driver.close();
        driver.quit();

    }

}
