package ru.Lopatina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.Contacts;
import ru.Lopatina.addressbook.model.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void  ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Gheorghe").withLastName("Smith").withCompanyAddress(
              "Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone(
                      "345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withGroup("test1"), true);
    }
  }

  @Test
  public void testContactModification() {
    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Gheorghe6").withMiddleName("Mi")
            .withLastName("Smith").withNickName("Sinus").withCompany("Sbis").withPosition("Killer")
            .withCompanyAddress("Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34")
            .withWorkPhone("345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withEmail2("eeeee")
            .withEmail3("ieeeeee").withHomepage("localhost").withBday(15).withBmonth("March").withByear("1995")
            .withAday(21).withAmonth("June").withAyear("2001").withHomeAddress("Rostov").withHomePhone2("5-48-54")
            .withNotes("blablabla");
    app.contact().modify(contact);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
