Part 1: 
Shared Resource #1: nextId (int)

Shared Resource #2:requests (ArrayList)

Concurrency Problem: Race condition — specifically a read-modify-write race on nextId and a TOCTOU compound action race on addRequest()

Why addRequest() is unsafe: It combines two operations on shared state — consuming an ID and inserting into the list — without making them atomic as a unit. A thread can be preempted in the gap between the two steps, allowing another thread to observe or act on a partially updated object where an ID has been consumed but no corresponding list entry exists yet.


Part 2:

Fix A: (incorrect) Synchronizing getNextId() alone only protects the ID increment in isolation. addRequest() remains unsynchronized, so another thread can still interleave between the getNextId() call and the requests.add() call. The compound action — getting an ID and inserting into the list — is still not atomic, meaning the list can end up in a corrupted or inconsistent state.

Fix B: (correct) Synchronizing addRequest() makes the entire compound action atomic. Both the ID generation and the list insertion happen as one uninterruptible unit — no other thread can enter addRequest() while one is already executing it. This eliminates both the duplicate ID race and the unsafe ArrayList write, which are the only two write paths to the shared resources.

Fix C: (incorrect) Synchronizing getRequests() only protects reads of the list, but the concurrency problem occurs during writes in addRequest(). A race condition can corrupt the list before any thread ever calls getRequests(), so locking the read does nothing to prevent the damage from happening.
Part 3: 
No, getNextId() should not be public.Riel's heuristics push toward minimal interfaces — a class should only expose what clients actually need to interact with it. The guiding principle is that implementation details should be hidden behind the class boundary.
getNextId() is an implementation detail of how addRequest() works internally. No outside caller needs to generate an ID — that's the class's job. Making it public breaks encapsulation, exposes internals and invites misuse — a public method is a contract. Other code will depend on it, and now you can't change or remove it without breaking something

Part 4:
Description: 
The alternative approach uses atomic classes from java.util.concurrent.atomic — specifically AtomicInteger for nextId and ConcurrentLinkedQueue for the request list. AtomicInteger uses CPU-level compare-and-swap (CAS) instructions instead of locks: it only updates the value if it still matches what the thread expects, and retries otherwise. ConcurrentLinkedQueue applies the same lock-free strategy internally for the list structure. Together they make each individual operation thread-safe without the synchronized keyword.

Code Snippet:
private final AtomicInteger nextId = new AtomicInteger(1);
private final ConcurrentLinkedQueue<String> requests = new ConcurrentLinkedQueue<>();

public void addRequest(String studentName) {
    int id = nextId.getAndIncrement(); // atomic CAS, no lock needed
    requests.add("Request-" + id + " from " + studentName);
}

	