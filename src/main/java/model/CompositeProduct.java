package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeProduct extends MenuItem implements Serializable {
    private List<MenuItem> menuItems= new ArrayList<MenuItem>();

    public CompositeProduct()
    {

    }

    public CompositeProduct(String title, List<MenuItem> menuItems1) {
        menuItems=menuItems1;
        setTitle(title);
        computeTitle();
        computeRating();
        computeCalories();
        computeProteins();
        computeFats();
        computeSodium();
        computePrice();
    }


    public void add(MenuItem item)
    {
        menuItems.add(item);
    }

    public MenuItem getItem(int indexItem)
    {
        return menuItems.get(indexItem);
    }
    public void remove(MenuItem item) {
        menuItems.remove(item);
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    @Override
    public String computeTitle() {
        setTitle(getTitle()+": "+ menuItems.stream().map(MenuItem::computeTitle).collect(Collectors.joining(", ")));
        return getTitle();
    }


    @Override
    public double computePrice() {
        setPrice(menuItems.stream().mapToDouble(MenuItem::getPrice).sum());
        return getPrice();
    }

    @Override
    public int computeCalories() {
        setCalories(menuItems.stream().mapToInt(MenuItem::getCalories).sum());
        return getCalories();
    }

    @Override
    public int computeProteins() {
        setProteins(menuItems.stream().mapToInt(MenuItem::getProteins).sum());
        return getProteins();
    }

    @Override
    public int computeFats() {
        setFats(menuItems.stream().mapToInt(MenuItem::getFats).sum());
        return getFats();
    }

    @Override
    public int computeSodium() {
        setSodium(menuItems.stream().mapToInt(MenuItem::getSodium).sum());
        return getSodium();
    }

    @Override
    public double computeRating() {
        setRating(menuItems.stream().mapToDouble(MenuItem::getRating).average().orElse(0));
        return getRating();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeProduct that = (CompositeProduct) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 && Objects.equals(menuItems, that.menuItems) && Objects.equals(getTitle(), that.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPrice());
    }

    @Override
    public String toString() {
        String result="CompositeProduct: ";
        for(MenuItem menuItem: menuItems)
        {
            result+=menuItem.getTitle()+" & ";
        }
        return result;
    }
}
