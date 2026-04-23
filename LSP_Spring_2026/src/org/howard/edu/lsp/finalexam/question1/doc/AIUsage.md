
AI Tools Used:
Claude

Prompts Used (2–5 max):
Prompt 1:

"In the RequestManager class, nextId and the ArrayList are both accessed by addRequest(). What concurrency problems could occur if multiple threads call addRequest() at the same time?"

Response summary AI gave:

Both nextId and the ArrayList are shared resources. If two threads call addRequest() simultaneously, they can read the same value of nextId before either increments it, producing duplicate IDs (a race condition). The ArrayList is also not thread-safe, so concurrent adds can corrupt its internal state.


Prompt 2:

"Fix A only synchronizes getNextId(). Fix B synchronizes the entire addRequest() method. Which one correctly solves the concurrency problem and why?"

Response summary AI gave:

Fix B is correct. Synchronizing addRequest() keeps the ID generation and list insertion atomic as one operation, preventing another thread from interleaving between the two steps. Fix A only protects the ID counter but leaves the ArrayList add unprotected, so a race condition on the list still exists.


Prompt 3:

"Based on Riel's OO heuristics, should getNextId() be public? Explain."

Response summary AI gave:

No — by Riel's heuristics, a class should minimize its public interface and only expose what external clients need. getNextId() is an internal implementation detail that supports addRequest(); making it public breaks encapsulation and exposes internal state management that callers shouldn't control directly.

How AI Helped (2–3 sentences):
AI helped me think through which synchronization fix was truly atomic versus only partially protected, which clarified why Fix B is the correct solution. It also helped me articulate Riel's encapsulation heuristic in the context of getNextId() being an internal detail. I verified all answers against my own understanding of the material before submitting.

Reflection (1–2 sentences):
Using AI to talk through concurrency concepts helped me see the distinction between protecting a single operation versus protecting a compound action as one atomic unit. I found that explaining my reasoning to the AI and then checking its response against my notes was a more effective study loop than just re-reading slides.
