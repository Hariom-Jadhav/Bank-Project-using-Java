# 🏦 Bank Management System (Console App)

A complete **Core Java Console Application** that simulates a banking system. It allows administrators to manage customer accounts, perform transactions (Credit/Debit), and update user details directly in a MySQL database.

## 🚀 Features

* **1. Add Customer:** Create a new bank account with Name, Account Number, Initial Balance, and Mobile Number.
* **2. Credit Money:** Deposit funds into a specific account (updates database instantly).
* **3. Debit Money:** Withdraw funds with validation (checks if sufficient balance exists).
* **4. Check Balance / View All:** * View details of a specific account.
    * View a formatted list of **ALL** customers in the bank.
* **5. Delete Account:** Permanently remove a customer's record from the database.
* **6. Update Information:** Modify a customer's **Name** or **Mobile Number** using their Account Number.

## 🛠️ Technology Stack

* **Language:** Java (JDK 8+)
* **Database:** MySQL
* **Connectivity:** JDBC (Java Database Connectivity)
* **Tools:** Eclipse IDE, MySQL Workbench

---

## 🗄️ Database Setup (SQL Query)

You must create the database and table before running the application. Copy and run this script in your MySQL Workbench or Command Line:

```sql
-- 1. Create Database
CREATE DATABASE bank;

-- 2. Use the Database
USE bank;

-- 3. Create Table
CREATE TABLE customer (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    accountNo BIGINT UNIQUE,
    balance DECIMAL(10, 2),
    mobileNo BIGINT
);

-- (Optional) Insert a dummy record to test
INSERT INTO customer (name, accountNo, balance, mobileNo) 
VALUES ('John Doe', 1001, 5000.00, 9876543210);
