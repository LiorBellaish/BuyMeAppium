import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;
import javafx.scene.input.KeyCode;
import org.junit.*;
//selenium
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.w3c.dom.Document;

//other
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.android.nativekey.AndroidKey.B;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.Dimension;
import java.time.Duration;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Tests {
    @Rule
    public TestName name = new TestName();
    TouchAction action = new TouchAction(driver);
    PointOption pointToClickOption = new PointOption();

    private static AndroidDriver<MobileElement> driver;
    General general = new General(driver);
    Login login = new Login(driver);
    HomeScreen homeScreen = new HomeScreen(driver);
    SenderReceiver senderReceiver = new SenderReceiver(driver);
    HowToSend howToSend = new HowToSend(driver);

    private static ExtentReports extent;
    private static ExtentTest liorTests;
    String imagePath = "C:\\Users\\טניה וליאור\\IdeaProjects\\BuyMeAppium\\screenshots";

    public Tests() throws Exception {
    }


    @BeforeClass
    public static void setUp() throws Exception {
        extent = new ExtentReports(readFromFile("reportPath"));   //report path
        extent.loadConfig(new File(readFromFile("loadConfig")));//report config
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Device");
        capabilities.setCapability(readFromFile("appPackage1"), readFromFile("appPackage2"));
        capabilities.setCapability(readFromFile("appActivity1"), readFromFile("appActivity2"));
        capabilities.setCapability("newCommandTimeout", 120);
        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub/"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void afterClass() {
        driver.closeApp();
        extent.flush();
    }

    @Before
    public void before() {
        startTest();
        System.out.println("start test " + name.getMethodName());
    }

    @After
    public void after() {
        endTest();
        System.out.println("end test " + name.getMethodName());
    }

    @Test
    public void test01_intro() throws InterruptedException {
        loginApp();
        Thread.sleep(2000);
        String currentActivity = driver.currentActivity();    //get current activity

        try {
            Assert.assertEquals(".activities.gifts.MainActivity", currentActivity);    //check mail to be correct
            printScreen();
            liorTests.log(LogStatus.PASS, "succeed to login!");
        } catch (Exception e) {
            printScreen();
            liorTests.log(LogStatus.FAIL, "failed to login! " + e);
        }
        Thread.sleep(1500);
    }

    @Test
    public void test02_homeScreen() throws Exception {
        pointToClickOption.withCoordinates(0, 1101);
        action.press(pointToClickOption).release().perform();   // choose fashion gift card
        Thread.sleep(1500);
        pointToClickOption.withCoordinates(28, 1360);
        action.press(pointToClickOption).release().perform();   // choose Renuar gift card
        homeScreen.budget.click();
        budgetEdit(readFromFile("budget"));
        homeScreen.toChoose.click();
        Thread.sleep(1500);
        String receiverActivity = driver.currentActivity();
        try {
            Assert.assertEquals(".activities.purchase.PurchaseFlowActivity", receiverActivity);
            liorTests.log(LogStatus.PASS, "succeed to choose business and busget!");
            printScreen();
        } catch (AssertionError a) {
            liorTests.log(LogStatus.FAIL, "failed to choose business and busget!");
            printScreen();
        }
    }

    @Test
    public void test03_senderReceiver() throws Exception {
        general.radioButtonSelect(senderReceiver.toSomeone);
        String currentActivity = driver.currentActivity();
        senderReceiver.receiver.clear();
        senderReceiver.receiver.sendKeys(readFromFile("receiver"));
        senderReceiver.blessing.clear();
        senderReceiver.blessing.sendKeys(readFromFile("blessing"));
        senderReceiver.sender.clear();
        senderReceiver.sender.sendKeys(readFromFile("sender"));
        driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()."
                + "scrollable(true)).scrollIntoView(new UiSelector().resourceId(\"il.co.mintapp.buyme:id/goNextButton\"))"));
        printScreen();

        try {
            senderReceiver.next.click();
            Thread.sleep(1500);
            Assert.assertTrue(driver.findElement(By.id("il.co.mintapp.buyme:id/checkContainer")).isDisplayed());
            liorTests.log(LogStatus.PASS, "succeed to enter receiver and sender!");
            printScreen();
        } catch (AssertionError a) {
            liorTests.log(LogStatus.FAIL, "failed to enter receiver and sender!");
            printScreen();
        }
    }

    @Test
    public void test04_howToSend() throws Exception {
        general.radioButtonSelect(howToSend.nowRadioButton);
        pointToClickOption.withCoordinates(982, 1489);
        action.press(pointToClickOption).release().perform();   //by mail
        howToSend.enterEmail.sendKeys(readFromFile("email"));
        printScreen();
        howToSend.next.click();
        try {
            Thread.sleep(1500);
            Assert.assertTrue(driver.findElement(By.id("il.co.mintapp.buyme:id/paymentTitle")).isDisplayed());
            liorTests.log(LogStatus.PASS, "succeed to go to next page!");
            printScreen();
        } catch (AssertionError a) {
            liorTests.log(LogStatus.FAIL, "failed to go to next page!");
            printScreen();
        }
    }

    @Test
    public void test05_swipingToCategory() throws InterruptedException {
        driver.resetApp();
        loginApp();
        Thread.sleep(2000);
        printScreen();
        verticalSwipeByPercentages(0.5, 0.4, 0.5);
        printScreen();
        pointToClickOption.withCoordinates(0, 1950);
        Thread.sleep(1000);
        action.press(pointToClickOption).release().perform();   // choose category
        try {
            Assert.assertEquals("גיפט קארד למלונות יוקרה", homeScreen.mainTollbarTitle.getText());
            liorTests.log(LogStatus.PASS, "succeed to swiping to wanted category!");
            printScreen();
        } catch (AssertionError a) {
            liorTests.log(LogStatus.FAIL, "failed to swiping to wanted category!");
            printScreen();
        }
    }

    @Test
    public void test07_aboutBuyme() throws InterruptedException {
        driver.resetApp();
        loginApp();
        Thread.sleep(1500);
        pointToClickOption.withCoordinates(2, 2127); //go to personal zone
        action.press(pointToClickOption).release().perform();

        pointToClickOption.withCoordinates(2, 938); //go to about buyme
        action.press(pointToClickOption).release().perform();

        try {
            Assert.assertEquals("על BUYME", homeScreen.aboutTitle.getText());
            liorTests.log(LogStatus.PASS, "succeed to go to 'about buyme' page.");
            liorTests.log(LogStatus.INFO, homeScreen.aboutTitleText.getText());
            liorTests.log(LogStatus.INFO, homeScreen.aboutContentText.getText());
            printScreen();
        } catch (AssertionError a) {
            liorTests.log(LogStatus.FAIL, "failed to go to 'about buyme' page!");
            printScreen();
        }
    }

    private void startTest() {
        liorTests = extent.startTest(name.getMethodName());
        liorTests.log(LogStatus.INFO, name.getMethodName() + ": start test");
    }

    private void endTest() {
        extent.endTest(liorTests);
        liorTests.log(LogStatus.INFO, name.getMethodName() + ": end test");
    }

    private void printScreen() {
        liorTests.log(LogStatus.INFO, "details", liorTests.addScreenCapture(general.takeScreenShot(imagePath + "\\" + System.currentTimeMillis(), driver)));
    }

    private static String readFromFile(String keyData) throws Exception {
        File xmlFile = new File("C:\\Users\\טניה וליאור\\IdeaProjects\\BuyMeAppium\\buyme.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
        Document doc = dbBuilder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyData).item(0).getTextContent();
    }

    private void budgetEdit(String budget) throws Exception {
        String num = readFromFile("budget");
        for (int i = 0; i < num.length(); i++) {
            String a = num.substring(i, i + 1);
            switch (a) {
                case "0":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_0));
                    break;
                case "1":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_1));
                    break;
                case "2":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_2));
                    break;
                case "3":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_3));
                    break;
                case "4":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_4));
                    break;
                case "5":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_5));
                    break;
                case "6":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_6));
                    break;
                case "7":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_7));
                    break;
                case "8":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_8));
                    break;
                case "9":
                    driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_9));
                    break;
            }
        }
    }

    private void loginApp() throws InterruptedException {
        Thread.sleep(1500);
        pointToClickOption.withCoordinates(10, 2148);
        action.press(pointToClickOption).release().perform();   //registration
        Thread.sleep(1500);
        pointToClickOption.withCoordinates(277, 1801);
        action.press(pointToClickOption).release().perform();   //with google
        Thread.sleep(3000);
        login.myGmail.click();
    }

    private void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);
        new TouchAction(driver)
                .press(point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(anchor, endPoint))
                .release().perform();
    }

}
