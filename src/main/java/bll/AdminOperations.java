package bll;

import model.MenuItem;

import java.util.List;

public interface AdminOperations {
    void insertItem(MenuItem menuItem);
    void deleteItem(int index);
    void modifyItem(int index,MenuItem newMenuItem);
    void insertNewComposite(List<Integer> selectedIndexes);
    void importProducts(String fileName);
}
