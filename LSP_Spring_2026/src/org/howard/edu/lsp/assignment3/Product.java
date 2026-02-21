package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents a product extracted from the CSV file.
 * Holds all fields associated with a single product row.
 * This class is used throughout the ETL pipeline to store,
 * transform, and load product data.
 */
public class Product {
    private String productId;
    private String name;
    private BigDecimal price;
    private String category;
    private String priceRange;

    /**
     * Constructs a raw Product with fields extracted directly from the CSV file.
     * The priceRange is not initialized at this stage because it is assigned
     * during the transformation phase.
     *
     * @param productId the unique identifier of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param category the category the product belongs to
     */
    public Product(String productId, String name, BigDecimal price, String category){
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Constructs a fully transformed Product including the assigned price range.
     * This constructor uses constructor chaining to reuse initialization logic
     * from the primary constructor.
     *
     * @param productId the unique identifier of the product
     * @param name the name of the product
     * @param price the price of the product
     * @param category the category the product belongs to
     * @param priceRange the calculated price range classification
     */
    public Product(String productId, String name, BigDecimal price, String category, String priceRange) {
        this(productId, name, price, category);
        this.priceRange = priceRange;
    }

    /**
     * Sets the product identifier.
     *
     * @param productId the unique identifier to assign to this product
     */
    public void setProductId(String productId){
        this.productId = productId;
    }

    /**
     * Sets the product name.
     *
     * @param name the name to assign to this product
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Sets the product price.
     *
     * @param price the price value to assign to this product
     */
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    /**
     * Sets the product category.
     *
     * @param category the category to assign to this product
     */
    public void setCategory(String category){
        this.category = category;
    }

    /**
     * Sets the price range classification of the product.
     *
     * @param priceRange the price range classification (Low, Medium, High, Premium)
     */
    public void setPriceRange(String priceRange){
        this.priceRange = priceRange;
    }

    /**
     * Returns the product identifier.
     *
     * @return the product's unique identifier
     */
    public String getProductId(){
        return this.productId;
    }

    /**
     * Returns the product name.
     *
     * @return the name of the product
     */
    public String getName(){
        return this.name;
    }

    /**
     * Returns the product price.
     *
     * @return the price of the product as a BigDecimal
     */
    public BigDecimal getPrice(){
        return this.price;
    }

    /**
     * Returns the product category.
     *
     * @return the category of the product
     */
    public String getCategory(){
        return this.category;
    }

    /**
     * Returns the price range classification of the product.
     *
     * @return the price range classification (Low, Medium, High, Premium)
     */
    public String getPriceRange(){
        return this.priceRange;
    }
}