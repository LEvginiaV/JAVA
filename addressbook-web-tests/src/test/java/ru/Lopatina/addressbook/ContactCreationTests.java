package ru.Lopatina.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ContactCreationTests {
  private WebDriver wd;

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

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillNameForms("Gheorghe", "Alan", "Smith", "Nicky");
    chooseAvatar("\\img\\i380664.jpg");
    fillAboutWork("Tester", "Kontur", "Leninskiy avenu,168");
    fillPhoneNumbers("7-09-46", "8-924-345-23-34", "345-45-35", "234-45-23");
    fillWebContacts("Email1@mail.ru", "Email2@bk.ru", "Email3@gmail.ru", "vk.com");
    fillDates(new Date("12", "March", "1995"), new Date("11", "June", "2001"));
    chooseGroup("test1");
    fillHomeContacts("SPb, Nevsky avenu", "345-56-34");
    fillNotes("Fish seller");
    submitContactCreation();
    returnToHomePage();
  }

  private void returnToHomePage() {
    wd.findElement(By.linkText("home page")).click();
  }

  private void submitContactCreation() {
    wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
  }

  private void fillNotes(String Notes) {
    wd.findElement(By.name("notes")).sendKeys(Notes);
  }

  private void fillHomeContacts(String homeAdress, String homePhone) {
    wd.findElement(By.name("address2")).sendKeys(homeAdress);
    wd.findElement(By.name("phone2")).sendKeys(homePhone);
  }

  private void chooseGroup(String groupTitle) {
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupTitle);
  }

  private void fillDates(Date birthDate, Date anniversaryDate) {
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(birthDate.getDay());
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(birthDate.getMonth());
    wd.findElement(By.name("byear")).sendKeys(birthDate.getYear());
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(anniversaryDate.getDay());
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(anniversaryDate.getMonth());
    wd.findElement(By.name("ayear")).sendKeys(anniversaryDate.getYear());
  }

  private void fillWebContacts(String email, String email2, String email3, String homepage) {
    wd.findElement(By.name("email")).sendKeys(email);
    wd.findElement(By.name("email2")).sendKeys(email2);
    wd.findElement(By.name("email3")).sendKeys(email3);
    wd.findElement(By.name("homepage")).sendKeys(homepage);
  }

  private void fillPhoneNumbers(String homePhone, String mobilePhone, String workPhone, String fax) {
    wd.findElement(By.name("home")).sendKeys(homePhone);
    wd.findElement(By.name("mobile")).sendKeys(mobilePhone);
    wd.findElement(By.name("work")).sendKeys(workPhone);
    wd.findElement(By.name("fax")).sendKeys(fax);
  }

  private void fillAboutWork(String position, String company, String companyAddress) {
    wd.findElement(By.name("title")).sendKeys(position);
    wd.findElement(By.name("company")).sendKeys(company);
    wd.findElement(By.name("address")).sendKeys(companyAddress);
  }

  private void chooseAvatar(String img) {
    String dir = new String(System.getProperty("user.dir"));
    System.out.println(dir);
    wd.findElement(By.name("photo")).sendKeys(dir+img);
  }

  private void fillNameForms(String firstName, String middleName, String lastName, String nickName) {
    wd.findElement(By.name("firstname")).sendKeys(firstName);
    wd.findElement(By.name("middlename")).sendKeys(middleName);
    wd.findElement(By.name("lastname")).sendKeys(lastName);
    wd.findElement(By.name("nickname")).sendKeys(nickName);
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() throws Exception {
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

}
