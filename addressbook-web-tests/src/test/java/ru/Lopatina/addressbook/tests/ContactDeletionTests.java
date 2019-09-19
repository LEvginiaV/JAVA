package ru.Lopatina.addressbook.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.TestBase;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.getContactHelper().selectContact();
        app.getContactHelper().deleteSelectedContact();
        app.wd.switchTo().alert().accept();
    }
}
