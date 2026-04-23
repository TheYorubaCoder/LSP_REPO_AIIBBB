Part 1: 
Shared Resource #1: nextId (int)

Shared Resource #2:requests (ArrayList)

Concurrency Problem: Race condition — specifically a read-modify-write race on nextId and a TOCTOU compound action race on addRequest()

Why addRequest() is unsafe: It combines two operations on shared state — consuming an ID and inserting into the list — without making them atomic as a unit. A thread can be preempted in the gap between the two steps, allowing another thread to observe or act on a partially updated object where an ID has been consumed but no corresponding list entry exists yet.


Part 2:
Fix A: (incorrect) Synchronizing getNextId() alone protects the ID increment in isolation, but addRequest() is still unsynchronized. Another thread can still jump into the gap between getting the ID and adding to the list. The compound action is still broken.

Fix B:(correct) This is the right one. Synchronizing addRequest() makes the entire compound action atomic — no thread can observe the object in a half-updated state. Since addRequest() is the only method that writes to both shared resources, putting the lock here covers everything that matters.

Fix C: (incorrect) Synchronizing getRequests() protects the read, but the damage happens during writes. By the time another thread calls getRequests(), the list may already be corrupted. Locking the door after the break-in doesn't help.

Part 3: 
No, getNextId() should not be public.Riel's heuristics push toward minimal interfaces — a class should only expose what clients actually need to interact with it. The guiding principle is that implementation details should be hidden behind the class boundary.
getNextId() is an implementation detail of how addRequest() works internally. No outside caller needs to generate an ID — that's the class's job. Making it public breaks encapsulation, exposes internals and invites misuse — a public method is a contract. Other code will depend on it, and now you can't change or remove it without breaking something

Part 4:
Description: 
Yes — AtomicInteger combined with ConcurrentLinkedQueue (or CopyOnWriteArrayList) gives you lock-free thread safety without synchronized.
1. Instead of locking a block of code so only one thread runs it at a time, atomic classes use CPU-level compare-and-swap (CAS) instructions. CAS says: "only update this value if it's still what I expect — otherwise retry." No thread ever blocks, they just retry on collision. ConcurrentLinkedQueue applies the same idea internally to the list structure.
This makes the two shared resources safe individually. The tradeoff vs synchronized is that you lose the ability to make the compound action atomic as a single unit — but if ordering between ID generation and insertion doesn't strictly matter to your use case, it works well.

Code Snippet:
private final AtomicInteger nextId = new AtomicInteger(1);
private final ConcurrentLinkedQueue<String> requests = new ConcurrentLinkedQueue<>();

public void addRequest(String studentName) {
    int id = nextId.getAndIncrement(); // atomic CAS, no lock needed
    requests.add("Request-" + id + " from " + studentName);
}

	