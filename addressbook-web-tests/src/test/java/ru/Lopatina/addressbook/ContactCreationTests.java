package ru.Lopatina.addressbook;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillNameForms("Gheorghe", "Alan", "Smith", "Nicky");
    chooseAvatar("\\img\\i380664.jpg");
    fillAboutWork("Tester", "Kontur", "Leninskiy avenu,168");
    fillPhoneNumbers("7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23");
    fillWebContacts("Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com");
    fillDates(new Date("12", "March", "1995"), new Date("11", "June", "2001"));
    chooseGroup("test1");
    fillHomeContacts("SPb, Nevsky avenu", "345-56-34");
    fillNotes("Fish seller");
    submitContactCreation();
    returnToHomePage();
  }
}
