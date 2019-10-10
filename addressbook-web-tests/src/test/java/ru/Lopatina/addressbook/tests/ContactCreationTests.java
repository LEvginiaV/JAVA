package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Gheorghe").withMiddleName("Alan").withLastName("Smith").withNickName(
            "Nicky").withPosition("Tester").withCompany("Kontur").withCompanyAddress("Leninskiy avenu,168").withHomePhone(
                    "7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone("345-45-35").withFax("234-45-23").withEmail(
                            "Email1@mail.ru").withEmail2("Email2@bk.ru").withEmail3("Email3@gmail.ru").withHomepage(
                                    "vk.com").withBday("12").withBmonth("March").withByear("1995").withAday("11").withAmonth(
                                            "June").withAyear("2001").withGroup("test1").withHomeAdress(
                                                    "SPb, Nevsky avenu").withHomePhone2("345-56-34").withPosition("Fish seller");
    app.contact().create(contact, true);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(after, before);
  }
}
