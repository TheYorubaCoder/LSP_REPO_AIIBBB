package org.howard.edu.lsp.finalexam.question3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeCalculatorTest {

    private GradeCalculator calculator;

    @BeforeEach
    public void setUp() {
        calculator = new GradeCalculator();
    }

    // --- average() ---

    @Test
    public void testAverageOfThreeScores() {
        assertEquals(80.0, calculator.average(70, 80, 90), 0.001);
    }

    // --- letterGrade() ---

    @Test
    public void testLetterGradeA() {
        assertEquals("A", calculator.letterGrade(95.0));
    }

    @Test
    public void testLetterGradeB() {
        assertEquals("B", calculator.letterGrade(85.0));
    }

    @Test
    public void testLetterGradeC() {
        assertEquals("C", calculator.letterGrade(75.0));
    }

    @Test
    public void testLetterGradeD() {
        assertEquals("D", calculator.letterGrade(65.0));
    }

    @Test
    public void testLetterGradeF() {
        assertEquals("F", calculator.letterGrade(55.0));
    }

    // --- isPassing() ---

    @Test
    public void testIsPassingReturnsTrueAbove60() {
        assertTrue(calculator.isPassing(75.0));
    }

    @Test
    public void testIsPassingReturnsFalseBelow60() {
        assertFalse(calculator.isPassing(59.9));
    }

    // --- Boundary value tests ---

    @Test
    public void testAverageAtExactlyZero() {
        assertEquals(0.0, calculator.average(0, 0, 0), 0.001);
    }

    @Test
    public void testAverageAtExactlyHundred() {
        assertEquals(100.0, calculator.average(100, 100, 100), 0.001);
    }

    @Test
    public void testLetterGradeAtExactly90BoundaryIsA() {
        assertEquals("A", calculator.letterGrade(90.0));
    }

    @Test
    public void testLetterGradeAtExactly60BoundaryIsD() {
        assertEquals("D", calculator.letterGrade(60.0));
    }

    @Test
    public void testIsPassingAtExactly60Boundary() {
        assertTrue(calculator.isPassing(60.0));
    }

    // --- Exception tests ---

    @Test
    public void testAverageThrowsExceptionForScoreAbove100() {
        assertThrows(IllegalArgumentException.class, () -> calculator.average(101, 50, 50));
    }

    @Test
    public void testAverageThrowsExceptionForNegativeScore() {
        assertThrows(IllegalArgumentException.class, () -> calculator.average(-1, 50, 50));
    }
}
