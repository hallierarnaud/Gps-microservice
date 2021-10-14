package tourGuide.gps.dao;

import java.util.List;

import tourGuide.gps.model.Entity;

public interface GpsDaoI {

  List<Entity> findAll();

}
