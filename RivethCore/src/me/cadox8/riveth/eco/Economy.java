package me.cadox8.riveth.eco;

import me.cadox8.riveth.api.RUser;

public class Economy {

    private RUser u;

    public Economy(RUser user) {
        this.u = user;
    }

    public void addMoney(double money) {
        setMoney(getMoney() + money);
    }

    public void remMoney(double money) {
        setMoney(getMoney() - money);
    }

    public void setMoney(double money) {
        if (money > Double.MAX_VALUE) setMoney(Double.MAX_VALUE);
        if (money < Double.MIN_VALUE) setMoney(Double.MIN_VALUE);

        u.getUserData().setMoney(money);
    }

    public boolean pay(RUser target, double amount) {
        if (getMoney() < amount) return false;

        remMoney(amount);
        new Economy(target).addMoney(amount);
        return true;
    }

    public double getMoney() {
        return u.getUserData().getMoney();
    }
}
