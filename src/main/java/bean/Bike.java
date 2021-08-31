package bean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;


public class Bike implements Serializable {
    private static final long serialVersionUID = 3L;
    private int id;
    private String name;
    private int year;
    private String description;
    private double price;
    private String image;
    private boolean ordered;
    private boolean deleted;

    public Bike() {
    }
    /**
     * Create new object with the given values
     *
     * @param id                  - id of Bike
     * @param name                - name of Bike
     * @param year                - year of publication of the Bike
     * @param description         - description of Bike
     * @param price               - price of Bike
     * @param image               - image of Bike
     * @param deleted           - meaning the Bike is deleted or not
     * @param ordered           - meaning the Bike is ordered or not
     */
    public Bike(int id, String name, int year, String description, double price, String image, boolean ordered, boolean deleted) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ordered = ordered;
        this.deleted = deleted;
    }

    public Bike(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Bike(String name, int year, String description, double price, String image, boolean ordered, boolean deleted) {
        this.name = name;
        this.year = year;
        this.description = description;
        this.price = price;
        this.image = image;
        this.ordered = ordered;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public static class NameComparator implements Comparator<Bike> {
        @Override
        public int compare(Bike o1, Bike o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public static class YearComparator implements Comparator<Bike> {
        @Override
        public int compare(Bike o1, Bike o2) {
            Integer a = o1.getYear();
            Integer b = o2.getYear();
            return a.compareTo(b);
        }
    }
}

