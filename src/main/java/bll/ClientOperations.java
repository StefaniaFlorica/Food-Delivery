package bll;

import model.Client;
import model.MenuItem;
import model.Order;

import java.util.List;
import java.util.Set;

public interface ClientOperations {

     void registerClient(String username, String password);
     Client loginClient(String user, String password);
     Order createNewOrder(Client client, List<MenuItem> selectedItems);
     Set<MenuItem> filterByKeyword(String keyword,Set<MenuItem> menuItems);
     Set<MenuItem> filterByRating(double rating,double rating2,Set<MenuItem> menuItems);
     Set<MenuItem> filterByCalories(int calories,int caloriess,Set<MenuItem> menuItems);
     Set<MenuItem> filterByProteins(int proteins,int proteinss,Set<MenuItem> menuItems);
     Set<MenuItem> filterByFats(int fats,int fatss,Set<MenuItem> menuItems);
     Set<MenuItem> filterBySodium(int sodium,int sodiums,Set<MenuItem> menuItems);
     Set<MenuItem> filterByPrice(double price,double prices,Set<MenuItem> menuItems);
}
