package testsuite;

import base.driver.MobileWrapper;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import screens.android.AndroidLogin;
import screens.android.DashBoard;

public class LoginTest extends MobileWrapper {

    String userName;
    String password;
    AndroidLogin androidLogin;
    DashBoard dashBoard;
    /**
     * this method instantiate required helpers depending on the platform(android or iOS)
     * @param invokeDriver android or iOS
     */
    @Parameters({"os"})
    @BeforeMethod
    public void instantiateHelpers(String invokeDriver){

        userName = localeConfigProp.getProperty("userName");
        password = localeConfigProp.getProperty("password");

        if (invokeDriver.equalsIgnoreCase("android")){
            androidLogin = new AndroidLogin(driver);
            dashBoard = new DashBoard(driver);
        }
        /*else if (invokeDriver.equalsIgnoreCase("iOS")){
            loginCoreLogic = new IOSLoginCoreLogic(driver);
        }*/
    }
    /**
     * method to verify login to android and iOS app
     * @throws InterruptedException Thrown when a thread is waiting, sleeping,
     * or otherwise occupied, and the thread is interrupted, either before
     *  or during the activity.
     */
    @Test
    public void LoginVerification() throws InterruptedException {
        //Log.info("Running login test");
        androidLogin.loginToApp(userName, password);
        androidLogin.waitForVisibility(androidLogin.startedButton);

        androidLogin.findElement(androidLogin.startedButton).click();
        androidLogin.waitForVisibility(androidLogin.titleBar);
        Assert.assertTrue(androidLogin.findElement(androidLogin.titleBar).isDisplayed());
        dashBoard.logout();
        androidLogin.waitForVisibility(androidLogin.loginViaSlideShare,5000);
        Assert.assertTrue(androidLogin.findElement(androidLogin.loginViaSlideShare).isDisplayed());

    }
    @Test
    public void InValidLoginVerification() throws InterruptedException {
        //Log.info("Running login test");
        androidLogin.loginToApp(userName, "fdfsdfs");
        androidLogin.waitForVisibility(androidLogin.loginError);
        Assert.assertEquals(androidLogin.findElement(androidLogin.loginError).getText(),"Oops. Please check your email or password.");

    }
}
