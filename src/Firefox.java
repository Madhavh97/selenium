import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Firefox {

    WebDriver driver;

    public Firefox() {
        System.setProperty("webdriver.gecko.driver", "/Users/madhavh/Downloads/geckodriver");
        driver = new FirefoxDriver();
    }

    public void launchBrowser() {
        driver.get("https://www.getcalley.com/how-calley-auto-dialer-app-works/");
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
        Firefox obj = new Firefox();
        obj.launchBrowser();
        // Assume deviceName and resolution are obtained dynamically
        String deviceName = "Firefox-Mobile";
        String resolution = "375×667";
        obj.takeScreenshot(deviceName, resolution);
        obj.driver.quit();
    }
}
