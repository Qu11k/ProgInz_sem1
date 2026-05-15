package Service.implementation;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Service.ICRUDprodService;
import lv.venta.model.Category;
import lv.venta.model.Product;
import lv.venta.repo.IProductRepo;

@Service
public class CRUDProductServiceImpl implements ICRUDprodService {
	@Autowired
	private IProductRepo prodRepo;
	@Override
	public Product createNewProduct(Product newProduct) throws Exception {
		if (newProduct==null) {
			throw new Exception("produkts nav pieejams");
		}
		if(prodRepo.existsByTitle(newProduct.getTitle())) {
			throw new Exception("jau eksiste produkts");
		}
		if(newProduct.getTitle()==null || newProduct.getTitle().isEmpty()||newProduct.getCategory()==null||newProduct.getPrice()<=0||newProduct.getPrice()>100000||newProduct.getDescription()==null||newProduct.getQuantity()<0||newProduct.getQuantity()>100000) {
			throw new Exception("nav korekti ievades dati");
		}
		return prodRepo.save(newProduct);
	}

	@Override
	public ArrayList<Product> retrieveAllProducts() throws Exception {
		if(prodRepo.count()==0) {
			throw new Exception("produktu tabula ir tuksa");
		}
		return (ArrayList<Product>) prodRepo.findAll();
	}

	@Override
	public Product updateProductbyId(int id, float price, Category category, String description, int quantity)
			throws Exception {
		if(id<1) {
			throw new Exception("id nevar but negativs");
		}
		
		return null;
	}

	@Override
	public void deleteProduct(int id) throws Exception {
		if(id < 1) {
	        throw new Exception("id nevar but negativs");
	    }

	    if(!prodRepo.existsById(id)) {
	        throw new Exception("produkts ar id " + id + " neeksiste");
	    }

	    prodRepo.deleteById(id);
	}
	

	@Override
	public Product retrieveById(int id) throws Exception {
		// TODO Auto-generated method stub
		if(id<1) {
			throw new Exception("id nevar but negativs");
		}
		if(!prodRepo.existsById(id)) {
			throw new Exception("produkts ar id"+id+ "neeksiste");
		}
			return prodRepo.findById(id).get();
	}

}
