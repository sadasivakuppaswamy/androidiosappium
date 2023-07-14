package screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screens.BaseScreen;

public class AndroidLogin extends BaseScreen {
    public AndroidLogin(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }


    // Locators on the login screen
    public By loginViaSlideShare = By.xpath("//android.widget.TextView[@text='Sign in with your SlideShare account']");
    public By userName = By.xpath("//android.widget.RelativeLayout//android.widget.EditText");
    public By password = By.xpath("//android.widget.RelativeLayout//android.widget.EditText[2]");
    public By signInButton = By.xpath("//android.widget.RelativeLayout//android.widget.Button");
    public By startedButton = By.id("net.slideshare.mobile:id/get_started_button");


    public By searchIcon = By.id("net.slideshare.mobile:id/action_search");
    public By titleBar = By.id("net.slideshare.mobile:id/title");
    public By alertHeader = By.id("android:id/alertTitle");
    public By alertOkButton =By.id("android:id/button1");
    public By loginError = By.id("net.slideshare.mobile:id/error");
    public void loginToApp(String userLoginName,String passWord) throws InterruptedException {
        if(isElementPresent(alertHeader))
            findElement(alertOkButton).click();

        if(isElementPresent(loginViaSlideShare))
            findElement(loginViaSlideShare).click();
        waitForVisibility(userName);
        findElement(userName).sendKeys(userLoginName);
        findElement(password).sendKeys(passWord);
        findElement(signInButton).click();

        //	verify if "Get Started" button is displayed


        // scroll down twice with each duration of 500 ms
        /*scrollDown(2, 500);
        waitForVisibility(searchIcon);

        // long press search icon
        longPress(searchIcon);*/

        // press back key
        //back();

        // below code will enable airplane mode on the device
        //  setNetworkConnection(true,false,false);

    }
}
