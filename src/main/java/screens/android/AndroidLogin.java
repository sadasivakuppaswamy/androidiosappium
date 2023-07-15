package screens.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import screens.BaseScreen;

public class AndroidLogin extends BaseScreen {
    public AndroidLogin(WebDriver driver) {
        super(driver);
        // TODO Auto-generated constructor stub
    }


    // Locators on the login screen
    @FindBy(xpath ="//android.widget.TextView[@text='Sign in with your SlideShare account']")
    public WebElement loginViaSlideShare ;
    @FindBy(xpath = "//android.widget.RelativeLayout//android.widget.EditText")
    public WebElement userName;
    @FindBy(xpath = "//android.widget.RelativeLayout//android.widget.EditText[2]")
    public WebElement password ;
    @FindBy(xpath ="//android.widget.RelativeLayout//android.widget.Button")
    public WebElement signInButton;
    @FindBy(id ="net.slideshare.mobile:id/get_started_button")
    public WebElement startedButton;

    @FindBy(id ="net.slideshare.mobile:id/action_search")
    public WebElement searchIcon;
    @FindBy(id ="net.slideshare.mobile:id/title")
    public WebElement titleBar;
    @FindBy(id ="android:id/alertTitle")
    public WebElement alertHeader;
    @FindBy(id ="android:id/button1")
    public WebElement alertOkButton ;
    @FindBy(id ="net.slideshare.mobile:id/error")
    public WebElement loginError ;
    public void loginToApp(String userLoginName,String passWord) throws InterruptedException {
        if(isElementPresent(alertHeader))
            alertOkButton.click();

        if(isElementPresent(loginViaSlideShare))
            loginViaSlideShare.click();
        waitForVisibility(userName);
        userName.sendKeys(userLoginName);
        password.sendKeys(passWord);
        signInButton.click();



    }
}
