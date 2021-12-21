package driver;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Driver {

    // Holds the WebDriver instance
    public static WebDriver webDriver;
    public static WebDriverWait waitTimeOut;
    public static Actions action;

    // Initialize a webDriver instance of required browser
    // Since this does not have a significance in the application's business domain, the BeforeSuite hook is used to instantiate the webDriver
    //@BeforeSuite
    public static void initializeDriver(){
        webDriver = DriverFactory.getDriver();
        webDriver.manage().window().maximize();
        action = new Actions(Driver.webDriver);
        //webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        waitTimeOut = new WebDriverWait(webDriver,60);
    }

    // Close the webDriver instance
    //@AfterSuite

    public static void closeDriver(){
        webDriver.quit();
    }




}
