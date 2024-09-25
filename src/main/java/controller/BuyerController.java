package controller;
import entities.Buyer;
import entities.Product;
import model.BuyerModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;


@ManagedBean(name = "buyerController", eager = true)
@SessionScoped
public class BuyerController {

    private BuyerModel bm = new BuyerModel();
    private Buyer b = new Buyer();

    private Buyer buyerId;

    public Buyer getB() {
        return b;
    }

    public void setB(Buyer b) {
        this.b = b;
    }

    public Buyer getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Buyer buyerId) {
        this.buyerId = buyerId;
    }

    public List<Buyer> findAll() {
        return bm.findAll();
    }

    public String add() {
        this.bm.create(this.b);
        this.b = new Buyer();
        return "buyerList";
    }

    public String edit(Buyer b) {
        this.b = b;
        return "buyerEdit";
    }

    public String edit() {
        this.bm.edit(this.b);
        return "buyerList";
    }

    public void delete(Buyer b) {
        this.bm.delete(b);
    }

}
