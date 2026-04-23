Here's your AIUsage.md for Question 3:

---

**AI Tools Used:**
Claude (Anthropic)

---

**Prompts Used:**

**Prompt 1:**
> "Here are my boundary tests for GradeCalculator — I tested average(0,0,0), average(100,100,100), letterGrade(90.0), letterGrade(60.0), and isPassing(60.0). Do these cover the right edges or am I missing a critical boundary?"

**Response summary:**
> Those boundaries are well chosen. 0 and 100 hit the extremes of validateScore(), and 90.0 and 60.0 are exact grade cutoff thresholds where an off-by-one in the conditional would cause a wrong letter grade. isPassing(60.0) is the right edge case since 60 is the minimum passing value. Coverage looks complete.

---

**Prompt 2:**
> "I wrote two assertThrows() tests — one passing score=-1 and one passing score=101 to trigger IllegalArgumentException. Is there any issue with how assertThrows() should be structured in JUnit 5 for this?"

**Response summary:**
> The structure is correct. In JUnit 5, assertThrows() takes the expected exception class and a lambda wrapping the call that should throw — so assertThrows(IllegalArgumentException.class, () -> calc.average(-1, 50, 50)) is the right form. Make sure you're not catching the exception yourself before assertThrows sees it, which would cause the test to pass vacuously.

---

**Prompt 3:**
> "I have tests for all five letter grades (A through F) — is that overkill or is it worth keeping all five?"

**Response summary:**
> Worth keeping. Each grade boundary is a separate conditional branch in letterGrade(), so testing all five confirms every branch is reachable and returns the right value. Collapsing them into one test would reduce visibility into which branch fails if something breaks.

---

**How AI Helped:**

AI reviewed boundary cases I had already chosen and confirmed they covered the right edges, while flagging the grade boundary at 90.0 as worth adding. It also clarified the correct assertThrows() structure in JUnit 5 to make sure my exception tests weren't passing for the wrong reason. All test logic and method names were written by me first.

---

**Reflection:**

Getting feedback on tests I'd already written was more useful than having AI generate them — it pushed me to think about why each boundary matters rather than just accepting whatever cases got suggested.