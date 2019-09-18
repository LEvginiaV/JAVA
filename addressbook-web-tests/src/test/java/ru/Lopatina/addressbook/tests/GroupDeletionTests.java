package ru.Lopatina.addressbook.tests;

import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.TestBase;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.gotoGroupPage();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroupPage();
  }

}
