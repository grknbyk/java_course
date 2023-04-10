import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
 
    private Lock lock;
    private double balance;
    private String accountNumber;
 
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.lock = new ReentrantLock();
    }

// SYNCHRONIZE ALL CODE
//    public synchronized void deposit(double amount) {
//        balance += amount;
//    }
// 
//    public synchronized void withdraw(double amount) {
//        balance -= amount;
//    }

// SYNCHRONIZE CODE IN THE BRACKET
//    public synchronized void deposit(double amount) {
//        synchronized(this){
//            balance += amount;
//        }
//    }
// 
//    public synchronized void withdraw(double amount) {
//        synchronized(this){
//            balance -= amount;
//        }
//    }

//LOCKING MANUALLY with ReentrantLock
//    public void deposit(double amount) {
//        lock.lock();
//        try{
//            balance += amount;
//        }finally{
//            lock.unlock();
//        }
//    }
// 
//    public void withdraw(double amount) {
//        lock.lock();
//        try{
//            balance -= amount;
//        }finally{
//            lock.unlock();
//        }
//    }

//LOCKING MANUALLY with tryLock
    public void deposit(double amount) {
        Boolean status = false;

        try {
            if(lock.tryLock(1000,TimeUnit.MILLISECONDS)){
                try{
                    balance += amount;
                    status = true;
                }finally{
                    lock.unlock();
                }
            }else{
                System.out.println("Couldn't get the lock.");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        System.out.println("Transact.on status = " + status);
    }
 
    public void withdraw(double amount) {
        boolean status = false;
        
        try {
            if(lock.tryLock(1000,TimeUnit.MILLISECONDS)){
                try{
                    balance -= amount;
                    status = true;
                }finally{
                    lock.unlock();
                }
            }else{
                System.out.println("Couldn't get the lock.");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }   

        System.out.println("Transact.on status = " + status);
    }


public String getAccountNumber(){
        return this.accountNumber;
    }

    public void printAccountNumber(){
        System.out.println("Account number = " + this.accountNumber);
    }


}