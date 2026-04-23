package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation for student data.
 * Displays student name and GPA in the report workflow.
 */
public class StudentReport extends Report {

    private String studentName;
    private double gpa;

    /**
     * Loads hardcoded student data into fields for use during formatting.
     */
    @Override
    protected void loadData() {
        studentName = "John Doe";
        gpa = 3.8;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String formatHeader() {
        return "Student Report\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String formatBody() {
        return "Student Name: " + studentName + "\nGPA: " + gpa + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String formatFooter() {
        return "End of Student Report\n";
    }
}