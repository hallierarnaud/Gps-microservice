package tourGuide.gps.DTO;

import org.springframework.stereotype.Service;

import java.util.UUID;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class MapService {

  public AttractionResponseToMainService convertAttractionToAttractionResponseToMainService(Attraction attraction) {
    AttractionResponseToMainService attractionResponseToMainService = new AttractionResponseToMainService();
    attractionResponseToMainService.setAttractionId(UUID.randomUUID());
    attractionResponseToMainService.setAttractionName(attraction.attractionName);
    attractionResponseToMainService.setCity(attraction.city);
    attractionResponseToMainService.setState(attraction.state);
    attractionResponseToMainService.setLongitude(attraction.longitude);
    attractionResponseToMainService.setLatitude(attraction.latitude);
    return attractionResponseToMainService;
  }

  public LocationResponseToMainService convertLocationToLocationResponseToMainService(Location location) {
    LocationResponseToMainService locationResponseToMainService = new LocationResponseToMainService();
    locationResponseToMainService.setLongitude(location.longitude);
    locationResponseToMainService.setLatitude(location.latitude);
    return locationResponseToMainService;
  }

  public VisitedLocationResponseToMainService convertVisitedLocationToVisitedLocationResponseToMainService(VisitedLocation visitedLocation) {
    VisitedLocationResponseToMainService visitedLocationResponseToMainService = new VisitedLocationResponseToMainService();
    visitedLocationResponseToMainService.setUserId(visitedLocation.userId);
    visitedLocationResponseToMainService.setLocationResponseToMainService(convertLocationToLocationResponseToMainService(visitedLocation.location));
    visitedLocationResponseToMainService.setTimeVisited(visitedLocation.timeVisited);
    return visitedLocationResponseToMainService;
  }

}
