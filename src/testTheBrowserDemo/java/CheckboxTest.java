import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class CheckboxTest {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(CheckboxTest.class);


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
    public void singleCheckboxDemo() {
        logger.info("Starting singleCheckboxDemo");
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-checkbox-demo.html");

        WebElement singleCheckbox = driver.findElement(By.id("isAgeSelected"));
        singleCheckbox.click();
        logger.info("Single checkbox clicked");

        WebElement successMessage = driver.findElement(By.id("txtAge"));
        String messageText = successMessage.getText();
        logger.info("Success message:"+ messageText);

        if (messageText.equals("Success - Check box is checked")) {
            logger.info("Single checkbox test passed");
        } else {
            logger.error("Single checkbox test failed");
        }

        assert(messageText.equals("Success - Check box is checked"));
    }

    @Test
    public void select_All_Checkbox_One_By_One_Test() {
        logger.info("Starting select_All_Checkbox_One_By_One_Test");
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-checkbox-demo.html");

        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        WebElement checkAllButton = driver.findElement(By.id("check1"));

        for (int i = 0; i < checkboxes.size(); i++) {
            checkboxes.get(i).click();
            try {
                if (i == checkboxes.size() - 1) {
                    assert checkAllButton.getAttribute("value").equals("Uncheck All");
                } else {
                    assert checkAllButton.getAttribute("value").equals("Check All");
                }
                logger.info("Checkbox " + (i + 1) + " clicked. Check All button status: " + checkAllButton.getAttribute("value"));
            } catch (AssertionError e) {
                logger.error("Assertion failed: Checkbox " + (i + 1) + " was not toggled correctly. Check All button status: " + checkAllButton.getAttribute("value"));
                throw e;
            }
        }
        logger.info("Finishing select_All_Checkbox_One_By_One_Test");
    }

    @Test
    public void select_From_Multiple_Checkbox_Demo_Test() {
        logger.info("Starting select_From_Multiple_Checkbox_Demo_Test");
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        WebElement checkAllButton = driver.findElement(By.id("check1"));

        for (int i = 1; i <= 4; i++) {
            WebElement checkbox = driver.findElement(By.xpath("//label[text()='Option " + i + "']/input[@class='cb1-element']"));
            checkbox.click();

            if (i < 4) {
                assert(checkAllButton.getAttribute("value").equals("Check All"));
            } else {
                assert(checkAllButton.getAttribute("value").equals("Uncheck All"));
            }
            logger.info("Option " + i + " checkbox clicked. Check All button status: " + checkAllButton.getAttribute("value"));
        }
        logger.info("Finishing select_From_Multiple_Checkbox_Demo_Test");
    }

    @Test
    public void validateCheckBoxBug() {
        logger.info("Starting validateCheckBoxBug");
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        WebElement checkAllButton = driver.findElement(By.id("check1"));
        String buttonText = checkAllButton.getAttribute("value");

        for (int i = 1; i <= 3; i++) {
            WebElement checkbox = driver.findElement(By.xpath("//label[text()='Option " + i + "']/input[@class='cb1-element']"));
            checkbox.click();
            assert(buttonText.equals("Check All"));
            logger.info("Option " + i + " checkbox clicked. Check All button status: " + checkAllButton.getAttribute("value"));
        }

        WebElement ageSelectedCheckbox = driver.findElement(By.id("isAgeSelected"));
        ageSelectedCheckbox.click();
        buttonText = checkAllButton.getAttribute("value");
        assert(buttonText.equals("Check All"));
        logger.info("Age Selected checkbox clicked. Check All button status: " + checkAllButton.getAttribute("value"));

        WebElement option4Checkbox = driver.findElement(By.xpath("//label[text()='Option 4']/input[@class='cb1-element']"));
        option4Checkbox.click();
        buttonText = checkAllButton.getAttribute("value");

        assert(buttonText.equals("Check All"));
        logger.info("Option 4 checkbox clicked. Check All button status: " + checkAllButton.getAttribute("value"));

        logger.info("Finishing validateCheckBoxBug. This test is pass by represent a bug.");
    }
}
