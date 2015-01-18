package pro.tmedia.web;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assume.assumeTrue;

/**
 * Created by Timofey Stanislavovich Ivaykin, M.Eng. on 1/18/15.
 * Techno Media LLC, 2015
 * All copyrights are reserved.
 */

@RunWith(JUnit4.class)
public class RequestsModuleTests {

    private WebDriver driver = null;

    private static boolean isSupportedPlatform() {
        Platform current = Platform.getCurrent();
        return Platform.MAC.is(current) || Platform.WINDOWS.is(current);
    }

    @Before
    public void createDriver() {
        assumeTrue(isSupportedPlatform());
        driver = new SafariDriver();
    }

    @After
    public void quitDriver() {
        driver.quit();
    }


    @Test
    public void shouldOpenAuthorisationPage() {
        driver.get("http://localhost:8080");
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.titleIs("Авторизация"));
    }

    /*
    Заявки доделать:
        Поля формы заявки:
            Добавить в базу и в поля на клиенте номер ячейки:

                Сделать автоматический выбор ячейки от 21 до 60
                    (не по умолчанию, а на лету при открытиии формы
                    создания заявки, чтобы каждый раз была другая,
                    не занятая (на основе данных с сервера))
     */
    @Test
    public void shouldProvideAutomaticSlotId() {
        enterRequestsModule();

        // Нажимаем кнопку "Добавить"
        waitToShow(By.xpath("//*[contains(text(), 'Добавить')]"));
        driver.findElement(By.xpath("//*[contains(text(), 'Добавить')]")).click();
        // Проверяем, открывается ли окошко class="ui-dialog-title" "Добавить"
        //waitToShow(By.cssSelector(".ui-dialog-title[display=\"block\"]"));
        //assert driver.findElement(By.className("ui-dialog-title:visible")).getText().equals("Добавить");
        //new WebDriverWait(driver, 3)
        //        .until(ExpectedConditions.visibilityOf("Авторизация"));

        // Проверяем, содержит ли name="slot_id" значение от 21 до 60
        waitToShow(By.name("slot_id"));
        WebElement slotElement = driver.findElement(By.name("slot_id"));
        String slot_id_text = "";
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end && slot_id_text.length() <= 0) {
            slot_id_text = slotElement.getAttribute("value");
        }

        int slot_id = Integer.parseInt(slot_id_text);
        Assert.assertTrue("slot_id is out of range: " + slot_id, 21 <= slot_id && slot_id <= 60);
    }

    @Test
    public void shouldEnterRequestsModule() {
        enterRequestsModule();
    }

    private void enterRequestsModule() {
        driver.get("http://localhost:8080");

        // Sleep until the div we want is visible or 5 seconds is over

        waitToShow(By.name("username"));

        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("12345678");
        driver.findElement(By.name("submit")).click();

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.titleIs("Домашняя страница"));
    }

    private void waitToShow(By var1) {
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            WebElement usernameField = null;
            try {
                usernameField = driver.findElement(var1);
            } catch (NotFoundException e) {
            }
            // If results have been returned, the results are displayed in a drop down.
            if (null != usernameField && usernameField.isDisplayed()) {
                return;
            }
        }
        // Элемент так и не появился за отведенное время
        assert false;
    }
}