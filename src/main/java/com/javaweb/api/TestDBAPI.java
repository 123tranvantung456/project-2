package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.beans.BuildingDTO;

@RestController
public class TestDBAPI {
	private static String url = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "123456";
	@GetMapping(value = "api/test")
	public List<BuildingDTO> get(@RequestParam (name = "name") String name){
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		String sql = "SELECT * FROM building WHERE name like '%" + name + "%'";
		try (Connection cnn = DriverManager.getConnection(url, username, password);
			 Statement statement = cnn.createStatement();
			 ResultSet resultSet = statement.executeQuery(sql);
				){
			while(resultSet.next()) {
				BuildingDTO buildingDTO = new BuildingDTO();
				buildingDTO.setName(resultSet.getString("name"));
				buildingDTO.setNumber(resultSet.getInt("numberofbasement"));
				buildingDTO.setWard(resultSet.getString("ward"));
				results.add(buildingDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
