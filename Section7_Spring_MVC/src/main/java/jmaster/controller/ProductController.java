package jmaster.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import jmaster.model.ProductDTO;
import jmaster.service.ProductService;
import jmaster.validator.ProductValidator;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductValidator productValidator;
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addProduct(HttpServletRequest httpServletRequest) {

		httpServletRequest.setAttribute("product", new ProductDTO());
		return "addProduct";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addProduct(HttpServletRequest httpServletRequest, @ModelAttribute("product") ProductDTO product,
			BindingResult bindingResult) throws IOException {
		productValidator.validate(product, bindingResult);
		if (bindingResult.hasErrors()) {
			return "addProduct";
		}
		MultipartFile file = product.getImageUrl();
		File newFile = new File(
				"C:\\Users\\Admin\\Desktop\\class-spring08\\Spring\\src\\main\\webapp\\resources\\image\\"
						+ file.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();

		product.setImg(file.getOriginalFilename());
		productService.addProduct(product);
		return "redirect:/product/view-product";
	}

	@RequestMapping(value = "/view-product", method = RequestMethod.GET)
	public String getAllUsers(HttpServletRequest httpServletRequest) {

		List<ProductDTO> allProducts = productService.getAllProducts();
		httpServletRequest.setAttribute("allProducts", allProducts);
		return "viewProduct";
	}

	@RequestMapping(value = "/detail/{productId}", method = RequestMethod.GET)
	public String inDetail(HttpServletRequest request, @PathVariable(value = "productId") int productId) {
		request.setAttribute("product", productService.getProductById(productId));
		return "viewProductInDetail";
	}

	@RequestMapping(value = "/edit/{productId}", method = RequestMethod.GET)
	public String editUser(HttpServletRequest request, @PathVariable(value = "productId") int productId) {

		request.setAttribute("product", productService.getProductById(productId));
		return "editProduct";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editUser(HttpServletRequest httpServletRequest, @ModelAttribute(value = "product") ProductDTO product,
			BindingResult bindingResult) throws IOException {
		productValidator.validate(product, bindingResult);
		if (bindingResult.hasErrors()) {
			return "editProduct";
		}
		MultipartFile file = product.getImageUrl();
		File newFile = new File(
				"C:\\Users\\Admin\\Desktop\\class-spring08\\Spring\\src\\main\\webapp\\resources\\image\\"
						+ file.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		fileOutputStream.write(file.getBytes());
		fileOutputStream.close();

		product.setImg(file.getOriginalFilename());
		productService.addProduct(product);
		productService.updateProduct(product);
		return "redirect:/product/view-product";
	}

	@RequestMapping("/delete/{productId}")
	public String deleteProduct(HttpServletRequest httpServletRequest,
			@PathVariable(value = "productId") int productId) {

		productService.deleteProduct(productId);
		return "redirect:/product/view-product";
	}
}
