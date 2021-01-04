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
    int totalEarned;



    public Statistics() {
    }

    public Statistics(int pizzaNo , int amountSold, int totalEarned) {
        this.pizzaNo = pizzaNo;
        this.amountSold = amountSold;
        this.totalEarned = totalEarned;
    }

    public int getPizzaNo() {
        return pizzaNo;
    }

    public int getTotalEarned() {
        return totalEarned;
    }

    public void setTotalEarned(int totalEarned) {
        this.totalEarned = totalEarned;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "pizzaNo=" + pizzaNo +
                ", pizzaName='" + pizzaName + '\'' +
                ", amountSold=" + amountSold +
                ", totalEarned=" + totalEarned +
                '}';
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




