In the previous assignment, I had already written some test cases for the IntegerSet class. So in this assignment I used AI primarily to detect the test cases required in the assignment that I did not have coverage for. Below is the transcript:

# AI Usage Report — Assignment 6 (JUnit Test Cases)
**Course:** Large Scale Programming
**Student:** Ibukunoluwa Adeloye
**Date:** April 13, 2026
**Tool Used:** Claude (Anthropic) — claude.ai

---

## Summary of Use

Claude was used to review JUnit test coverage against assignment requirements and provide feedback on test correctness across three iterations.

---

## Conversation Transcript

---

**Prompt:**
*(Uploaded: HW_6_Writing_JUnit_test_cases, IntegerSetTest.java, IntegerSet.java)*
Which tests am I missing for the requirements of this assignment?

**Claude:**
Identified the following missing tests based on the assignment rubric:
- `clear()` on an already-empty set
- `length()` on an empty set
- `union()` with an empty set
- `diff()` on identical sets
- `complement()` on disjoint sets
- `isEmpty()` asserting false on a non-empty set
- `largest()` and `smallest()` with a single-element set

---

**Prompt:**
*(Uploaded updated IntegerSetTest.java)*
I have updated the tests, what do you think of my approach?

**Claude:**
Confirmed all previously missing tests were added. Identified one logical error: `testComplementDisjoint` expected an empty set, but based on the `complement()` implementation (elements in B not in A), disjoint sets should return all of B. Suggested correcting the expected value to `{2, 4}`. Also noted that `testIntersect` combined two scenarios in one method and recommended splitting for cleaner failure reporting.

---

**Prompt:**
*(Uploaded updated IntegerSetTest.java)*
Updated my tests, how about now?

**Claude:**
Confirmed the file was complete and correct. The complement fix was applied, intersect was split into `testIntersect` and `testIntersectDisjoint`, and all rubric requirements were covered. Reminded to include `AI_Usage_Report.md` in the correct directory.

---

## Conclusion

Claude was used for test coverage review and logical correctness feedback only. All test code was written by the student. No implementation code was generated or modified by AI.

---
