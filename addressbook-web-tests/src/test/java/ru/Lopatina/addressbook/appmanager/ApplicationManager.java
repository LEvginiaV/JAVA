package ru.Lopatina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.Lopatina.addressbook.model.Date;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    public WebDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
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

    public void returnToHomePage() {
      wd.findElement(By.linkText("home page")).click();
    }

    public void submitContactCreation() {
      wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillNotes(String Notes) {
      wd.findElement(By.name("notes")).sendKeys(Notes);
    }

    public void fillHomeContacts(String homeAdress, String homePhone) {
      wd.findElement(By.name("address2")).sendKeys(homeAdress);
      wd.findElement(By.name("phone2")).sendKeys(homePhone);
    }

    public void chooseGroup(String groupTitle) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupTitle);
    }

    public void fillDates(Date birthDate, Date anniversaryDate) {
      new Select(wd.findElement(By.name("bday"))).selectByVisibleText(birthDate.getDay());
      new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(birthDate.getMonth());
      wd.findElement(By.name("byear")).sendKeys(birthDate.getYear());
      new Select(wd.findElement(By.name("aday"))).selectByVisibleText(anniversaryDate.getDay());
      new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(anniversaryDate.getMonth());
      wd.findElement(By.name("ayear")).sendKeys(anniversaryDate.getYear());
    }

    public void fillWebContacts(String email, String email2, String email3, String homepage) {
      wd.findElement(By.name("email")).sendKeys(email);
      wd.findElement(By.name("email2")).sendKeys(email2);
      wd.findElement(By.name("email3")).sendKeys(email3);
      wd.findElement(By.name("homepage")).sendKeys(homepage);
    }

    public void fillPhoneNumbers(String homePhone, String mobilePhone, String workPhone, String fax) {
      wd.findElement(By.name("home")).sendKeys(homePhone);
      wd.findElement(By.name("mobile")).sendKeys(mobilePhone);
      wd.findElement(By.name("work")).sendKeys(workPhone);
      wd.findElement(By.name("fax")).sendKeys(fax);
    }

    public void fillAboutWork(String position, String company, String companyAddress) {
      wd.findElement(By.name("title")).sendKeys(position);
      wd.findElement(By.name("company")).sendKeys(company);
      wd.findElement(By.name("address")).sendKeys(companyAddress);
    }

    public void chooseAvatar(String img) {
      String dir = new String(System.getProperty("user.dir"));
      System.out.println(dir);
      wd.findElement(By.name("photo")).sendKeys(dir+img);
    }

    public void fillNameForms(String firstName, String middleName, String lastName, String nickName) {
      wd.findElement(By.name("firstname")).sendKeys(firstName);
      wd.findElement(By.name("middlename")).sendKeys(middleName);
      wd.findElement(By.name("lastname")).sendKeys(lastName);
      wd.findElement(By.name("nickname")).sendKeys(nickName);
    }

    public void initContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
