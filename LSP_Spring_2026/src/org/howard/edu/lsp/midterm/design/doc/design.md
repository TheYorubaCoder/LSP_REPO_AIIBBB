**Class:** Customer
**Responsibilities:** holds customer info, access methods
**Collaborators:** none

---

**Class:** PriceCalculator
**Responsibilities:** calculates tax + final price, calculates discount + final price
**Collaborators:** Customer

---

**Class:** OrderDatabase
**Responsibilities:** writes order to file, logs order process
**Collaborators:** Customer

---

**Class:** CustomerNotifier
**Responsibilities:** prints receipt, sends confirmation email
**Collaborators:** Customer

---

**Class:** OrderProcessor
**Responsibilities:** creates Customer, calls PriceCalculator, delegates to Database and Notifier
**Collaborators:** Customer, PriceCalculator, OrderDatabase, CustomerNotifier