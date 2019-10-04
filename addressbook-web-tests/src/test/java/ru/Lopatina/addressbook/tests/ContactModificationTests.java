package ru.Lopatina.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Gheorghe", "Alan", "Smith", "Nicky", "Tester", "Kontur", "Leninskiy avenu,168", "7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23", "Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com", "12", "March", "1995", "11", "June", "2001", "test1", "SPb, Nevsky avenu", "345-56-34", "Fish seller"), true);
    }
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size() - 1);
    app.getContactHelper().initContactModification(before.size() - 1);
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Gheorghe6", "Alan", "Smith5", "Nicky", "Tester", "Kontur", "Leninskiy avenu,168", "7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23", "Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com", "12", "March", "1995", "11", "June", "2001", null, "SPb, Nevsky avenu", "345-56-34", "Fish seller");
    app.getContactHelper().fillContactForms(contact, false);
    //app.getContactHelper().chooseAvatar("\\img\\i380664.jpg");
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> ById = ((o1, o2) -> Integer.compare(o1.getId(), o2.getId()));
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before,after);
  }
}
