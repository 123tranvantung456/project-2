package jdbc.repository.iplm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import jdbc.repository.BuildingReponsitory;
import jdbc.repository.entity.BuildingEntity;
import jdbc.util.ConnectionUtil;
import jdbc.util.NumberUtil;
import jdbc.util.StringUtil;
@Repository
public class BuildingRepositoryImpl implements BuildingReponsitory{
	public void joinTable (Map<String, Object> map, List<String> typeCode, StringBuilder sql) {
		String staffId = (String)map.get("staffId");
		if (StringUtil.checkString(staffId)) {
			sql.append("INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		} 
		if (typeCode != null && typeCode.size() != 0) {
			sql.append("INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append("INNER JOIN renttype ON buildingrenttype.renttypeid = renttype.id ");
		}
		String areaFrom = (String)map.get("areaFrom");
		String areaTo = (String)map.get("areaTo");  
		if (StringUtil.checkString(areaFrom) || StringUtil.checkString(areaTo)) {
			sql.append("INNER JOIN rentarea ON b.id = rentarea.buildingid ");
		}
	}
	
	public void queryNormal(Map<String, Object> map, List<String> typeCode, StringBuilder where) {
		for (Map.Entry<String, Object> item : map.entrySet()) {
			if (!item.getKey().equals("staffId") && !item.getKey().equals("typeCode") && !item.getKey().startsWith("area")
				&& !item.getKey().startsWith("price")) {
				if(NumberUtil.CheckNumber(item.getKey())) {
					where.append("AND " + item.getKey() + " = " + item.getValue() + " ");
				}
				else {
					where.append("AND " + item.getKey() + " like '%" + item.getValue() + "%' ");
				}
			}
		}
	}
	
	public void querySpecial(Map<String, Object> map, List<String> typeCode, StringBuilder where) {
		String staffId = (String)map.get("staffId");
		if (StringUtil.checkString(staffId)) {
			where.append("AND assignmentbuilding.staffId = " + staffId + " ");
		}
		if (typeCode != null && typeCode.size() != 0) {
			List<String> typeCodeTemp = new ArrayList<String>();
			for (String str : typeCode) {
				typeCodeTemp.add("'" + str + "'");  
			}
			where.append("AND renttype.code IN (" + String.join(",", typeCodeTemp)+ ") ");
		}
		String areaFrom = (String)map.get("areaFrom");
		if (StringUtil.checkString(areaFrom)) {
			where.append("AND rentarea.value >= " + areaFrom + " ");
		}
		String areaTo = (String)map.get("areaTo");
		if (StringUtil.checkString(areaTo)) {
			where.append("AND rentarea.value <= " + areaTo + " ");
		}
		String priceFrom = (String)map.get("priceFrom");
		if (StringUtil.checkString(priceFrom)) {
			where.append("AND b.rentprice >= " + priceFrom + " ");
		}
		String priceTo = (String)map.get("priceTo");
		if (StringUtil.checkString(priceTo)) {
			where.append("AND b.rentprice <= " + priceTo + " ");
		}
	}
	
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> map, List<String> typeCode) {
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.districtid, b.street, b.ward, b.numberofbasement, b.floorarea, b.rentprice, b.managerphonenumber, b.servicefee, b.brokeragefee FROM building b ");
		joinTable(map, typeCode, sql);
		StringBuilder where = new StringBuilder("WHERE 1 = 1 ");
		queryNormal(map, typeCode, where);
		querySpecial(map, typeCode, where);
		sql.append(where);
		sql.append("GROUP BY b.id");
		try(Connection cnn = ConnectionUtil.getConnection();
				 Statement statement = cnn.createStatement();
				 ResultSet resultSet = statement.executeQuery(sql.toString());){
			while (resultSet.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setBrokeragefee(resultSet.getLong("brokeragefee"));
				buildingEntity.setDistrictid(resultSet.getLong("districtid"));
				buildingEntity.setFloorArea(resultSet.getLong("floorarea"));
				buildingEntity.setId(resultSet.getLong("id"));
				buildingEntity.setName(resultSet.getString("name"));
				buildingEntity.setRentprice(resultSet.getLong("rentprice"));
				buildingEntity.setServicefee(resultSet.getString("servicefee"));
				buildingEntity.setStreet(resultSet.getString("street"));
				buildingEntity.setWard(resultSet.getString("ward"));
				results.add(buildingEntity);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
