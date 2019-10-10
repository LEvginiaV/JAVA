package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Set;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void  ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Gheorghe").withLastName("Smith").withCompanyAddress(
              "Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone(
                      "345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Set<ContactData> before = app.contact().all();
    ContactData modifyContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifyContact.getId()).withFirstName("Gheorghe6").withLastName("Smith").withCompanyAddress(
            "Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone(
            "345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withBday("12").withBmonth("March").withByear("1995").withAday("11").withAmonth(
            "June").withAyear("2001");
    app.contact().modify(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size());

    before.remove(modifyContact);
    before.add(contact);
    Assert.assertEquals(before,after);
  }

}
