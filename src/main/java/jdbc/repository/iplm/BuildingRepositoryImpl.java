package jdbc.repository.iplm;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import jdbc.Builder.BuildingSearchBuilder;
import jdbc.repository.BuildingReponsitory;
import jdbc.repository.entity.BuildingEntity;
@Repository
public class BuildingRepositoryImpl implements BuildingReponsitory{
	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		// JPQL
//		String sql = "FROM BuildingEntity b";
//		Query query = entityManager.createQuery(sql, BuildingEntity.class);
//		return query.getResultList();
		
		// sql native
		String sql = "SELECT * FROM Building b WHERE b.name like '%building%'";
		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
		return query.getResultList();
	}
	@Override
	public void create(BuildingEntity buildingEntity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(BuildingEntity buildingEntity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete(List<Long> ids) {
		// TODO Auto-generated method stub
		
	}
	
}
