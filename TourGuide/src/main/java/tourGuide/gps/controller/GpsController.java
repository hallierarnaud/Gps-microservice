package tourGuide.gps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import tourGuide.gps.DTO.AttractionResponse;
import tourGuide.gps.DTO.VisitedLocationResponse;
import tourGuide.gps.service.GpsService;

@RestController
public class GpsController {

  @Autowired
  private GpsService gpsService;

  /**
   *
   * @param userId an id of a user
   * @return the user current location
   */
  @GetMapping("/trackUserLocations")
  public VisitedLocationResponse getUserLocation(@RequestParam UUID userId) {
    return gpsService.trackUserLocation(userId);
  }

  /**
   *
   * @return a list of attractions
   */
  @GetMapping("/attractions")
  public List<AttractionResponse> getAttractions() {
    return gpsService.getAttractions();
  }

}
