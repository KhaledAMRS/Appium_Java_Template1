package org.example.step_definitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileCommand;
import io.appium.java_client.Setting;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.example.utilities.ConfigurationReader;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Hooks {

public static AppiumDriver driver = null;
public static String executionMode = ConfigurationReader.get("executionMode");

    @Before
    public static void OpenApp(){
    try {

        DesiredCapabilities caps = new DesiredCapabilities();

        URL url = null;
        String platformName = ConfigurationReader.get("platformName");
        String appPackage = ConfigurationReader.get("appPackage");
        String appActivity = ConfigurationReader.get("appActivity");
        String udid = ConfigurationReader.get("udid");
        String browserstackUserID = ConfigurationReader.get("browserstackUserID");
        String browserstackKey = ConfigurationReader.get("browserstackKey");
        String browserstackAppID = ConfigurationReader.get("browserstackAppID");
        String browserstackDevice = ConfigurationReader.get("browserstackDevice");
        String browserstackOS = ConfigurationReader.get("browserstackOS");
        String automationName = ConfigurationReader.get("automationName");
        String testRunName = ConfigurationReader.get("testRunName");
        String app = ConfigurationReader.get("app");

        switch (executionMode) {
            case "Android":
                url = new URL(ConfigurationReader.get("appiumUrl"));
                caps.setCapability("UDID", udid);
                caps.setCapability("NEW_COMMAND_TIMEOUT", 60);
                caps.setCapability("appPackage", appPackage);
                caps.setCapability("appActivity", appActivity);
                caps.setCapability("PLATFORM_NAME", platformName);
                caps.setCapability("AUTOMATION_NAME", automationName);
                caps.setCapability("no-reset", true);
                caps.setCapability("full-reset", false);
                driver = new AndroidDriver<>(url, caps);
                break;
            case "IOS":
                url = new URL(ConfigurationReader.get("appiumUrl"));
                caps.setCapability("UDID", udid);
                caps.setCapability("NEW_COMMAND_TIMEOUT", 60);
                caps.setCapability("PLATFORM_NAME", platformName);
                caps.setCapability("AUTOMATION_NAME", automationName);
                caps.setCapability("APP", app);
                caps.setCapability("unicodeKeyboard", true);
                caps.setCapability("resetKeyboard", true);
                caps.setCapability("autoAcceptAlerts", true);
                driver = new IOSDriver<>(url, caps);
                break;

            case "BS-IOS":
                url = new URL(ConfigurationReader.get("browserstackUrl"));
                caps.setCapability("browserstack.user", browserstackUserID);
                caps.setCapability("browserstack.key", browserstackKey);
                caps.setCapability("app", browserstackAppID);
                caps.setCapability("device", browserstackDevice);
                caps.setCapability("os_version", browserstackOS);
                caps.setCapability("unicodeKeyboard", true);
                caps.setCapability("resetKeyboard", true);
                caps.setCapability("autoAcceptAlerts", true);
                caps.setCapability("autoGrantPermissions", true);
                caps.setCapability("build", "IOS TEST" + testRunName);
                caps.setCapability("browserstack.idleTimeout", 40000);
                driver = new IOSDriver<>(url, caps);
                driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 10);
                break;

            case "BS-Android":
                url = new URL(ConfigurationReader.get("browserstackUrl"));
                caps.setCapability("browserstack.user", browserstackUserID);
                caps.setCapability("browserstack.key", browserstackKey);
                caps.setCapability("app", browserstackAppID);
                caps.setCapability("device", browserstackDevice);
                caps.setCapability("os_version", browserstackOS);
                caps.setCapability("unicodeKeyboard", true);
                caps.setCapability("resetKeyboard", true);
                caps.setCapability("autoAcceptAlerts", true);
                caps.setCapability("autoGrantPermissions", true);
                caps.setCapability("build", "Android Test" + testRunName);
                driver = new AndroidDriver<>(url, caps);
                driver.setSetting(Setting.WAIT_FOR_IDLE_TIMEOUT, 10);
                break;

        }
        setUp();

    } catch (Exception ex) {
        ex.printStackTrace();
    }
}
    public static void setUp() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public static void closeDriver(Scenario scenario) {
        if (executionMode.contains("BS")) {
            if (scenario.isFailed()) {
                setSessionStatus("failed", String.format("%s failed.", scenario.getName()));
            } else {
                setSessionStatus("passed", String.format("%s passed.", scenario.getName()));
            }
        }
        driver.quit();
    }

    public static void setSessionStatus(String status, String reason) {
        JavascriptExecutor jse = driver;
        jse.executeScript(String.format("browserstack_executor: {\"action\": \"setSessionStatus\", \"arguments\": {\"status\": \"%s\", \"reason\": \"%s\"}}", status, reason));
    }
}
