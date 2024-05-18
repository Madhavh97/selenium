package Browsers;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Safaritest {

    WebDriver driver = new SafariDriver();

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
        Safaritest obj = new Safaritest();
        obj.launchBrowser();
        // Assume deviceName and resolution are obtained dynamically
        String deviceName = "Safari-Mobile";
        String resolution = "375×667";
        obj.takeScreenshot(deviceName, resolution);
        obj.driver.quit();
    }
}
