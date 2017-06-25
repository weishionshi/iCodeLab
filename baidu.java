import java.util.List;
import java.util.Map;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class baidu {
    private Map<String, String> data;
    private WebDriver driver;
    private int timeout = 15;

    @FindBy(id = "jgwab")
    @CacheLookup
    private WebElement 11000002000001;

    @FindBy(css = "a[href='http://ir.baidu.com']")
    @CacheLookup
    private WebElement aboutBaidu;

    @FindBy(name = "tj_trhao123")
    @CacheLookup
    private WebElement hao123;

    @FindBy(id = "kw")
    @CacheLookup
    private WebElement metaHttpequivrefreshContent0Urlbaidu;

    private final String pageLoadedText = "";

    private final String pageUrl = "/";

    public baidu() {
    }

    public baidu(WebDriver driver) {
        this();
        this.driver = driver;
    }

    public baidu(WebDriver driver, Map<String, String> data) {
        this(driver);
        this.data = data;
    }

    public baidu(WebDriver driver, Map<String, String> data, int timeout) {
        this(driver, data);
        this.timeout = timeout;
    }

    /**
     * Click on About Baidu Link.
     *
     * @return the baidu class instance.
     */
    public baidu clickAboutBaiduLink() {
        aboutBaidu.click();
        return this;
    }

    /**
     * Click on Hao123 Link.
     *
     * @return the baidu class instance.
     */
    public baidu clickHao123Link() {
        hao123.click();
        return this;
    }

    /**
     * Click on 11000002000001 Link.
     *
     * @return the baidu class instance.
     */
    public baidu clickLink11000002000001() {
        11000002000001.click();
        return this;
    }

    /**
     * Fill every fields in the page.
     *
     * @return the baidu class instance.
     */
    public baidu fill() {
        setMetaHttpequivrefreshContent0UrlbaiduTextField();
        return this;
    }

    /**
     * Fill every fields in the page and submit it to target page.
     *
     * @return the baidu class instance.
     */
    public baidu fillAndSubmit() {
        fill();
        return submit();
    }

    /**
     * Set default value to Meta Httpequivrefresh Content0 Urlbaidu Text field.
     *
     * @return the baidu class instance.
     */
    public baidu setMetaHttpequivrefreshContent0UrlbaiduTextField() {
        return setMetaHttpequivrefreshContent0UrlbaiduTextField(data.get("META_HTTPEQUIVREFRESH_CONTENT0_URLBAIDU"));
    }

    /**
     * Set value to Meta Httpequivrefresh Content0 Urlbaidu Text field.
     *
     * @return the baidu class instance.
     */
    public baidu setMetaHttpequivrefreshContent0UrlbaiduTextField(String metaHttpequivrefreshContent0UrlbaiduValue) {
        metaHttpequivrefreshContent0Urlbaidu.sendKeys(metaHttpequivrefreshContent0UrlbaiduValue);
        return this;
    }

    /**
     * Submit the form to target page.
     *
     * @return the baidu class instance.
     */
    public baidu submit() {
        clickButton();
        return this;
    }

    /**
     * Verify that the page loaded completely.
     *
     * @return the baidu class instance.
     */
    public baidu verifyPageLoaded() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getPageSource().contains(pageLoadedText);
            }
        });
        return this;
    }

    /**
     * Verify that current page URL matches the expected URL.
     *
     * @return the baidu class instance.
     */
    public baidu verifyPageUrl() {
        (new WebDriverWait(driver, timeout)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getCurrentUrl().contains(pageUrl);
            }
        });
        return this;
    }
}
