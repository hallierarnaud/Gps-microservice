package tourGuide.gps.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitedLocationResponse {

  //Y public UUID userId;
  public LocationResponse locationResponse;
  public Date timeVisited;

}
