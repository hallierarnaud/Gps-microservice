package tourGuide.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import tourGuide.gps.DTO.VisitedLocationResponse;

@Data
public class UserDTOToMainService {

  /*Y private UUID userId;
  private String userName;
  private String phoneNumber;
  private String emailAddress;*/
  private Date latestLocationTimestamp;
  private List<VisitedLocationResponse> visitedLocationResponseList = new ArrayList<>();

  /*public void addToVisitedLocationResponseList(VisitedLocationResponse visitedLocationResponse) {
    visitedLocationResponseList.add(visitedLocationResponse);
  }*/

  //Commentage de cette méthode
  /*public VisitedLocation getLastVisitedLocation() {
    return visitedLocations.get(visitedLocations.size() - 1);
  }*/

}
