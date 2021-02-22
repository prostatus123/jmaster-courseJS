package jmaster.service.impl;

import jmaster.dao.ProductDao;
import jmaster.entity.Product;
import jmaster.model.ProductDTO;
import jmaster.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Override
    public void addProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setDescription(productDTO.getDescription());
        product.setImg(productDTO.getImg());
        productDao.add(product);
    }

    @Override
    public void updateProduct(ProductDTO productDTO) {
        Product product = productDao.get(productDTO.getId());
        if(product != null){
            product.setName(productDTO.getName());
            product.setQuantity(productDTO.getQuantity());
            product.setPrice(productDTO.getPrice());
            product.setDescription(productDTO.getDescription());
            product.setImg(productDTO.getImg());
            productDao.update(product);
        }
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productDao.get(id);
        if(product != null){
            productDao.delete(id);
        }
    }

    @Override
    public ProductDTO getProductById(int id) {
        Product product = productDao.get(id);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(product.getName());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setPrice(product.getPrice());
        productDTO.setDescription(product.getDescription());
        productDTO.setImg(product.getImg());

        return productDTO;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productDao.getAll();
        List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();
        for(Product product : products){
            ProductDTO productDTO = new ProductDTO();

            productDTO.setId(product.getId());
            productDTO.setName(product.getName());
            productDTO.setQuantity(product.getQuantity());
            productDTO.setPrice(product.getPrice());
            productDTO.setDescription(product.getDescription());
            productDTO.setImg(product.getImg());

            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
