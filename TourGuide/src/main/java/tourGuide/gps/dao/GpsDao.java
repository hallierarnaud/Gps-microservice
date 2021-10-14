package tourGuide.gps.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import tourGuide.gps.model.Entity;

@Repository
public class GpsDao implements GpsDaoI {

  public static List<Entity> entities = new ArrayList<>();
  static {
    entities.add(new Entity(1, "Paris", 350));
    entities.add(new Entity(2, "New York", 500));
    entities.add(new Entity(3, "Rome", 400));
  }

  @Override
  public List<Entity> findAll() {
    return entities;
  }

}
