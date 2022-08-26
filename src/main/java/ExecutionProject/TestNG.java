package ExecutionProject;

import FrameworkProject.ParentsClass;
import FrameworkProject.Setup;
import FrameworkProject.Identifiers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

public class TestNG extends ParentsClass{

    Setup connect = new Setup();
    Setup setup = new Setup();
    Identifiers locators = new Identifiers();
    JSONObject arr;

    @Test(priority = 1)
    public void the_user_is_on_login_page() {
        connect.driver = connect.CreateDriver();
        ClickOnElement(setup, locators.getAccount());
        System.out.println("The user in on login page");
    }

    @Test(priority = 2)
    public void the_user_enter_valid_credentials() {
        arr = setup.readJson();
        SendText(setup,locators.getEmail(),arr.get("email").toString());
        SendText(setup,locators.getPassword(),arr.get("password").toString());
    }

    @Test(priority = 3)
    public void searching_cell_phone() {
        SendText(setup,locators.getSearchTextbox(),"Samsung Galaxy S9 64GB");
        findPrice(setup,locators.getFirstPrice(), "This is the first price:  ");
        ClickOnElement(setup,locators.getFirstResult());
    }

    @Test(priority = 4)
    public void comparing_second_price() {
        findPrice(setup,locators.getSecondPrice(), "This is the second price: ");
        ClickOnElement(setup,locators.getAddCartBtn());
        ClickOnElement(setup,locators.getNotThanks());
    }

    @Test(priority = 5)
    public void comparing_third_price(){
        ClickOnElement(setup,locators.getGoCart());
        findPrice(setup,locators.getThirdPrice(), "This is the third price: ");
        compareText(setup,locators.getTextCart(),"Subtotal (1 producto):");
    }

    @Test(priority = 6)
    public void adding_new_product(){
        SendText(setup,locators.getSearchTextbox(),"Alienware Aw3418DW");
        ClickOnElement(setup,locators.getSpfirstResult());
        ClickOnElement(setup,locators.getAddCartBtn());
        ClickOnElement(setup,locators.getNotThanks());
        ClickOnElement(setup,locators.getGoCart());
        compareText(setup,locators.getTextCart(),"Subtotal (2 productos):");
    }
}
