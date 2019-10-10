package ru.Lopatina.addressbook.tests;

import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.Contacts;
import ru.Lopatina.addressbook.model.TestBase;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Gheorghe").withMiddleName("Alan").withLastName("Smith").withNickName(
            "Nicky").withPosition("Tester").withCompany("Kontur").withCompanyAddress("Leninskiy avenu,168").withHomePhone(
                    "7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone("345-45-35").withFax("234-45-23").withEmail(
                            "Email1@mail.ru").withEmail2("Email2@bk.ru").withEmail3("Email3@gmail.ru").withHomepage(
                                    "vk.com").withBday("12").withBmonth("March").withByear("1995").withAday("11").withAmonth(
                                            "June").withAyear("2001").withGroup("test1").withHomeAdress(
                                                    "SPb, Nevsky avenu").withHomePhone2("345-56-34").withPosition("Fish seller");
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}
