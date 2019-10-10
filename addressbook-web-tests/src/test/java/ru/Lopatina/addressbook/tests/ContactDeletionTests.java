package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Set;

public class ContactDeletionTests extends TestBase {

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
    public void testContactDeletion() {
        Set<ContactData> before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);
      Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(deletedContact);
        Assert.assertEquals(before,after);
    }


}
