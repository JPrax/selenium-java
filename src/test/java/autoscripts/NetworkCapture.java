package autoscripts;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class NetworkCapture {

    public static ChromeDriver chromeDriver;
    public static DevTools devTools;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        this.chromeDriver = new ChromeDriver();
        devTools = chromeDriver.getDevTools();
        devTools.createSession();
    }

    @Test
    public void getRequestsAndResponseUrls() throws InterruptedException {
        devTools.send(new Command<>("Network.enable", ImmutableMap.of()));
        ;
        devTools.addListener(Network.responseReceived(), l -> {
            System.out.print("Response URL: ");
            System.out.println(l.getResponse().getUrl());
            System.out.print("Response Status: ");
            System.out.println(l.getResponse().getStatusText());
            System.out.print("Response Header JSON: ");
            System.out.println(l.getResponse().getHeaders().toJson());
            System.out.print("Response Request header: ");
            System.out.println(l.getResponse().getRequestHeaders().toString());
        });


        devTools.addListener(Network.requestWillBeSent(), l -> {
            System.out.print("Request URL: ");
            System.out.println(l.getRequest().getUrl());
            System.out.print("Request Header String: ");
            System.out.println(l.getRequest().getHeaders().toString());
            System.out.print("Request Post Data: ");
            System.out.println(l.getRequest().getPostData());
            System.out.print("Request JSON: ");
            System.out.println(l.getRequest().getHeaders().toJson());
        });

        chromeDriver.get("https://86400directhomeloan.loanworks.com.au/Refinance/enquiry/property/1");
        devTools.clearListeners();

        // While Thread.sleep you you will see requests and responses appearing in console.
        Thread.sleep(600000);
    }


}
