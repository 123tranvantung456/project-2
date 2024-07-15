package jdbc.repository.iplm;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jdbc.repository.RentAreaRepository;
import jdbc.repository.entity.RentAreaEntity;
import jdbc.util.db.DAL_Util;
@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository{

	@Override
	public List<RentAreaEntity> findByBuildingId(Long buildingIdId) {
		String sql = "SELECT r.id, r.value, r.buildingid FROM rentarea r WHERE r.buildingId = " + buildingIdId + " ";
//		List<RentAreaEntity> results = new ArrayList<RentAreaEntity>();
//		try (Connection cnn = ConnectionUtil.getConnection();
//				Statement statement = cnn.createStatement();
//				ResultSet resultSet = statement.executeQuery(sql);) {
//			while (resultSet.next()) {
//				RentAreaEntity rentAreaEntity = new RentAreaEntity();
//				rentAreaEntity.setId(resultSet.getLong("id"));
//				rentAreaEntity.setValue(resultSet.getInt("value"));
//				rentAreaEntity.setBuildingid(resultSet.getLong("buildingid"));
//				results.add(rentAreaEntity);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		List<RentAreaEntity> results = DAL_Util.getInstance().executeQuery(sql, RentAreaEntity.class).toList(); 
		return results;
	}

}
