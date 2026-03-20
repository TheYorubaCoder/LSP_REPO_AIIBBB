**Design Evaluation of the OrderProcessor Class**

Looking at the `OrderProcessor` class, there are a few clear design issues that stand out when applying object-oriented principles, specifically Arthur Riel's design heuristics.

**Poor Encapsulation**

One of the first things I noticed is that all the customer information is just publicly available in one class. This is a problem because sensitive data — like billing or contact details — can be accessed and changed from anywhere in the codebase, which is exactly what Heuristic 2.1 warns against: all data should be hidden within its class. Customer data should be private and only accessible through controlled methods.

**The God Class Problem**

The bigger issue is that this class is trying to do everything. Tax calculation, discount logic, saving orders — it's all sitting in one place, which makes it a textbook God Class. Heuristic 2.8 says a class should capture one and only one key abstraction, and this class is doing the opposite. Instead of being object-oriented, the design ends up feeling procedural because all the logic is just stacked on top of each other in a single class.

**Cluttered Interface**

Because so much is crammed into one class, the public interface ends up full of things that have nothing to do with each other. Heuristics 2.3 and 2.6 both speak to this — you want to minimize what's exposed and avoid cluttering the interface with things users of the class don't need or shouldn't be touching in the first place.

**Why This Hurts Maintainability**

The practical consequence of all this is that any time something needs to change — whether that's how taxes are calculated, how discounts are applied, or how orders get saved — you have to go into the same God Class and modify it. That gets messy fast, and the risk of breaking something unrelated is high. On top of that, because there's no clear separation of concerns, the code is just hard to read. There's no obvious structure that tells you what each part is responsible for.
