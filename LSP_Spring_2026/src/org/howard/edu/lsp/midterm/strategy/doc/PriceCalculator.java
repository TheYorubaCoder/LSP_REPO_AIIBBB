package org.howard.edu.lsp.midterm.strategy.doc;

public class PriceCalculator {

    public double calculatePrice(String customerType, double price) {
        PricingStrategy strategy = switch (customerType) {
            case "REGULAR" -> new RegularPricing();
            case "MEMBER"  -> new MemberPricing();
            case "VIP"     -> new VIPPricing();
            case "HOLIDAY" -> new HolidayPricing();
            default -> throw new IllegalArgumentException("Unknown customer type: " + customerType);
        };
        return strategy.calculatePrice(price);
    }
}
