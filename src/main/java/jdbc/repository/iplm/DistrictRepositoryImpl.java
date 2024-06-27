package jdbc.repository.iplm;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import jdbc.repository.DistrictRepository;
import jdbc.repository.entity.DistrictEntity;
import jdbc.util.ConnectionUtil;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity findById(long id) {
		String sql = "SELECT d.id, d.code, d.name FROM district d WHERE d.id = " + id + " ";
		DistrictEntity results = new DistrictEntity();
		try (Connection cnn = ConnectionUtil.getConnection();
				Statement statement = cnn.createStatement();
				ResultSet resultSet = statement.executeQuery(sql);) {
			while (resultSet.next()) {
				results.setId(resultSet.getLong("id"));
				results.setCode(resultSet.getString("code"));
				results.setName(resultSet.getString("name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
