package jdbc.util.db;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAL_Util {
	private static String url = "jdbc:mysql://localhost:3306/estatebasic?autoReconnect=true&useSSL=false";
	private static String username = "root";
	private static String password = "123456";
	private static final DAL_Util instance = new DAL_Util();

	private DAL_Util() {

	}

	public static DAL_Util getInstance() {
		return instance;
	}

	private <T> void setPreparedStatementValues(PreparedStatement preparedStatement, T data, EnumDB method)
			throws SQLException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException,
			SecurityException {
		int index = 1;
		if (method == EnumDB.CREATE || method == EnumDB.UPDATE) {
			Class<?> clazz = data.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field item : fields) {
				item.setAccessible(true);
				String fieldName = item.getName();
				if (fieldName.equals("id")) {
					continue;
				}
				Object value = item.get(data);
				if (value instanceof String) {
					preparedStatement.setString(index, (String) value);
				} else if (value instanceof Long) {
					preparedStatement.setLong(index, (Long) value);
				} else if (value instanceof Integer) {
					preparedStatement.setInt(index, (Integer) value);
				} else {
					preparedStatement.setObject(index, value);
				}
				index++;
			}
			if (method == EnumDB.UPDATE) {
				Field idField = clazz.getDeclaredField("id");
				idField.setAccessible(true);
				preparedStatement.setLong(index, (Long) idField.get(data));
			}
		}
		if (method == EnumDB.DELETE) {
			preparedStatement.setLong(index, (Long) data);
		}
	}

	public boolean executeUpdate(Object sql, Object data, EnumDB method) {
		try (Connection cnn = DriverManager.getConnection(url, username, password);
				PreparedStatement preparedStatement = cnn.prepareStatement(sql.toString());) {
			setPreparedStatementValues(preparedStatement, data, method);
			int rows = preparedStatement.executeUpdate();
			return rows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private <T> T mapResultSetToEntity(ResultSet resultSet, Class<T> clazz) 
			throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T instance = clazz.getDeclaredConstructor().newInstance();
		Field[] fields = clazz.getDeclaredFields();
		for (Field item : fields) {
			item.setAccessible(true);
			String fieldName = item.getName();
			Object value = resultSet.getObject(fieldName);
			if (value != null) {
				//////////////////////////////////////////////////////////
				if (item.getType() == Long.class && value instanceof Integer) {
					item.set(instance, (Integer)value + 1L - 1L);
				}
				/////////////////////////////////////////////////////////
				else item.set(instance, value);
			}
		}
		return instance;
	} 
	
	public <T> ExecuteQuery<T> executeQuery (Object sql, Class<T> clazz) {
		List<T> results = new ArrayList<T>(); 
		try (Connection cnn = DriverManager.getConnection(url, username, password);
				Statement statement = cnn.createStatement();
				ResultSet resultSet = statement.executeQuery(sql.toString());) {
				while (resultSet.next()) {
					results.add(mapResultSetToEntity(resultSet, clazz));
				}
				return new ExecuteQuery<T>(results) ;
		} catch (Exception e) {
			e.printStackTrace();
			return null; 
		}
	}
}

 
