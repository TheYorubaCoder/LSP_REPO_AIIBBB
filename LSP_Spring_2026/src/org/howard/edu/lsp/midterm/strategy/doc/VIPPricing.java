package org.howard.edu.lsp.midterm.strategy.doc;

/**
 * Concrete pricing strategy for VIP customers.
 * Applies a 20% discount to the base price.
 * 
 * @author Ibukunoluwa Adeloye
 */
public class VIPPricing extends PricingStrategy {

    /**
     * Returns the discount multiplier for VIP customers.
     * 
     * @return 0.80, representing a 20% discount
     */
    protected double getDiscountMultiplier() { return 0.80; }
}