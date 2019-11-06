package ru.Lopatina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAdditionToGroup extends TestBase {

  @BeforeMethod
  public void  ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactAdditionToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    if (!contacts.stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().isPresent()) {
      app.contact().create(new ContactData().withFirstName("Igor").withLastName("Ivanov").withBday(15).withBmonth("March").withByear("1995")
              .withAday(21).withAmonth("June").withAyear("2001"), true);
    }
    ContactData addedContact =
            app.db().contacts().stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().get();
    Groups before = addedContact.getGroups();
    GroupData group = groups.without(addedContact.getGroups()).iterator().next();
    app.contact().addToGroup(addedContact, group);
    Groups after = app.db().contacts().stream().filter((s) -> s.equals(addedContact)).findFirst().get().getGroups();
    assertThat(after, equalTo(before.withAdded(group)));
  }
}
