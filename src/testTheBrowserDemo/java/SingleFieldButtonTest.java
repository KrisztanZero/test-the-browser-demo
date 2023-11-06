import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingleFieldButtonTest {

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

    @ParameterizedTest
    @ValueSource(strings = {"Hello, Selenium!", "Testing with JUnit", "Multiple Strings"})
    public void enterMessageAndValidate(String inputText) {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-first-form-demo.html");

        WebElement inputField = driver.findElement(By.id("user-message"));
        inputField.sendKeys(inputText);

        WebElement showMessageButton = driver.findElement(By.xpath("//button[text()='Show Message']"));
        showMessageButton.click();

        WebElement displayedMessage = driver.findElement(By.id("display"));
        String messageText = displayedMessage.getText();

        assert(messageText.equals(inputText));
    }
}
