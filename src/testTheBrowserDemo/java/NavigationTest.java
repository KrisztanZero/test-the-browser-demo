import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTest {

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
    public void navigateToSimpleFormDemo() {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-first-form-demo.html");

        WebElement allExamples = driver.findElement(By.linkText("All Examples"));
        allExamples.click();

        WebElement inputForms = driver.findElement(By.linkText("Input Forms"));
        inputForms.click();

        WebElement simpleFormDemo = driver.findElement(By.linkText("Simple Form Demo"));
        simpleFormDemo.click();

        WebElement paragraph = driver.findElement(By.xpath("//p[contains(text(),'First Let us try be very simple with only one input field and a Button')]"));
        boolean isPageLoaded = paragraph.isDisplayed();

        assert(isPageLoaded);
    }
}
