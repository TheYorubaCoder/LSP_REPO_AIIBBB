package org.howard.edu.lsp.finalexam.question2;

/**
 * Concrete report implementation for course data.
 * Displays course name and enrollment count in the report workflow.
 */
public class CourseReport extends Report {

    private String courseName;
    private int enrollment;

    /**
     * Loads hardcoded course data into fields for use during formatting.
     */
    @Override
    protected void loadData() {
        courseName = "CSCI 363";
        enrollment = 45;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String formatHeader() {
        return "Course Report\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String formatBody() {
        return "Course: " + courseName + "\nEnrollment: " + enrollment + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String formatFooter() {
        return "End of Course Report\n";
    }
}
