import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    WebDriver driver = new ChromeDriver();

    public Main() {
        System.setProperty("webdriver.chrome.driver", "/Users/madhavh/Downloads/chromedriver_mac64/chromedriver");

    }

    public void launchBrowser() {
        driver.get("https://www.getcalley.com/");
    }

    public void takeScreenshot(String deviceName, String resolution) {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
        String folderPath = "screenshots/" + deviceName + "/" + resolution + "/";
        File destFile = new File(folderPath + "Screenshot-" + timestamp + ".png");
        try {
            FileUtils.copyFile(screenshotFile, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Main obj = new Main();
        obj.launchBrowser();
        // Assume deviceName and resolution are obtained dynamically
        String deviceName = "Chrome";
        String resolution = "1920x1080";
        obj.takeScreenshot(deviceName, resolution);
        obj.driver.quit();
    }
}
