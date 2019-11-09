package ru.Lopatina.soap;

import com.lavasoft.GeoIPService;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIPServiceTests {

  @Test
  public void testMyIp() {
    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getLocation();
    Assert.assertEquals(ipLocation.substring(16, 18), "RU" );
  }
}
