package ru.Lopatina.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @XStreamOmitField
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;

  @Expose
  @Column(name = "firstname")
  private String firstName;

  @Expose
  @Column(name = "middlename")
  private String middleName;

  @Expose
  @Column(name = "lastname")
  private String lastName;

  @Expose
  @Column(name = "nickname")
  private String nickName;

  @Expose
  @Column(name = "title")
  private String position;

  @Expose
  @Column(name = "company")
  private String company;

  @Expose
  @Column(name = "address")
  @Type(type = "text")
  private String companyAddress;

  @Expose
  @Column(name = "home")
  @Type(type = "text")
  private String homePhone;

  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobilePhone;

  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workPhone;

  @Expose
  @Transient
  private String allPhones;

  @Expose
  @Column(name = "fax")
  @Type(type = "text")
  private String fax;

  @Expose
  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @Expose
  @Transient
  private String allEmails;

  @Expose
  @Column(name = "homepage")
  @Type(type = "text")
  private String homepage;

  @Expose
  @Column(name = "bday", columnDefinition = "TINYINT")
  private Integer bday;

  @Expose
  @Column(name = "bmonth")
  private String bmonth;

  @Expose
  @Column(name = "byear")
  private String byear;

  @Expose
  @Column(name = "aday", columnDefinition = "TINYINT")
  private Integer aday;

  @Expose
  @Column(name = "amonth")
  private String amonth;

  @Expose
  @Column(name = "ayear")
  private String ayear;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn (name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Expose
  @Column(name = "address2")
  @Type(type = "text")
  private String homeAddress;

  @Expose
  @Column(name = "phone2")
  @Type(type = "text")
  private String homePhone2;

  @Expose
  @Column(name = "notes")
  @Type(type = "text")
  private String notes;

  @Expose
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  @Override
  public String toString() {
    return "ContactData{" +
            "id=" + id +
            ", firstName='" + firstName + '\'' +
            ", middleName='" + middleName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", nickName='" + nickName + '\'' +
            ", position='" + position + '\'' +
            ", company='" + company + '\'' +
            ", companyAddress='" + companyAddress + '\'' +
            ", homePhone='" + homePhone + '\'' +
            ", mobilePhone='" + mobilePhone + '\'' +
            ", workPhone='" + workPhone + '\'' +
            ", allPhones='" + allPhones + '\'' +
            ", fax='" + fax + '\'' +
            ", email='" + email + '\'' +
            ", email2='" + email2 + '\'' +
            ", email3='" + email3 + '\'' +
            ", allEmails='" + allEmails + '\'' +
            ", homepage='" + homepage + '\'' +
            ", bday=" + bday +
            ", bmonth='" + bmonth + '\'' +
            ", byear='" + byear + '\'' +
            ", aday=" + aday +
            ", amonth='" + amonth + '\'' +
            ", ayear='" + ayear + '\'' +
            ", groups=" + groups +
            ", homeAddress='" + homeAddress + '\'' +
            ", homePhone2='" + homePhone2 + '\'' +
            ", notes='" + notes + '\'' +
            ", photo='" + photo + '\'' +
            '}';
  }

  public File getPhoto() {
    if (photo== null) {
      return null;
    } else {
      return new File(photo);
    }
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public String getAllPhones() {
    return allPhones;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withFirstName(String firstName) {
    this.firstName = firstName;
    return this;
  }

  public ContactData withMiddleName(String middleName) {
    this.middleName = middleName;
    return this;
  }

  public ContactData withLastName(String lastName) {
    this.lastName = lastName;
    return this;
  }

  public ContactData withNickName(String nickName) {
    this.nickName = nickName;
    return this;
  }

  public ContactData withPosition(String position) {
    this.position = position;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
    return this;
  }

  public ContactData withHomePhone(String homePhone) {
    this.homePhone = homePhone;
    return this;
  }

  public ContactData withMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
    return this;
  }

  public ContactData withWorkPhone(String workPhone) {
    this.workPhone = workPhone;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactData withBday(Integer bday) {
    this.bday = bday;
    return this;
  }

  public ContactData withBmonth(String bmonth) {
    this.bmonth = bmonth;
    return this;
  }

  public ContactData withByear(String byear) {
    this.byear = byear;
    return this;
  }

  public ContactData withAday(Integer aday) {
    this.aday = aday;
    return this;
  }

  public ContactData withAmonth(String amonth) {
    this.amonth = amonth;
    return this;
  }

  public ContactData withAyear(String ayear) {
    this.ayear = ayear;
    return this;
  }

  public ContactData withHomeAddress(String homeAddress) {
    this.homeAddress = homeAddress;
    return this;
  }

  public ContactData withHomePhone2(String homePhone2) {
    this.homePhone2 = homePhone2;
    return this;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getPosition() {
    return position;
  }

  public String getCompany() {
    return company;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getMobilePhone() {
    return mobilePhone;
  }

  public String getWorkPhone() {
    return workPhone;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public String getBday() {
    return bday.toString();
  }

  public String getBmonth() {
    return bmonth;
  }

  public String getByear() {
    return byear;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ContactData that = (ContactData) o;
    return id == that.id &&
            Objects.equals(firstName, that.firstName) &&
            Objects.equals(middleName, that.middleName) &&
            Objects.equals(lastName, that.lastName) &&
            Objects.equals(nickName, that.nickName) &&
            Objects.equals(position, that.position) &&
            Objects.equals(company, that.company) &&
            Objects.equals(companyAddress, that.companyAddress) &&
            Objects.equals(homePhone, that.homePhone) &&
            Objects.equals(mobilePhone, that.mobilePhone) &&
            Objects.equals(workPhone, that.workPhone) &&
            Objects.equals(allPhones, that.allPhones) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3) &&
            Objects.equals(allEmails, that.allEmails) &&
            Objects.equals(homepage, that.homepage) &&
            Objects.equals(bday, that.bday) &&
            Objects.equals(byear, that.byear) &&
            Objects.equals(aday, that.aday) &&
            Objects.equals(ayear, that.ayear) &&
            Objects.equals(homeAddress, that.homeAddress) &&
            Objects.equals(homePhone2, that.homePhone2) &&
            Objects.equals(notes, that.notes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, middleName, lastName, nickName, position, company, companyAddress, homePhone, mobilePhone, workPhone, allPhones, fax, email, email2, email3, allEmails, homepage, bday, byear, aday, ayear, homeAddress, homePhone2, notes);
  }

  public String getAday() {
    return aday.toString();
  }

  public String getAmonth() {
    return amonth;
  }

  public String getAyear() {
    return ayear;
  }

  public Groups getGroups() {
    return new Groups(groups);
  }

  public String getHomeAddress() {
    return homeAddress;
  }

  public String getHomePhone2() {
    return homePhone2;
  }

  public String getNotes() {
    return notes;
  }

  public ContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
}
