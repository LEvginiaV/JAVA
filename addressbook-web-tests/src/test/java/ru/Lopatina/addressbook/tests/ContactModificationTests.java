package ru.Lopatina.addressbook.tests;

import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() {
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForms(
                new ContactData("Gheorghe", "Alan", "Smith", "Nicky", "Tester", "Kontur", "Leninskiy avenu,168", "7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23", "Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com", "12", "March", "1995", "11", "June", "2001", "SPb, Nevsky avenu", "345-56-34", "Fish seller"));
        //app.getContactHelper().chooseAvatar("\\img\\i380664.jpg");
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }
}
