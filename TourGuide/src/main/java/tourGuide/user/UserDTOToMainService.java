package tourGuide.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import gpsUtil.location.VisitedLocation;
import lombok.Data;

@Data
public class UserDTOToMainService {

  private UUID userId;
  private String userName;
  private String phoneNumber;
  private String emailAddress;
  private Date latestLocationTimestamp;
  private List<VisitedLocation> visitedLocations = new ArrayList<>();

  public void addToVisitedLocations(VisitedLocation visitedLocation) {
    visitedLocations.add(visitedLocation);
  }

  //Commentage de cette méthode
  /*public VisitedLocation getLastVisitedLocation() {
    return visitedLocations.get(visitedLocations.size() - 1);
  }*/

}
