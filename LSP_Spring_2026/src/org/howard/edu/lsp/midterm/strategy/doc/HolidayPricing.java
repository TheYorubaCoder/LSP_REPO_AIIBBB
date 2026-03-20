package org.howard.edu.lsp.midterm.strategy.doc;

/**
 * Concrete pricing strategy for HOLIDAY customers.
 * Applies a 15% discount to the base price.
 * 
 * @author Ibukunoluwa Adeloye
 */
public class HolidayPricing extends PricingStrategy {

    /**
     * Returns the discount multiplier for HOLIDAY customers.
     * 
     * @return 0.85, representing a 15% discount
     */
    protected double getDiscountMultiplier() { return 0.85; }
}