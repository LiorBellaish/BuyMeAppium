import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SenderReceiver
{
    public static WebDriver driver;

    @FindBy(id ="il.co.mintapp.buyme:id/someOneElseButton")
    WebElement toSomeone;
    @FindBy(id = "il.co.mintapp.buyme:id/toEditText")
    WebElement receiver;
    @FindBy(id = "il.co.mintapp.buyme:id/blessEditText")
    WebElement blessing;
    @FindBy(id = "il.co.mintapp.buyme:id/userFrom")
    WebElement sender;
    @FindBy(id = "il.co.mintapp.buyme:id/goNextButton")
    WebElement next;

    public SenderReceiver (WebDriver driver) throws Exception {  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
