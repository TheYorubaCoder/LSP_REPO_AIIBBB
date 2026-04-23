package org.howard.edu.lsp.finalexam.question2;

import java.util.List;
import java.util.ArrayList;

/**
 * Abstract base class defining the Template Method pattern for report generation.
 * The fixed workflow is: loadData → formatHeader → formatBody → formatFooter → generateReport.
 * Subclasses must implement the variable steps but cannot alter the workflow.
 */
public abstract class Report {

    /**
     * Template method defining the fixed report generation workflow.
     * This method is final to prevent subclasses from altering the sequence.
     */
    public final void generateReport() {
        loadData();
        System.out.println("=== HEADER ===");
        System.out.println(formatHeader());
        System.out.println("=== BODY ===");
        System.out.println(formatBody());
        System.out.println("=== FOOTER ===");
        System.out.println(formatFooter());
    }

    /**
     * Loads report-specific data before formatting begins.
     * Subclasses must initialize their fields here.
     */
    protected abstract void loadData();

    /**
     * Returns the formatted header string for the report.
     *
     * @return header content
     */
    protected abstract String formatHeader();

    /**
     * Returns the formatted body string for the report.
     *
     * @return body content
     */
    protected abstract String formatBody();

    /**
     * Returns the formatted footer string for the report.
     *
     * @return footer content
     */
    protected abstract String formatFooter();
}
