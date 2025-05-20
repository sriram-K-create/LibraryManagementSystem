# Library Management System

## Features

### ✅ Core Features
- Book Management (Add, Update, Remove, Search)
- Patron Management (Register, Update, Borrow History)
- Lending (Check-out, Return)
- Inventory Management

### 🚀 Optional Extensions
- Multi-branch Support
- Book Transfer Between Branches
- Reservation System with Notifications
- Recommendation System

## Run Instructions
```bash
javac -d out src/com/library/Main.java
java -cp out com.library.Main
```

## Class Diagram (Simple)

```
Patron
+ id
+ name
+ email
+ borrowingHistory
+ reservedBooks
+ notifyAvailable()

Book
+ isbn
+ title
+ author
+ publicationYear
+ isBorrowed
+ currentBranch

LibraryInitializer
+ books
+ patrons
+ reservations
+ branches
+ run()
```
