package tourGuide.gps.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Entity {
  private int id;
  private String name;
  private int price;

}
