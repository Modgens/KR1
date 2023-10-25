package org.example;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Menu {
    @Getter
    private final DataStorage dataStorage;
    private final List<Souvenir> list;

    public Menu() {
        this.dataStorage = new DataStorage();
        this.list = dataStorage.getList();
    }

    /*
        Спершу дуже хотілось використати патерн Спостерігач, щоб при кожній змінні списку Сувенірів при роботі
        в консолі, обновлявся цей список у файлі, але вийшла якась мініатюрна копія Спостерігача з 1 Спостерігачем,
        та 1 Підписником на спостерігача, окрім цього я зрозумів що мені немає сенсу в реальному часі оновлювати файл
        так як мені достатньо оновити його 1 раз після завершення роботи зі списком і виходом з консолі. Тому вирішив, що
        тут цей патерн є не доцільним, а використовувати його просто щоб він був для мене - bad practice.
     */

    public void addSouvenir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву сувеніру: ");
        String name = scanner.nextLine();

        Producer producer = chooseProducerOrCreateOne();

        System.out.print("Введіть дату випуску (рік-місяць-день): ");
        String dateInput = scanner.nextLine();
        LocalDate releaseDate = LocalDate.parse(dateInput);

        System.out.print("Введіть ціну сувеніру (грн): ");
        double price = scanner.nextDouble();

        Souvenir newSouvenir = Souvenir.builder()
                .name(name)
                .producer(producer)
                .releaseDate(releaseDate)
                .price(price)
                .build();
        list.add(newSouvenir);
    }

    public void editSouvenir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть індекс сувеніру для редагування: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < list.size()) {
            Souvenir souvenir = list.get(index);

            System.out.print("Введіть нову назву сувеніру: ");
            scanner.nextLine(); // Consume the newline character
            String name = scanner.nextLine();

            Producer producer = chooseProducerOrCreateOne();

            System.out.print("Введіть нову дату випуску (рік-місяць-день): ");
            String dateInput = scanner.nextLine();
            LocalDate releaseDate = LocalDate.parse(dateInput);

            System.out.print("Введіть нову ціну сувеніру: ");
            double price = scanner.nextDouble();

            souvenir.save(Souvenir.builder()
                    .name(name)
                    .producer(producer)
                    .releaseDate(releaseDate)
                    .price(price)
                    .build());

        } else {
            System.out.println("Сувенір з вказаним індексом не існує.");
        }
    }

    // Додано окремий метод для виведення меню
    public void viewAllProducers() {
        list.stream().map(Souvenir::getProducer).distinct().forEach(System.out::println);
    }

    public void viewAllSouvenirs() {
        list.forEach(System.out::println);
    }

    public void viewSouvenirsByProducer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву виробника");
        String producerName = scanner.nextLine();
        List<Souvenir> matchingSouvenirs = list.stream()
                .filter(s -> s.getProducer().getName().equals(producerName))
                .toList();

        if (matchingSouvenirs.isEmpty()) {
            System.out.println("Сувенірів від цього виробника не знайдено.");
        } else {
            matchingSouvenirs.forEach(System.out::println);
        }
    }

    public void viewSouvenirsInCountry() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть країну");
        String country = scanner.nextLine();

        List<Souvenir> matchingSouvenirs = list.stream()
                .filter(s -> s.getProducer().getCountry().equals(country))
                .toList();

        if (matchingSouvenirs.isEmpty()) {
            System.out.println("Сувенірів з країни " + country + " не знайдено.");
        } else {
            matchingSouvenirs.forEach(System.out::println);
        }
    }


    public void viewProducersWithLowerPrices() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть максимальну ціну");
        int max = scanner.nextInt();

        List<Souvenir> matchingSouvenirs = list.stream()
                .filter(s -> s.getPrice() < max)
                .toList();

        if (matchingSouvenirs.isEmpty()) {
            System.out.println("Сувенірів з ціною менше " + max + " не знайдено.");
        } else {
            matchingSouvenirs.forEach(System.out::println);
        }
    }

    public void viewAllProducersWithTheirSouvenirs() {
        Map<Producer, List<Souvenir>> souvenirsByProducer = list.stream()
                .collect(Collectors.groupingBy(Souvenir::getProducer));

        if (souvenirsByProducer.isEmpty()) {
            System.out.println("Сувенірів від жодного виробника не знайдено.");
        } else {
            souvenirsByProducer.forEach((producer, souvenirList) -> {
                System.out.println("Виробник: " + producer);
                souvenirList.forEach(souvenir -> System.out.println("Сувенір: " + souvenir.getName()));
            });
        }
    }


    public void viewSouvenirsInEnteredYear() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть Рік:");
        int year = scanner.nextInt();

        List<Souvenir> matchingSouvenirs = list.stream()
                .filter(s -> s.getReleaseDate().getYear() == year)
                .toList();

        if (matchingSouvenirs.isEmpty()) {
            System.out.println("Сувенірів, виготовлених у " + year + " році, не знайдено.");
        } else {
            matchingSouvenirs.forEach(System.out::println);
        }
    }

    public void viewAllSouvenirsSeparatedByYears() {
        Map<Integer, List<Souvenir>> souvenirsByYear = list.stream()
                .collect(Collectors.groupingBy(s -> s.getReleaseDate().getYear()));

        if (souvenirsByYear.isEmpty()) {
            System.out.println("Сувенірів за жодний рік не знайдено.");
        } else {
            souvenirsByYear.forEach((year, souvenirList) -> {
                System.out.println("Рік: " + year);
                souvenirList.forEach(System.out::println);
            });
        }
    }


    public void deleteProducerAndSouvenirs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть назву виробника");
        String producerName = scanner.nextLine();

        boolean anyRemoved = list.removeIf(s -> s.getProducer().getName().equals(producerName));

        if (!anyRemoved) {
            System.out.println("Сувенірів від виробника з назвою " + producerName + " не знайдено.");
        }
    }


    // Це мені захотілося мати можливість обирати Виробників з уже існуючих сувенірів...
    // В моїй уяві воно здавалося меншим, можна було й прибрати, але вирішив лишити
    private Producer chooseProducerOrCreateOne() {
        while (true) {
            System.out.println("1 - Обрати існуючого виробника");
            System.out.println("2 - Створити виробника");

            System.out.print("Ваш вибір: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    List<Producer> producers = list.stream().map(Souvenir::getProducer).distinct().toList();
                    if (!producers.isEmpty()) {
                        while (true) {
                            System.out.println("Виробники:");
                            for (int i = 0; i < producers.size(); i++) {
                                System.out.println(i + " - " + producers.get(i));
                            }
                            System.out.println("Оберіть індекс виробника:");
                            int index = scanner.nextInt();
                            if (index < producers.size() && index >= 0) {
                                return producers.get(index);
                            } else {
                                System.out.println("Невірний вибір. Будь ласка, виберіть існуючу опцію.");
                            }
                        }
                    }
                    System.out.println("Виробників не існує, створіть одного:");
                case 2:
                    scanner.nextLine();
                    System.out.println("Введіть назву виробника:");
                    String name = scanner.nextLine();
                    System.out.println("Введіть країну виробника:");
                    String country = scanner.nextLine();
                    return new Producer(name, country);
                default:
                    System.out.println("Невірний вибір. Будь ласка, виберіть існуючу опцію.");
            }
        }
    }

}
