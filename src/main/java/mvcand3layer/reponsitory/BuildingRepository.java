package mvcand3layer.reponsitory;

import java.util.List;

import mvcand3layer.reponsitory.entity.BuildingEntity;

public interface BuildingRepository {
	List<BuildingEntity> findAll(String name, Integer district);
	  
}
