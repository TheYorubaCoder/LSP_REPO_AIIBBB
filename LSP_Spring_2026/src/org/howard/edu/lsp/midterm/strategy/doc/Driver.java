package org.howard.edu.lsp.midterm.strategy.doc;

/**
 * Driver class to demonstrate the Template Method pricing pattern.
 * 
 * @author Ibukunoluwa Adeloye
 */
public class Driver {

    /**
     * Main method that demonstrates price calculation for each customer type
     * using a base purchase price of 100.0.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        PriceCalculator calculator = new PriceCalculator();
        double price = 100.0;

        System.out.println("REGULAR: " + calculator.calculatePrice("REGULAR", price));
        System.out.println("MEMBER: " + calculator.calculatePrice("MEMBER", price));
        System.out.println("VIP: " + calculator.calculatePrice("VIP", price));
        System.out.println("HOLIDAY: " + calculator.calculatePrice("HOLIDAY", price));
    }
}