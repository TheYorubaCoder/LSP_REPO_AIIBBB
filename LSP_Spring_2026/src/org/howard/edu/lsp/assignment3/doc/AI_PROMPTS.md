# AI Prompts and Responses – Assignment 3
### CSCI 363 | ETL Pipeline OO Redesign

---

## Prompt 1 – Initial OO Design

**Prompt:**
> "I have this code and I am trying to re-design it to make it more object oriented. My first idea is to have an ETLUtils class to contain all the constants. Then an Extract class, Transform class and Load class but I am unsure if that is the best way to make it object oriented following standard OOP principles."

**AI Response Summary:**
The AI advised against a simple ETLUtils constants class, noting it is not truly OOP but more of a workaround. Instead it recommended the following class structure based on Single Responsibility Principle:

- `Product` – a data model (POJO) to represent a single CSV row with typed fields
- `ProductExtractor` – reads and parses the CSV, returning a `List<Product>`
- `ProductTransformer` – applies all business rules and returns a transformed `List<Product>`
- `ProductLoader` – writes the transformed list to the output CSV
- `ETLConfig` – holds configuration values (file paths, delimiter) as instance fields
- `ETLPipelineOrchestrator` – the main class that wires all stages together

The AI also mentioned that defining `Extractor`, `Transformer`, and `Loader` interfaces would allow swapping implementations later, but noted this was optional for the scope of the assignment.

---

## Prompt 2 – ETLConfig and Instance Fields

**Prompt:**
> "What do you mean by instance fields in ETLConfig instead of constants?"

**AI Response Summary:**
The AI explained the difference between static constants (hardcoded, belong to the class, unchangeable at runtime) and instance fields (belong to an object, set via constructor, flexible). It showed that with instance fields, a config object can be created with different values at runtime, passed into other classes, and potentially loaded from a file later. Static constants like `public static final String INPUT_FILE` are not truly OOP since they cannot be swapped without changing the source code.

---

## Prompt 3 – ETLConfig Contents

**Prompt:**
> "I am thinking the ETL config should have just the input file, output file and the delimiter. Is that okay or am I missing anything?"

**AI Response Summary:**
The AI confirmed this was a clean and appropriate design. It noted that `ELECTRONICS_DISCOUNT` could arguably belong in the config as a business rule that might change, but for this assignment it was perfectly reasonable to keep it as a constant inside `ProductTransformer` alongside the price thresholds. The advice was to keep the config focused on what is genuinely configurable.

---

## Prompt 4 – General Java Class Structure

**Prompt:**
> "What is the general structure for creating a class in Java?"

**AI Response Summary:**
The AI outlined the standard Java class structure in order: fields (private instance variables), constructor(s) (using `this.fieldName` to distinguish from parameters), getters and setters to control access, and then any additional methods. It emphasised that fields should be `private` to enforce encapsulation, and that `this` is used to distinguish instance fields from constructor parameters when they share the same name.

---

## Prompt 5 – ProductExtractor Initialisation

**Prompt:**
> "I know ProductExtractor will contain everything related to extracting data in the products file, but I am wondering how I would initialise and create the class."

**AI Response Summary:**
The AI showed that `ProductExtractor` only needs one field — the `ETLConfig` object — passed in via constructor. The `extract()` method then contains all the CSV reading and parsing logic, returning a `List<Product>`. The pattern is: the constructor sets up what the object *needs*, and the method is what it actually *does*. It also clarified that config could be a field (constructor injection) or a method parameter, and explained the tradeoff between both approaches.

---

## Prompt 6 – Why a Product Class?

**Prompt:**
> "Why do I need a Product class?"

**AI Response Summary:**
The AI explained that without a `Product` class, data gets passed between pipeline stages as loose raw strings with no type safety. A `Product` class bundles all fields into a typed object, allowing `List<Product>` to be passed cleanly between `ProductExtractor`, `ProductTransformer`, and `ProductLoader`. This is one of the most fundamental OOP concepts — modelling real-world entities as objects.

---

## Prompt 7 – List vs ArrayList

**Prompt:**
> "Why should I use a List instead of an ArrayList?"

**AI Response Summary:**
The AI clarified that `List` is an interface and `ArrayList` is its implementation, so both are used together: `List<Product> products = new ArrayList<>()`. Declaring the variable as `List` on the left side is a best practice called *programming to the interface* — if you ever need to swap `ArrayList` for a `LinkedList`, only one word changes and nothing else breaks. For this use case `ArrayList` is the right choice since we are just storing and iterating rows.

---

## Prompt 8 – Constructor Overloading and Chaining

**Prompt:**
> "Is it possible to have two Product constructors? One for the raw data and one for the transformed fields. This would be overloading right? Where would constructor chaining come in?"

