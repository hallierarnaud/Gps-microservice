package tourGuide.gps.DTO;

import org.springframework.stereotype.Service;

import java.util.UUID;

import gpsUtil.location.Attraction;

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

}
