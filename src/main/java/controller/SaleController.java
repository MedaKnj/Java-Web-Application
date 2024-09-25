package controller;
import entities.Buyer;
import entities.Product;
import entities.Sale;
import model.SaleModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "saleController", eager = true)
@SessionScoped
public class SaleController {

    private SaleModel sm = new SaleModel();
    private Sale s = new Sale();

    private Product product;
    Buyer buyer = new Buyer();

    int quantitySale;

    private double price;

    protected SessionFactory sessionFactory;
    protected Session session;


    private Sale saleId;

    public SaleModel getSm() {
        return sm;
    }

    public void setSm(SaleModel sm) {
        this.sm = sm;
    }

    public Sale getS() {
        return s;
    }

    public void setS(Sale s) {
        this.s = s;
    }

    public Sale getSaleId() {
        return saleId;
    }

    public void setSaleId(Sale saleId) {
        this.saleId = saleId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void setBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    public int getQuantitySale() {
        return quantitySale;
    }

    public void setQuantitySale(int quantitySale) {
        this.quantitySale = quantitySale;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Sale> findAll() {

        return sm.findAll();
    }

    public String add() {
        this.sm.create(this.s);
        this.s = new Sale();
        return "sales";
    }

    public String recordSale(int buyerId, int productId, int quantitySale) {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            // Get the buyer and product
            Buyer buyer = session.get(Buyer.class, buyerId);
            Product product = session.get(Product.class, productId);

            // Ažuriranje stanja proizvoda
            int updatedQuantity = product.getQuantity() - quantitySale;
            product.setQuantity(updatedQuantity);
            session.merge(product);

            // Ažuriranje stanja kupca
            double updatedBalance = (buyer.getBalance() - (product.getPrice() * quantitySale));
            buyer.setBalance(updatedBalance);
            session.merge(buyer);

            // Create a new Sale
            Sale sale = new Sale();
            sale.setBuyer(buyer);
            sale.setProduct(product);
            sale.setQuantitySale(quantitySale);
            sale.setPrice(product.getPrice() * quantitySale);

            // Save the Sale
            session.persist(sale);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return "sales";
    }

    }



