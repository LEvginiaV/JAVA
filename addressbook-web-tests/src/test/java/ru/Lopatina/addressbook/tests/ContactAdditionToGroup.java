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
      app.group().create(new GroupData().withName("test1"));
    }
    app.goTo().homePage();
  }

  @Test
  public void testContactAdditionToGroup() {
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    ContactData addedContact = contacts.stream().filter((s) -> (s.getGroups().size() < groups.size())).findAny().get();
    Groups before = addedContact.getGroups();
    GroupData group = groups.without(addedContact.getGroups()).iterator().next();
    app.contact().addToGroup(addedContact, group);
    Groups after = app.db().contacts().stream().filter((s) -> s.equals(addedContact)).findFirst().get().getGroups();
    assertThat(after, equalTo(before.withAdded(group)));

    /*GroupData modifiedGroup = groups.iterator().next();
    if (modifiedGroup.getContacts().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Gheorghe6").withMiddleName("Mi")
              .withLastName("Smith").withNickName("Sinus").withCompany("Sbis").withPosition("Killer")
              .withCompanyAddress("Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34")
              .withWorkPhone("345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru").withEmail2("eeeee")
              .withEmail3("ieeeeee").withHomepage("localhost").withBday(15).withBmonth("March").withByear("1995")
              .withAday(21).withAmonth("June").withAyear("2001").withHomeAddress("Rostov").withHomePhone2("5-48-54")
              .withNotes("blablabla").inGroup(modifiedGroup), true);
    }
*/
  }
}
