package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lv.venta.Service.ICRUDprodService;
import lv.venta.model.Product;

@Controller
@RequestMapping("/product/crud")
public class ProductCRUDController {
	@Autowired
	private ICRUDprodService prodService;

	@GetMapping("/all")//http://localhost:8080/product/crud/all
	public String getControllerRetrieveAllProducts(Model model) {
		try {
			ArrayList<Product> ProductFromDB = prodService.retrieveAllProducts();
			model.addAttribute("package", ProductFromDB);
			return "show-all-products";
		}

		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	@GetMapping("/all/{id}")
	public String getControllerRetrieveById(@PathVariable(name = "id")int id,Model model) {
		try {
		Product productFromDB = prodService.retrieveById(id);
		model.addAttribute("package", productFromDB);
		return "show-one-product";
	}
		catch(Exception e){
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
		}
	@GetMapping("/add")
	public String getControllerForProductAdding(Model model) {
		
		model.addAttribute("product", new Product());
		return "add-one-product";
	}
	@PostMapping ("/add")
	public String postcontrollerForProductAdding(Product product, Model model) {
		try {
			prodService.createNewProduct(product);
			return "redirect:/product/crud/all";
		}
		catch(Exception e){
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	@GetMapping("/update/{id}")
	public String getControllerForUpdateById(@PathVariable(name = "id") int id, Model model) {

	    try {
	        Product productFromDB = prodService.retrieveById(id);

	        model.addAttribute("product", productFromDB);

	        return "update-one-product";
	    }
	    catch(Exception e) {
	        model.addAttribute("package", e.getMessage());
	        return "error-page";
	    }
	}
	@PostMapping("/update/{id}")
	public String postControllerForUpdateById(@PathVariable(name = "id") int id, Product product,
			Model model) {
		try
		{
			prodService.updateProductbyId(id, product.getPrice(), product.getCategory(),
				product.getDescription(), product.getQuantity());
			return "redirect:/product/crud/all/" + id;
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	@GetMapping("/delete/{id}")
	public String getControllerForDeletion(@PathVariable(name = "id") int id, Model model) {
		try
		{
			prodService.deleteProduct(id);
			ArrayList<Product> productsFromDB = prodService.retrieveAllProducts();
			model.addAttribute("package", productsFromDB);
			return "show-all-products";
		}
		catch (Exception e) {
			model.addAttribute("package", e.getMessage());
			return "error-page";
		}
	}
	
	
}
