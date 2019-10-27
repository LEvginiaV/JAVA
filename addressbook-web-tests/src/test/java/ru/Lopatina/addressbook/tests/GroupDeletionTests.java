package ru.Lopatina.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.Lopatina.addressbook.model.GroupData;
import ru.Lopatina.addressbook.model.Groups;
import ru.Lopatina.addressbook.model.TestBase;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().GroupPage();
    if (app.db().groups().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Groups before = app.db().groups();
    GroupData deletedGroup = before.iterator().next();
    app.group().delete(deletedGroup);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
    assertThat(after, equalTo(before.without(deletedGroup)));
  }

}
