package jdbc.repository.custom;

import java.util.List;

import jdbc.Builder.BuildingSearchBuilder;
import jdbc.repository.entity.BuildingEntity;

public interface BuildingRepositoryCustom {
	List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder);  
}
 