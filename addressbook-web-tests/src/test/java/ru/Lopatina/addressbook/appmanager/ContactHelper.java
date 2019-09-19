package ru.Lopatina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.Lopatina.addressbook.model.Date;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToHomePage() {
      click(By.linkText("home page"));
    }

    public void submitContactCreation() {
      click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillNotes(String Notes) {
      type(By.name("notes"), Notes);
    }

    public void fillHomeContacts(String homeAdress, String homePhone) {
      type(By.name("address2"), homeAdress);
      type(By.name("phone2"), homePhone);
    }

    public void chooseGroup(String groupTitle) {
        select(By.name("new_group"), groupTitle);
    }

    private void select(By locator, String groupTitle) {
        new Select(wd.findElement(locator)).selectByVisibleText(groupTitle);
    }

    public void fillDates(Date birthDate, Date anniversaryDate) {
        select(By.name("bday"), birthDate.getDay());
        select(By.name("bmonth"), birthDate.getMonth());
        type(By.name("byear"), birthDate.getYear());
        select(By.name("aday"), anniversaryDate.getDay());
        select(By.name("amonth"), anniversaryDate.getMonth());
        type(By.name("ayear"), anniversaryDate.getYear());
    }

    public void fillWebContacts(String email, String email2, String email3, String homepage) {
      type(By.name("email"), email);
      type(By.name("email2"), email2);
      type(By.name("email3"), email3);
      type(By.name("homepage"), homepage);
    }

    public void fillPhoneNumbers(String homePhone, String mobilePhone, String workPhone, String fax) {
      type(By.name("home"),homePhone);
      type(By.name("mobile"), mobilePhone);
      type(By.name("work"), workPhone);
      type(By.name("fax"), fax);
    }

    public void fillAboutWork(String position, String company, String companyAddress) {
      type(By.name("title"), position);
      type(By.name("company"), company);
      type(By.name("address"), companyAddress);
    }

    public void chooseAvatar(String img) {
      String dir = new String(System.getProperty("user.dir"));
      System.out.println(dir);
      type(By.name("photo"),dir+img);
    }

    public void fillNameForms(String firstName, String middleName, String lastName, String nickName) {
      type(By.name("firstname"), firstName);
      type(By.name("middlename"), middleName);
      type(By.name("lastname"), lastName);
      type(By.name("nickname"), nickName);
    }

    public void initContactCreation() {
      click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.name("selected[]"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }
}
