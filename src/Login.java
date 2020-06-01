import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login
{
    public static WebDriver driver;

    @FindBy(id = "com.google.android.gms:id/container")
    WebElement myGmail;
    @FindBy(id = "il.co.mintapp.buyme:id/email")
    WebElement personalZoneMail;
    @FindBy(id = "il.co.mintapp.buyme:id/homeTab")
    WebElement main;


    public Login (WebDriver driver) throws Exception {  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

}
