**Class:** Customer<br>
**Responsibilities:** holds customer info, access methods<br>
**Collaborators:** none

---

**Class:** PriceCalculator<br>
**Responsibilities:** calculates tax + final price, calculates discount + final price<br>
**Collaborators:** Customer

---

**Class:** OrderDatabase<br>
**Responsibilities:** writes order to file, logs order process<br>
**Collaborators:** Customer

---

**Class:** CustomerNotifier<br>
**Responsibilities:** prints receipt, sends confirmation email<br>
**Collaborators:** Customer

---

**Class:** OrderProcessor<br>
**Responsibilities:** creates Customer, calls PriceCalculator, delegates to Database and Notifier<br>
**Collaborators:** Customer, PriceCalculator, OrderDatabase, CustomerNotifier