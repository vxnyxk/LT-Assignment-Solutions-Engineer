import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.io.IOException;

public class TestCases {
    WebDriver driver;

    @BeforeMethod
    public WebDriver setup() {
        /*WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize(); */
        // LambdaTest setup
        if (driver == null) {
            try {
                ChromeOptions browserOptions = new ChromeOptions();
                browserOptions.setPlatformName("Windows 10");
                browserOptions.setBrowserVersion("135");
                HashMap<String, Object> ltOptions = new HashMap<>();
                ltOptions.put("username", ConfigReader.get("lt.username"));
                ltOptions.put("accessKey", ConfigReader.get("lt.accessKey"));
                ltOptions.put("build", "SE Assignment");
                ltOptions.put("project", "Vinayak1");
                ltOptions.put("name", "SE assignment test1");
                ltOptions.put("w3c", true);
                ltOptions.put("plugin", "java-testNG");
                browserOptions.setCapability("LT:Options", ltOptions);
                driver = new RemoteWebDriver(
                    new URL("https://hub.lambdatest.com/wd/hub"), browserOptions
                );
            } catch (MalformedURLException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to initialize WebDriver with LambdaTest capabilities.");
            }
        }
        return driver;
    }

    @Test
    public void testCase1() throws InterruptedException {
        driver.get("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("apple iphone 15 pro");
        searchBox.submit(); 
        driver.get("https://www.amazon.in/Apple-iPhone-15-Pro-TB/dp/B0CHWWVSLF/ref=sr_1_1_sspa?dib=eyJ2IjoiMSJ9.-Qy66WqNlaGT_35xc22-rVKsaG-nK9yNwYXLJPcYE4V6YYmEpHxp1ek5oho5l2JwgDhJdh-iPSHf2NKCHQhSf80cpJUS2mGavxUZ8oLbGGvh_GjwdFwN1p6iePrkS69QEmZp08HeT4JTWaTI3EhKS3MzWov1SMIaZ7ka0wa9wa1o5pc0uFXFMxlZY4-Hk4EMH_BcCIburSMne-uFQ8PjNNTGyxukw8G0NNv44wQN3kM.a5F-_xUEjkdVtYJvw9Gh_KNZdg4EQroTc_FQDW1YKjI&dib_tag=se&keywords=apple+iphone+15+pro&nsdOptOutParam=true&qid=1746908131&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&psc=1");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[5]/div[1]/div[4]/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/form/div/div/div[38]/div[1]/span/span"));
        addToCartButton.click();
        WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"attach-accessory-cart-subtotal\"]")));
        System.out.println("Price of the selected iPhone: " + priceElement.getText());
    }

    @Test
    public void testCase2() {
        driver.get("https://www.amazon.in");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("samsung galaxy");
        searchBox.submit();
        driver.get("https://www.amazon.in/Samsung-Smartphone-Titanium-Storage-Included/dp/B0DSKMKJV5/ref=sr_1_1_sspa?crid=2ODUBL2P1EOEJ&dib=eyJ2IjoiMSJ9.NJsyv0pf19PHrpOsuY1AuiAOHHQg8QhlthfzgCCo0_00uveNfNUn0zXbsIVa1mDfimSJU0bluo8S5BC0WEPq64GzGI_fcJtLIdZeIatHDwpAXLzddDYpWf61eHCLIhuVhGjyIySDyzDRB8gfdqAGVpl6OxQThAxGDZSYPkDdxj-JpxhtvAdvsIACIZwEE8bMLpNSeVoScyNof482e_hl-cLRGBpA-5T9SVQ0LFf0SRI.L8qQnGw7czn_Y9htBMuJr0IDTZN6qXUfqp2OOOpmG98&dib_tag=se&keywords=samsung%2Bgalaxy&nsdOptOutParam=true&qid=1746912603&sprefix=samsung%2Bgalaxy%2B%2Caps%2C246&sr=8-1-spons&sp_csd=d2lkZ2V0TmFtZT1zcF9hdGY&th=1");   
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addToCartButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[5]/div[1]/div[4]/div/div[1]/div/div[1]/div/div/div[2]/div/div[2]/div/form/div/div/div[38]/div[1]/span/span"));
        addToCartButton.click();
        WebElement priceElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"attach-accessory-cart-subtotal\"]")));
        System.out.println("Price of the selected Galaxy device: " + priceElement.getText());
    }

   @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}