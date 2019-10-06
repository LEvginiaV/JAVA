package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.GroupData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData("test1", "test2", "tewt3"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.group().list();
    //int before = app.getContactHelper().getGroupCount();
    int index = before.size() - 1;
    app.group().delete(index);
    List<GroupData> after = app.group().list();
    //int after = app.getContactHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
