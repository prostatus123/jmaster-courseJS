package jmaster.dao;

import java.util.List;

import jmaster.entity.Product;

public interface ProductDao {
	void add(Product product);

	void update(Product product);

	void delete(int id);

	Product get(int id);

	List<Product> getAll();
}
