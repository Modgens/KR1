package org.example;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ListManager implements IListManager{
    private final List<Souvenir> list;

    public ListManager() {
        // Перевірка, чи існує файл "list.dat" і чи він не є порожнім
        File file = new File("list.dat");
        if (file.exists() && file.length() > 0) {
            list = loadListFromFile();
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    public void update() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("list.dat"))) {
            objectOutputStream.writeObject(list);
            System.out.println("Список збережено в бінарний файл 'list.dat'.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Souvenir> loadListFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("list.dat"))) {
            return (List<Souvenir>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
