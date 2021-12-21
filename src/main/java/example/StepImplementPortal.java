package example;


import driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementPortal {




    public static void goto86400Page(String page) {
        String app_url = System.getProperty("86400_BASE_URL")+ "/" + System.getProperty(page+"_URL");
        Driver.webDriver.get(app_url);
        assertThat(Driver.webDriver.getTitle()).contains("86 400 - Smarter. Every second. Every Day");
        ScenarioDataStore.put("application", "Portal");
    }


    public static void enterAddress(String address, String field){
        if (address.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement label = Commons.getLabelElement(field,"span");
        WebElement element = Commons.getSiblingElement(label,"input");
        element.clear();
        element.sendKeys(address);
        element.click();
        Driver.action.
                pause(1000).
                sendKeys(Keys.SPACE).
                pause(1000).
                sendKeys(Keys.BACK_SPACE).
                perform();
        WebElement element2 = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//span[contains(@class, \"option\")][.=\""+address+"\"]")));
        element2.click();
    }


    public static void verifyCoreLogicReturn(){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[contains(text(),\"OK, here are your property details from CoreLogic. Please change anything that doesn't look right.\") or contains(text(),\"Nice one, here are some details about the area from CoreLogic.\")]")));
        assertThat(element.isDisplayed()).isTrue();
        Driver.action.pause(3000).perform();
    }


    public static void selectPropertyType(String property_type){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[@class=\"label\"]/span[.=\"" + property_type + "\"]")));
        element.click();
    }


    public static void verifyLandSize(String land_size){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class, \"land\")][contains(@class, \"size\")]//input")));
        String value = element.getAttribute("value");
        assertThat(value).isEqualTo(land_size);
    }


    public static void verifyEstimatedValueRating(String rating){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//span[.=\"Estimated value\"]/../*[contains(@class,\"confidence\")]")));
        String value = element.getText();
        assertThat(value).isEqualTo(rating);
    }


    public static void verifyEstimatedValueAmount(String amount){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable(By.xpath("//span[.=\"Estimated value\"]/../..//input")));
        String value = element.getAttribute("value");
        assertThat(value).isEqualTo(amount);
    }


    public static void respondToQuestion(String response, String question){
        if (response.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement element = Commons.getLabelElement(question,"span");
        WebElement element1 = Commons.getSiblingElement(element,"button/span[contains(text(),\""+response+"\")]");
        element1.click();
        Driver.action.pause(600).perform();
    }


    public static void enterCurrentLoanBalance(String loan_balance){
        WebElement label = Commons.getLabelElement("And what's your current loan balance?", "span");
        WebElement element = Commons.getSiblingElement(label,"input");
        element.clear();
        element.sendKeys(loan_balance);
        Driver.action.
                sendKeys(Keys.NUMPAD0).
                sendKeys(Keys.BACK_SPACE).
                perform();
    }


    public static void verifyLoanBalanceError(String loan_balance_error) {

        String value;

        try {
            WebElement element = Driver.webDriver.findElement(By.xpath("//*[@id=\"loanBalance\"]/..//app-error-message"));
            value = element.getText();
            Actions actions = new Actions(Driver.webDriver);
            actions.moveToElement(element).pause(1000).perform();
        } catch (NoSuchElementException e) {
            value = "";
            System.out.println("No Error Message Displayed");
        }

        assertThat(value).isEqualTo(loan_balance_error);
    }


    public static void verifyCheckPropertyButton(boolean true_false){
        WebElement element = Commons.getLabelElement("Check my property","span");
        assertThat(element.isEnabled()).isEqualTo(true_false);
    }



    public static void portalClickButton(String button) {
        if (button.isBlank()){
            System.out.println("Button value is blank, step has been skipped.");
            return;}
        WebElement element = Commons.getLabelElement(button,"button");
        element.click();
        Driver.action.pause(600).perform();
    }

    public static void portalClickLink(String link) {
        if (link.isBlank()){
            System.out.println("Button value is blank, step has been skipped.");
            return;}
        WebElement element = Commons.getLabelElement(link,"span");
        element.click();
        Driver.action.pause(600).perform();
    }


    public static void portalClickTab(String tab) {
        WebElement element = Commons.getLabelElement(tab,"*[contains(@class,\"tab\")][contains(@class,\"label\")][contains(@class,\"content\")]");
        element.click();
        Driver.action.pause(100).perform();
    }



    public static void verifyTextDisplays(String text){
        if (text.isBlank()){
            System.out.println("Verify value is blank, step has been skipped.");
            return;}
        WebElement element;
        //Find out if there is an element with given text
        try{
            element = Driver.waitTimeOut.until
                    (ExpectedConditions.presenceOfElementLocated(By.xpath("//*[name()=\"div\" or name()=\"span\" or name()=\"h2\" or name()=\"p\"][contains(text(),\"" + text + "\")]")));
        }
        catch(TimeoutException e){
            assertThat(false).isTrue();
            System.out.println("Element: '"+text+"' does not exist.");
            return;
        }
        //Iterate the collection of elements until one is found as displayed
        Driver.action.pause(500).perform();
        element = Commons.getLabelElementLike(text,"*[name()=\"div\" or name()=\"span\" or name()=\"h2\" or name()=\"p\"]");
        assertThat(element.isDisplayed()).isTrue();
    }



    public static void portalVerifyFieldValue(String field, String value){
        if (value.isBlank()){
            System.out.println("Verify value is blank, step has been skipped.");
            return;}
        WebElement element1;
        try{
            element1 = Driver.webDriver.findElement(By.xpath("//*[name()=\"div\" or name()=\"span\"][contains(text(),\""+field+"\")]/../*[name()=\"div\" or name()=\"span\"][text()][not(contains(text(),\""+field+"\"))]"));
        }catch(NoSuchElementException e) {
            WebElement element = Commons.getLabelElement(field, "*[name()=\"div\" or name()=\"span\"][contains(@class,\"label\") or contains(@class,\"card-name\") or contains(@class,\"header\")]");
            element1 = Commons.getSiblingElement(element, "*[name()=\"div\" or name()=\"span\"][contains(@class,\"value\") or contains(@class,\"numbers\") or contains(@class,\"amt\") or contains(@class,\"amount\") or contains(@class,\"price\") or contains(@class,\"content\")]");
        }
        Driver.action.moveToElement(element1).perform();
        String actual = Commons.removeBlankSpace(element1.getText());
        assertThat(actual).isEqualTo(value);
    }


    public static void portalEnterOTP(String otp){
        List<WebElement> elements = Driver.webDriver.findElements(By.xpath("//*[contains(name(),\"otp\")][contains(name(),\"input\")][not(contains(@style,\"display: none;\"))]//input"));
        for (WebElement element : elements) {
            element.sendKeys(otp.substring(elements.indexOf(element)));
        }
    }


    public static void portalSwitchBank(){
        Driver.action.pause(10000).perform();
        Driver.waitTimeOut.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[contains(@src,\"bankstatements\")]")));
    }


    public static void portalSwitchDefault(){
        Driver.webDriver.switchTo().defaultContent();
    }


    public static void portalWaitSeconds(long seconds){
        Driver.action.pause(seconds*1000).perform();
    }


    public static void portalGetRefID(){
        String url = Driver.webDriver.getCurrentUrl();
        String[] path = url.split("/");
        String reference = path[path.length-1];
        System.out.println("Reference ID is "+reference);
    }


    public static void portalEnterCustomerLastName(String field_name){
        String text = String.format("Auto-%s", UUID.randomUUID().toString());
        String application = (String) ScenarioDataStore.get("application");
        WebElement element;
        if(application.equalsIgnoreCase("LOS")){element = Commons.getLabelElement(field_name,"label");}
        else element = Commons.getLabelElement(field_name, "*[name()=\"span\" or name()=\"h2\"or name()=\"label\"]");
        WebElement element1 = Commons.getSiblingElement(element,"input[not(@type=\"hidden\")]");
        element1.clear();
        element1.sendKeys(text);
        Driver.action.doubleClick(element1).perform();
    }




}