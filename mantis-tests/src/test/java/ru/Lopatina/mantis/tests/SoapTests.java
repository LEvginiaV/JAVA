package ru.Lopatina.mantis.tests;

import org.testng.annotations.Test;
import ru.Lopatina.mantis.model.Issue;
import ru.Lopatina.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.*;

public class SoapTests extends TestBase {

  @Test
  public void testGetProgect() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects();
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCeateIssue() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(2);
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue").withDescription("Test issue description")
            .withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }
}
