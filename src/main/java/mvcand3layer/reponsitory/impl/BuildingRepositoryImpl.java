package mvcand3layer.reponsitory.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import mvcand3layer.reponsitory.BuildingRepository;
import mvcand3layer.reponsitory.entity.BuildingEntity;

@Repository
public class BuildingRepositoryImpl implements BuildingRepository{
	private static String url = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "123456";
	@Override
	public List<BuildingEntity> findAll(String name, Integer district) {
		List<BuildingEntity> results = new ArrayList<BuildingEntity>();
		StringBuilder sql = new StringBuilder("SELECT * FROM building WHERE 1 = 1 ");
		if (name != null && !name.equals("")) {
			sql.append("AND name like '%" + name + "%' ");
		}
		if (district != null) {
			sql.append("AND districtid = " + district + " ");
		}
		try (Connection cnn = DriverManager.getConnection(url, username, password);
			 Statement statement = cnn.createStatement();
			 ResultSet resultSet = statement.executeQuery(sql.toString());
				){	
			while(resultSet.next()) {
				BuildingEntity buildingEntity = new BuildingEntity();
				buildingEntity.setName(resultSet.getString("name"));
				buildingEntity.setNumber(resultSet.getInt("numberofbasement"));
				buildingEntity.setWard(resultSet.getString("ward"));
				results.add(buildingEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
