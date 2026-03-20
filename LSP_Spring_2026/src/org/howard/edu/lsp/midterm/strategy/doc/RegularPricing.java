package org.howard.edu.lsp.midterm.strategy.doc;

/**
 * Concrete pricing strategy for REGULAR customers.
 * Applies no discount to the base price.
 * 
 * @author Ibukunoluwa Adeloye
 */
public class RegularPricing extends PricingStrategy {

    /**
     * Returns the discount multiplier for REGULAR customers.
     * 
     * @return 1.00, representing no discount
     */
    protected double getDiscountMultiplier() { return 1.00; }
}