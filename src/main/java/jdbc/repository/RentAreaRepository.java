package jdbc.repository;

import java.util.List;

import jdbc.repository.entity.RentAreaEntity;

public interface RentAreaRepository {
	List<RentAreaEntity> findByBuildingId(Long buildingId);
}
