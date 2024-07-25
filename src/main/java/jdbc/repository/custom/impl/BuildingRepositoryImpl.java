package jdbc.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import jdbc.Builder.BuildingSearchBuilder;
import jdbc.repository.custom.BuildingRepositoryCustom;
import jdbc.repository.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	public void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			sql.append("INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			sql.append("INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append("INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
		}
		Long areaFrom = buildingSearchBuilder.getAreaFrom();
		Long areaTo = buildingSearchBuilder.getAreaTo();
		if (areaFrom != null || areaTo != null) {
			sql.append("INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}
	}

	public void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		try {
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
						&& !fieldName.startsWith("price")) {
					Object value = item.get(buildingSearchBuilder);
					if (value != null) {
						if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")
								|| item.getType().getName().equals("java.lang.Float")) {
							where.append("AND b." + fieldName + " = " + value + " ");
						} else {
							where.append("AND b." + fieldName + " like '%" + value + "%' ");
						}
					}
				} 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
		Long staffId = buildingSearchBuilder.getStaffId();
		if (staffId != null) {
			where.append("AND assignmentbuilding.staffId = " + staffId + " ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if (typeCode != null && typeCode.size() != 0) {
			List<String> typeCodeTemp = new ArrayList<String>();
			for (String str : typeCode) {
				typeCodeTemp.add("'" + str + "'");
			}
			where.append("AND renttype.code IN (" + String.join(",", typeCodeTemp) + ") ");
		} 
		Long areaFrom = buildingSearchBuilder.getAreaFrom();
		if (areaFrom != null) {
			where.append("AND rentarea.value >= " + areaFrom + " "); 
		}
		Long areaTo = buildingSearchBuilder.getAreaTo();
		if (areaTo != null) {
			where.append("AND rentarea.value <= " + areaTo + " ");
		}
		Long priceFrom = buildingSearchBuilder.getPriceFrom();
		if (priceFrom != null) {
			where.append("AND b.rentprice >= " + priceFrom + " ");
		}
		Long priceTo = buildingSearchBuilder.getPriceTo();
		if (priceTo != null) {
			where.append("AND b.rentprice <= " + priceTo + " ");
		}
	}

	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder(
				"SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		sql.append(where);
		sql.append("GROUP BY b.id");
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		return query.getResultList();
	}
}
