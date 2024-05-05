package com.example.demo.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.model.dto.Employee;
import com.example.demo.utility.DbQueries;
import com.example.demo.utility.DbUtility;

@Repository
public class EmployeeRepository implements RepositoryContract<Employee, Integer> {

	@Override
	public List<Employee> getRecords() throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		Statement statement = null;
		ResultSet records = null;
		List<Employee> employeeRecords = null;
		try {
			connection = DbUtility.createConnection();

			statement = connection.createStatement();
			records = statement.executeQuery(DbQueries.SELECT_ALL_QUERY);

			if (records != null) {

				employeeRecords = new ArrayList<Employee>();

				while (records.next()) {
//					int id = records.getInt("id");
//					String name = records.getString("name");
//					double salary = records.getDouble("salary");

					int product_id = records.getInt("product_id");
					String product_name = records.getString("product_name");
					double price = records.getDouble("price");
					String desciption = records.getString("description");
					String product_code = records.getString("product_code");
					Date release_date = records.getDate("release_date");
					String image_url = records.getString("image_url");
					Double star_rating = records.getDouble("star_rating");
					java.sql.Date sqlDate = new java.sql.Date(release_date.getTime());

					Employee employee = new Employee(product_id, product_name, price, desciption, product_code, sqlDate,
							image_url, star_rating);

//					Employee employee = new Employee(id, name, salary);
					employeeRecords.add(employee);
				}
			}
			return employeeRecords;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	@Override
	public Employee getRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet records = null;
		Employee employee = null;
		try {
			connection = DbUtility.createConnection();

			statement = connection.prepareStatement(DbQueries.SELECT_SINGLE_QUERY);
			statement.setInt(1, id);
			records = statement.executeQuery();

			if (records != null) {

				while (records.next()) {
//					int eid = records.getInt("id");
//					String name = records.getString("name");
//					double salary = records.getDouble("salary");
					int product_id = records.getInt("product_id");
					String product_name = records.getString("product_name");
					double price = records.getDouble("price");
					String desciption = records.getString("description");
					String product_code = records.getString("product_code");
					Date release_date = records.getDate("release_date");
					String image_url = records.getString("image_url");
					Double star_rating = records.getDouble("star_rating");
					java.sql.Date sqlDate = new java.sql.Date(release_date.getTime());

					employee = new Employee(product_id, product_name, price, desciption, product_code, sqlDate,
							image_url, star_rating);

//					employee = new Employee(eid, name, salary);
				}
			}

			return employee;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	@Override
	public Integer addRecord(Employee modelObject) throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbUtility.createConnection();
			statement = connection.prepareStatement(DbQueries.INSERT_QUERY);
			statement.setInt(1, modelObject.getProduct_id());
			statement.setString(2, modelObject.getProduct_name());
			statement.setDouble(3, modelObject.getPrice());
			statement.setString(4, modelObject.getDescription());
			statement.setString(5, modelObject.getProduct_code());
			statement.setDate(6, modelObject.getRelease_date());
			statement.setString(7, modelObject.getImage_url());
			statement.setDouble(8, modelObject.getStar_rating());
			int res = statement.executeUpdate();
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}

	}

	@Override
	public Integer updateRecord(Integer id, Employee modelObject)
			throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbUtility.createConnection();
			statement = connection.prepareStatement(DbQueries.UPDATE_QUERY);

			statement.setString(1, modelObject.getProduct_name());
			statement.setDouble(2, modelObject.getPrice());
			statement.setString(3, modelObject.getDescription());
			statement.setString(4, modelObject.getProduct_code());
			statement.setDate(5, modelObject.getRelease_date());
			statement.setString(6, modelObject.getImage_url());
			statement.setDouble(7, modelObject.getStar_rating());

			statement.setInt(8, id);

			// statement.setInt(3, id);
			return statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

	@Override
	public Integer deleteRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = DbUtility.createConnection();
			statement = connection.prepareStatement(DbQueries.DELETE_QUERY);
			statement.setInt(1, id);
			return statement.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null)
				connection.close();
		}
	}

}