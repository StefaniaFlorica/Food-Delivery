package bll;

import model.Order;

public interface Subject {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers(Object o);
}
