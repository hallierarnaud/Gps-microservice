package tourGuide;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tourGuide.gps.DTO.AttractionResponse;
import tourGuide.gps.DTO.MapService;
import tourGuide.gps.DTO.VisitedLocationResponse;
import tourGuide.gps.service.GpsService;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GpsServiceTest {

  @Test
  public void trackUserLocation_shouldReturnOk() {
    // GIVEN
    Locale.setDefault(Locale.ENGLISH);
    GpsUtil gpsUtil = Mockito.mock(GpsUtil.class);
    MapService mapService = new MapService();
    GpsService gpsService = new GpsService(gpsUtil, mapService);
    UUID userId = UUID.randomUUID();
    Location location = new Location(0.0, 0.0);
    Date timeVisited = new Date();
    VisitedLocation visitedLocation = new VisitedLocation(userId, location, timeVisited);
    Mockito.when(gpsUtil.getUserLocation(userId)).thenReturn(visitedLocation);

    // WHEN
    VisitedLocationResponse visitedLocationResponse = gpsService.trackUserLocation(userId);

    // THEN
    assertEquals(userId, visitedLocationResponse.userId);
  }

  @Test
  public void getAttractions_shouldReturnOk() {
    // GIVEN
    GpsUtil gpsUtil = Mockito.mock(GpsUtil.class);
    MapService mapService = new MapService();
    GpsService gpsService = new GpsService(gpsUtil, mapService);
    List<Attraction> attractionList = new ArrayList<>();
    Attraction attraction = new Attraction("Disneyland", "Anaheim", "CA", 33.817595D, -117.922008D);
    attractionList.add(attraction);
    Mockito.when(gpsUtil.getAttractions()).thenReturn(attractionList);

    // WHEN
    List<AttractionResponse> attractionResponseList = gpsService.getAttractions();

    // THEN
    assertEquals("Disneyland", attractionResponseList.get(0).attractionName);

  }

}
