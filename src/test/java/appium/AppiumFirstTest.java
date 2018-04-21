package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;


public class AppiumFirstTest {

    private static AppiumDriver<MobileElement> driver;

    @BeforeClass
    public static void init() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("deviceName", "Nexus_5_API_23");
        capabilities.setCapability("platformVersion", "6.0");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appPackage", "com.android.calculator2"); // This is package name of your app (you can get it from apk info app
        capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");
        capabilities.setCapability("avd", "Nexus_5_API_23");

        driver = new AndroidDriver(new URL(" http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @AfterClass
    public static void end_test(){
        driver.quit();
        closeEmulator();

    }


    @Test
    public void test_positive1_addition(){

        MobileElement number5 = driver.findElementById("com.android.calculator2:id/digit_5");
        number5.click();

        MobileElement operation = driver.findElementById("com.android.calculator2:id/op_add");
        operation.click();

        number5.click();

        MobileElement eq = driver.findElementById("com.android.calculator2:id/eq");
        eq.click();

        MobileElement result = driver.findElementById("com.android.calculator2:id/formula");
        assertTrue("Неверный результат", result.getText().equals("10"));
    }

    @Test
    public void test_positive2_multiplication(){

        MobileElement number2 = driver.findElementById("com.android.calculator2:id/digit_2");
        number2.click();

        MobileElement operation = driver.findElementById("com.android.calculator2:id/op_mul");
        operation.click();

        MobileElement number7 = driver.findElementById("com.android.calculator2:id/digit_7");
        number7.click();

        MobileElement eq = driver.findElementById("com.android.calculator2:id/eq");
        eq.click();

        MobileElement result = driver.findElementById("com.android.calculator2:id/formula");
        assertTrue("Неверный результат", result.getText().equals("14"));
    }

    @Test
    public void test_negative1_division(){

        MobileElement number1 = driver.findElementById("com.android.calculator2:id/digit_1");
        number1.click();

        MobileElement operation = driver.findElementById("com.android.calculator2:id/op_div");
        operation.click();

        MobileElement number0 = driver.findElementById("com.android.calculator2:id/digit_0");
        number0.click();

        MobileElement eq = driver.findElementById("com.android.calculator2:id/eq");
        eq.click();

        MobileElement result = driver.findElementById("com.android.calculator2:id/formula");
        assertTrue("Неверный результат: "+ result.getText(), result.getText().equals("∞"));
    }

    @Test
    public void test_negative2_substraction(){

        MobileElement number8 = driver.findElementById("com.android.calculator2:id/digit_8");
        number8.click();

        MobileElement operation = driver.findElementById("com.android.calculator2:id/op_sub");
        operation.click();

        MobileElement number4 = driver.findElementById("com.android.calculator2:id/digit_4");
        number4.click();

        MobileElement eq = driver.findElementById("com.android.calculator2:id/eq");
        eq.click();

        MobileElement result = driver.findElementById("com.android.calculator2:id/formula");
        assertFalse("Неверный результат", result.getText().equals("0"));
    }


    private static void closeEmulator(){
        System.out.println("Closing Emulator");

    }
}
