package screens.android;

import base.driver.MobileWrapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import screens.BaseScreen;

public class DashBoard extends BaseScreen {
    public DashBoard(WebDriver driver) {
        super(driver);
    }
    public By moreOptions = By.xpath("//android.widget.ImageButton[@content-desc='More options']");
    public By settings = By.xpath("//android.widget.TextView[@text='Settings']");
    public By signOut = By.xpath("//android.widget.TextView[@text='Sign Out']");

    public void logout(){
        findElement(moreOptions).click();
        waitForVisibility(settings);
        findElement(settings).click();
        scrollDown(2,500);
        findElement(signOut).click();
    }
}
