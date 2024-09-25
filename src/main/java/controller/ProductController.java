package controller;

import entities.Product;
import model.ProductModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "productController", eager = true)
@SessionScoped
public class ProductController {

    private ProductModel pm = new ProductModel();
    private Product p = new Product();

    private Product productId;

    public Product getP() {
        return p;
    }

    public void setP(Product p) {
        this.p = p;
    }

    public Product getProductId() {return productId;}

    public void setProductId(Product productId) {
        this.productId = productId;
    }

    public List<Product> findAll() {return pm.findAll();}

    public String add() {
        this.pm.create(this.p);
        this.p = new Product();
        return "productList";
    }

    public void delete(Product p) {
        this.pm.delete(p);
    }

    public String edit(Product p) {
        this.p = p;
        return "edit";
    }

    public String edit() {
        this.pm.edit(this.p);
        return "productList";
    }

}
