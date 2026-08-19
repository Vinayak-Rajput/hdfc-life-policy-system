# HDFC Life Policy System

A modular Java console application that simulates policy creation, premium calculation, claim processing, and notification events using Factory, Strategy, Observer, and layered architecture patterns.

---
## Project Structure

```
hdfc-life-policy-system/
src/com/hdfclife/
Main.java
model/          Policy, TermLifePolicy, UlipPolicy, EndowmentPolicy, Claim, Urgency
store/          PolicyStore
config/         AppConfig
factory/        PolicyFactory
strategy/       PremiumStrategy, TermPremiumStrategy, UlipPremiumStrategy,
                EndowmentPremiumStrategy, PremiumCalculator
observer/       ClaimObserver, ClaimEventPublisher, InAppNotifier, BranchLetterNotifier
service/        ClaimService, AuditLogger
exception/      PolicyServiceException, PolicyNotFoundException,
                InvalidClaimException, UnknownPolicyTypeException
README.md
.gitignore
```

---
## SOLID Principles Followed

1. **SRP** - Single Responsibility Principle
   - **PolicyStore** only stores and retrieves policy objects. It does not calculate premiums or process claims.
   - **ClaimService** handles only claim validation and processing. It does not notify observers or store policies.
   - **PremiumCalculator** only delegates premium calculation to specific strategies. It does not create policies or store data. 
   - **PolicyFactory** is responsible only for creating the correct policy type.


2. **OCP** - Open Closed Principle
   - **New policy types** (e.g., MoneyBackPolicy) can be added by extending Policy and plugging in new premium strategies.
   - **New premium algorithms** can be introduced by creating new classes implementing PremiumStrategy without modifying PremiumCalculator.
   - **Adding new observers** (e.g., EmailNotifier) requires only implementing ClaimObserver and registering it; no changes to publisher or service.


3. **LSP** - Liskov Substitution Principle
   - **TermLifePolicy**, **UlipPolicy**, and **EndowmentPolicy** can all be used wherever **Policy** is expected.
   - Any class implementing **PremiumStrategy** can be injected into **PremiumCalculator** without changing system behavior.


4. **ISP** - Interface Segregation Principle
   - **PremiumStrategy** contains only one method: calculate(Policy policy).
   - **ClaimObserver** contains only one method: update(Claim claim).


5. **DIP** - Dependency Inversion Principle
   - **PremiumCalculator** depends on the abstraction **PremiumStrategy**, not concrete strategies.
   - **ClaimService** depends on **ClaimEventPublisher** (interface-like abstraction), not the actual notifiers.
   - **PolicyFactory** returns Policy objects, not concrete types.

---
## Design Patterns Used

### 1. Factory Pattern
**Location:** `factory/PolicyFactory.java`

The factory pattern is used to centralize the creation of policy objects such as TermLifePolicy, UlipPolicy, and EndowmentPolicy.  
This ensures:
- Object creation logic is in one place.
- New policy types can be added without modifying client code.
- The system remains decoupled from specific implementations.

Example behavior:
The client requests a policy by type string, and the factory returns the correct subclass of Policy.

### 2. Strategy Pattern
**Location:** `strategy/`

Premium calculation varies for different policy types.  
To avoid large conditional blocks and to keep algorithms separate, each policy type has its own premium strategy:
- TermPremiumStrategy
- UlipPremiumStrategy
- EndowmentPremiumStrategy

PremiumCalculator accepts a PremiumStrategy at runtime, which allows:
- Changing algorithms without altering the Policy class.
- Adding new premium formulas without modifying existing code.

### 3. Observer Pattern
**Location:** `observer/`

When a claim is filed, different modules need to react to the event.  
Instead of directly invoking notifiers inside ClaimService, an Observer setup is used:
- ClaimEventPublisher manages observer registration.
- Observers include InAppNotifier and BranchLetterNotifier.
- When a claim is created, the publisher notifies all attached observers.

This provides:
- Loose coupling between claim logic and notification logic.
- Ability to add or remove observers without modifying ClaimService.


### 4. Singleton Pattern
**Location:**
- `config/AppConfig.java`

AppConfig loads initial configurations and seed data once.

By using Singleton, the system ensures:
- Controlled instance creation.
- Consistent global state where necessary (store, config).

---

## Other Features

### Centralised Services 
**Location:** `service/`

Services such as ClaimService and AuditLogger contain business logic independent of models and UI.  
This ensures:
- Clean separation of concerns.
- Models stay simple and only carry data.
- Adding new operations does not affect data structures.

### Custom Exception Pattern
**Location:** `exception/`

Custom exceptions clearly represent specific failure scenarios:
- PolicyNotFoundException
- InvalidClaimException
- UnknownPolicyTypeException
- PolicyServiceException

This helps:
- Improve error clarity.
- Avoid catching generic exceptions.
- Maintain clean fail-fast behavior.

### Dependency Injection (Manual)
**Location:** `AppConfig`

AppConfig manually creates and wires:
- Strategies
- Observers
- Services
- PolicyStore
- Publisher

Although it is not a framework-driven DI, it follows the same principle:
- Objects receive their required dependencies from outside.
- Classes do not create their own dependencies, improving testability.

### Interface-Based Abstraction
Interfaces such as PremiumStrategy and ClaimObserver enable:
- Swapping behavior at runtime.
- Avoiding tight coupling to concrete classes.
- Adding new behaviors without modifying existing implementation.

This supports both OCP and DIP in SOLID principles.

---

