import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class SelectListTest {

    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("web-driver.chrome.driver", "path_to_chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeEach
    public void openTestPage() {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
    }

    @AfterEach
    public void resetSelection() {
        driver.findElement(By.id("select-demo")).click();
        driver.findElement(By.id("select-demo")).sendKeys("Sunday");
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"})
    public void testDaySelection(String day) {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");

        Select select = new Select(driver.findElement(By.id("select-demo")));
        select.selectByVisibleText(day);

        String selectedText = select.getFirstSelectedOption().getText();
        assert selectedText.equals(day);

        WebElement resultElement = driver.findElement(By.className("selected-value"));
        String resultText = resultElement.getText();
        assert resultText.equals("Day selected :- " + day);
    }

    @Test
    public void selectCurrentDay() {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");

        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();
        String currentDayString = currentDay.toString();
        String formattedCurrentDay = currentDayString.charAt(0) + currentDayString.substring(1).toLowerCase();

        Select select = new Select(driver.findElement(By.id("select-demo")));
        select.selectByVisibleText(formattedCurrentDay);

        String selectedText = select.getFirstSelectedOption().getText();
        assert selectedText.equals(formattedCurrentDay);

        WebElement resultElement = driver.findElement(By.className("selected-value"));
        String resultText = resultElement.getText();
        assert resultText.equals("Day selected :- " + formattedCurrentDay);
    }
}

