package Service;

import java.util.ArrayList;

import lv.venta.model.Category;
import lv.venta.model.Product;

public interface ICRUDprodService {
//CRUD
	//C - create
	public abstract Product createNewProduct(Product newProduct) throws Exception;
	public abstract ArrayList<Product> retrieveAllProducts() throws Exception;
	//R-retrieve by id
	public abstract Product retrieveById(int id) throws Exception;
	//U-update by id
	 public abstract Product updateProductbyId(int id, float price, Category category, String description,int quantity) throws Exception;
	//D - delete by id
	 public abstract void deleteProduct(int id) throws Exception;
}
