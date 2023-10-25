package org.example;

import java.util.Scanner;

/*
    Клас Menu вийшов занадто здоровим і я вже сам почав в ньому потрохи заплутуватись,
    тому вирішив витягнути головне меню консолі в Фасад
    і таким чином відділити інтерфейс з користувачем від бізнес-логіки, звісно в Menu
    ще лишилися методи які тим чи іншим чином взаємодіють з користувачем, але там
    по іншому й ніяк.
    Як результат вийшов більш структурований та зрозумілий код.
 */

public class MenuFacade {
    private final Menu menu;

    public MenuFacade() {
        this.menu = new Menu();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice != 0) {
            printMenu();
            System.out.print("Ваш вибір: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    menu.addSouvenir();
                    break;
                case 2:
                    menu.editSouvenir();
                    break;
                case 3:
                    menu.viewAllProducers();
                    break;
                case 4:
                    menu.viewAllSouvenirs();
                    break;
                case 5:
                    menu.viewSouvenirsByProducer();
                    break;
                case 6:
                    menu.viewSouvenirsInCountry();
                    break;
                case 7:
                    menu.viewProducersWithLowerPrices();
                    break;
                case 8:
                    menu.viewAllProducersWithTheirSouvenirs();
                    break;
                case 9:
                    menu.viewSouvenirsInEnteredYear();
                    break;
                case 10:
                    menu.viewAllSouvenirsSeparatedByYears();
                    break;
                case 11:
                    menu.deleteProducerAndSouvenirs();
                    break;
                case 0:
                    System.out.println("До побачення!");
                    menu.getDataStorage().update();
                    break;
                default:
                    System.out.println("Невірний вибір. Будь ласка, виберіть існуючу опцію.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("Оберіть опцію:");
        System.out.println("1. Додати сувенір");
        System.out.println("2. Редагувати сувенір");
        System.out.println("3. Переглянути всіх виробників");
        System.out.println("4. Переглянути всі сувеніри");
        System.out.println("5. Вивести інформацію про сувеніри заданого виробника");
        System.out.println("6. Вивести інформацію про сувеніри, виготовлені в заданій країні");
        System.out.println("7. Вивести інформацію про виробників, чиї ціни на сувеніри менше заданої");
        System.out.println("8. Вивести інформацію по всіх виробниках та сувенірах");
        System.out.println("9. Вивести інформацію про виробників заданого сувеніру, виробленого у заданому року.");
        System.out.println("10. Для кожного року вивести список сувенірів, зроблених цього року.");
        System.out.println("11. Видалити виробника та його сувеніри");
        System.out.println("0. Зберегти та вийти");
    }
}
