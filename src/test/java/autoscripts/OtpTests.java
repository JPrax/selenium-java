package autoscripts;


import driver.Driver;
import example.Commons;
import example.StepImplementLOS;
import example.StepImplementPortal;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class OtpTests {

    @BeforeTest
    public void initializeEnvironment(){
        Commons.preScenario();
    }

    @BeforeMethod
    public void rampup(){
        Driver.initializeDriver();
    }

    @AfterMethod
    public void cooldown(){
        Driver.closeDriver();
    }




    @Test
    public void refinanceCreateApplication(){
        StepImplementPortal.goto86400Page("Refinance");
        StepImplementPortal.enterAddress("190 Neerim Road Carnegie VIC 3163","What's the address?");
        StepImplementPortal.verifyCoreLogicReturn();
        StepImplementPortal.respondToQuestion("No, it's an investment", "Do you live here?");
        StepImplementLOS.losEnterInput("600,000","And what's your current loan balance?");
        StepImplementPortal.portalClickButton("Check my property");
        StepImplementLOS.losEnterInput("50,000","How much more do you want to borrow?");
        StepImplementPortal.portalClickButton("Check my property");
        StepImplementPortal.verifyTextDisplays("Reduce my repayments");
        StepImplementLOS.losToggleCheckbox("Reduce my repayments","on");
        StepImplementLOS.losToggleCheckbox("Better interest rate","on");
        StepImplementLOS.losToggleCheckbox("Not happy with my lender","off");
        StepImplementLOS.losToggleCheckbox("More flexibility e.g. extra repayments, offset, redraw","off");
        StepImplementLOS.losToggleCheckbox("Borrow more money","on");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("OK, now tell us what matters to you about your loan.");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Very worried", "How worried are you about interest rates going up?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Don't mind", "How important is it to be able to redraw?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Not Important", "How important is having an offset account?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("No", "Are you interested in a split loan?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.portalClickButton("Choose my loan options");
        StepImplementPortal.verifyTextDisplays("What loan term would you like?");
        StepImplementLOS.losEnterInput("20","What loan term would you like?");
        StepImplementPortal.respondToQuestion("Principal and interest", "How would you like to repay your loan?");
        StepImplementPortal.respondToQuestion("5", "How many years do you want to set the interest rate of your fixed loan for?");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.portalWaitSeconds(5);
        StepImplementPortal.verifyTextDisplays("Est. monthly repayments²");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("How many people are applying?");
        StepImplementPortal.respondToQuestion("It's just me", "How many people are applying?");
        StepImplementLOS.losEnterInput("Jprax","What's your first name?");
        StepImplementLOS.losEnterInput("0444332211","Mobile number");
        StepImplementPortal.respondToQuestion("Australian citizen", "Are you an Australian citizen or permanent resident?");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("We've sent a security code to your mobile number:");
        StepImplementPortal.portalEnterOTP("864000");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("Here are some things you'll need to have handy:");
    }

    @Test
    public void purchaseCreateApplication(){
        StepImplementPortal.goto86400Page("Purchase");
        StepImplementPortal.enterAddress("190 Neerim Road Carnegie VIC 3163","What's the address?");
        StepImplementPortal.verifyCoreLogicReturn();
        StepImplementPortal.respondToQuestion("No, it's an investment", "Will you live in this property?");
        StepImplementLOS.losEnterInput("1,750,000","How much is the price of the property?");
        StepImplementLOS.losEnterInput("650,000","How much do you want to borrow?");
        StepImplementPortal.portalClickButton("Check my property");
        StepImplementLOS.losEnterInput("580","How much weekly rent do you expect to receive from the property?");
        StepImplementPortal.portalClickButton("Check my property");
        StepImplementPortal.verifyTextDisplays("OK, now tell us what matters to you about your loan.");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Very worried", "How worried are you about interest rates going up?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Don't mind", "How important is it to be able to redraw?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Not Important", "How important is having an offset account?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("No", "Are you interested in a split loan?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.portalClickButton("Choose my loan options");
        StepImplementPortal.verifyTextDisplays("What loan term would you like?");
        StepImplementLOS.losEnterInput("20","What loan term would you like?");
        StepImplementPortal.respondToQuestion("Principal and interest", "How would you like to repay your loan?");
        StepImplementPortal.respondToQuestion("5", "How many years do you want to set the interest rate of your fixed loan for?");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.portalWaitSeconds(5);
        StepImplementPortal.verifyTextDisplays("Est. monthly repayments²");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("How many people are applying?");
        StepImplementPortal.respondToQuestion("It's just me", "How many people are applying?");
        StepImplementLOS.losEnterInput("Jprax","What's your first name?");
        StepImplementLOS.losEnterInput("0444332211","Mobile number");
        StepImplementPortal.respondToQuestion("Australian citizen", "Are you an Australian citizen or permanent resident?");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("We've sent a security code to your mobile number:");
        StepImplementPortal.portalEnterOTP("864000");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("Here are some things you'll need to have handy:");
    }


    @Test
    public void preapprovalCreateApplication(){
        StepImplementPortal.goto86400Page("Preapproval");
        StepImplementPortal.enterAddress("Carnegie VIC 3163","What's the suburb?");
        StepImplementPortal.verifyCoreLogicReturn();
        StepImplementPortal.respondToQuestion("No, it's an investment", "Will you live in this property?");
        StepImplementLOS.losEnterInput("1,750,000","How much do you want to spend on your property?");
        StepImplementLOS.losEnterInput("650,000","How much do you want to borrow?");
        StepImplementPortal.portalClickButton("Check my suburb");
        StepImplementLOS.losEnterInput("580","How much weekly rent do you expect to receive from the property?");
        StepImplementPortal.portalClickButton("Check my suburb");
        StepImplementPortal.verifyTextDisplays("OK, now tell us what matters to you about your loan.");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Very worried", "How worried are you about interest rates going up?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Don't mind", "How important is it to be able to redraw?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("Not Important", "How important is having an offset account?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.respondToQuestion("No", "Are you interested in a split loan?");
        StepImplementPortal.portalWaitSeconds(1);
        StepImplementPortal.portalClickButton("Choose my loan options");
        StepImplementPortal.verifyTextDisplays("What loan term would you like?");
        StepImplementLOS.losEnterInput("20","What loan term would you like?");
        StepImplementPortal.respondToQuestion("Principal and interest", "How would you like to repay your loan?");
        StepImplementPortal.respondToQuestion("5", "How many years do you want to set the interest rate of your fixed loan for?");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.portalWaitSeconds(5);
        StepImplementPortal.verifyTextDisplays("Est. monthly repayments²");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("How many people are applying?");
        StepImplementPortal.respondToQuestion("It's just me", "How many people are applying?");
        StepImplementLOS.losEnterInput("Jprax","What's your first name?");
        StepImplementLOS.losEnterInput("0444332211","Mobile number");
        StepImplementPortal.respondToQuestion("Australian citizen", "Are you an Australian citizen or permanent resident?");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("We've sent a security code to your mobile number:");
        StepImplementPortal.portalEnterOTP("864000");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("Here are some things you'll need to have handy:");
    }

    @Test
    public void refinanceLogin(){
        StepImplementPortal.goto86400Page("Refinance");
        StepImplementPortal.portalClickLink("Log in");
        StepImplementLOS.losEnterInput("+61481000070","What's your mobile number?");
        StepImplementPortal.portalClickButton("Send security code");
        StepImplementPortal.verifyTextDisplays("Please log into the 86 400 app to get your security code.");
        StepImplementPortal.portalEnterOTP("864005");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("Welcome back!");
    }

    @Test
    public void purchaseLogin(){
        StepImplementPortal.goto86400Page("Purchase");
        StepImplementPortal.portalClickLink("Log in");
        StepImplementLOS.losEnterInput("+61481000070","What's your mobile number?");
        StepImplementPortal.portalClickButton("Send security code");
        StepImplementPortal.verifyTextDisplays("Please log into the 86 400 app to get your security code.");
        StepImplementPortal.portalEnterOTP("864005");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("Welcome back!");
    }

    @Test
    public void preapprovalLogin(){
        StepImplementPortal.goto86400Page("Preapproval");
        StepImplementPortal.portalClickLink("Log in");
        StepImplementLOS.losEnterInput("+61481000070","What's your mobile number?");
        StepImplementPortal.portalClickButton("Send security code");
        StepImplementPortal.verifyTextDisplays("Please log into the 86 400 app to get your security code.");
        StepImplementPortal.portalEnterOTP("864005");
        StepImplementPortal.portalClickButton("Next");
        StepImplementPortal.verifyTextDisplays("Welcome back!");
    }

}
