package org.howard.edu.lsp.midterm.strategy.doc;

/**
 * Concrete pricing strategy for MEMBER customers.
 * Applies a 10% discount to the base price.
 * 
 * @author Ibukunoluwa Adeloye
 */
public class MemberPricing extends PricingStrategy {

    /**
     * Returns the discount multiplier for MEMBER customers.
     * 
     * @return 0.90, representing a 10% discount
     */
    protected double getDiscountMultiplier() { return 0.90; }
}