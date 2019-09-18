package ru.Lopatina.addressbook.tests;

import org.testng.annotations.*;
import ru.Lopatina.addressbook.model.GroupData;
import ru.Lopatina.addressbook.model.TestBase;

public class GroupCreationTests extends TestBase {

    @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test1", "test2", "test3"));
    app.submitGroupCreation();
    app.returnToGroupPage();
  }

}
