package ucu.lab7.payment;

public class PayPalPaymentStrategy implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Processing PayPal payment: " + amount + " UAH");
    }
}
