package com.example.demo.model.services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.dto.Employee;
import com.example.demo.model.repository.RepositoryContract;

@Service
public class EmployeeService implements ServiceContract<Employee, Integer> {

	@Autowired
	private RepositoryContract<Employee, Integer> _repository;

	@Override
	public List<Employee> fetchRecords(int sortingChoice) throws ClassNotFoundException, SQLException, Exception {
		try {
			List<Employee> employees = _repository.getRecords();
			List<Employee> sortedEmployees = null;
			switch (sortingChoice) {
			case 1:
				sortedEmployees = employees.stream()
						.sorted((e1, e2) -> Integer.compare(e1.getProduct_id(), e2.getProduct_id())).toList();
				break;

			case 2:
				sortedEmployees = employees.stream()
						.sorted((e1, e2) -> e1.getProduct_name().compareTo(e2.getProduct_name())).toList();
				break;

			case 3:
				sortedEmployees = employees.stream().sorted((e1, e2) -> Double.compare(e1.getPrice(), e2.getPrice()))
						.toList();
				break;

			default:
				sortedEmployees = employees.stream()
						.sorted((e1, e2) -> Integer.compare(e1.getProduct_id(), e2.getProduct_id())).toList();
				break;
			}
			return sortedEmployees;

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Employee fetchRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		try {
			if (id <= 0)
				throw new IllegalArgumentException("the employee id should be greater than zero");

			return _repository.getRecord(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Integer insertRecord(Employee modelObject) throws ClassNotFoundException, SQLException, Exception {
		try {
			if (modelObject == null)
				throw new NullPointerException("employee object is NULL");
			// modelObject.setId(0);
			return _repository.addRecord(modelObject);
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Integer modifyRecord(Integer id, Employee modelObject)
			throws ClassNotFoundException, SQLException, Exception {
		try {
			if (modelObject == null)
				throw new NullPointerException("employee object is NULL");

			if (id <= 0)
				throw new IllegalArgumentException("the employee id should be greater than zero");

			return _repository.updateRecord(id, modelObject);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (NullPointerException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Integer removeRecord(Integer id) throws ClassNotFoundException, SQLException, Exception {
		try {
			if (id <= 0)
				throw new IllegalArgumentException("the employee id should be greater than zero");

			return _repository.deleteRecord(id);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}