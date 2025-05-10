-- Financial Tracker Database Schema

-- Drop tables if they exist (in reverse order of creation to handle foreign key constraints)
DROP TABLE IF EXISTS "Recurrence";
DROP TABLE IF EXISTS "History";
DROP TABLE IF EXISTS "Activity";
DROP TABLE IF EXISTS "Users";
DROP TABLE IF EXISTS "Category";

-- Create Category table
CREATE TABLE "Category" (
    category_id SERIAL PRIMARY KEY,
    description VARCHAR(50) NOT NULL
);

-- Initialize with default categories
INSERT INTO "Category" (description) VALUES ('Housing');
INSERT INTO "Category" (description) VALUES ('Transportation');
INSERT INTO "Category" (description) VALUES ('Food');
INSERT INTO "Category" (description) VALUES ('Utilities');
INSERT INTO "Category" (description) VALUES ('Insurance');
INSERT INTO "Category" (description) VALUES ('Medical');
INSERT INTO "Category" (description) VALUES ('Savings');
INSERT INTO "Category" (description) VALUES ('Personal');
INSERT INTO "Category" (description) VALUES ('Entertainment');
INSERT INTO "Category" (description) VALUES ('Clothing');
INSERT INTO "Category" (description) VALUES ('Education');
INSERT INTO "Category" (description) VALUES ('Gifts/Donations');
INSERT INTO "Category" (description) VALUES ('Salary');
INSERT INTO "Category" (description) VALUES ('Investment');
INSERT INTO "Category" (description) VALUES ('Other Income');
INSERT INTO "Category" (description) VALUES ('Other Expense');

-- Create Users table
CREATE TABLE "Users" (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(20) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    creation_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create Activity table
CREATE TABLE "Activity" (
    activity_id SERIAL PRIMARY KEY,
    entry_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    activity_type BOOLEAN NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES "Users"(user_id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES "Category"(category_id)
);

-- Create History table
CREATE TABLE "History" (
    user_id INT NOT NULL,
    month_year DATE NOT NULL,
    budget DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (user_id, month_year),
    FOREIGN KEY (user_id) REFERENCES "Users"(user_id) ON DELETE CASCADE
);

-- Create Recurrence table
CREATE TABLE "Recurrence" (
    recurrence_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    activity_id INT NOT NULL,
    interval_days INT NOT NULL,
    last_change DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES "Users"(user_id) ON DELETE CASCADE,
    FOREIGN KEY (activity_id) REFERENCES "Activity"(activity_id) ON DELETE CASCADE
);