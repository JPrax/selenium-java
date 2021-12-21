package example;


import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class StepImplementLOS {


    public static void losOpen() {
        Driver.webDriver.get(System.getProperty("LOS_BASE_URL"));
        assertThat(Driver.webDriver.getTitle()).contains("LoanWorks Login Screen");
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//*[contains(text(),\"Welcome, Please login.\")]")));
        assertThat(element.isDisplayed()).isEqualTo(true);
        //Store the Main Window Handle
        ScenarioDataStore.put("main_window", Driver.webDriver.getWindowHandle());
        ScenarioDataStore.put("application", "LOS");
    }

    public static void losEnterUser(String username){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[contains(@name,\"User\")][not(contains(@type,\"hidden\"))]")));
        element.clear();
        element.sendKeys(username);
    }

    public static void losEnterPassword(String username){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[contains(@name,\"Password\")][not(contains(@type,\"hidden\"))]")));
        element.clear();
        element.sendKeys(username);
    }

    public static void losClickLogin(){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[contains(@value,\"Login\")][not(contains(@type,\"hidden\"))]")));
        element.click();
    }

    public static void losVerifyLinkDisplays(String text) {
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.presenceOfElementLocated
                        (By.xpath("//a[contains(text(),\""+text+"\")]")));
        assertThat(element.isDisplayed()).isEqualTo(true);
    }

    public static void losSelectMenu(String menu){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[contains(@class,\"navigation\")][not(contains(@class,\"mobile\"))]//*[@href=\"#\"][contains(text(),\""+menu+"\")]")));
        element.click();
    }


    public static void losSelectSubMenu(String submenu){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//*[contains(@class,\"navigation\")][not(contains(@class,\"mobile\"))]//*[@class=\"sub_menu\"]//a[.=\""+submenu+"\"]")));
        element.click();
    }


    public static void losVerifyModuleTitle(String module_name){
        WebElement element = Driver.waitTimeOut.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[contains(@class,\"heading\")]")));
        String value = element.getText();
        assertThat(value).isEqualTo(module_name);
        Driver.action.pause(4000).perform();
    }



    public static void losVerifyInputValue(String field_name, String value){
        WebElement element = Commons.getLabelElement(field_name,"label");
        WebElement element1 = Commons.getSiblingElement(element,"input");
        String actual = element1.getAttribute("value").trim();
        assertThat(actual).isEqualTo(value);
    }


    public static void losClickEditIntroducers(){
        Driver.action.moveByOffset(1,1).perform();
        WebElement element = Driver.waitTimeOut.until(ExpectedConditions.elementToBeClickable
                (By.xpath("//*[contains(@class,\"block\")][contains(@class,\"collapsable\")]//*[@class=\"title\"][.=\"Introducers\"]")));
        WebElement element1 = Driver.webDriver.findElement(with(By.xpath("//b[contains(text(),\"Edit Introducers\")]")).below(element));
        element1.click();
    }


    public static void losSwitchPopupWindow(){
        //Store the last window handle
        String last_window = Driver.webDriver.getWindowHandle();
        String main_window = (String) ScenarioDataStore.get("main_window");
        ScenarioDataStore.put("last_window", last_window);
        Set<String> allWindowHandles = Driver.webDriver.getWindowHandles();

        for (String ChildWindow : allWindowHandles) {
            if (!last_window.equalsIgnoreCase(ChildWindow) && !main_window.equalsIgnoreCase(ChildWindow)) {
                Driver.webDriver.switchTo().window(ChildWindow);
            }
        }
    }


    public static void losSwitchFrame(String frame_name){
        Driver.webDriver.switchTo().frame(frame_name);
    }

    public static void losClickButton(String button){
        WebElement element = Driver.waitTimeOut.until
                (ExpectedConditions.elementToBeClickable
                        (By.xpath("//input[contains(@value,\""+button+"\")]")));
        element.click();
    }


    public static void losSwitchMainWindow(){
        String main_window = (String) ScenarioDataStore.get("main_window");
        Driver.webDriver.switchTo().window(main_window);
    }


    public static void losVerifyFieldValue(String field_name, String value){
        WebElement element = Commons.getLabelElement(field_name, "label");
        WebElement element1 = Driver.webDriver.findElement(with(By.tagName("td")).toRightOf(element));
        String actual = element1.getAttribute("innerText").trim();
        assertThat(actual).isEqualTo(value);
    }


    public static void losSelectFieldValue(String value, String field_name){
        if (value.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        String application = (String) ScenarioDataStore.get("application");
        WebElement element;
        WebElement element1;
        if(application.equalsIgnoreCase("LOS")) {
            element = Commons.getLabelElement(field_name, "label");
            element1 = Commons.getSiblingElement(element, "select");
            Select select = new Select(element1);
            select.selectByVisibleText(value);
            Driver.action.sendKeys(Keys.TAB).perform();
        }
        else{
            element = Commons.getLabelElement(field_name,"span");
            element1 = Commons.getSiblingElement(element, "*[contains(@class,\"select\")][contains(@class,\"value\")]");
            element1.click();
            Driver.action.pause(500).perform();
            WebElement option = Driver.webDriver.findElement(By.xpath("//*[contains(@class,\"option\")][contains(@class,\"text\")][contains(text(),\""+value+"\")]"));
            option.click();
        }
    }


    public static void losEnterTextArea(String text, String field_name){
        if (text.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement element = Commons.getLabelElement(field_name, "label");
        WebElement element1 = Commons.getSiblingElement(element,"textarea");
        element1.clear();
        element1.sendKeys(text);
    }


    public static void losEnterInput(String text, String field_name){
        if (text.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        String application = (String) ScenarioDataStore.get("application");
        WebElement element;
        if(application.equalsIgnoreCase("LOS")){element = Commons.getLabelElement(field_name,"label");}
        else element = Commons.getLabelElement(field_name, "*[name()=\"span\" or name()=\"h2\"or name()=\"label\"]");
        WebElement element1 = Commons.getSiblingElement(element,"input[not(@type=\"hidden\")]");
        element1.clear();
        element1.sendKeys(text);
        Driver.action.doubleClick(element1).perform();
    }


    public static void losToggleCheckbox(String field_name, String on_off){
        if (on_off.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        String application = (String) ScenarioDataStore.get("application");
        WebElement element;
        WebElement element1;
        if(application.equalsIgnoreCase("LOS")){
            element = Commons.getLabelElement(field_name,"label");
            element1 = Commons.getSiblingElement(element,"input[@type=\"checkbox\"]");}
        else{
            element = Commons.getLabelElement(field_name,"span");
            element1 = Commons.getSiblingElement(element,"div[contains(@class,\"checkbox\")][contains(@class,\"container\")]");}
        boolean wants_on = on_off.equalsIgnoreCase("on");
        if (!element1.isSelected()==wants_on){
            element1.click();}
    }


    public static void losPressKeyDown(){
        Driver.action.sendKeys(Keys.PAGE_DOWN).pause(1000).perform();
    }


    public static void losBrowserAlert(String message){
        String alert = Driver.webDriver.switchTo().alert().getText();
        assertThat(alert).isEqualTo(message);
        Driver.webDriver.switchTo().alert().accept();
    }


    public static void losStoreDealNumber(){
        WebElement element = Driver.webDriver.findElement(By.xpath("//tr//*[contains(text(),\"Dealing Number:\")]/.."));
        String deal_number = element.getAttribute("innerText");
        deal_number = Commons.removeBlankSpace(deal_number).replace("Dealing Number:","").trim();
        ScenarioDataStore.put("deal_number",deal_number);
        System.out.println("Deal Number: "+ ScenarioDataStore.get("deal_number") + " successfully stored.");
    }


    public static void losNavSideBar(String section){
        WebElement element = Driver.webDriver.findElement(By.xpath("//nav//*[contains(text(),\""+section+"\")]"));
        Driver.action.moveToElement(element).pause(500).click(element).perform();
    }


    public static void losVerifySelection(String field_name, String value){
        WebElement element = Commons.getLabelElement(field_name, "label");
        WebElement element1 = Commons.getSiblingElement(element,"select");
        Select select  = new Select(element1);
        String actual = select.getFirstSelectedOption().getText();
        assertThat(actual).isEqualTo(value);
    }


    public static void losInputOnTable(String value, String row_text, String column_number){
        if (value.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement element = Commons.getLabelElement(row_text,"td");
        WebElement element1 = element.findElement(By.xpath("..//td["+column_number+"]/input"));
        element1.clear();
        element1.sendKeys(value);
        Driver.action.click(element1).sendKeys(element1,Keys.TAB).perform();
    }


    public static void losSetContext(String section_name){
        String path = "//*[contains(@class,\"block\")]//*[@class=\"title\"][contains(text(),\""+section_name+"\")]/../../..";
        ScenarioDataStore.put("context",path);
        System.out.println("All succeeding steps should be within the "+section_name+" section of the page.");
    }



    public static void losEnterInputIndexed(String text, String field_name, Integer index){
        if (text.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement element = Commons.getLabelElement(field_name, "label");
        List<WebElement> elements = element.findElements(By.xpath("..//input"));
        if (elements.isEmpty()){elements = element.findElements(By.xpath("../..//input"));}
        if (elements.isEmpty()){elements = element.findElements(By.xpath("../../..//input"));}
        WebElement element1 = elements.get(index - 1);
        element1.clear();
        element1.sendKeys(text);
        Driver.action.click(element1).sendKeys(element1,Keys.TAB).perform();
    }


    public static void losEnterInputNear(String text, String field_name){
        if (text.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement element1 = Commons.getElementByLocator(field_name,"near","input");
        element1.clear();
        element1.sendKeys(text);
        Driver.action.click(element1).sendKeys(element1,Keys.TAB).perform();
    }


    public static void losSelectValueNear(String value, String field_name){
        if (value.isBlank()){
            System.out.println("Input value is blank, step has been skipped.");
            return;}
        WebElement element1 = Commons.getElementByLocator(field_name,"near","select");
        Select select = new Select(element1);
        select.selectByVisibleText(value);
        Driver.action.sendKeys(Keys.TAB).perform();
    }


    public static void losCopyCurrentToMailing(String on_off){
        WebElement element1 = Driver.webDriver.findElement(By.xpath("//*[contains(text(),\"Same as Current\")][@class=\"title\"]/../input[@type=\"checkbox\"]"));
        boolean wants_on = on_off.equalsIgnoreCase("on");
        if (!element1.isSelected()==wants_on){
            element1.click();}
    }


}