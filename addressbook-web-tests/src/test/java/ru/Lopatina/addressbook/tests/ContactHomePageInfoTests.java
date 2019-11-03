package ru.Lopatina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class ContactHomePageInfoTests extends TestBase {

  @BeforeMethod
  public void  ensurePreconditions() {
    app.goTo().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData().withFirstName("Gheorghe").withLastName("Smith").withCompanyAddress(
              "Leninskiy avenu,168").withHomePhone("7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone(
              "345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru"), true);
    }
  }

  @Test
  public void  testContactPhones() {
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
    assertThat(contact.getCompanyAddress(), equalTo(contactInfoFromEditForm.getCompanyAddress()));
  }

  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getHomePhone2())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactHomePageInfoTests::cleanedFormat)
            .collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
            .stream().filter((s) -> ! s.equals(""))
            .map(ContactHomePageInfoTests::cleaned)
            .collect(Collectors.joining("\n"));
  }

  public static String cleanedFormat(String phone) {
    return phone.replaceAll("\\s","")
            .replaceAll("[-()]","");
  }

  public static String cleaned(String address) {
    return address.replaceAll("\\s+"," ").trim();
  }

}
