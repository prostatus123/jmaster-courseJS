package jmaster.service;

import java.util.List;

import jmaster.model.ProductDTO;

public interface ProductService {
    void addProduct(ProductDTO product);

    void updateProduct(ProductDTO product);

    void deleteProduct(int id);

    ProductDTO getProductById(int id);

    List<ProductDTO> getAllProducts();
}
