package bll;
import dao.FileWriterr;
import dao.ReceiptWriter;
import dao.Serializer;
import model.Client;
import model.CompositeProduct;
import model.MenuItem;
import model.Order;
import presentation.controllers.Criteria;
import presentation.views.ErrorMessage;

import java.awt.*;
import java.time.ZoneId;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Clasa pentru realizarea operatiilor specifice clientilor si administratorilor unei aplicatiei de food delivery
 */
public class DeliveryService implements Subject,IDeliveryServiceProcessing{
    private final List<Observer> observers;
    private Map<Order,List<MenuItem>> orderHistory;
    private Set<MenuItem> menuItems;
    private Set<MenuItem> filter1;
    private final Set<Client> clients;
    private final Serializer serializer;

    /**
     * Contructorul clasei DeliveryService
     */
    public DeliveryService()
    {
        observers= new ArrayList<Observer>();
        observers.add(new ErrorMessage());
        serializer=new Serializer();
        menuItems= serializer.deserializeMenuItems();
        orderHistory= serializer.deserializeOrderHistory();
        clients= serializer.deserializeClients();
        filter1=new HashSet<MenuItem>(getSortedMenuItemsList());
    }

    /**
     * Invarinat al clasei DeliveryService care verifica daca variabilele instanta de tip Collection au fost initializate
     * @return true daca colectiile sunt valide, false altfel
     */
    public boolean isWellFormed() {
        if(orderHistory == null)
        {
            return false;
        }

        if(menuItems == null)
        {
            return false;
        }

        if(observers == null)
        {
            return false;
        }
        return clients != null;
    }

