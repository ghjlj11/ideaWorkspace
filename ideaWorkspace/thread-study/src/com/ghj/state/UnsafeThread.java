package com.ghj.state;

/**
 * @author 86187
 */
public class UnsafeThread {
    public static void main(String[] args) {
        Account account = new Account(200, "结婚");
        Tack you = new Tack(account, 50);
        Tack girlfriend = new Tack(account, 100);
        new Thread(you,"you").start();
        new Thread(girlfriend, "girlfriend").start();
    }
}
class Account{
    int curMoney;
    String name;

    public Account(int curMoney, String name) {
        this.curMoney = curMoney;
        this.name = name;
    }
}
class  Tack implements Runnable{
    Account account ;
    int tackMoney;

    public Tack(Account account, int tackMoney) {
        this.account = account;
        this.tackMoney = tackMoney;
    }

    @Override
    public void run() {
        tack();
    }
    public void tack(){
        synchronized (account){
            if(account.curMoney - tackMoney < 0){
                System.out.println("钱不够了");
                return;
            }
            this.account.curMoney -= tackMoney;
            System.out.println(Thread.currentThread().getName() + "取了" + tackMoney);
            System.out.println("余额还有:" + account.curMoney);
        }
    }
}
