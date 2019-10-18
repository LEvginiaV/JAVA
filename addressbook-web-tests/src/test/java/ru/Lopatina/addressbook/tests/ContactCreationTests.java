package ru.Lopatina.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.Contacts;
import ru.Lopatina.addressbook.model.TestBase;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    list.add(new Object[] {new ContactData().withFirstName("Gheorghe").withMiddleName("Alan").withLastName("Smith")
            .withNickName("Nicky").withPhoto(new File("src/test/resources/avatar.jpg")).withPosition("Tester")
            .withCompany("Kontur").withCompanyAddress("Leninskiy avenu,168").withHomePhone("7-09-46")
            .withMobilePhone("8-924-345-23-34").withWorkPhone("345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru")
            .withEmail2("Email2@bk.ru").withEmail3("Email3@gmail.ru").withHomepage("vk.com").withBday("12").withBmonth("March")
            .withByear("1995").withAday("11").withAmonth("June").withAyear("2001").withGroup("test1")
            .withHomeAddress("SPb, Nevsky avenu").withHomePhone2("345-56-34").withPosition("Fish seller")});
    list.add(new Object[] {new ContactData().withFirstName("Gheorghe1").withMiddleName("Alan1").withLastName("Smith1")
            .withNickName("Nicky1").withPhoto(new File("src/test/resources/avatar.jpg")).withPosition("Tester1")
            .withCompany("Kontur1").withCompanyAddress("Leninskiy avenu,168").withHomePhone("7-09-46")
            .withMobilePhone("8-924-345-23-34").withWorkPhone("345-45-35").withFax("234-45-23").withEmail("1Email1@mail.ru")
            .withEmail2("1Email2@bk.ru").withEmail3("1Email3@gmail.ru").withHomepage("1vk.com").withBday("12").withBmonth("March")
            .withByear("1995").withAday("11").withAmonth("June").withAyear("2001").withGroup("test1")
            .withHomeAddress("1SPb, Nevsky avenu").withHomePhone2("345-56-34").withPosition("1Fish seller")});
    list.add(new Object[] {new ContactData().withFirstName("Gheorghe2").withMiddleName("Alan2").withLastName("Smith2")
            .withNickName("Nicky2").withPhoto(new File("src/test/resources/avatar.jpg")).withPosition("Tester2")
            .withCompany("Kontur2").withCompanyAddress("Leninskiy avenu,168").withHomePhone("7-09-46")
            .withMobilePhone("8-924-345-23-34").withWorkPhone("345-45-35").withFax("234-45-23").withEmail("Email1@mail.ru")
            .withEmail2("2Email2@bk.ru").withEmail3("2Email3@gmail.ru").withHomepage("2vk.com").withBday("12").withBmonth("March")
            .withByear("1995").withAday("11").withAmonth("June").withAyear("2001").withGroup("test1")
            .withHomeAddress("2SPb, Nevsky avenu").withHomePhone2("345-56-34").withPosition("2Fish seller")});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    Contacts before = app.contact().all();
    app.contact().create(contact, true);
    Contacts after = app.contact().all();
    assertThat(after.size(), equalTo(before.size() + 1));

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/avatar.jpg");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

  @Test(enabled = false)
  public void testBadContactCreation() throws Exception {
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().withFirstName("Gho'").withMiddleName("Alan").withLastName("Smith").withNickName(
            "Nicky").withBday("12").withBmonth("March").withByear("1995").withAday("11").withAmonth(
            "June").withAyear("2001").withGroup("test1");
    app.contact().create(contact, true);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();

    assertThat(after, equalTo(before));
  }
}
