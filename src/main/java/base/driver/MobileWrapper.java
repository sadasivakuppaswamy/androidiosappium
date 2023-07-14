package base.driver;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static com.mysql.cj.conf.PropertyKey.logger;
import static io.appium.java_client.service.local.AppiumDriverLocalService.buildService;

public class MobileWrapper {
    public WebDriver driver;
    Properties configFile ;
    protected static Properties lobConfigProp = new Properties();
    protected static Properties localeConfigProp = new Properties();
    protected FileInputStream configFis, lobConfigFis, localeConfigFis;
    public Properties testDataFile;
    private final String CONFIG_FILE_PATH="//config//config.properties";
    protected File file = new File("");
    Properties configProp = new Properties();
    String OS;


    /**
     * this method starts Appium server. Calls startAppiumServer method to start the session depending upon your OS.
     * @throws Exception Unable to start appium server
     */
    @BeforeSuite
    public void invokeAppium() throws Exception
    {
        String OS = System.getProperty("os.name").toLowerCase();

        try{
            startAppiumServer(OS);
            //Log.info("Appium server started successfully");
        }
        catch (Exception e) {
           // Log.logError(getClass().getName(), "startAppium", "Unable to start appium server");
            throw new Exception(e.getMessage());
        }
    }

    /**
     * this method stops Appium server.Calls stopAppiumServer method to
     * stop session depending upon your OS.
     * @throws Exception Unable to stop appium server
     */
    @AfterSuite(alwaysRun = true)
    public void stopAppium() throws Exception {
        try{
            stopAppiumServer(OS);
            //Log.info("Appium server stopped successfully");

        }
        catch (Exception e) {
           // Log.logError(getClass().getName(), "stopAppium", "Unable to stop appium server");
            throw new Exception(e.getMessage());
        }
    }


    /**
     * this method creates the driver depending upon the passed parameter (android or iOS)
     *  and loads the properties files (config and test data properties files).
     * @param os android or iOS
     * @param methodName - name of the method under execution
     * @throws Exception issue while loading properties files or creation of driver.
     */
    @Parameters({"os","env"})
    @BeforeClass
    public  void createDriver(String os,String env, Method methodName) throws Exception{

        propertiesFileLoad(os,env);

        /*File propertiesFile = new File(file.getAbsoluteFile() + "//src//main//java//log4j.properties");
        Logger.configure(propertiesFile.toString());
        Log.info("--------------------------------------");*/



        if (os.equalsIgnoreCase("android")){
            String buildPath = choosebuild(os);
            androidDriver(buildPath, methodName);
            //Log.info("Android driver created");

        }
        else if (os.equalsIgnoreCase("iOS")){
            String buildPath = choosebuild(os);
            iOSDriver(buildPath, methodName);
            //Log.info("iOS driver created");
        }
    }

    private void propertiesFileLoad(String env,String os) throws Exception {
        configFis = new FileInputStream(file.getAbsoluteFile()
                + CONFIG_FILE_PATH);
        configProp .load(configFis);
        localeConfigFis = new FileInputStream(file.getAbsoluteFile()
                + "//src//test//java//testData//" + os + "_" + env  + ".properties");
        localeConfigProp.load(localeConfigFis);
        /*File f = new File(file.getAbsoluteFile() + "//src//main//java//config//" + os
                + "_config.properties");


        if (f.exists() && !f.isDirectory()) {
            lobConfigFis = new FileInputStream(file.getAbsoluteFile()
                    + "/src//main//java//config//" + os + "_config.properties");
            lobConfigProp.load(lobConfigFis);

            *//*String locale = lobConfigProp.getProperty("LOCALE");

            localeConfigFis = new FileInputStream(file.getAbsoluteFile()
                    + "//src//main//java//testData//" + locale + "_" + os  + ".properties");
            localeConfigProp.load(localeConfigFis);*//*
        }
        else {
            throw new Exception("Properties files loading failed ");
        }*/
    }

    public String choosebuild(String invokeDriver){
        String appPath = null;
        if(invokeDriver.equals("android")){
            appPath = configProp.getProperty("AndroidAppPath");
            return appPath;
        }
        else if(invokeDriver.equals("iOS")){
            appPath = configProp.getProperty("iOSAppPath");
            return appPath;
        }

        return appPath;
    }

