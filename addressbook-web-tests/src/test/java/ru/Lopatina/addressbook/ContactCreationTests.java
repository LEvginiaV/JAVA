package ru.Lopatina.addressbook;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactCreationTests {
  private WebDriver wd;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    login("admin", "secret");
  }

  private void login(String username, String password) {
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }

  @Test
  public void testContactCreation() throws Exception {
    initContactCreation();
    fillNameForms();
    chooseAvatar();
    fillAboutWork();
    fillPhoneNumbers();
    fillWebContacts();
    fillDates();
    chooseGroup();
    fillHomeContacts();
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

  private void fillHomeContacts() {
    wd.findElement(By.name("address2")).clear();
    wd.findElement(By.name("address2")).sendKeys("SPb, Nevsky avenu");
    wd.findElement(By.name("phone2")).clear();
    wd.findElement(By.name("phone2")).sendKeys("345-56-34");
  }

  private void chooseGroup() {
    wd.findElement(By.name("new_group")).click();
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText("test1");
    wd.findElement(By.name("new_group")).click();
  }

  private void fillDates() {
    wd.findElement(By.name("bday")).click();
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("12");
    wd.findElement(By.name("bday")).click();
    wd.findElement(By.name("bmonth")).click();
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("March");
    wd.findElement(By.name("bmonth")).click();
    wd.findElement(By.name("byear")).click();
    wd.findElement(By.name("byear")).clear();
    wd.findElement(By.name("byear")).sendKeys("1995");
    wd.findElement(By.name("aday")).click();
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText("11");
    wd.findElement(By.name("aday")).click();
    wd.findElement(By.name("amonth")).click();
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText("June");
    wd.findElement(By.name("amonth")).click();
    wd.findElement(By.name("ayear")).click();
    wd.findElement(By.name("ayear")).clear();
    wd.findElement(By.name("ayear")).sendKeys("2001");
  }

  private void fillWebContacts() {
    wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys("Email1@mail.ru");
    wd.findElement(By.name("email2")).clear();
    wd.findElement(By.name("email2")).sendKeys("Email2@bk.ru");
    wd.findElement(By.name("email3")).clear();
    wd.findElement(By.name("email3")).sendKeys("Email3@gmail.ru");
    wd.findElement(By.name("homepage")).clear();
    wd.findElement(By.name("homepage")).sendKeys("vk.com");
  }

  private void fillPhoneNumbers() {
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys("7-09-46");
    wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys("8-924-345-23-34");
    wd.findElement(By.name("work")).clear();
    wd.findElement(By.name("work")).sendKeys("345-45-35");
    wd.findElement(By.name("fax")).clear();
    wd.findElement(By.name("fax")).sendKeys("234-45-23");
  }

  private void fillAboutWork() {
    wd.findElement(By.name("title")).click();
    wd.findElement(By.name("title")).clear();
    wd.findElement(By.name("title")).sendKeys("Work");
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys("Kontur");
    wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys("Leninskiy avenu,168");
  }

  private void chooseAvatar() {
    wd.findElement(By.name("photo")).clear();
    wd.findElement(By.name("photo")).sendKeys("C:\\workspace\\JAVA_lessons\\addressbook-web-tests\\src\\test\\java\\ru\\Lopatina\\addressbook\\i380664.jpg");
  }

  private void fillNameForms() {
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys("Gheorghe");
    wd.findElement(By.name("middlename")).clear();
    wd.findElement(By.name("middlename")).sendKeys("Alan");
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys("Smith");
    wd.findElement(By.name("nickname")).clear();
    wd.findElement(By.name("nickname")).sendKeys("Nicky");
  }

  private void initContactCreation() {
    wd.findElement(By.linkText("add new")).click();
  }

  @AfterClass(alwaysRun = true)
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
