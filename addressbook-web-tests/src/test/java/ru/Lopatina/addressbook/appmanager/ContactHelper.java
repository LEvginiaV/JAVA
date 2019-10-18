package ru.Lopatina.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.Lopatina.addressbook.model.ContactData;
import ru.Lopatina.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  private void select(By locator, String value) {
    new Select(wd.findElement(locator)).selectByVisibleText(value);
  }

    /*
    public void chooseAvatar(String img) {
      String dir = new String(System.getProperty("user.dir"));
      System.out.println(dir);
      typeImg(By.name("photo"),dir+img);
    }
    */

  public void fillContactForms(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    attach(By.name("photo"), contactData.getPhoto());
    type(By.name("title"), contactData.getPosition());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getCompanyAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    select(By.name("bday"), contactData.getBday());
    select(By.name("bmonth"), contactData.getBmonth());
    type(By.name("byear"), contactData.getByear());
    select(By.name("aday"), contactData.getAday());
    select(By.name("amonth"), contactData.getAmonth());
    type(By.name("ayear"), contactData.getAyear());
    if (creation) {
      select(By.name("new_group"), contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    type(By.name("address2"), contactData.getHomeAddress());
    type(By.name("phone2"), contactData.getHomePhone2());
    type(By.name("notes"), contactData.getNotes());
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[id='" + id +"']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void initContactModificationById(int id) {
    //wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id +"']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@name='update']"));
  }

  public void create(ContactData contact, boolean creation) {
    initContactCreation();
    fillContactForms(contact, creation);
    // app.getContactHelper().chooseAvatar("\\img\\i380664.jpg");
    submitContactCreation();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForms(contact, false);
    //app.getContactHelper().chooseAvatar("\\img\\i380664.jpg");
    submitContactModification();
    contactCache = null;
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    wd.switchTo().alert().accept();
    contactCache = null;
  }

  public void gotoHomePage() {
    click(By.linkText("home page"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.cssSelector("tr[name]"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
      String firstName = element.findElement(By.xpath("td[3]")).getText();
      String lastName = element.findElement(By.xpath("td[2]")).getText();
      String allPhones = element.findElement(By.xpath("td[6]")).getText();
      String companyAddress = element.findElement(By.xpath("td[4]")).getText();
      String allEmails = element.findElement(By.xpath("td[5]")).getText();
      contactCache.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
              .withAllPhones(allPhones).withCompanyAddress(companyAddress).withAllEmails(allEmails));
    }
    return new Contacts(contactCache);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String companyAddress = wd.findElement(By.name("address")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withHomePhone2(phone2)
            .withCompanyAddress(companyAddress).withEmail(email).withEmail2(email2).withEmail3(email3);
  }
}
