**AI Tools Used:**
Claude (Anthropic)

---

**Prompts Used:**

**Prompt 1:**
> "In the Template Method pattern, which methods should be abstract and which should be concrete in the base class? How does that apply to a report system with loadData, formatHeader, formatBody, formatFooter, and generateReport?"

**Response summary:**
> generateReport() should be the single concrete method in the base class — it defines the fixed sequence by calling the other four steps in order. loadData, formatHeader, formatBody, and formatFooter should all be abstract, forcing each subclass to provide its own implementation. This is the core of Template Method: the skeleton lives in the parent, the details live in the children.

---

**Prompt 2:**
> "Should loadData() be abstract or concrete in this report design, given that each subclass needs to set its own field values like studentName and gpa?"

**Response summary:**
> loadData() should be abstract. Since StudentReport needs to initialize studentName and gpa while CourseReport needs courseName and enrollment, there's no shared implementation the base class can provide. Making it abstract forces each subclass to take responsibility for its own data setup before the rest of the workflow runs.

---

**Prompt 3:**
> "How do I demonstrate polymorphism correctly using List\<Report\> when calling generateReport() on both StudentReport and CourseReport?"

**Response summary:**
> Declare the list as List\<Report\>, add instances of StudentReport and CourseReport to it, then loop through and call generateReport() on each element. Because generateReport() is defined in the base class and both subclasses inherit it, Java resolves the correct formatHeader/formatBody/formatFooter implementations at runtime — that's polymorphism in action.

---

**How AI Helped:**

AI helped me clarify which methods belong as abstract versus concrete in the base class, specifically confirming that generateReport() should be the fixed concrete method that calls the variable abstract steps. It also helped me think through why loadData() needs to be abstract since each subclass owns its own data fields. I wrote and verified all code myself before submitting.

---

**Reflection:**

Working through this problem with AI reinforced that the Template Method pattern is really about where you place control — the parent class owns the sequence, and subclasses only fill in the variable parts. That distinction became much clearer once I had to explain it out loud rather than just read about it.