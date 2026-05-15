package lv.venta.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String getCpntrollerRetrieveById(@PathVariable(name = "id")int id,Model model) {
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
}