    /**
     * this method quit the driver after the execution of test(s)
     */
    @AfterClass
    public void teardown(){
       // Log.info("Shutting down driver");
        driver.quit();
    }



    /**
     *  this method creates the android driver
     *  @param buildPath - path to pick the location of the app
     *  @param methodName - name of the method under execution
     * @throws MalformedURLException Thrown to indicate that a malformed URL has occurred.
     */
    public synchronized void androidDriver(String buildPath, Method methodName) throws MalformedURLException{
        File app = new File(buildPath);
		/*DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("deviceName", "Android Emulator");
		capabilities.setCapability("platformName","Android");
		capabilities.setCapability("appPackage", "net.slideshare.mobile");
		capabilities.setCapability("appActivity", "net.slideshare.mobile.ui.SplashActivity");
		capabilities.setCapability("name", methodName.getName());
		capabilities.setCapability("app", app.getAbsolutePath());
		// added "MobileCapabilityType.FULL_RESET" capability to start app in fresh state (logout).
		// Remove it if not required
		capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
		capabilities.setCapability("automationName", "UiAutomator2");
		driver = new AndroidDriver( appiumService.getUrl(), capabilities);*/

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:automationName", "UiAutomator2");
        capabilities.setCapability("appium:platformVersion", "11");
        capabilities.setCapability("appium:deviceName", "Pixel_XL_API_30");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage", "net.slideshare.mobile");
        capabilities.setCapability("appActivity", "net.slideshare.mobile.ui.SplashActivity");
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, true);
        capabilities.setCapability("autoGrantPermissions", true);
        capabilities.setCapability("appium:app", app.getAbsolutePath());//System.getProperty("user.dir") + "/apps/app-debug.apk");
        driver = new    AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

    }

    /**
     *  this method creates the iOS driver
     *  @param buildPath- path to pick the location of the app
     *  @param methodName- name of the method under execution
     * @throws MalformedURLException Thrown to indicate that a malformed URL has occurred.
     */
    public void iOSDriver(String buildPath, Method methodName) throws MalformedURLException {
        File app = new File(buildPath);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName","iOS");
        capabilities.setCapability("platformVersion", "8.2");
        capabilities.setCapability("appiumVersion", "1.3.7");
        capabilities.setCapability("name", methodName.getName());
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"iPhone 5s");
        capabilities.setCapability("app", app.getAbsolutePath());
        driver  = new IOSDriver( appiumService.getUrl(), capabilities);

    }
    private static AppiumDriverLocalService appiumService;
    private static AppiumServiceBuilder builder;
    public void startAppiumServer(String os) throws IOException, InterruptedException {
        if (os.contains("windows")) {
			/* builder = new AppiumServiceBuilder()
					 //.usingDriverExecutable()
					.usingAnyFreePort()
					.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
					.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
			appiumService = builder.build();
			appiumService.start();*/
            //appiumService = AppiumDriverLocalService.buildDefaultService();
            appiumService = buildService(new AppiumServiceBuilder()
                    .withArgument(() -> "--base-path", "/wd/hub")
                    .withArgument(() -> "--use-plugins", "images")
            );
            appiumService.start();

			/*AppiumDriverLocalService service = buildService(new AppiumServiceBuilder()
					.withArgument(() -> "--base-path", "/wd/hub")
					.withIPAddress("127.0.0.1")
                .usingPort(Integer.parseInt("4723"))
					.usingDriverExecutable(new File("C:\\Users\\sadas\\AppData\\Roaming\\npm"))
                .withAppiumJS(new File("C:\\Users\\sadas\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\appium.js"))
                .withLogFile(new File("target/appium.log"))
					.withArgument(GeneralServerFlag.LOG_LEVEL, "error"));
			service.start();*/
        } else if (os.contains("mac os x")) {
            builder = new AppiumServiceBuilder()
                    .usingAnyFreePort()
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        }
    }

/**
 *  this method stops the appium  server.
 * @param os your machine OS (windows/linux/mac).
 * @throws IOException Signals that an I/O exception of some sort has occurred.
 * @throws ExecuteException An exception indicating that the executing a subprocesses failed.
 */
        public void stopAppiumServer(String os) throws ExecuteException, IOException {
            if (appiumService != null) {
                appiumService.stop();
                //Log.info("Appium server stopped");
            } else {
                //Log.error(getClass().getName()+ getClass().getEnclosingMethod().getName()+"Appium server fail to stopped");
            }
        }



    }
