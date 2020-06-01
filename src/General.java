import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;

public class General
{
    public static WebDriver driver;
    HomeScreen homeScreen=new HomeScreen(driver);


    public General (WebDriver driver) throws Exception {  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public static String takeScreenShot(String ImagesPath, WebDriver driver) {   //take screenshot
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File screenShotFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagesPath + ".png");
        try {
            FileHandler.copy(screenShotFile, destinationFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ImagesPath + ".png";
    }

    public void radioButtonSelect(WebElement radioButton){
        boolean selected=radioButton.isSelected();
        if(!selected)
            radioButton.click();
    }

}
