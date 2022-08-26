package FrameworkProject;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Setup {
    public static WebDriver driver;
    public static String url = "https://www.amazon.com.mx";

    public WebDriver CreateDriver()	{

        System.setProperty("webdriver.chrome.driver", "src/main/Resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        return driver;
    }

    public void visit(String url) {
        driver.get(url);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public String getText(By locator) {
        return driver.findElement(locator).getText();
    }

    public WebElement findElement(By locator)	{
        return driver.findElement(locator);
    }

    public List<WebElement> findElements(By locator)	{
        return driver.findElements(locator);
    }

    public void type(String inputText, By locator) {
        driver.findElement(locator).sendKeys(inputText);
    }

    public static boolean WaitHelper(WebDriver diver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(diver, 50);
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public static boolean WaitHelper2(WebDriver diver, By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(diver, 50);
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
    public JSONObject readJson() {
        JSONParser jsonParser = new JSONParser();

        JSONObject json = new JSONObject();

        try {
            json = (JSONObject)jsonParser.parse(new FileReader("src/JSON/data.json"));

        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return json;
    }

}