**AI Response Summary:**
The AI confirmed this is constructor overloading — Java picks the right constructor based on the number of arguments. For constructor chaining, it showed that the second constructor (with `priceRange`) can call the first using `this(...)` as its first line, avoiding repeating field assignments. This means if a new field is added to the first constructor later, only one place needs updating instead of two.

```java
public Product(String productId, String name, BigDecimal price, String category, String priceRange) {
    this(productId, name, price, category); // calls first constructor
    this.priceRange = priceRange;
}
```

---

## Prompt 9 – Iterating a List

**Prompt:**
> "There seems to be an issue with how I am iterating through products."

**AI Response Summary:**
The AI identified that square bracket indexing (`products[i]`) is array syntax and does not work on a `List`. For lists, the `.get(i)` method is used instead. However it recommended a for-each loop as cleaner since the index is not needed. It also flagged that `config` was being passed as both a field and a method parameter unnecessarily, and that getters were missing from the `Product` class.

---

## Prompt 10 – ProductLoader Writing Only Last Row

**Prompt:**
> "For some reason my loader only writes the last row of the transformed data."

**AI Response Summary:**
The AI identified that `PrintWriter` was being opened inside the loop, which overwrites the file from scratch on every iteration. The fix was to move the `PrintWriter` outside the loop so the file is opened once and all rows are written to it. It also flagged the redundant `config` parameter again.

---

## Prompt 11 – ProductExtractor Writing Same Row Repeatedly

**Prompt:**
> "Now it just rewrites the same line over and over."

**AI Response Summary:**
The AI traced the issue to `ProductExtractor` — a single `Product` object was created outside the loop and reused by updating its fields on every iteration. Since all list entries pointed to the same object in memory, they all reflected the last row's values. The fix was to create a new `Product` object inside the loop on each iteration, and to pass the real values directly into the constructor instead of using setters.

---

## Prompt 12 – Javadoc Standards

**Prompt:**
> "How do I add Javadocs to each of the classes? Do I need Javadocs on get/set methods?"

**AI Response Summary:**
The AI explained that Javadocs use `/** */` syntax placed directly above classes, constructors, and public methods. Key tags are `@param` (describes a parameter), `@return` (describes the return value), and `@throws` (describes exceptions). Getters and setters do not need Javadocs since their purpose is self-explanatory — effort should focus on classes, constructors, and meaningful methods like `extractData()`, `transform()`, and `loadTransformed()`. Field-level Javadoc comments inside the `Product` class were recommended to describe what each field represents.

---

## Prompt 13 – Package Structure

**Prompt:**
> "Is this package structure matching what my instructor requires?" *(with screenshots of project structure)*

**AI Response Summary:**
The AI confirmed the structure matched the requirements exactly — all Java classes in `org.howard.edu.lsp.assignment3`, the `doc` subfolder containing both `.md` files, and `products.csv` in the `data` folder. It noted that `transformed_products.csv` would be generated automatically into the `data` folder on first run since `ETLConfig` was configured with `"data/transformed_products.csv"`.

## Prompt 14 - Markdown File

**Prompt:**
> "What is a .md file?"

**AI Response Summary:**
The AI explained that a `.md` file is a Markdown file used for formatted documentation, commonly used for README files and technical documentation.

## Prompt 15 – Where Should File Existence Check Live?

**Prompt:**
> "In top design should the checking if the products file exist be in the orchestrator or the extractor?"

**AI Response Summary:**
The AI recommended placing the file existence check inside `ProductExtractor` since that class is responsible for everything related to reading the file. Having the orchestrator know about file validation details would break Single Responsibility Principle. The extractor should return an empty list if the file is not found, and the orchestrator should check if the list is empty and stop the pipeline accordingly. This keeps each class focused on its own concern and means if the extractor is ever swapped for a database reader, the orchestrator does not need to change.

---

## Prompt 16 – Graceful Exit Not Stopping the Program

**Prompt:**
> "Why doesn't my graceful exit stop the whole code?"

**AI Response Summary:**
The AI identified that the `gracefulExit()` method only contained `return`, which returns control back to `extractData()` rather than stopping the program. Two proper options were explained:

**Option 1 (recommended) — return an empty list and handle it in the orchestrator:**
```java
if (!inputFile.exists()) {
    System.out.println("Input file products.csv not found");
    return products; // exits extractData() with empty list
}
```
Then in the orchestrator:
```java
if (products.isEmpty()) {
    System.out.println("No products extracted, stopping pipeline.");
    return;
}
```

**Option 2 — use `System.exit(0)` to terminate the JVM immediately:**
```java
System.exit(0);
```

The AI recommended Option 1 as the better OOP approach since it keeps the exit decision in the orchestrator rather than having the extractor forcefully terminate the program. `System.exit()` is considered bad practice as it bypasses normal program flow and makes code harder to test. The `gracefulExit()` method was advised to be removed entirely since it served no purpose.

---