package ui;

import domain.Order;
import domain.Pizza;
import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;

import java.util.Date;
import java.util.List;

public class MainMenu {

    private final String USER = "testdb_user";
    private final String PASSWORD = "1234";
    private final String URL = "jdbc:mysql://localhost:3306/mario?serverTimezone=CET&useSSL=false";

    private Database database = new Database(USER, PASSWORD, URL);
    private DbMenuCardMapper dbMenuCardMapper = new DbMenuCardMapper(database);
    private DbOrderMapper dbOrderMapper = new DbOrderMapper(database);

    public void mainMenuLoop() {

        boolean running = true;

        while (running) {
            showMenu();
            switch (Input.getInt("Vælg 1-7: ")) {
                    case 1:
                        showMenuCard();
                        break;
                    case 2:
                        showSinglePizza();
                        break;
                    case 3:
                        deletePizza();
                        break;
                    case 4:
                        insertPizza();
                        break;
                    case 5:
                        updatePizza();
                        break;

                    case 6:
                        showOrderList();
                        break;
                    case 7:
                        insertOrder();
                        break;
                    case 8:
                        running = false;
                        break;


            }
        }
        System.out.println("Tak for denne gang!");
    }

    private void showMenu() {
        System.out.println("**** Marios pizzabar - hovedmenu ******");
        System.out.println("[1] Vis menukort [2] Vis enkelt pizza [3] Fjern pizza [4] Opret ny pizza [5] Opdater pizza [6] Vis ordrerliste [7] Indsæt ordrer [8] Afslut");
    }

    private void showMenuCard() {
        System.out.println("**** Menukort hos Marios ******");
        List<Pizza> menuCard = dbMenuCardMapper.getAllPizzas();
        for (Pizza pizza : menuCard) {
            System.out.println(pizza.toString());
        }
    }

    private void updatePizza() {
        System.out.println("***** Opdater pizza *******");
        int pizzaNo = Input.getInt("Indtast pizza nummer på den du vil rette: ");
        System.out.println("Indtast ny værdi, hvis den skal rettes - eller blot <retur>: ");
        Pizza pizza = dbMenuCardMapper.getPizzaById(pizzaNo);
        String newPizzaNoInput = Input.getString("Pizzanummer: (" + pizza.getPizzaNo() + "): ");
        if (newPizzaNoInput.length() > 0) {
            pizza.setPizzaNo(Integer.parseInt(newPizzaNoInput));
        }
        String newPizzaNameInput = Input.getString("Pizza navn: (" + pizza.getName() + "): ");
        if (newPizzaNameInput.length() > 0) {
            pizza.setName(newPizzaNameInput);
        }
        String newPizzaIngredientsInput = Input.getString("Pizza ingredienser: (" + pizza.getIngredients() + "): ");
        if (newPizzaIngredientsInput.length() > 0) {
            pizza.setIngredients(newPizzaIngredientsInput);
        }
        String newPizzaPriceInput = Input.getString("Pizza pris: (" + pizza.getPrice() + "): ");
        if (newPizzaPriceInput.length() > 0) {
            pizza.setPrice(Integer.parseInt(newPizzaPriceInput));
        }
        boolean result = dbMenuCardMapper.updatePizza(pizza);
        if (result) {
            System.out.println("Pizzaen med nr = " + pizzaNo + " er nu opdateret");
        } else {
            System.out.println("Vi kunne desværre ikke opdatere den nye pizza.");
        }
    }

    private void insertPizza() {
        System.out.println("**** Opret ny pizza *******");
        int pizzaNo = Input.getInt("Indtast pizza nummer: ");
        String name = Input.getString("Indtast navn på pizza: ");
        String ingredients = Input.getString("Indtast indhold: ");
        int price = Input.getInt("Indtast pris: ");
        Pizza newPizza = new Pizza(pizzaNo, name, ingredients, price);
        Pizza insertedPizza = dbMenuCardMapper.insertPizza(newPizza);
        if (insertedPizza != null) {
            System.out.println("Pizzaen med nr = " + pizzaNo + " er nu tilføjet");
            System.out.println("Pizzaen har fået DB id = " + insertedPizza.getPizzaId());
        } else {
            System.out.println("Vi kunne desværre ikke oprette den nye pizza. PizzaNo findes allerede.");
        }


    }

