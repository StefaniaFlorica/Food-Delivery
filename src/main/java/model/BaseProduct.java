package model;

import java.io.Serializable;
import java.util.Objects;

public class BaseProduct extends MenuItem implements Serializable {

    public BaseProduct(String title, double rating, int calories, int proteins, int fats, int sodium, double price) {
        super(title, rating, calories, proteins, fats, sodium, price);
    }

    @Override
    public String computeTitle() {
        return getTitle();
    }


    @Override
    public double computePrice() {
        return getPrice();
    }

    @Override
    public int computeCalories() {
        return getCalories();
    }

    @Override
    public int computeProteins() {
        return getProteins();
    }

    @Override
    public int computeFats() {
        return getFats();
    }

    @Override
    public int computeSodium() {
        return getSodium();
    }

    @Override
    public double computeRating() {
        return getRating();
    }


    public String getName() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseProduct that = (BaseProduct) o;
        return Double.compare(that.getRating(), getRating()) == 0 && getCalories() == that.getCalories() && getProteins() == that.getProteins() && getFats() == that.getFats() && getSodium()== that.getSodium() && Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getRating(), getCalories(), getProteins(), getFats(), getSodium(), getPrice());
    }

    @Override
    public String toString() {
        return "BaseProduct:" + getTitle();
    }
}
