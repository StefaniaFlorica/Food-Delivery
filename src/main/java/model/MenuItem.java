package model;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String title;
    private double rating;
    private int calories;
    private int proteins;
    private int fats;
    private int sodium;
    private double price;

    public MenuItem(String title, double rating, int calories, int proteins, int fats, int sodium, double price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.proteins = proteins;
        this.fats = fats;
        this.sodium = sodium;
        this.price = price;
    }

    public MenuItem()
    {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getSodium() {
        return sodium;
    }

    public void setSodium(int sodium) {
        this.sodium = sodium;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void replaceItem(MenuItem newMenuItem)
    {
        title=newMenuItem.getTitle();
        rating=newMenuItem.getRating();
        calories=newMenuItem.getCalories();
        proteins=newMenuItem.getProteins();
        fats=newMenuItem.getFats();
        sodium=newMenuItem.getSodium();
        price=newMenuItem.getPrice();

    }
    public abstract String computeTitle();
    public abstract double computePrice();
    public abstract int computeCalories();
    public abstract int computeProteins();
    public abstract int computeFats();
    public abstract int computeSodium();
    public abstract double computeRating();

}
