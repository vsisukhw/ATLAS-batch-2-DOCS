package Day26.

public class CardPaymentConcreteStrategy implements PracticeSet.atlaslearnings.day26.StrategyPattern.PaymentStrategy {
    public void process(double price) {
        System.out.println("payment processing using card" + price);
    }
}
