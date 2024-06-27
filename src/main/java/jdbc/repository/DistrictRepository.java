package jdbc.repository;

import jdbc.repository.entity.DistrictEntity;

public interface DistrictRepository {
	DistrictEntity findById(long id);
}
