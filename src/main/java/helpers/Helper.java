package helpers;

import org.openqa.selenium.WebElement;

public class Helper {

    public static String getElementValue(WebElement element){
        return element.getCssValue("value");
    }
}
