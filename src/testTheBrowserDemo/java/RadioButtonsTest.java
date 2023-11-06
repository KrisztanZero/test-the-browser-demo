import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RadioButtonsTest {

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
    public void simpleRadioButtonsTest() {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-radiobutton-demo.html");

        WebElement maleRadio = driver.findElement(By.xpath("//input[@value='Male' and @name='optradio']"));
        WebElement femaleRadio = driver.findElement(By.xpath("//input[@value='Female' and @name='optradio']"));
        WebElement getValuesButton = driver.findElement(By.id("buttoncheck"));

        maleRadio.click();
        getValuesButton.click();
        assertMaleRadioButtonIsChecked();

        femaleRadio.click();
        getValuesButton.click();
        assertFemaleRadioButtonIsChecked();
    }

    private void assertMaleRadioButtonIsChecked() {
        WebElement result = driver.findElement(By.className("radiobutton"));
        String resultText = result.getText();
        assertEquals("Radio button 'Male' is checked", resultText);
    }

    @Test
    public void genderAndAgeGroupRadioButtonsTest() {
        driver.get("https://web.archive.org/web/20180926132852/http://www.seleniumeasy.com/test/basic-radiobutton-demo.html");

        WebElement maleRadio = driver.findElement(By.xpath("//input[@value='Male' and @name='gender']"));
        WebElement femaleRadio = driver.findElement(By.xpath("//input[@value='Female' and @name='gender']"));
        WebElement ageGroup0to5 = driver.findElement(By.xpath("//input[@value='0 - 5' and @name='ageGroup']"));
        WebElement ageGroup5to15 = driver.findElement(By.xpath("//input[@value='5 - 15' and @name='ageGroup']"));
        WebElement ageGroup15to50 = driver.findElement(By.xpath("//input[@value='15 - 50' and @name='ageGroup']"));
        WebElement getValuesButton = driver.findElement(By.xpath("//button[text()='Get values']"));

        maleRadio.click();
        ageGroup0to5.click();
        getValuesButton.click();
        assertGenderAndAgeGroup("Sex : Male", "Age group: 0 - 5");

        femaleRadio.click();
        ageGroup5to15.click();
        getValuesButton.click();
        assertGenderAndAgeGroup("Sex : Female", "Age group: 5 - 15");

        maleRadio.click();
        ageGroup15to50.click();
        getValuesButton.click();
        assertGenderAndAgeGroup("Sex : Male", "Age group: 15 - 50");
    }

    private void assertFemaleRadioButtonIsChecked() {
        WebElement result = driver.findElement(By.className("radiobutton"));
        String resultText = result.getText();
        assertEquals("Radio button 'Female' is checked", resultText);
    }

    private void assertGenderAndAgeGroup(String expectedGender, String expectedAgeGroup) {
        WebElement result = driver.findElement(By.className("groupradiobutton"));
        String resultText = result.getText();
        String[] resultLines = resultText.split("\n");
        assertEquals(expectedGender, resultLines[0]);
        assertEquals(expectedAgeGroup, resultLines[1]);
    }
}
