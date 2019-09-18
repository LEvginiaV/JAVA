package ru.Lopatina.addressbook.tests;

import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.Date;
import ru.Lopatina.addressbook.model.TestBase;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContactCreation();
    app.fillNameForms("Gheorghe", "Alan", "Smith", "Nicky");
    app.chooseAvatar("\\img\\i380664.jpg");
    app.fillAboutWork("Tester", "Kontur", "Leninskiy avenu,168");
    app.fillPhoneNumbers("7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23");
    app.fillWebContacts("Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com");
    app.fillDates(new Date("12", "March", "1995"), new Date("11", "June", "2001"));
    app.chooseGroup("test1");
    app.fillHomeContacts("SPb, Nevsky avenu", "345-56-34");
    app.fillNotes("Fish seller");
    app.submitContactCreation();
    app.returnToHomePage();
  }
}
