package org.example;

import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
    Я розумію що серіалізація бінарним форматом не самий надійний спосіб збереження
    даних, (тут і проблема з версіями й безпекою) проте враховуючи той факт, що
    в завдані нам розв'язали руки й дали використовувати той спосіб збереження
    інформації який ми захочемо, а також той момент що оцінка від цього
    не залежатиме, обрав саме серіалізацію, через її простоту й не віру в те що
    ця програма матиме продовження після здачі, а отже за конфлікт версій можна не
    хвилюватись

    +ніколи до цього не використовував серіалізацію, вирішив спробувати щось нове
 */

public class DataStorage{
    @Getter
    private final List<Souvenir> list;
    private final String fileName;

    public DataStorage() {
        this.fileName = "souvenirs.dat";
        File file = new File(fileName);
        if (file.exists() && file.length() > 0) {
            list = loadListFromFile();
        } else {
            list = new ArrayList<>();
        }
    }

    public void update() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            objectOutputStream.writeObject(list);
            System.out.println("Список збережено в бінарний файл '" + fileName + "'.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Souvenir> loadListFromFile() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Souvenir>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
