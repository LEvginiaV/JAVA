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
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      GroupData newGroup = app.db().groups().iterator().next();
      app.contact().create(new ContactData().withFirstName("Gheorghe").withLastName("Smith").withCompanyAddress(
              "Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone(
              "345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withBday(15).withBmonth("March").withByear("1995")
              .withAday(21).withAmonth("June").withAyear("2001").inGroup(newGroup), true);
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactDeletionFromGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if (!contacts.stream().filter((s) -> (s.getGroups().size() > 0)).findAny().isPresent()) {
      app.contact().addToGroup(contacts.iterator().next(), groups.iterator().next());
    }
    ContactData removedContact = app.db().contacts().stream().filter((s) -> (s.getGroups().size() > 0)).findAny().get();
    Groups before = removedContact.getGroups();
    GroupData modifiedGroup = before.iterator().next();
    app.contact().deleteFromGroup(modifiedGroup, removedContact);
    Groups after = app.db().contacts().stream().filter((s) -> s.equals(removedContact)).findFirst().get().getGroups();;
    assertThat(after, equalTo(before.without(modifiedGroup)));
  }
}
