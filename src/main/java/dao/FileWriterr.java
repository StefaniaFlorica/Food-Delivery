package dao;

import model.BaseProduct;
import model.MenuItem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileWriterr {

    private BufferedWriter bufferedWriter;
    public FileWriterr(String fileName)
    {
        try {
            bufferedWriter= new BufferedWriter(new FileWriter(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String s)
    {
        try {
            bufferedWriter.write(s+"\n");
            bufferedWriter.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void close()
    {
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
    public static Set<MenuItem> readFromCVSFile(String fileName)
    {
        Set<MenuItem> menuItems= new HashSet<MenuItem>();
        try {
            menuItems= Files.lines(Paths.get("src\\main\\resources\\" + fileName))
                    .skip(1)
                    .map(line->line.split(","))
                    .map(fields->new BaseProduct(fields[0],
                            Double.parseDouble(fields[1]),
                            Integer.parseInt(fields[2]),
                            Integer.parseInt(fields[3]),
                            Integer.parseInt(fields[4]),
                            Integer.parseInt(fields[5]),
                            Double.parseDouble(fields[6])))
                    .filter(distinctByKey(product -> product.getTitle()))
                    .collect(Collectors.toSet());
           // menuItems.forEach(System.out::println);
            //System.out.println(menuItems.stream().count());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return menuItems;
    }

}
