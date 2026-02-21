package org.howard.edu.lsp.assignment3;

/**
 * Stores configuration settings for the ETL pipeline,
 * including file paths and the CSV delimiter.
 * 
 * This class centralizes runtime configuration values so they
 * can be shared across the Extract, Transform, and Load components.
 */
public class ETLConfig {
    private String inputFile;
    private String outputFile;
    private String delimiter;

    /**
     * Constructs an ETLConfig with the specified file paths and delimiter.
     *
     * @param inputFile the path to the input CSV file
     * @param outputFile the path to the output CSV file
     * @param delimiter the delimiter used to separate values in the CSV file
     */
    public ETLConfig(String inputFile, String outputFile, String delimiter){
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.delimiter = delimiter;
    }

    /**
     * Returns the configured input file path.
     *
     * @return the path to the input CSV file
     */
    public String getInputFile(){
        return inputFile;
    }
    
    /**
     * Returns the configured output file path.
     *
     * @return the path to the output CSV file
     */
    public String getOutputFile(){
        return outputFile;
    }
    
    /**
     * Returns the delimiter used for parsing the CSV file.
     *
     * @return the delimiter string (e.g., "," for comma-separated files)
     */
    public String getDelimiter(){
        return delimiter;
    }
}