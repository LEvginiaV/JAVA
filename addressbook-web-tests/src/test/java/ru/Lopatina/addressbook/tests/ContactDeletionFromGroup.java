package ru.Lopatina.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.*;

import java.util.Objects;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionFromGroup extends TestBase {

  @BeforeMethod
  public void  ensurePreconditions() {
    app.goTo().homePage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testContactDeletionFromGroup() {
    Groups groups = app.db().groups();
    GroupData modifiedGroup = groups.iterator().next();
    if (modifiedGroup.getContacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Gheorghe6").withMiddleName("Mi")
              .withLastName("Smith").withNickName("Sinus").withCompany("Sbis").withPosition("Killer")
              .withCompanyAddress("Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34")
              .withWorkPhone("345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withEmail2("eeeee")
              .withEmail3("ieeeeee").withHomepage("localhost").withBday(15).withBmonth("March").withByear("1995")
              .withAday(21).withAmonth("June").withAyear("2001").withHomeAddress("Rostov").withHomePhone2("5-48-54")
              .withNotes("blablabla").inGroup(modifiedGroup), true);
    }
    GroupData modifiedGroup2 = app.db().groups().stream().filter((s) -> s.equals(modifiedGroup)).findFirst().get();
    Contacts before = modifiedGroup2.getContacts();
    ContactData deletedContact = modifiedGroup2.getContacts().iterator().next();
    app.contact().deleteFromGroup(modifiedGroup2, deletedContact);

    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.db().groups().stream().filter((s) -> s.equals(modifiedGroup)).findFirst().get().getContacts();;
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
