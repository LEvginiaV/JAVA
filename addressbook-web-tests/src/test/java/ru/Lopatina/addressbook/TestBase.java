package ru.Lopatina.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {
    protected WebDriver wd;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws Exception {
      wd = new FirefoxDriver();
      wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      wd.get("http://localhost/addressbook/");
      login("admin", "secret");

    }

    private void login(String username, String password) {
      wd.findElement(By.name("user")).sendKeys(username);
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    protected void returnToGroupPage() {
      wd.findElement(By.linkText("group page")).click();
    }

    protected void submitGroupCreation() {
      wd.findElement(By.name("submit")).click();
    }

    protected void fillGroupForm(GroupData groupData) {
      wd.findElement(By.name("group_name")).sendKeys(groupData.getName());
      wd.findElement(By.name("group_header")).sendKeys(groupData.getHeader());
      wd.findElement(By.name("group_footer")).sendKeys(groupData.getFooter());
    }

    protected void initGroupCreation() {
      wd.findElement(By.name("new")).click();
    }

    protected void gotoGroupPage() {
      wd.findElement(By.linkText("groups")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
      wd.findElement(By.linkText("Logout")).click();
      wd.quit();
    }

    private boolean isElementPresent(By by) {
      try {
        wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    protected void deleteSelectedGroups() {
      wd.findElement(By.name("delete")).click();
    }

    protected void selectGroup() {
      wd.findElement(By.name("selected[]")).click();
    }

    protected void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    protected void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    protected void fillNotes(String Notes) {
      wd.findElement(By.name("notes")).sendKeys(Notes);
    }

    protected void fillHomeContacts(String homeAdress, String homePhone) {
      wd.findElement(By.name("address2")).sendKeys(homeAdress);
      wd.findElement(By.name("phone2")).sendKeys(homePhone);
    }

    protected void chooseGroup(String groupTitle) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupTitle);
    }

    protected void fillDates(Date birthDate, Date anniversaryDate) {
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(birthDate.getDay());
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(birthDate.getMonth());
      wd.findElement(By.name("byear")).sendKeys(birthDate.getYear());
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(anniversaryDate.getDay());
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(anniversaryDate.getMonth());
      wd.findElement(By.name("ayear")).sendKeys(anniversaryDate.getYear());
    }

    protected void fillWebContacts(String email, String email2, String email3, String homepage) {
      wd.findElement(By.name("email")).sendKeys(email);
      wd.findElement(By.name("email2")).sendKeys(email2);
      wd.findElement(By.name("email3")).sendKeys(email3);
      wd.findElement(By.name("homepage")).sendKeys(homepage);
    }

    protected void fillPhoneNumbers(String homePhone, String mobilePhone, String workPhone, String fax) {
      wd.findElement(By.name("home")).sendKeys(homePhone);
      wd.findElement(By.name("mobile")).sendKeys(mobilePhone);
      wd.findElement(By.name("work")).sendKeys(workPhone);
      wd.findElement(By.name("fax")).sendKeys(fax);
    }

    protected void fillAboutWork(String position, String company, String companyAddress) {
      wd.findElement(By.name("title")).sendKeys(position);
      wd.findElement(By.name("company")).sendKeys(company);
      wd.findElement(By.name("address")).sendKeys(companyAddress);
    }

    protected void chooseAvatar(String img) {
      String dir = new String(System.getProperty("user.dir"));
      System.out.println(dir);
      wd.findElement(By.name("photo")).sendKeys(dir+img);
    }

    protected void fillNameForms(String firstName, String middleName, String lastName, String nickName) {
      wd.findElement(By.name("firstname")).sendKeys(firstName);
      wd.findElement(By.name("middlename")).sendKeys(middleName);
      wd.findElement(By.name("lastname")).sendKeys(lastName);
      wd.findElement(By.name("nickname")).sendKeys(nickName);
    }

    protected void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }
}
