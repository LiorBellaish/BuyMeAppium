import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HowToSend
{
    public static WebDriver driver;

    @FindBy(id = "il.co.mintapp.buyme:id/nowRadioButton")
    WebElement nowRadioButton;
    @FindBy(css = "il.co.mintapp.buyme:id/giftSendEmail .android.widget.CheckBox")
    WebElement byMail;
    @FindBy(className = "android.widget.EditText")
    WebElement enterEmail;
    @FindBy(id = "il.co.mintapp.buyme:id/goNextButton")
    WebElement next;

    public HowToSend (WebDriver driver) throws Exception {  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }


    public  void checkBoxSelect(WebElement checkBox){
        boolean selected=checkBox.isSelected();
        if(!selected)
            checkBox.click();
    }
}
