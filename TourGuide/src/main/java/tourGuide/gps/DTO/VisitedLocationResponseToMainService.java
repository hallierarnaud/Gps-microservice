package tourGuide.gps.DTO;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitedLocationResponseToMainService {

  public UUID userId;
  public LocationResponseToMainService locationResponseToMainService;
  public Date timeVisited;

}
