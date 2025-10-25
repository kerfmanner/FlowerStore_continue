package ucu.lab7.payment;

public class CreditCardPaymentStrategy implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Processing Credit Card payment: " + amount + " UAH");
    }
}
