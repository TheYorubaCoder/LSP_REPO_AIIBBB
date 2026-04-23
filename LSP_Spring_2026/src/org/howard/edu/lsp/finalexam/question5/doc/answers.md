Heuristic 1:
Name: H2.1 — All data should be hidden within its class.
Explanation:
Classes should make all data attributes private, exposing state only through a controlled public interface. This improves maintainability because internal data structures can change without breaking outside code that depends on the class — callers are insulated from implementation details. In lecture, this was framed as the foundation of encapsulation.

---

Heuristic 2:
Name: H3.2 — Do not create god classes in your system.
Explanation:
No single class should centralize too much of the system's logic or data. This improves readability and maintainability because responsibilities are distributed across classes that are easier to understand, test, and modify in isolation. In lecture, this was illustrated with the home heating system example — the original HeatFlowRegulator was a god class that reached into Room to grab ActualTemp, DesiredTemp, and Occupancy before making decisions. The improved design moved that logic into Room itself via a do_I_need_heat() method, making HeatFlowRegulator much simpler and reducing inter-class dependency.

---

Heuristic 3:
Name: H5.1 — Inheritance should be used only to model a specialization hierarchy.
Explanation:
A subclass should represent an "is a special kind of" relationship, not a role or a convenience reuse. This improves maintainability because misusing inheritance — like extending a class just to reuse a method — creates brittle hierarchies where changes in the base class ripple unpredictably into subclasses. In lecture, this was connected to the Liskov Substitution Principle and illustrated with the Passenger/Agent example, where inheriting from Person failed because a person can switch roles at runtime, which static inheritance cannot model cleanly.