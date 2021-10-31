package tourGuide.gps.DTO;

import org.springframework.stereotype.Service;

import java.util.UUID;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class MapService {

  public AttractionResponse convertAttractionToAttractionResponse(Attraction attraction) {
    AttractionResponse attractionResponse = new AttractionResponse();
    attractionResponse.setAttractionId(UUID.randomUUID());
    attractionResponse.setAttractionName(attraction.attractionName);
    attractionResponse.setCity(attraction.city);
    attractionResponse.setState(attraction.state);
    attractionResponse.setLongitude(attraction.longitude);
    attractionResponse.setLatitude(attraction.latitude);
    return attractionResponse;
  }

  public LocationResponse convertLocationToLocationResponse(Location location) {
    LocationResponse locationResponse = new LocationResponse();
    locationResponse.setLongitude(location.longitude);
    locationResponse.setLatitude(location.latitude);
    return locationResponse;
  }

  public VisitedLocationResponse convertVisitedLocationToVisitedLocationResponse(VisitedLocation visitedLocation) {
    VisitedLocationResponse visitedLocationResponse = new VisitedLocationResponse();
    //Y visitedLocationResponseToMainService.setUserId(visitedLocation.userId);
    visitedLocationResponse.setLocationResponse(convertLocationToLocationResponse(visitedLocation.location));
    visitedLocationResponse.setTimeVisited(visitedLocation.timeVisited);
    return visitedLocationResponse;
  }

}
