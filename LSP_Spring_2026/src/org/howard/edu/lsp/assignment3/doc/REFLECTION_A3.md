# Comparison of Assignment 2 and Assignment 3 ETL Implementations

Assignment 3 represents a major design improvement over Assignment 2 by applying object-oriented programming (OOP) principles to the ETL pipeline. While Assignment 2 followed a procedural approach with most logic contained in a single class and method, Assignment 3 restructures the system into multiple classes with clearly defined responsibilities. The result is a more modular, readable, and maintainable design.

## Differences in Design

In Assignment 2, the entire ETL process was implemented inside one class (`ETLPipeline`) and primarily within a single static method (`readCSV`). That method handled multiple responsibilities at once: reading the input file, validating data, transforming records, and writing the output file. Because everything was centralized, the method became long and harder to maintain or debug.

In Assignment 3, the design separates these responsibilities into different classes. Extraction, transformation, and loading are handled independently. Configuration values such as file paths and delimiters are stored in a dedicated configuration class instead of being hard-coded. This separation makes the system easier to modify. For example, changes to input/output files can be made in the configuration class without affecting transformation logic.

Another major design difference is the introduction of a `Product` class. In Assignment 2, product attributes (productId, name, price, category) were stored in separate local variables. In Assignment 3, those attributes are grouped into a single object, making the data more organized and easier to pass between components of the pipeline.

## How Assignment 3 is More Object-Oriented

Assignment 3 is more object-oriented because it models real-world concepts using classes and objects rather than relying on procedural logic. The `Product` class represents a product as an object containing all relevant fields, including product ID, name, price, category, and price range.

The ETL stages are also represented as separate classes, each with a single responsibility. This aligns with the Single Responsibility Principle. Instead of one large method performing all tasks, each class now focuses on one part of the process.

An orchestrator class coordinates the overall flow by creating objects and calling the appropriate methods in sequence. This improves organization and makes the program structure clearer.

## Object-Oriented Concepts Used

Several OOP concepts were applied in Assignment 3:

**Objects and Classes:**  
The `Product` class represents a product as a structured object instead of using scattered variables. Additional classes were created for extraction, transformation, loading, configuration, and orchestration.

**Encapsulation:**  
Product fields are stored within the `Product` class and accessed through constructors and methods. This protects data integrity and ensures that modifications occur in a controlled way. Configuration values are also encapsulated in a separate class.

**Polymorphism:**  
Polymorphism was implemented using multiple constructors in the `Product` class. One constructor initializes a product from raw input data, and another initializes a transformed product. This allows the same class to support different stages of the pipeline.

Although inheritance was not required, the design still demonstrates strong object-oriented structure through modularity and clear responsibility boundaries.

## Testing and Verification

To confirm that Assignment 3 works the same as Assignment 2, I ran both implementations using the same `products.csv` input file. First, I executed the Assignment 2 pipeline and generated `transformed_products.csv`. Then, I ran the Assignment 3 orchestrator class, which produced its own version of `transformed_products.csv`.

I compared both output files and confirmed that they were identical. All price calculations, category updates, and price range classifications matched exactly. This verified that the object-oriented redesign preserved the original functionality while improving the internal structure of the program.

## Conclusion

Assignment 3 improves upon Assignment 2 by applying object-oriented principles such as encapsulation, modular design, and separation of responsibilities. While both implementations produce the same output, Assignment 3 offers better organization, easier debugging, and greater flexibility for future modifications. The redesign successfully maintains functionality while significantly improving code quality.