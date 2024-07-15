package jdbc.repository;

import java.util.List;
import java.util.Map;

import jdbc.repository.entity.BuildingEntity;

public interface BuildingReponsitory {
	List<BuildingEntity> findAll(Map<String, Object> map, List<String> typeCode);
	boolean create (BuildingEntity buildingEntity);
	boolean update (BuildingEntity buildingEntity); 
	void delete (List<Long> ids);
}
   