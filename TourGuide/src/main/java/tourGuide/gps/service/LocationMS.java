package tourGuide.gps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tourGuide.TourGuideController;
import tourGuide.gps.DTO.AttractionResponseToMainService;
import tourGuide.gps.DTO.LocationResponseToMainService;
import tourGuide.gps.DTO.MapService;
import tourGuide.gps.DTO.VisitedLocationResponseToMainService;
import tourGuide.proxies.UserProxy;
import tourGuide.user.UserDTOToMainService;
import tourGuide.user.UserDTOFromMainService;

@Service
public class LocationMS {

  @Autowired
  private MapService mapService;

  @Autowired
  private UserProxy userProxy;

  private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;
  private Logger logger = LoggerFactory.getLogger(LocationMS.class);
  private final GpsUtil gpsUtil;
  private int attractionProximityRange = 200;

  public LocationMS(GpsUtil gpsUtil) {
    this.gpsUtil = gpsUtil;
  }



  //Modification de cette méthode
  public UserDTOToMainService returnModifiedUserDTOToMainService(String userName) {
    //Y retrait de l'appel au service principal
    UserDTOToMainService userDTOToMainService = new UserDTOToMainService();
    //Y récupération directement du userDTOFromMainService
    UserDTOFromMainService userDTOFromMainService = initializeInternalUser(userName);
    userDTOToMainService.setLatestLocationTimestamp(getRandomTime());
    userDTOToMainService.setVisitedLocationResponseToMainServiceList(userDTOFromMainService.getVisitedLocationResponseToMainServiceList());
    return userDTOToMainService;
  }

  public UserDTOFromMainService getModifiedUserDTOFromMainService(String userName) {
    return internalUserMap.get(userName);
  }

  public List<UserDTOFromMainService> getAllUsersDTO() {
    return internalUserMap.values().stream().collect(Collectors.toList());
  }

  //Commentage de cette méthode
  /*public VisitedLocation getUserLocation(UserDTO userDTO) {
    VisitedLocation visitedLocation = (userDTO.getVisitedLocations().size() > 0) ?
            userDTO.getLastVisitedLocation() :
            trackUserLocation(userDTO);
    return visitedLocation;
  }*/

  //add method to mimic running
  public String getUserLocationCheck(String userName) {
    return userName;
  }

  //Commentage de cette méthode suite au dernière modification
  /*public VisitedLocation trackUserLocation(UserDTOToMainService userDTOToMainService) {
    VisitedLocation visitedLocation = gpsUtil.getUserLocation(userDTOToMainService.getUserId());
    userDTOToMainService.addToVisitedLocationResponseToMainServiceList(mapService.convertVisitedLocationToVisitedLocationResponseToMainService(visitedLocation));
    return visitedLocation;
  }*/

  public List<Attraction> getNearByAttractions(VisitedLocation visitedLocation) {
    List<Attraction> nearbyAttractions = new ArrayList<>();
    for(Attraction attraction : gpsUtil.getAttractions()) {
      if(isWithinAttractionProximity(attraction, visitedLocation.location)) {
        nearbyAttractions.add(attraction);
      }
    }
    return nearbyAttractions;
  }

  public boolean isWithinAttractionProximity(Attraction attraction, Location location) {
    return getDistance(attraction, location) > attractionProximityRange ? false : true;
  }

  public double getDistance(Location loc1, Location loc2) {
    double lat1 = Math.toRadians(loc1.latitude);
    double lon1 = Math.toRadians(loc1.longitude);
    double lat2 = Math.toRadians(loc2.latitude);
    double lon2 = Math.toRadians(loc2.longitude);
    double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
            + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));
    double nauticalMiles = 60 * Math.toDegrees(angle);
    double statuteMiles = STATUTE_MILES_PER_NAUTICAL_MILE * nauticalMiles;
    return statuteMiles;
  }

  //Commentage de cette méthode suite au dernière modification
  /*public List<VisitedLocationResponseToMainService> getVisitedLocations(UserDTOToMainService userDTOToMainService) {
    List<VisitedLocation> visitedLocationList = userDTOToMainService.getVisitedLocations();
    List<VisitedLocationResponseToMainService> visitedLocationResponseToMainServiceList = new ArrayList<>();
    for(VisitedLocation visitedLocation : visitedLocationList) {
      VisitedLocationResponseToMainService visitedLocationResponseToMainService = mapService.convertVisitedLocationToVisitedLocationResponseToMainService(visitedLocation);
      visitedLocationResponseToMainServiceList.add(visitedLocationResponseToMainService);
    }
    return visitedLocationResponseToMainServiceList;
  }*/

  public List<AttractionResponseToMainService> getAttractions() {
    List<Attraction> attractionList = gpsUtil.getAttractions();
    List<AttractionResponseToMainService> attractionResponseToMainServiceList = new ArrayList<>();
    for(Attraction attraction : attractionList) {
      AttractionResponseToMainService attractionResponseToMainService = mapService.convertAttractionToAttractionResponseToMainService(attraction);
      attractionResponseToMainServiceList.add(attractionResponseToMainService);
    }
    return attractionResponseToMainServiceList;
  }
  /*public AttractionResponse getAttraction(int attractionNumber) {
    return mapService.convertAttractionToAttractionResponse(gpsUtil.getAttractions().get(attractionNumber));
  }*/

  /**********************************************************************************
   *
   * Methods Below: For Internal Testing
   *
   **********************************************************************************/
  //private static final String tripPricerApiKey = "test-server-api-key";
  // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
  private final Map<String, UserDTOFromMainService> internalUserMap = new HashMap<>();

  //Modification de ces méthodes
  private UserDTOFromMainService initializeInternalUser(String userName) {
      //Y appel au proxy directement et retour d'un UserDTOFromMainService
      UserDTOFromMainService userDTOFromMainService = userProxy.getUserDTOFromMainService(userName);
      generateUserLocationHistory(userDTOFromMainService);
      return userDTOFromMainService;
  }

  private void generateUserLocationHistory(UserDTOFromMainService userDTOFromMainService) {
    IntStream.range(0, 3).forEach(i-> {
      //Y retrait de l'Id
      userDTOFromMainService.addToVisitedLocationResponseToMainServiceList(new VisitedLocationResponseToMainService(new LocationResponseToMainService(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
    });
  }

  private double generateRandomLongitude() {
    double leftLimit = -180;
    double rightLimit = 180;
    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
  }

  private double generateRandomLatitude() {
    double leftLimit = -85.05112878;
    double rightLimit = 85.05112878;
    return leftLimit + new Random().nextDouble() * (rightLimit - leftLimit);
  }

  private Date getRandomTime() {
    LocalDateTime localDateTime = LocalDateTime.now().minusDays(new Random().nextInt(30));
    return Date.from(localDateTime.toInstant(ZoneOffset.UTC));
  }

}
