package tourGuide.gps.DTO;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class VisitedLocationResponseToMainService {

  public UUID userId;
  public LocationResponseToMainService locationResponseToMainService;
  public Date timeVisited;

}
