package tourGuide.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import tourGuide.gps.DTO.VisitedLocationResponseToMainService;

@Data
public class UserDTOToMainService {

  /*Y private UUID userId;
  private String userName;
  private String phoneNumber;
  private String emailAddress;*/
  private Date latestLocationTimestamp;
  private List<VisitedLocationResponseToMainService> visitedLocationResponseToMainServiceList = new ArrayList<>();

  /*public void addToVisitedLocationResponseToMainServiceList(VisitedLocationResponseToMainService visitedLocationResponseToMainService) {
    visitedLocationResponseToMainServiceList.add(visitedLocationResponseToMainService);
  }*/

  //Commentage de cette méthode
  /*public VisitedLocation getLastVisitedLocation() {
    return visitedLocations.get(visitedLocations.size() - 1);
  }*/

}
