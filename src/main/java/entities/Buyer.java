package entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buyers")
public class Buyer {

    @Id
    @Column(name = "buyer_id", unique=true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int buyerId;
    private String name;

    @Column(name = "last_name")
    private String lastName;
    private String address;
    private double balance;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL)
    private List<Sale> sales = new ArrayList<>();

    public int getBuyerId(int buyerId) {
        return this.buyerId;
    }

    public void setBuyerId(int buyerId) {
        this.buyerId = buyerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBuyerId() {
        return buyerId;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public void setSales(List<Sale> sales) {
        this.sales = sales;
    }

    public Buyer() {
    }

    public Buyer(String name, String lastName, String address, double balance) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "buyerId=" + buyerId +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                ", sales=" + sales +
                '}';
    }
}
