package jmaster.controller.API;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import jmaster.model.ProductDTO;
import jmaster.service.ProductService;

@Controller
public class ProductAPIController {

	@Autowired
	ProductService productService;

	@RequestMapping(value = "/list-product", method = RequestMethod.GET)
	public @ResponseBody List<ProductDTO> getAllUsers(HttpServletRequest httpServletRequest) {

		List<ProductDTO> allProduct = productService.getAllProducts();
		return allProduct;
	}

	@RequestMapping(value = "/prodcut/{productId}", method = RequestMethod.GET)
	public @ResponseBody ProductDTO viewUser(HttpServletRequest request,
			@PathVariable(value = "productId") int productId) {
		return productService.getProductById(productId);
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody void addUser(HttpServletRequest httpServletRequest, @RequestBody ProductDTO product) {
		productService.addProduct(product);
	}

}
