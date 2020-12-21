package domain;

import org.omg.PortableServer.LifespanPolicy;
import persistence.Database;
import persistence.DbMenuCardMapper;
import persistence.DbOrderMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Statistics {

    int pizzaNo;
    String pizzaName;
    int amountSold;


    public Statistics() {
    }

    public Statistics(int pizzaNo, int amountSold) {
        this.pizzaNo = pizzaNo;
     //   this.pizzaName = pizzaName;
        this.amountSold = amountSold;
    }

    public int getPizzaNo() {
        return pizzaNo;
    }

    public void setPizzaNo(int pizzaNo) {
        this.pizzaNo = pizzaNo;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public int getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(int amountSold) {
        this.amountSold = amountSold;
    }


    }



