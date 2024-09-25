package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sale_id")
    private int saleId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;

    @Temporal(TemporalType.DATE)
    @Column(name = "sale_date")
    private Date sale_date = new Date();

    @Column(name = "quantity_sale")
    private int quantitySale;

    private double price;

    // Getteri i setteri


    public Sale() {
    }

    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public Buyer getBuyer() {return buyer;}

    public void setBuyer(Buyer buyer) {this.buyer = buyer;}

    public Product getProduct() {return product;}

    public void setProduct(Product product) {this.product = product;}

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

    public Sale(int saleId, Date sale_date, Buyer buyer, Product product, int quantitySale, double price) {
        this.saleId = saleId;
        this.sale_date = sale_date;
        this.buyer = buyer;
        this.product = product;
        this.quantitySale = quantitySale;
        this.price = price;
    }


    @Override
    public String toString() {
        return "Sale{" +
                "saleId=" + saleId +
                ", sale_date=" + sale_date +
                ", buyer =" + buyer +
                ", product =" + product +
                ", quantitySale=" + quantitySale +
                ", price=" + price +
                '}';
    }
}

