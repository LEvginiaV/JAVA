package ru.Lopatina.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.Lopatina.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("Unrecognized format " + format);
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file);) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 1; i < count + 1; i++) {
      contacts.add(new ContactData().withFirstName(String.format("FirstName %s", i))
              .withMiddleName(String.format("MiddleName %s", i)).withLastName(String.format("LastName %s", i))
              .withNickName(String.format("NickName %s", i)).withPhoto(new File("src/test/resources/avatar.jpg"))
              .withPosition(String.format("Position %s", i)).withCompany(String.format("Company %s", i))
              .withCompanyAddress(String.format("CompanyAddress %s", i))
              .withHomePhone(String.format("%s-%s%s-%s%s", i, i, i, i, i))
              .withMobilePhone(String.format("8-911-%s%s%s-%s%s-%s%s", i, i, i, i, i, i, i))
              .withWorkPhone(String.format("%s%s%s-%s%s-%s%s", i, i, i, i, i, i, i))
              .withFax(String.format("%s%s%s-00-00", i, i, i))
              .withEmail(String.format("Email1-%s@mail.ru", i))
              .withEmail2(String.format("Email2-%s@bk.ru", i)).withEmail3(String.format("Email3-%s@gmail.ru", i))
              .withHomepage(String.format("vk%s.com", i)).withBday(i%28).withBmonth("March")
              .withByear(Integer.toString(1950 + i%69)).withAday((15 + i) % 28).withAmonth("June")
              .withAyear(Integer.toString(2000 + i%20)).withHomeAddress(String.format("HomeAddress %s", i))
              .withHomePhone2(String.format("111-%s%s-%s%s", i, i, i, i)).withPosition(String.format("Position %s", i))
              .withNotes(String.format("Notes %s", i)));
    }
    return contacts;
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                contact.getFirstName(), contact.getMiddleName(), contact.getLastName(),
                contact.getNickName(), contact.getPhoto(), contact.getPosition(), contact.getCompany(),
                contact.getCompanyAddress(), contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(),
                contact.getFax(), contact.getEmail(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
                contact.getHomepage(), contact.getBday(), contact.getBmonth(), contact.getByear(), contact.getAday(),
                contact.getAmonth(), contact.getAyear(), contact.getHomeAddress(),
                contact.getHomePhone2(), contact.getPosition(), contact.getNotes()));
      }
    }
  }
}
