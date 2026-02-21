package org.howard.edu.lsp.assignment3;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ProductLoader {
    private ETLConfig config;

    public ProductLoader(ETLConfig config) {
        this.config = config;

    }

    /**
     * This loads the transformed list of products into the output file "data/transformed_products.csv"
     * Writes a header row followed by each transformed product as a comma separated row.
     *
     * @param transformedProducts the list of transformed product objects
     */

    public void loadTransformed(List<Product> transformedProducts) {
    	
        try (PrintWriter writer = new PrintWriter(new File(config.getOutputFile()))) {
            writer.println("ProductID,Name,Price,Category,PriceRange");
            for (Product product : transformedProducts) {
                // Load Data Rows to csv file
                writer.println(product.getProductId() + "," + product.getName() + "," + product.getPrice() + "," + product.getCategory() + "," + product.getPriceRange());
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
