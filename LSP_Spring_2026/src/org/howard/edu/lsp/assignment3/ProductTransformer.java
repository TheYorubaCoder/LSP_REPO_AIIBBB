package org.howard.edu.lsp.assignment3;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class ProductTransformer {


    private static final BigDecimal TEN = new BigDecimal("10");
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal FIVE_HUNDRED = new BigDecimal("500");
    private static final BigDecimal ELECTRONICS_DISCOUNT = new BigDecimal("0.9");
    private static int rowsTransformed = 0;

    /**
     * Transforms a list of raw products by applying business rules.
     * Applies a 10% discount to electronics, assigns price ranges,
     * and converts product names to uppercase.
     *
     * @param products the list of raw products to transform
     * @return a list of transformed products
     */
    public List<Product> transform(List<Product> products){
        List<Product> transformedProducts = new ArrayList<>();
        
        for(Product product : products){
            String name = product.getName();
            BigDecimal price = product.getPrice();
            String category = product.getCategory();

            BigDecimal newPrice;
            String priceRange;
            String updatedCategory;

            // transform name to uppercase
            String upperName = name.toUpperCase();
            updatedCategory = category;

            //apply 10% discount on electronics, change category if discounted price is greater than 500
            if(category.equals("Electronics")){
                newPrice = price.multiply(ELECTRONICS_DISCOUNT).setScale(2, RoundingMode.HALF_UP);

                if (newPrice.compareTo(FIVE_HUNDRED)>0){
                    updatedCategory = "Premium Electronics";
                }
            }
            else{
                newPrice = price;
                updatedCategory = category;
            }

            //create new metric per row called price range based on price
            if (newPrice.compareTo(TEN)<=0){
                priceRange = "Low";
            }
            else if (newPrice.compareTo(HUNDRED)<=0 &&
                    newPrice.compareTo(TEN)>0){
                priceRange = "Medium";
            }
            else if (newPrice.compareTo(FIVE_HUNDRED)<=0 &&
                    newPrice.compareTo(HUNDRED)>0){
                priceRange = "High";
            }
            else{
                priceRange = "Premium";
            }

            product.setName(upperName);
            product.setCategory(updatedCategory);
            product.setPrice(newPrice);
            product.setPriceRange(priceRange);
            transformedProducts.add(product);
            rowsTransformed++;
        }
    return transformedProducts;
    }

    public Integer getRowsTransformed(){
        return rowsTransformed;
    }

}
