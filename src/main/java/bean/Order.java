package bean;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Order implements Serializable {
    private int id;
    private int userId;
    private int bikeId;
    private String description;
    private Date date;
    private double price;

    public Order() {
    }

    public Order(int userId, int bikeId, String description) {
        this.userId = userId;
        this.bikeId = bikeId;
        this.description = description;
    }

    public Order(int id, int userId, int bikeId, String description, Date date, double price) {
        this.id = id;
        this.userId = userId;
        this.bikeId = bikeId;
        this.description = description;
        this.date = date;
        this.price = price;
    }

    public Order(String description) {
        this.description=description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