    public int getMaxOrderID()
    {
        return orderHistory.keySet().stream().mapToInt(Order::getOrderID).max().orElse(0);
    }
    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observers.remove(o);
    }

    /**
     * Metoda specifica interfetei Subject, semnalizeaza observerilor ca s-a inserat o comanda noua sau ca o asertiune a esuat
     * @Precondition isWellFormed()
     * @param object obiect de tip Order sau String
     */
    @Override
    public void notifyObservers(Object object) {
        assert isWellFormed();
        for(Observer observer:observers)
        {
            if(object instanceof Order)
                observer.update((Order)object);
            else if(object instanceof String && observer instanceof Exception)
            {

                observer.update((String)object);
            }
        }
    }

    /**
     * Metoda pentru inserarea unui produs in lista menuItems
     * @Invariant validateData(menuItem)
     * @Precondition assert validateData(menuItem);
     * @Postcondition assert menuItems.size()==oldSize+1;
     * @param menuItem produsul de inserat
     */
    @Override
    public void insertItem(MenuItem menuItem) {
        assert validateData(menuItem);
        int oldSize=menuItems.size();
        menuItems.add(menuItem);
        saveMenuItemsData();
        assert menuItems.size()==oldSize+1;
    }

    /**
     * Metoda pentru stergerea unui produs din lista menuItems
     * @Invariant index < menuItems.size()
     * @Precondition assert index < menuItems.size(): "index out of bounds";
     * @Postcondition assert menuItems.size()==oldSize-1;
     * @param index index-ul produsul de sters
     */
    @Override
    public void deleteItem(int index) {
        assert index < menuItems.size(): "index out of bounds";
        List<MenuItem> menuItemList= getSortedMenuItemsList();
        int oldSize=menuItemList.size();
        menuItemList.remove(index);
        menuItems=menuItemList.stream().collect(Collectors.toSet());
        saveMenuItemsData();
        assert menuItems.size()==oldSize-1;
    }

    /**
     * Metoda pentru modificarea datelor unui produs
     * @Invariant index < menuItems.size()
     * @Invariant validateData(newMenuItem)
     * @Precondition assert validateData(newMenuItem);
     * @Postcondition assert oldMenuItem.equals(newMenuItem): "product was not modified";
     * @param index index-ul produsului de modificat
     * @param newMenuItem produsul
     */
    @Override
    public void modifyItem(int index,MenuItem newMenuItem) {
        assert index < menuItems.size(): "index out of bounds";
        assert validateData(newMenuItem);
        List<MenuItem> menuItemList= getSortedMenuItemsList();
        MenuItem oldMenuItem=menuItemList.get(index);
        oldMenuItem.replaceItem(newMenuItem);
        menuItems=menuItemList.stream().collect(Collectors.toSet());
        saveMenuItemsData();
        assert oldMenuItem.equals(newMenuItem): "product was not modified";
    }

    @Override
    public void insertNewComposite(String name,List<Integer> selectedIndexes) {
        assert validateList(selectedIndexes);
        List<MenuItem> items= new ArrayList<MenuItem>();
        int oldSize=menuItems.size();
        for(Integer index:selectedIndexes)
        {
            List<MenuItem> sortedItems=getSortedMenuItemsList();
            items.add(sortedItems.get(index));
        }
        menuItems.add(new CompositeProduct(name+": ",items));
        saveMenuItemsData();
        assert menuItems.size()==oldSize+1;
    }

    /**
     * Clasa care genereaza raportul 1
     * @Invariant validateTime(timeMin,timeMax)
     * @Precondition assert validateTime(timeMin,timeMax);
     * @Postcondition assert collect.size()==0 : "No orders found!";
     * @param timeMin ora minima
     * @param timeMax ora maxima
     */

    public void generateReport1(int timeMin, int timeMax){

        assert validateTime(timeMin,timeMax);
        FileWriterr writer= new FileWriterr("report1.txt");
        writer.writeToFile(" The orders performed between "+timeMin+":00 and "+timeMax+":00 :");
        Set<Order> collect = orderHistory.keySet().stream().filter(order -> order.getOrderDate().getHours() >= timeMin && order.getOrderDate().getHours() <= timeMax).collect(Collectors.toSet());

        collect.forEach(item->writer.writeToFile(item.toString()));

        //assert collect.size()==0 : "No orders found!";
        //assert false : "Report 1 was not created!";
    }

    /**
     * Clasa care genereaza raportul 2
     * @Invariant validateData(nrMin)
     * @Precondition assert validateData(nrMin);
     * @Postcondition assert result.size()==0 : "No items found!";
     * @param nrMin nr min de comenzi
     */
    public void generateReport2(int nrMin)
    {
        assert validateData(nrMin);
        FileWriterr writer= new FileWriterr("report2.txt");
        writer.writeToFile("The products ordered more than "+nrMin+" times so far: ");
        List<MenuItem> productsDuplicated = new ArrayList<MenuItem>();
        Set<MenuItem> result= new HashSet<MenuItem>();
        orderHistory.values().stream().forEach(productsDuplicated::addAll);
        Set<MenuItem> productsNoDuplicated= productsDuplicated.stream().collect(Collectors.toSet());
        for(MenuItem item:productsNoDuplicated)
        {
            long frequency= productsDuplicated.stream().filter(i->i.getTitle().equals(item.getTitle())).count();
            if(frequency>nrMin)
            {
                result.add(item);
            }
        }
        if(result.size()>0)
            result.forEach(item->writer.writeToFile(item.toString()));
        else
            writer.writeToFile("No items found :(");

        //assert writer==null: "Report 2 was not created!";
        //assert result.size()==0 : "No items found!";
    }

    /**
     * Clasa care genereaza raportul 3
     * @Invariant validateData(minNrTimes,minPrice);
     * @Precondition assert validateData(minNrTimes,minPrice);
     * @Postcondition assert result.size()==0 :  "No clients found!";
     * @param minNrTimes nr min de timpuri
     * @param minPrice pretul minim
     */
    public void generateReport3(int minNrTimes, int minPrice)
    {
        assert validateData(minNrTimes,minPrice);
        FileWriterr writer= new FileWriterr("report3.txt");
        writer.writeToFile("The clients that have ordered more than "+minNrTimes+ " times so far and the value of the order was higher than "+minPrice+": ");
        Set<Client> result= new HashSet<Client>();
        Map<Integer, List<Order>> collected = orderHistory.keySet().stream().filter(order -> order.getTotalPrice() >= minPrice).collect(Collectors.groupingBy(Order::getClientID));

        for(Map.Entry<Integer,List<Order>> entry:collected.entrySet())
        {
            long frequency=entry.getValue().stream().count();
            if(frequency>=minNrTimes)
            {
                List<Client> clientById= clients.stream().filter(client -> client.getId()==entry.getKey()).collect(Collectors.toList());
                result.add(clientById.get(0));
            }
        }
        //assert writer==null : "Report 3 was not created!";

        if(result.size()>0)
            result.forEach(item->writer.writeToFile(item.toString()));
        else
            writer.writeToFile("No clients found");
       // assert result.size()==0 :  "No clients found!";
    }

    /**
     * Clasa pentru generarea raportului 4
     * @Invariant validateData(day,month,year)
     * @Precondition assert validateData(day,month,year);
     * @Postcondition assert result.size()==0 :  "No items found!";
     * @param day ziua
     * @param month luna
     * @param year anul
     */
    public void generateReport4(int day,int month, int year)
    {
        assert validateData(day,month,year);
        FileWriterr writer= new FileWriterr("report4.txt");
        writer.writeToFile("The products ordered on <<"+day+"-"+month+"-"+year+">> with the number of times they have been ordered: ");
        System.out.println();
        List<Order> ordersByDay= orderHistory.keySet().stream().filter(order->order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getDayOfMonth()==day &&
                order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonthValue()==month &&
                order.getOrderDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear()==year).collect(Collectors.toList());

        List<MenuItem> itemsDup= new ArrayList<MenuItem>();
        for(Order order:ordersByDay)
        {
            itemsDup.addAll(orderHistory.get(order));
        }
        Map<MenuItem,Long> result= itemsDup.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        //assert writer==null : "Report 4 was not created!";
        for(Map.Entry<MenuItem,Long> entry:result.entrySet())
        {
            writer.writeToFile(entry.getKey().getTitle()+" -> ordered x"+entry.getValue()+" times");
        }
        //assert result.size()==0 :  "No items found!";
    }

    @Override
    public void importProducts(String fileName) {
        assert isWellFormed(): "collection error";
        menuItems=FileWriterr.readFromCVSFile(fileName);
        saveMenuItemsData();
    }

    @Override
    public void registerClient(String username, String password) {
        assert validateNewClient(username);
        int oldSize=clients.size();
        clients.add(new Client(clients.size()+1,username,password));
        saveClientData();
        assert clients.size()==oldSize+1: "client was not inserted";
    }

    /**
     * Metoda folosita la logarea unui client in aplicatie
     * @Invariant isWellFormed()
     * @Precondition assert isWellFormed(): "collection error";
     * @param user username
     * @param password parola
     * @return clientul daca a fost gasit, null altfel
     */
    @Override
    public Client loginClient(String user, String password) {
        assert isWellFormed(): "collection error";
        Client auxClient=new Client(0,user,password);
        for(Client client:clients)
        {
            if(client.equals(auxClient))
                return client;
        }
        return null;
    }

    /**
     * Metoda ce realizeaza plasarea unei comenzi
     * @Invariant validNewOrder()
     * @Precondition assert validNewOrder() : true
     * @Postcondition assert orderHistory.size()==oldSize+1
     * @param client clientul care a plasat comanda
     * @param selectedItems lista produselor ce se comanda
     */
    @Override
    public Order createNewOrder(Client client, List<MenuItem> selectedItems) {
        assert validateNewOrder(client,selectedItems) : "Empty items list";
        Order order=new Order(client.getId());
        if(orderHistory==null)
        {
            orderHistory=new HashMap<>();
            order.setOrderID(1);

        }
        else
        {
            order.setOrderID(getMaxOrderID()+1);
            System.out.println(order.getOrderDate());
        }

        double totalPrice=selectedItems.stream().mapToDouble(MenuItem::getPrice).sum();
        order.setTotalPrice(totalPrice);
        int oldSize=orderHistory.size();
        orderHistory.put(order,selectedItems);
        notifyObservers(order);
        saveOrderData();
        ReceiptWriter receiptWriter= new ReceiptWriter("order"+order.getOrderID()+".txt");
        receiptWriter.writeReceipt(order,orderHistory.get(order),client);
        //assert receiptWriter==null: "receipt was not created";
        assert orderHistory.size()==oldSize+1: "order was not inserted";
        return order;
    }

    /**
     * Metoda care returneaza lista ordonata a produselor rezultate in urma filtrarii
     * @Invariant isWellFormed()
     * @Precondition assert isWellFormed():"collection error";
     * @Postcondition assert menuItemsList.size()==sorted.size():"sorting failed";
     * @param set set-ul de menuItems
     * @return lista sortata
     */

    public List<MenuItem> sortedListOfFilteredMenuItems(Set<MenuItem> set)
    {
        assert isWellFormed():"collection error";
        List<MenuItem> menuItemsList= set.stream().toList();
        List<MenuItem> sorted = menuItemsList.stream().sorted(Comparator.comparing(MenuItem::getTitle)).collect(Collectors.toList());
        assert menuItemsList.size()==sorted.size():"sorting failed";
        return sorted;

    }
    public void resetFilterList()
    {
        filter1=getSortedMenuItemsList().stream().collect(Collectors.toSet());
    }

    /**
     * Metoda care realizeaza filtrarea din menil clientului
     * @Invariant validNewOrder()
     * @Invariant isWellFormed()
     * @Precondition assert filters.length>0 : "array-ul de string-uri este gol";
     * @Precondition assert isWellFormed() : "collection error";
     * @Postcondition assert validateList(filter1.stream().toList());
     * @param filters array de string-uri cu field-urile luate din textfield-urile din GUI
     * @return lista cu produsele rezultate in urma filtrarii
     */
    public List<MenuItem> filterAll(String[] filters)
    {
        assert filters.length>0 : "array-ul de string-uri este gol";
        assert isWellFormed() : "collection error";
        filter1= new HashSet<MenuItem>(getSortedMenuItemsList());
        if(!filters[Criteria.KEYWORD].equals(""))
        {
            filter1=filterByKeyword(filters[Criteria.KEYWORD],filter1);
        }
        for(int i=1;i<filters.length;i++)
        {
            if(!filters[i].equals(""))
            {
                if(i%2!=0) //daca e field pt MIN
                {
                    switch(i)
                    {
                        case Criteria.RATING_MIN:
                            filter1=filterByRating(Double.parseDouble(filters[Criteria.RATING_MIN]),5,filter1);
                            break;
                        case Criteria.CALORIES_MIN:
                            filter1=filterByCalories(Integer.parseInt(filters[Criteria.CALORIES_MIN]),Integer.MAX_VALUE,filter1);
                            break;
                        case Criteria.PROTEINS_MIN:
                            filter1=filterByProteins(Integer.parseInt(filters[Criteria.PROTEINS_MIN]),Integer.MAX_VALUE,filter1);
                            break;
                        case Criteria.FATS_MIN:
                            filter1=filterByFats(Integer.parseInt(filters[Criteria.FATS_MIN]),Integer.MAX_VALUE,filter1);
                            break;
                        case Criteria.SODIUM_MIN:
                            filter1=filterBySodium(Integer.parseInt(filters[Criteria.SODIUM_MIN]),Integer.MAX_VALUE,filter1);
                            break;
                        case Criteria.PRICE_MIN:
                            filter1=filterByPrice(Double.parseDouble(filters[Criteria.PRICE_MIN]),Double.MAX_VALUE,filter1);
                            break;

                    }
                }
                else //daca e field pt MAX si fields ul nu e gol
                {
                    switch(i)
                    {
                        case Criteria.RATING_MAX:
                            filter1=filterByRating(0,Double.parseDouble(filters[Criteria.RATING_MAX]),filter1);
                            break;
                        case Criteria.CALORIES_MAX:
                            filter1=filterByCalories(0,Integer.parseInt(filters[Criteria.CALORIES_MAX]),filter1);
                            break;
                        case Criteria.PROTEINS_MAX:
                            filter1=filterByProteins(0,Integer.parseInt(filters[Criteria.PROTEINS_MAX]),filter1);
                            break;
                        case Criteria.FATS_MAX:
                            filter1=filterByFats(0,Integer.parseInt(filters[Criteria.FATS_MAX]),filter1);
                            break;
                        case Criteria.SODIUM_MAX:
                            filter1=filterBySodium(0,Integer.parseInt(filters[Criteria.SODIUM_MAX]),filter1);
                            break;
                        case Criteria.PRICE_MAX:
                            filter1=filterByPrice(0,Double.parseDouble(filters[Criteria.PRICE_MAX]),filter1);
                            break;

                    }
                }
            }
        }
        assert validateList(filter1.stream().toList());
        return sortedListOfFilteredMenuItems(filter1);
    }

    public MenuItem getFilteredListItemByIndex(int index) {
        return sortedListOfFilteredMenuItems(filter1).get(index);
    }



    @Override
    public Set<MenuItem> filterByKeyword(String keyword,Set<MenuItem> menuItems) {
        return menuItems.stream().filter(item-> item.getTitle().contains(keyword)).collect(Collectors.toSet());

    }

    @Override
    public Set<MenuItem> filterByRating(double ratingMin,double ratingMax,Set<MenuItem> menuItems) {

        Set<MenuItem> result = menuItems.stream().filter(item -> item.getRating() >= ratingMin && item.getRating()<=ratingMax).collect(Collectors.toSet());
        return result;
    }

    @Override
    public Set<MenuItem> filterByCalories(int calorieMin, int caloriesMax,Set<MenuItem> menuItems) {
        return menuItems.stream().filter(item-> item.getCalories()>=calorieMin && item.getCalories()<=caloriesMax).collect(Collectors.toSet());
    }

    @Override
    public Set<MenuItem> filterByProteins(int proteinsMin, int proteinsMax,Set<MenuItem> menuItems) {
        return menuItems.stream().filter(item-> item.getProteins()>=proteinsMin && item.getProteins()<=proteinsMax).collect(Collectors.toSet());
    }

    @Override
    public Set<MenuItem> filterByFats(int fatsMin,int fatsMax,Set<MenuItem> menuItems) {
        return menuItems.stream().filter(item-> item.getFats()>=fatsMin &&item.getFats()<=fatsMax).collect(Collectors.toSet());
    }

    @Override
    public Set<MenuItem> filterBySodium(int sodiumMin,int sodiumMax,Set<MenuItem> menuItems) {
        return menuItems.stream().filter(item-> item.getSodium()>=sodiumMin && item.getSodium()<=sodiumMax).collect(Collectors.toSet());
    }

    @Override
    public Set<MenuItem> filterByPrice(double priceMin,double priceMax,Set<MenuItem> menuItems) {
        return menuItems.stream().filter(item-> item.getPrice()>=priceMin && item.getPrice()<=priceMax).collect(Collectors.toSet());
    }

    public void saveMenuItemsData()
    {
        serializer.serializeMenuItems(menuItems);
    }

    public List<MenuItem> getSortedMenuItemsList()
    {
        List<MenuItem> menuItemsList= menuItems.stream().toList();
        return menuItemsList.stream().sorted(Comparator.comparing(MenuItem::getTitle)).collect(Collectors.toList());
    }
    public void saveOrderData()
    {
        serializer.serializeOrderHistory(orderHistory);
    }
    public void saveClientData()
    {
        serializer.serializeClients(clients);
    }

    public Map<Order, List<MenuItem>> getOrderHistory() {
        return orderHistory;
    }

    public Set<MenuItem> getMenuItems() {
        return menuItems;
    }
    /**
     * Invariant al clasei DeliveryService
     * @param client cleintul care paplseaza comanda
     * @param menuItems lista cu produsele comandate
     * @return true daca datele sunt valide, altfel false
     */
    public boolean validateNewOrder(Client client,List<MenuItem> menuItems)
    {
        if(!clients.contains(client))
        {
            notifyObservers("Client does not exits!");
            return false;
        }
        else if(menuItems.size()==0)
        {
            notifyObservers("No items added to cart!");
            return false;
        }
        return true;
    }

    /**
     * Invariant ce verifica daca lista trimisa ca parametru este goala
     * @param list o lista
     * @param <T> tipul obiecelor din lista list
     * @return true daca lista contine cel putin un element, false altfel
     */
    public <T> boolean validateList(List<T> list)
    {
        if(list.size()==0)
        {
            notifyObservers("No selected rows");
            return false;
        }
        return true;
    }

    /**
     * Metoda care verifica daca exista deja in lista de clienti un client cu username-ul trimis ca parametru
     * @param username username-ul unui posibil client
     * @return true daca am gasit un client cu username-ul dat, false altfel
     */
    public boolean clientExits(String username)
    {
        for(Client client:clients)
        {
            if(client.getUser().equals(username))
                return true;
        }
        return false;
    }

    /**
     * Invariant al clasei DeliveryService care verifica daca un client exista deja in lista de clienti
     * @param username username-ul unui posibil client
     * @return true daca am gasit un client cu username-ul dat, false altfel
     */
    public boolean validateNewClient(String username)
    {
        if(clientExits(username))
        {
            notifyObservers("Client with given username already exits. Try again!");
            return false;
        }
        return true;
    }

    /**
     * Invariant care verifica daca o data calendaristica este valida
     * @param day ziua
     * @param month luna
     * @param year anul
     * @return true daca e valida, false altfel
     */

    public boolean validateData(int day, int month, int year)
    {
        if(!(day>=1 && day<=31))
        {
            notifyObservers("Invalid day!");
            return false;
        }

        else if(!(month>=1 && month<=12))
        {
            notifyObservers("Invalid month!");
            return false;
        }

        else if (!(year>=2022))
        {
            notifyObservers("No order history registered in given year!");
            return false;
        }
        return true;
    }

    /**
     * Invariant care verifica daca un interval orar este valid
     * @param startHour ora de start
     * @param endHour ora de final
     * @return true daca intervalul e valid, false altfel
     */
    public boolean validateTime(int startHour, int endHour)
    {
        if(startHour>endHour)
        {
            notifyObservers("Invalid time interval");
            return false;
        }

        return true;
    }

    /**
     * Invariant care verifica datele pentru raportul de tip 3.
     * @param nrMin nr minim de comenzi
     * @param priceMin pretul minim al comenzilor
     * @return true daca datele sunt valide, false altfel
     */
    public boolean validateData(int nrMin, int priceMin)
    {
        validateData(nrMin);
        if(priceMin<=0)
        {
            notifyObservers("The minimum price must be greater than 0!");
            return false;
        }
        return true;
    }

    /**
     * Invariant care verifica daca parametru este > 0
     * @param nrMin nr minim
     * @return true daca e valid, false altfel
     */
    public boolean validateData(int nrMin)
    {
        if(nrMin<=0)
        {
            notifyObservers("The number of times an item has been order must be greater than 0!");
            return false;
        }

        return true;
    }

    /**
     * Invariant care valideaaza field-urile unui produs
     * @param menuItem produsul de validat
     * @return true daca e valid, false altfel
     */
    public boolean validateData(MenuItem menuItem)
    {
        if(!(menuItem.getRating()>=0 && menuItem.getRating()<=5))
        {
            notifyObservers("Rating must be a value between 0 and 5!");
            return false;
        }
        if(!(menuItem.getCalories()>=0))
        {
            notifyObservers("Calories value must be positive!");
            return false;
        }

        if(!(menuItem.getProteins()>=0))
        {
            notifyObservers("Proteins value must be positive!");
            return false;
        }
        if(!(menuItem.getFats()>=0))
        {
            notifyObservers("Fats value must be positive!");
            return false;
        }
        if(!(menuItem.getSodium()>=0))
        {
            notifyObservers("Sodium value must be positive!");
            return false;
        }
        if(!(menuItem.getPrice()>=0))
        {
            notifyObservers("Price value must be positive!");
            return false;
        }
        return true;
    }

}
