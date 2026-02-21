package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for extracting raw product data from the input CSV file.
 * This class reads the file specified in the ETLConfig object, validates each row,
 * and converts valid rows into Product objects.
 * It also tracks statistics such as rows read and rows skipped.
 */
public class ProductExtractor {
    private ETLConfig config;
    private int rowsRead = 0;
    private int rowsSkipped = 0;

    /**
     * Constructs a ProductExtractor using the provided ETL configuration.
     * The configuration specifies the input file location and delimiter.
     *
     * @param config the ETLConfig object containing input file and delimiter settings
     */
    public ProductExtractor(ETLConfig config) {
        this.config = config;
    }

    /**
     * Extracts product data from the configured input CSV file.
     * <p>
     * This method performs the following steps:
     * <ul>
     *   <li>Reads the CSV file line by line</li>
     *   <li>Skips blank or improperly formatted rows</li>
     *   <li>Validates product ID and price values</li>
     *   <li>Creates Product objects for valid rows</li>
     *   <li>Tracks the number of rows read and skipped</li>
     * </ul>
     *
     * @return a List of Product objects representing valid rows extracted from the file;
     *         returns an empty list if the file does not exist or contains no valid data
     */
    public List<Product> extractData(){
        List<Product> products = new ArrayList<>();

        // Ensure data directory exists
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();

        File inputFile = new File(config.getInputFile());

        if (!inputFile.exists()) {
        	System.out.println("Input file products.csv not found");
            return null;
        }

        // Try-With-Resources automatically closes the reader
        try (BufferedReader br = new BufferedReader(new FileReader(config.getInputFile()))) {

            String line = br.readLine(); // read header once

            while ((line = br.readLine()) != null) {

                rowsRead++;

                // Skip blank lines
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                // Split row using configured delimiter
                String[] lineParts = line.split(config.getDelimiter());

                if (lineParts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String productId = lineParts[0].trim();
                String name = lineParts[1].trim();
                String price = lineParts[2].trim();
                String category = lineParts[3].trim();

                BigDecimal parsedPrice;

                // Validate productId and price
                try {
                    Integer.parseInt(productId);
                    parsedPrice = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);

                } catch (Exception e) {
                    System.out.println("Skipping row due to invalid ProductID or Price");
                    rowsSkipped++;
                    continue;
                }

                Product product = new Product(productId, name, parsedPrice, category);
                products.add(product);
            }

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return products;
    }

    /**
     * Returns the total number of rows read from the input file.
     * This includes both valid and invalid rows encountered during extraction.
     *
     * @return the number of rows read
     */
    public Integer getRowsRead(){
        return rowsRead;
    }

    /**
     * Returns the total number of rows skipped during extraction.
     * Rows may be skipped due to:
     * <ul>
     *   <li>Blank lines</li>
     *   <li>Incorrect column count</li>
     *   <li>Invalid product ID</li>
     *   <li>Invalid price format</li>
     * </ul>
     *
     * @return the number of rows skipped
     */
    public Integer getRowsSkipped(){
        return rowsSkipped;
    }

}