import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeScreen
{
    public static WebDriver driver;

    @FindBy(id = "il.co.mintapp.buyme:id/priceEditText")
    WebElement budget;
    @FindBy(id = "il.co.mintapp.buyme:id/purchaseButton")
    WebElement toChoose;
    @FindBy(id="il.co.mintapp.buyme:id/categoryName")
    WebElement mainTollbarTitle;
    @FindBy(id = "il.co.mintapp.buyme:id/main_toolbar_title")
    WebElement aboutTitle;
    @FindBy(id = "il.co.mintapp.buyme:id/titleText")
    WebElement aboutTitleText;
    @FindBy(id = "il.co.mintapp.buyme:id/contentText")
    WebElement aboutContentText;


    public HomeScreen (WebDriver driver) throws Exception {  //constructor
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
}
