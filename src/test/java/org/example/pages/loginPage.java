package org.example.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.example.utilities.ConfigurationReader;

import java.net.MalformedURLException;

public class loginPage extends BasePage{
    public loginPage() throws MalformedURLException {
    }

    @AndroidFindBy(id = "nameField")
    @iOSXCUITFindBy(accessibility = "")
    public MobileElement EnterName;

    @AndroidFindBy(id = "btnLetsShop")
    @iOSXCUITFindBy(iOSNsPredicate = "")
    public MobileElement LetsGo;


    public void login()
    {
        type(EnterName, ConfigurationReader.get("username"),"users enter their names");
        click(LetsGo,"users click on letsGo button");
    }

}
