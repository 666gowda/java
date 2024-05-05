package com.example.demo.utility;

public class DbQueries {
	public final static String SELECT_ALL_QUERY = "SELECT * FROM products";
	public final static String SELECT_SINGLE_QUERY = "SELECT * FROM products WHERE product_id=?";
	public final static String INSERT_QUERY = "INSERT INTO products (product_id, product_name, price, description, product_code, release_date, image_url, star_rating) VALUES (?,?,?,?,?,?,?,?)";
	public final static String UPDATE_QUERY = "UPDATE products SET product_name=?, price=?, description=?, product_code=?, release_date=?, image_url=?, star_rating=? WHERE product_id=?";
	public final static String DELETE_QUERY = "DELETE FROM products WHERE product_id=?";

}