package org.howard.edu.lsp.midterm.strategy.doc;

/**
 * Abstract base class that defines the Template Method pricing pattern.
 * Subclasses must implement getDiscountMultiplier() to provide
 * customer-type-specific pricing.
 *
 * @author Ibukunoluwa Adeloye
 */
public abstract class PricingStrategy {

    /**
     * Template method that calculates the final price for a customer.
     * This method is final and cannot be overridden by subclasses.
     *
     * @param price the base purchase price
     * @return the final price after applying the discount multiplier
     */
    public final double calculatePrice(double price) {
        return price * getDiscountMultiplier();
    }

    /**
     * Hook method that returns the discount multiplier for a specific customer type.
     * Must be implemented by each concrete subclass.
     *
     * @return the discount multiplier to apply to the base price
     */
    protected abstract double getDiscountMultiplier();
}