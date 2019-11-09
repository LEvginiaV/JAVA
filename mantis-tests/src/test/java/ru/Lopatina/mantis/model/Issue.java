package ru.Lopatina.mantis.model;

public class Issue {

  private int id;
  private String summary;
  private  String description;
  private  Project progect;

  public int getId() {
    return id;
  }

  public Issue withId(int id) {
    this.id = id;
    return this;
  }

  public String getSummary() {
    return summary;
  }

  public Issue withSummary(String summary) {
    this.summary = summary;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Issue withDescription(String description) {
    this.description = description;
    return this;
  }

  public Project getProgect() {
    return progect;
  }

  public Issue withProject(Project progect) {
    this.progect = progect;
    return this;
  }
}
