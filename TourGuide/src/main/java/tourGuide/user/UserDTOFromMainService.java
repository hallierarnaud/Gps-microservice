package tourGuide.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import tourGuide.gps.DTO.VisitedLocationResponseToMainService;

@Data
public class UserDTOFromMainService {

  private UUID userId;
  private String userName;
  private String phoneNumber;
  private String emailAddress;
  private List<VisitedLocationResponseToMainService> visitedLocationResponseToMainServiceList = new ArrayList<>();

  public void addToVisitedLocationResponseToMainServiceList(VisitedLocationResponseToMainService visitedLocationResponseToMainService) {
    visitedLocationResponseToMainServiceList.add(visitedLocationResponseToMainService);
  }

}
