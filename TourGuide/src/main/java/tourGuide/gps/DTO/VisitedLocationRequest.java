package tourGuide.gps.DTO;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class VisitedLocationRequest {

  public UUID userId;
  public LocationRequest locationRequest;
  public Date timeVisited;

}
