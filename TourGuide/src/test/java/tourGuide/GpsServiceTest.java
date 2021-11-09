package tourGuide;

import org.junit.Test;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import gpsUtil.GpsUtil;
import tourGuide.gps.DTO.AttractionResponse;
import tourGuide.gps.DTO.MapService;
import tourGuide.gps.DTO.VisitedLocationResponse;
import tourGuide.gps.service.GpsService;

import static org.junit.Assert.assertEquals;

public class GpsServiceTest {

  @Test
  public void trackUserLocation_shouldReturnOk() {
    // GIVEN
    Locale.setDefault(Locale.ENGLISH);
    GpsUtil gpsUtil = new GpsUtil();
    MapService mapService = new MapService();
    GpsService gpsService = new GpsService(gpsUtil, mapService);
    UUID userId = UUID.randomUUID();

    // WHEN
    VisitedLocationResponse visitedLocationResponse = gpsService.trackUserLocation(userId);

    // THEN
    assertEquals(userId, visitedLocationResponse.userId);
  }

  @Test
  public void getAttractions_shouldReturnOk() {
    // GIVEN
    GpsUtil gpsUtil = new GpsUtil();
    MapService mapService = new MapService();
    GpsService gpsService = new GpsService(gpsUtil, mapService);

    // WHEN
    List<AttractionResponse> attractionResponseList = gpsService.getAttractions();

    // THEN
    assertEquals("Disneyland", attractionResponseList.get(0).attractionName);

  }

}