    private void deletePizza() {
        int pizzaNo = Input.getInt("Indtast nummer på pizza som skal fjernes: ");
        boolean result = dbMenuCardMapper.deletePizza(pizzaNo);
        if (result) {
            System.out.println("Pizzaen med nr = " + pizzaNo + " er nu fjernet");
        } else {
            System.out.println("Pizzaen med nr = " + pizzaNo + " findes ikke, og kan derfor ikke fjernes");
        }

    }

    private void showSinglePizza() {
        int pizzaNo = Input.getInt("Indtast pizzanummer: ");
        Pizza pizza = dbMenuCardMapper.getPizzaById(pizzaNo);
        if (pizza != null) {
            System.out.println("Du har fundet pizza nummer: " + pizzaNo);
            System.out.println(pizza.toString());
        } else {
            System.out.println("Pizza med nr = " + pizzaNo + " findes desværre ikke");
        }
    }

    private void showOrderList(){
        System.out.println("**** Orderliste ******");
        List<Order> orderList = dbOrderMapper.getAllOrders();
        for (Order order : orderList) {
            System.out.println(order.toString());
        }


    }

    private void insertOrder() {
        System.out.println("*** Opret ny order *** ");
        int pizzaNo = Input.getInt("Indtast pizza nummer: ");
        int amount = Input.getInt("Indtast antal pizzaer: ");
        String customer_name = Input.getString("Indtast dit navn: ");
        String customer_phone = Input.getString("Indtast tlf nr: ");
        int pickup_time = Input.getTimeInMinutes("indtast afhentningstid: ");
        Order newOrder = new Order(pizzaNo, amount, customer_name, customer_phone, pickup_time);
        Order insertedOrder = dbOrderMapper.insertOrder(newOrder);
        if (insertedOrder != null) {
            System.out.println("Orderen med id nr: " + insertedOrder.getOrder_id() + " er nu oprettet");
            System.out.println("Afhentningstidspunkt er: " + Input.getMinutesToTimeFormat(pickup_time));
        } else {
            System.out.println("Orderen kunne ikke oprettes");
        }

    }
/*
    private void updateOrder() {
        System.out.println("***** Opdater Order *******");
        int order_id = Input.getInt("Indtast order_id på den du vil rette: ");
        System.out.println("Indtast ny værdi, hvis den skal rettes - eller blot <retur>: ");
        Order order = dbOrderMapper.getOrderById(order_id);
        String newOrderNoInput = Input.getString("Ordernummer: (" + order.getOrder_id() + "): ");
        if (newOrderNoInput.length() > 0) {
            order.setOrder_id(Integer.parseInt(newOrderNoInput));
        }
        int newOrderPizza_noInput = Input.getInt("Pizza nummer: (" + order.getPizza_no() + "): ");
        if (newOrderPizza_noInput.length() > 0) {
            order.setPizza_no(newOrderPizza_noInput);
        }
        int newOrderAmountInput = Input.getInt("order amount: (" + order.getAmount() + "): ");
        if (newOrderAmountInput.length() > 0) {
            order.setAmount(newOrderAmountInput);
        }
        String newCustomer_nameInput = Input.getString("Customer name: (" + order.getCustomer_name() + "): ");
        if (newCustomer_nameInput.length() > 0) {
            order.setCustomer_name((newCustomer_nameInput));
        }
        String newCustomer_phoneInput = Input.getString("Customer phone: (" + order.getCustomer_phone() + "): ");
        if (newCustomer_phoneInput.length() > 0) {
            order.setCustomer_phone((newCustomer_phoneInput));
        }
        int newPickup_timeInput = Input.getInt("Pickup_time: (" + order.getPickup_time() + "): ");
        if (newPickup_timeInput.length() > 0) {
            order.setPickup_time((newPickup_timeInput));
        }

        boolean result = dbOrderMapper.updateOrder(order);
        if (result) {
            System.out.println("Orderen med id = " + order_id + " er nu opdateret");
        } else {
            System.out.println("Vi kunne desværre ikke opdatere den nye pizza.");
        }
    }

 */
}