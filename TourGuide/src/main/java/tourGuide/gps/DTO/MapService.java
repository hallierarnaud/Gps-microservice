package tourGuide.gps.DTO;

import org.springframework.stereotype.Service;

import java.util.UUID;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;

@Service
public class MapService {

  public AttractionRequest convertAttractionToAttractionRequest(Attraction attraction) {
    AttractionRequest attractionRequest = new AttractionRequest();
    attractionRequest.setAttractionId(UUID.randomUUID());
    attractionRequest.setAttractionName(attraction.attractionName);
    attractionRequest.setCity(attraction.city);
    attractionRequest.setState(attraction.state);
    attractionRequest.setLongitude(attraction.longitude);
    attractionRequest.setLatitude(attraction.latitude);
    return attractionRequest;
  }

  public LocationRequest convertLocationToLocationRequest(Location location) {
    LocationRequest locationRequest = new LocationRequest();
    locationRequest.setLongitude(location.longitude);
    locationRequest.setLatitude(location.latitude);
    return locationRequest;
  }

  public VisitedLocationRequest convertVisitedLocationToVisitedLocationRequest(VisitedLocation visitedLocation) {
    VisitedLocationRequest visitedLocationRequest = new VisitedLocationRequest();
    visitedLocationRequest.setUserId(visitedLocation.userId);
    visitedLocationRequest.setLocationRequest(convertLocationToLocationRequest(visitedLocation.location));
    visitedLocationRequest.setTimeVisited(visitedLocation.timeVisited);
    return visitedLocationRequest;
  }

}
