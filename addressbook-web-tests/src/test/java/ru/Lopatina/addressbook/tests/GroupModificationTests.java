package ru.Lopatina.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.GroupData;
import ru.Lopatina.addressbook.model.TestBase;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() {
    app.getNavigationHelper().gotoGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "tewt3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    //int before = app.getContactHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before.size() - 1);
    app.getGroupHelper().initGroupModification();
    GroupData group = new GroupData(before.get(before.size() - 1).getId(), "test1", "test2", "test3");
    app.getGroupHelper().fillGroupForm(group);
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroupPage();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //int after = app.getContactHelper().getGroupCount();
    Assert.assertEquals(after.size(), before.size());

    before.remove(before.size() - 1);
    before.add(group);
    Comparator<? super GroupData> ById = ((g1, g2) -> Integer.compare(g1.getId(), g2.getId()));
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }
}
