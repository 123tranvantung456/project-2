package jdbc.repository;

import java.util.List;

import jdbc.Builder.BuildingSearchBuilder;
import jdbc.repository.entity.BuildingEntity;

public interface BuildingReponsitory {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);
}
 