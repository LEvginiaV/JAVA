package ru.Lopatina.addressbook.tests;

import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.Date;
import ru.Lopatina.addressbook.model.TestBase;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillNameForms("Gheorghe", "Alan", "Smith", "Nicky");
        app.getContactHelper().chooseAvatar("\\img\\i380664.jpg");
        app.getContactHelper().fillAboutWork("Tester", "Kontur", "Leninskiy avenu,168");
        app.getContactHelper().fillPhoneNumbers("7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23");
        app.getContactHelper().fillWebContacts("Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com");
        app.getContactHelper().fillDates(new Date("12", "March", "1995"), new Date("11", "June", "2001"));
        app.getContactHelper().fillHomeContacts("SPb, Nevsky avenu", "345-56-34");
        app.getContactHelper().fillNotes("Fish seller");
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
