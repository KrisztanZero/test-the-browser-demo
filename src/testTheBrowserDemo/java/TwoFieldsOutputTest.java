import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TwoFieldsOutputTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("web-driver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void enterValuesAndValidateTotal() {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-first-form-demo.html");

        WebElement valueA = driver.findElement(By.id("sum1"));
        valueA.sendKeys("123");

        WebElement valueB = driver.findElement(By.id("sum2"));
        valueB.sendKeys("456");

        WebElement getTotalButton = driver.findElement(By.xpath("//button[text()='Get Total']"));
        getTotalButton.click();

        WebElement displayedTotal = driver.findElement(By.id("displayvalue"));
        String totalText = displayedTotal.getText();

        assert(totalText.equals("579"));
    }
}
