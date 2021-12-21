package example;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class Commons {

    public static void preScenario(){
        ScenarioDataStore.put("context","");
        System.setProperty("LOS_BASE_URL","https://lwdevtestlab1.australiaeast.cloudapp.azure.com/LoanworkSparkle/");
        //UAT
        System.setProperty("86400_BASE_URL","https://86400directhomeloan.loanworks.com.au/");
        //Prod
        //System.setProperty("86400_BASE_URL","https://applynow.86400.com.au/");
        System.setProperty("Refinance_URL","Refinance/enquiry/property/");
        System.setProperty("Purchase_URL","Purchase/enquiry/property/");
        System.setProperty("Preapproval_URL","Preapproval/enquiry/property/");
        System.setProperty("BROWSER","CHROME");
        System.setProperty("HEADLESS","N");
    }

    public static WebElement getLabelElement(String field_name, String tag) {
        List<WebElement> elements = Driver.webDriver.findElements(By.xpath(ScenarioDataStore.get("context")+"//"+tag));
        for (WebElement element : elements) {
            try {
                String innerText = element.getAttribute("innerText");
                innerText = Commons.removeBlankSpace(innerText);
                if (innerText.equalsIgnoreCase(field_name)) {
                    if (element.isDisplayed()) {
                        return element;}
                }
            }catch (StaleElementReferenceException e){
                System.out.println(e);}
        }
        return null;
    }

    public static WebElement getLabelElementLike(String field_name, String tag) {
        List<WebElement> elements = Driver.webDriver.findElements(By.xpath(ScenarioDataStore.get("context")+"//"+tag));
        for (WebElement element : elements) {
            String innerText = element.getAttribute("innerText");
            innerText = Commons.removeBlankSpace(innerText);
            if (innerText.contains(field_name)) {
                if(element.isDisplayed()){return element;}
            }
        }
        return null;
    }

    public static WebElement getSiblingElement(WebElement element, String type) {
        try {element = element.findElement(By.xpath("..//"+type));}
        catch (NoSuchElementException e){
            try{element = element.findElement(By.xpath("../..//"+type));}
            catch (NoSuchElementException f){element = element.findElement(By.xpath("../../..//"+type));}
        }
        return element;
    }

    public static String inputHandler(String input) {
        String processed_input = input;
        return processed_input;
    }


    public static WebElement getElementByLocator(String field_name, String location, String type) {
        WebElement element = Driver.webDriver.findElement(By.xpath(ScenarioDataStore.get("context")+"//*[.=\""+field_name+"\"]"));
        return switch (location) {
            case "below" -> Driver.webDriver.findElement(with(By.tagName(type)).below(element));
            case "above" -> Driver.webDriver.findElement(with(By.tagName(type)).above(element));
            case "left" -> Driver.webDriver.findElement(with(By.tagName(type)).toLeftOf(element));
            case "right" -> Driver.webDriver.findElement(with(By.tagName(type)).toRightOf(element));
            case "near" -> Driver.webDriver.findElement(with(By.tagName(type)).near(element, 100));
            default -> throw new IllegalStateException("Unexpected value: " + location);
        };
    }

    public static String removeBlankSpace(String text) {
        return text.replaceAll("&" + "nbsp;", "").replaceAll(String.valueOf((char) 160), "").trim();
    }


}