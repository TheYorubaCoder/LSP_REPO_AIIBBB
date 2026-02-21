package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Main orchestrator class for the ETL (Extract-Transform-Load) pipeline.
 * This class coordinates the extraction, transformation, and loading of product data
 * from a CSV file.
 *
 * The pipeline performs the following steps:
 * 1. Extracts product data from the input CSV file
 * 2. Transforms the extracted product data
 * 3. Loads the transformed data into an output CSV file
 *
 * It also reports statistics such as number of rows read, transformed, and skipped.
 *
 * @author Ibukunoluwa Adeloye
 * @version 1.0
 */
public class ETLPipelineOrchestrator {

    /**
     * Entry point for the ETL pipeline application.
     * This method initializes the ETL configuration, creates instances of the
     * extractor, transformer, and loader, and executes the full ETL process.
     *
     * The method performs the following operations:
     * - Reads product data from the input CSV file
     * - Applies transformations to the data
     * - Writes the transformed data to the output CSV file
     * - Prints execution statistics to the console
     *
     * @param args command-line arguments (not used in this implementation)
     */
    public static void main(String[] args) {
        ETLConfig config = new ETLConfig("data/products.csv", "data/transformed_products.csv", ",");

        ProductExtractor extractor = new ProductExtractor(config);
        ProductTransformer transformer = new ProductTransformer();
        ProductLoader loader = new ProductLoader(config);

        System.out.println("Starting ETL Pipeline...");

        List<Product> products = extractor.extractData();
        if (products == null) {
            System.out.println("Stopping pipeline.");
            return; 
        }

        List<Product> transformedProducts = transformer.transform(products);
        loader.loadTransformed(transformedProducts);

        System.out.println("Number of rows read: " + extractor.getRowsRead());
        System.out.println("Number of rows transformed: " + transformer.getRowsTransformed());
        System.out.println("Number of rows skipped: " + extractor.getRowsSkipped());
        System.out.println("File created successfully at: " + config.getOutputFile());
    }
}