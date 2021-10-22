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
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tourGuide.gps.DTO.AttractionRequest;
import tourGuide.gps.DTO.MapService;
import tourGuide.gps.tracker.TrackerMS;
import tourGuide.helper.InternalTestHelper;
import tourGuide.service.TourGuideService;
import tourGuide.user.User;

@Service
public class LocationMS {

  @Autowired
  private MapService mapService;

  private static final double STATUTE_MILES_PER_NAUTICAL_MILE = 1.15077945;

  private Logger logger = LoggerFactory.getLogger(TourGuideService.class);
  private final GpsUtil gpsUtil;
  public final TrackerMS trackerMS;
  boolean testMode = true;
  private int attractionProximityRange = 200;

  public LocationMS(GpsUtil gpsUtil) {
    this.gpsUtil = gpsUtil;

    if(testMode) {
      logger.info("TestMode enabled");
      logger.debug("Initializing users");
      initializeInternalUsers();
      logger.debug("Finished initializing users");
    }
    trackerMS = new TrackerMS(this);
    addShutDownHook();
  }

  public List<User> getAllUsers() {
    return internalUserMap.values().stream().collect(Collectors.toList());
  }

  public VisitedLocation getUserLocation(User user) {
    VisitedLocation visitedLocation = (user.getVisitedLocations().size() > 0) ?
            user.getLastVisitedLocation() :
            trackUserLocation(user);
    return visitedLocation;
  }

  //add method to mimic running
  public String getUserLocationCheck(String userName) {
    return userName;
  }

  public VisitedLocation trackUserLocation(User user) {
    VisitedLocation visitedLocation = gpsUtil.getUserLocation(user.getUserId());
    user.addToVisitedLocations(visitedLocation);
    return visitedLocation;
  }

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

  public List<VisitedLocation> getVisitedLocations(User user) {
    return user.getVisitedLocations();
  }

  public List<AttractionRequest> getAttractions() {
    List<Attraction> attractionList = gpsUtil.getAttractions();
    List<AttractionRequest> attractionRequestList = new ArrayList<>();
    for(Attraction attraction : attractionList) {
      AttractionRequest attractionRequest = mapService.convertAttractionToAttractionRequest(attraction);
      attractionRequestList.add(attractionRequest);
    }
    return attractionRequestList;
  }
  /*public AttractionResponse getAttraction(int attractionNumber) {
    return mapService.convertAttractionToAttractionResponse(gpsUtil.getAttractions().get(attractionNumber));
  }*/

  private void addShutDownHook() {
    Runtime.getRuntime().addShutdownHook(new Thread() {
      public void run() {
        trackerMS.stopTracking();
      }
    });
  }

  /**********************************************************************************
   *
   * Methods Below: For Internal Testing
   *
   **********************************************************************************/
  private static final String tripPricerApiKey = "test-server-api-key";
  // Database connection will be used for external users, but for testing purposes internal users are provided and stored in memory
  private final Map<String, User> internalUserMap = new HashMap<>();
  private void initializeInternalUsers() {
    IntStream.range(0, InternalTestHelper.getInternalUserNumber()).forEach(i -> {
      String userName = "internalUser" + i;
      String phone = "000";
      String email = userName + "@tourGuide.com";
      User user = new User(UUID.randomUUID(), userName, phone, email);
      generateUserLocationHistory(user);

      internalUserMap.put(userName, user);
    });
    logger.debug("Created " + InternalTestHelper.getInternalUserNumber() + " internal test users.");
  }

  private void generateUserLocationHistory(User user) {
    IntStream.range(0, 3).forEach(i-> {
      user.addToVisitedLocations(new VisitedLocation(user.getUserId(), new Location(generateRandomLatitude(), generateRandomLongitude()), getRandomTime()));
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
