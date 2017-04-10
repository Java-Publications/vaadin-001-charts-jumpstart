package junit.org.rapidpm.vaadin.jumpstart.gui.screens.login;

import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.HorizontalLayoutElement;
import com.vaadin.testbench.elements.NotificationElement;
import junit.org.rapidpm.vaadin.jumpstart.gui.BaseMicroserviceTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.rapidpm.vaadin.jumpstart.gui.screens.login.LoginScreenCustom;
import org.rapidpm.vaadin.jumpstart.gui.uilogic.api.Languages;

import static org.junit.Assert.assertEquals;

/**
 * Created by svenruppert on 07.04.17.
 */
public class LoginScreenCustomTest extends BaseMicroserviceTest {


  // not working on MainLayout so far
  protected WebElement mainLayout() {
    return $(HorizontalLayoutElement.class).id(LoginScreenCustom.LOGIN_SCREEN);
  }

  @Test
  public void test001() throws Exception {

    getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("admin");

    ComboBoxElement comboBoxElement = $(ComboBoxElement.class).id(LoginScreenCustom.LANGUAGE_COMBO);
    comboBoxElement.openPopup();
    comboBoxElement.sendKeys(Keys.ARROW_DOWN);
    comboBoxElement.sendKeys(Keys.ENTER);
    saveScreenshot("klicked_001");

    Assert.assertEquals(comboBoxElement.getValue(), resolve("login.language.en"));

    getElement(LoginScreenCustom.LOGIN_BUTTON).click();

    final WebElement menubar = mainMenue();
    Assert.assertNotNull(menubar);
    Assert.assertTrue(menubar.isDisplayed());
  }

  @Test
  public void test002() throws Exception {
//    saveScreenshot("before");
    getElement(LoginScreenCustom.USERNAME_FIELD).sendKeys("admin");
    getElement(LoginScreenCustom.PASSWORD_FIELD).sendKeys("XX");
    getElement(LoginScreenCustom.LOGIN_BUTTON).click();
    saveScreenshot("klicked_001");

    NotificationElement notification = $(NotificationElement.class).first();
    assertEquals(propertyService.resolve("login.failed"), notification.getCaption());
    assertEquals(propertyService.resolve("login.failed.description"), notification.getDescription());
    assertEquals("warning", notification.getType());
  }





}