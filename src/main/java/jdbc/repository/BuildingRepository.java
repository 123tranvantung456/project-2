package jdbc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jdbc.repository.custom.BuildingRepositoryCustom;
import jdbc.repository.entity.BuildingEntity;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>, BuildingRepositoryCustom{
	void deleteByIdIn(List<Long> ids);
	List<BuildingEntity> findByNameContainingAndStreet(String s1, String s2);
}
  