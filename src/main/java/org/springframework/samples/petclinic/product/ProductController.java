package org.springframework.samples.petclinic.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    //jsp
    private static final String VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM = "products/createOrUpdateProductForm";
    private static final String VIEWS_PRODUCTS_LIST = "products/listProducts";

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
		this.productService = productService;
	}
    
    // accede a $(types) en jsp siempre que estes en una ruta /products sin tener que hacer model.put
    @ModelAttribute("types")
	public List<ProductType> populateProductTypes() {
		return this.productService.findAllProductTypes();
	}
    
    
    @GetMapping(value = "/create")
	public String initCreationForm(ModelMap model) {
		Product product = new Product();
        model.put("product", product);
		return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/create")
	public String processCreationForm(@Valid Product product, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("product", product);
			return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
		}
		else {
            this.productService.save(product);
            return "welcome"; //esto es lo que pide el enunciado pero he creado otra vista por los loles
//            return "redirect:/product/all";
		}
	}
	
	//Esto es de gratis no viene en el control
	
	@GetMapping(value = "/all")
	public String showProducts(ModelMap model) {
		List<Product> products = productService.getAllProducts();
        model.put("products", products);
		return VIEWS_PRODUCTS_LIST;
	}

	
	@GetMapping(value = "/{productId}/edit")
	public String editProduct(@PathVariable Integer productId, ModelMap model) {
		Product product = productService.findById(productId);
		if(product != null) {
			model.put("product", product);
			return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
		}else {
			return "/oups";
		}
	} 
	
	@PostMapping(value = "/{productId}/edit")
	public String showEditedProduct(@PathVariable int productId, @Valid Product product, BindingResult result, ModelMap model) {
		System.out.println(product.toString());
		if (result.hasErrors()) {			
			model.put("product", product);
			return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
		}
		else {
			product.setId(productId);
			/*es necesario porque el form no guarda datos del id 
			 * otra forma seria escribir en el jsp:
			 * <input name="id" value="${product.id}" hidden=true />
			 */
            this.productService.save(product);
            return "redirect:/product/all"; 
            //redirect manda a ruta y si no pones nada se busca un jsp en la carpeta WEB-INF NO CARGA OBJETOS solo para jsp simples
		}
	}
	
	
    @GetMapping(value = "/create2")
	public String initCreationForm2(ModelMap model) {
		Product product = new Product();
        model.put("product", product);
		return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/create2")
	public String processCreationForm2(@Valid Product product, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("product", product);
			return VIEWS_PRODUCTS_CREATE_OR_UPDATE_FORM;
		}
		else {
            this.productService.save(product);
            return "redirect:/product/all";
		}
	}
	
	
	
	
}