package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu{
    private final ListManager listManager;
    private final List<Souvenir> list;

    public Menu() {
        this.listManager = new ListManager();
        this.list = listManager.getList();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            System.out.println("Оберіть опцію:");
            System.out.println("1. Додати сувенір");
            System.out.println("2. Редагувати сувенір");
            System.out.println("3. Переглянути всіх виробників");
            System.out.println("4. Переглянути всі сувеніри");
            System.out.println("5. Вивести інформацію про сувеніри заданого виробника");
            System.out.println("6. Вивести інформацію про сувеніри, виготовлені в заданій країні");
            System.out.println("7. Вивести інформацію про виробників, чиї ціни на сувеніри менше заданої");
            System.out.println("8. Вивести інформацію по всіх виробниках та сувенірах");
            System.out.println("9. Видалити виробника та його сувеніри");
            System.out.println("0. Вийти");

            System.out.print("Ваш вибір: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addSouvenir();
                    break;
                case 2:
                    editSouvenir();
                    break;
                case 3:
                    viewAllProducers();
                    break;
                case 4:
                    viewAllSouvenirs();
                    break;
                case 5:
                    viewSouvenirsByProducer();
                    break;
                case 6:
                    viewSouvenirsInCountry();
                    break;
                case 7:
                    viewProducersWithLowerPrices();
                    break;
                case 8:
                    viewAllProducersWithTheirSouvenirs();
                    break;
                case 9:
                    deleteProducerAndSouvenirs();
                    break;
                case 0:
                    System.out.println("До побачення!");
                    listManager.update();
                    break;
                default:
                    System.out.println("Невірний вибір. Будь ласка, виберіть існуючу опцію.");
            }
        }

        scanner.close();
    }

    public void addSouvenir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву сувеніру: ");
        String name = scanner.nextLine();

        System.out.print("Введіть назву виробника: ");
        String producerName = scanner.nextLine();

        System.out.print("Введіть країну виробника: ");
        String producerCountry = scanner.nextLine();

        System.out.print("Введіть дату випуску (рік-місяць-день): ");
        String dateInput = scanner.nextLine();
        LocalDate releaseDate = LocalDate.parse(dateInput);

        System.out.print("Введіть ціну сувеніру: ");
        double price = scanner.nextDouble();

        Souvenir newSouvenir = Souvenir.builder()
                .name(name)
                .producer(new Producer(producerName, producerCountry))
                .releaseDate(releaseDate)
                .price(price)
                .build();
        listManager.getList().add(newSouvenir);
    }

    public void editSouvenir() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть індекс сувеніру для редагування: ");
        int index = scanner.nextInt();

        if (index >= 0 && index < listManager.getList().size()) {
            Souvenir souvenir = listManager.getList().get(index);

            System.out.print("Введіть нову назву сувеніру: ");
            String name = scanner.nextLine();

            System.out.print("Введіть нову назву виробника: ");
            String producerName = scanner.nextLine();

            System.out.print("Введіть нову країну виробника: ");
            String producerCountry = scanner.nextLine();

            System.out.print("Введіть нову дату випуску (рік-місяць-день): ");
            String dateInput = scanner.nextLine();
            LocalDate releaseDate = LocalDate.parse(dateInput);

            System.out.print("Введіть нову ціну сувеніру: ");
            double price = scanner.nextDouble();

            souvenir.save(Souvenir.builder()
                    .name(name)
                    .producer(new Producer(producerName, producerCountry))
                    .releaseDate(releaseDate)
                    .price(price)
                    .build());

        } else {
            System.out.println("Сувенір з вказаним індексом не існує.");
        }
    }

    private void viewAllProducers() {
        list.stream().map(Souvenir::getProducer).forEach(System.out::println);


    }
    private void viewAllSouvenirs() {
        List<Souvenir> list = listManager.getList();

    }

    private void viewSouvenirsByProducer() {

    }

    private void viewSouvenirsInCountry() {

    }

    private void viewProducersWithLowerPrices() {

    }

    private void viewAllProducersWithTheirSouvenirs() {

    }

    private void deleteProducerAndSouvenirs() {

    }

}
