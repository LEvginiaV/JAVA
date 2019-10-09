package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData().withFirstName("Gheorghe").withMiddleName("Alan").withLastName("Smith").withNickName(
            "Nicky").withPosition("Tester").withCompany("Kontur").withCompanyAddress("Leninskiy avenu,168").withHomePhone(
                    "7-09-46").withMobilePhone("8-924-345-23-34").withWorkPhone("345-45-35").withFax("234-45-23").withEmail(
                            "Email1@mail.ru").withEmail2("Email2@bk.ru").withEmail3("Email3@gmail.ru").withHomepage(
                                    "vk.com").withBday("12").withBmonth("March").withByear("1995").withAday("11").withAmonth(
                                            "June").withAyear("2001").withGroup("test1").withHomeAdress(
                                                    "SPb, Nevsky avenu").withHomePhone2("345-56-34").withPosition("Fish seller");
    app.contact().create(contact, true);
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId());
    before.add(contact);
    Comparator<? super ContactData> ById = ((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
    after.sort(ById);
    before.sort(ById);
    Assert.assertEquals(after, before);
  }
}
