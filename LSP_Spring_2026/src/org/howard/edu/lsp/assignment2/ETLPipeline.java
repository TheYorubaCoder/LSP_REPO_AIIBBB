package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {
    private static final BigDecimal TEN = new BigDecimal("10");
    private static final BigDecimal HUNDRED = new BigDecimal("100");
    private static final BigDecimal FIVE_HUNDRED = new BigDecimal("500");
    private static final BigDecimal ELECTRONICS_DISCOUNT = new BigDecimal("0.9");

    private static final String INPUT_FILE = "data/products.csv";
    private static final String OUTPUT_FILE = "data/transformed_products.csv";
    private static final String DELIMITER = ",";

    public static void main(String[] args) {
        // check that the products.csv file exists
        File inputFile = new File(INPUT_FILE);
        if (!inputFile.exists()) {
            System.out.println("Input file products.csv not found");
            return;
        }


        System.out.println("Starting ETL Pipeline...");

        // 3. Call method
        ETLPipeline.readCSV(INPUT_FILE, DELIMITER);

        //System.out.println("ETL Process Finished.");
    }

    public static void readCSV(String csvFilePath, String csvDelimiter) {
        int rowsRead = 0;
        int rowsSkipped = 0;
        int rowsTransformed = 0;

        // Ensure data directory exists
        File dataDir = new File("data");
        if (!dataDir.exists()) dataDir.mkdir();


        // 'Try With Resources' to auto-close the reader

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
             PrintWriter writer = new PrintWriter(new File(OUTPUT_FILE))) {
            // 1. Write the Header Row
            writer.println("ProductID,Name,Price,Category,PriceRange");


            String line = br.readLine(); // read header once
            while ((line = br.readLine()) != null) {
                BigDecimal newPrice;
                String priceRange;
                String updatedCategory;

                rowsRead++;
                // Skip Blank Lines (if any).
                if (line.trim().isEmpty()) {
                    rowsSkipped++;
                    continue;
                }


                // Split the file data line into its respective columnar slot.
                String[] lineParts = line.split(csvDelimiter);

                if (lineParts.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                String productId = lineParts[0].trim();
                String name = lineParts[1].trim();
                String price = lineParts[2].trim();
                String category = lineParts[3].trim();

                BigDecimal parsedPrice;
                /** check that @productId and @price are parsable **/
                try {
                    Integer.parseInt(productId);
                    parsedPrice = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);;

                } catch(Exception e) {
                    System.out.println("Skipping row due to invalid ProductID or Price");
                    rowsSkipped++;
                    continue;
                }

                // transform name to upper case
                String upperName = name.toUpperCase();
                updatedCategory = category;

                //apply 10% discount on electronics, change category if discounted price is greater than 500
                if(category.equals("Electronics")){
                    newPrice = parsedPrice.multiply(ELECTRONICS_DISCOUNT);

                    if (newPrice.compareTo(FIVE_HUNDRED)>0){
                        updatedCategory = "Premium Electronics";
                    }
                }
                else{
                    newPrice = parsedPrice;
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


                // Load Data Rows to csv file
                writer.println( productId + "," + upperName + "," +  newPrice + "," +  updatedCategory+ "," + priceRange);
                rowsTransformed++;
            }

            System.out.println("Number of rows read: " + (rowsRead));
            System.out.println("Number of rows transformed: " + rowsTransformed);
            System.out.println("Number of rows skipped: " + rowsSkipped);
            System.out.println("File created successfully at: " + OUTPUT_FILE);
        }
        // Trap these Exceptions
        catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        }
        catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

}





