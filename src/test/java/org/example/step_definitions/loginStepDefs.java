package org.example.step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.example.pages.loginPage;
import org.openqa.selenium.By;

import java.net.MalformedURLException;

import static org.example.step_definitions.Hooks.driver;
public class loginStepDefs {

    loginPage login = new loginPage();

    public loginStepDefs() throws MalformedURLException {
    }

    @Given("Users login to the app")
    public void EnterYourName() {
    login.login();
    }

}
