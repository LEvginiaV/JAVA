package ru.Lopatina.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditHelper extends HelperBase {

  public EditHelper(ApplicationManager app) {
    super(app);
  }

  public void ManageUsers() {
    if (isElementPresent(By.tagName("h4"))
            && wd.findElement(By.tagName("h4")).getText().equals("\n" +
            "\tManage Accounts\t")) {
      return;
    }
    //click(By.linkText(" Manage "));
    click(By.cssSelector("li[class='active']"));
    click(By.linkText("Manage Users"));
  }
/*
  public void homePage() {
    if (isElementPresent(By.id("mailtable"))) {
      return;
    }
    click(By.linkText("home"));
  }*/
}
