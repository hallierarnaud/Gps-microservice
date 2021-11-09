package tourGuide.gps.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import tourGuide.gps.DTO.AttractionResponse;
import tourGuide.gps.DTO.MapService;
import tourGuide.gps.DTO.VisitedLocationResponse;

@Service
public class GpsService {

  private Logger logger = LoggerFactory.getLogger(GpsService.class);
  private final GpsUtil gpsUtil;
  private final MapService mapService;

  public GpsService(GpsUtil gpsUtil, MapService mapService) {
    this.gpsUtil = gpsUtil;
    this.mapService = mapService;
  }

  public VisitedLocationResponse trackUserLocation(UUID userId) {
    VisitedLocation visitedLocation = gpsUtil.getUserLocation(userId);
    VisitedLocationResponse visitedLocationResponse = mapService.convertVisitedLocationToVisitedLocationResponse(visitedLocation);
    return visitedLocationResponse;
  }

  public List<AttractionResponse> getAttractions() {
    List<Attraction> attractionList = gpsUtil.getAttractions();
    List<AttractionResponse> attractionResponseList = new ArrayList<>();
    for (Attraction attraction : attractionList) {
      AttractionResponse attractionResponse = mapService.convertAttractionToAttractionResponse(attraction);
      attractionResponseList.add(attractionResponse);
    }
    return attractionResponseList;
  }

}
