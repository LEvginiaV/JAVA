package ru.Lopatina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactEmailTests extends TestBase {

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
  public void  testContactPhones() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .collect(Collectors.joining("\n"));
  }

}
