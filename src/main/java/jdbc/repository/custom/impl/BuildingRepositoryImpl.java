//package jdbc.repository.custom.impl;
//
//import java.util.List;
//
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Repository;
//
//
//import jdbc.Builder.BuildingSearchBuilder;
//import jdbc.repository.custom.BuildingRepositoryCustom;
//import jdbc.repository.entity.BuildingEntity;
//@Primary
//@Repository
//public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
////	@PersistenceContext
////	private EntityManager entityManager;
////	@Override
//	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
//		// JPQL
////		String sql = "FROM BuildingEntity b";
////		Query query = entityManager.createQuery(sql, BuildingEntity.class);
////		return query.getResultList();
//		
//		// sql native
////		String sql = "SELECT * FROM Building b WHERE b.name like '%building%'";
////		Query query = entityManager.createNativeQuery(sql, BuildingEntity.class);
////		return query.getResultList();
//		return null;
//	} 
//	
//}
//
//
//
