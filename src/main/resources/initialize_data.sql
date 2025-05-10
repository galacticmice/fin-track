-- Financial Tracker Data Initialization

-- Users data (15 users)
INSERT INTO "Users" (username, email, password, creation_time) VALUES
('john_doe', 'john.doe@example.com', 'password123', '2023-01-01 10:00:00'),
('jane_smith', 'jane.smith@example.com', 'securepass456', '2023-01-02 11:30:00'),
('mike_johnson', 'mike.johnson@example.com', 'mikepass789', '2023-01-03 09:15:00'),
('sarah_williams', 'sarah.williams@example.com', 'sarahpass123', '2023-01-04 14:20:00'),
('david_brown', 'david.brown@example.com', 'davidpass456', '2023-01-05 16:45:00'),
('lisa_davis', 'lisa.davis@example.com', 'lisapass789', '2023-01-06 08:30:00'),
('robert_miller', 'robert.miller@example.com', 'robertpass123', '2023-01-07 12:15:00'),
('emily_wilson', 'emily.wilson@example.com', 'emilypass456', '2023-01-08 10:45:00'),
('michael_moore', 'michael.moore@example.com', 'michaelpass789', '2023-01-09 09:00:00'),
('jessica_taylor', 'jessica.taylor@example.com', 'jessicapass123', '2023-01-10 15:30:00'),
('william_anderson', 'william.anderson@example.com', 'williampass456', '2023-01-11 11:20:00'),
('olivia_thomas', 'olivia.thomas@example.com', 'oliviapass789', '2023-01-12 13:40:00'),
('james_jackson', 'james.jackson@example.com', 'jamespass123', '2023-01-13 10:10:00'),
('sophia_white', 'sophia.white@example.com', 'sophiapass456', '2023-01-14 14:50:00'),
('daniel_harris', 'daniel.harris@example.com', 'danielpass789', '2023-01-15 09:25:00');

-- Activity data (15 entries per user for the first 3 users = 45 total)
-- User 1 (John Doe) activities
INSERT INTO "Activity" (entry_time, user_id, category_id, activity_type, amount, description) VALUES
('2023-02-01 08:00:00', 1, 13, true, 5000.00, 'Monthly salary'),
('2023-02-02 09:30:00', 1, 1, false, 1500.00, 'Rent payment'),
('2023-02-03 12:15:00', 1, 3, false, 150.00, 'Grocery shopping'),
('2023-02-04 15:45:00', 1, 4, false, 100.00, 'Electricity bill'),
('2023-02-05 10:20:00', 1, 9, false, 50.00, 'Movie tickets'),
('2023-02-06 14:30:00', 1, 2, false, 40.00, 'Gas for car'),
('2023-02-07 11:00:00', 1, 7, false, 500.00, 'Savings deposit'),
('2023-02-08 16:15:00', 1, 5, false, 200.00, 'Health insurance'),
('2023-02-09 09:45:00', 1, 10, false, 80.00, 'New shirt'),
('2023-02-10 13:30:00', 1, 6, false, 30.00, 'Pharmacy'),
('2023-02-11 10:15:00', 1, 14, true, 100.00, 'Stock dividend'),
('2023-02-12 14:00:00', 1, 11, false, 25.00, 'Online course'),
('2023-02-13 11:30:00', 1, 12, false, 50.00, 'Charity donation'),
('2023-02-14 15:20:00', 1, 8, false, 60.00, 'Haircut'),
('2023-02-15 09:00:00', 1, 15, true, 200.00, 'Freelance work');

-- User 2 (Jane Smith) activities
INSERT INTO "Activity" (entry_time, user_id, category_id, activity_type, amount, description) VALUES
('2023-02-01 08:30:00', 2, 13, true, 6000.00, 'Monthly salary'),
('2023-02-02 10:00:00', 2, 1, false, 1800.00, 'Rent payment'),
('2023-02-03 13:00:00', 2, 3, false, 200.00, 'Grocery shopping'),
('2023-02-04 16:30:00', 2, 4, false, 120.00, 'Electricity bill'),
('2023-02-05 11:00:00', 2, 9, false, 70.00, 'Concert tickets'),
('2023-02-06 15:15:00', 2, 2, false, 50.00, 'Gas for car'),
('2023-02-07 12:00:00', 2, 7, false, 800.00, 'Savings deposit'),
('2023-02-08 17:00:00', 2, 5, false, 250.00, 'Health insurance'),
('2023-02-09 10:30:00', 2, 10, false, 120.00, 'New shoes'),
('2023-02-10 14:15:00', 2, 6, false, 45.00, 'Pharmacy'),
('2023-02-11 11:00:00', 2, 14, true, 150.00, 'Stock dividend'),
('2023-02-12 15:00:00', 2, 11, false, 35.00, 'Books'),
('2023-02-13 12:30:00', 2, 12, false, 100.00, 'Charity donation'),
('2023-02-14 16:15:00', 2, 8, false, 80.00, 'Spa day'),
('2023-02-15 09:45:00', 2, 15, true, 300.00, 'Freelance work');

-- User 3 (Mike Johnson) activities
INSERT INTO "Activity" (entry_time, user_id, category_id, activity_type, amount, description) VALUES
('2023-02-01 09:00:00', 3, 13, true, 5500.00, 'Monthly salary'),
('2023-02-02 10:30:00', 3, 1, false, 1600.00, 'Rent payment'),
('2023-02-03 13:30:00', 3, 3, false, 180.00, 'Grocery shopping'),
('2023-02-04 17:00:00', 3, 4, false, 110.00, 'Electricity bill'),
('2023-02-05 11:30:00', 3, 9, false, 60.00, 'Movie tickets'),
('2023-02-06 15:45:00', 3, 2, false, 45.00, 'Gas for car'),
('2023-02-07 12:30:00', 3, 7, false, 600.00, 'Savings deposit'),
('2023-02-08 17:30:00', 3, 5, false, 220.00, 'Health insurance'),
('2023-02-09 11:00:00', 3, 10, false, 90.00, 'New pants'),
('2023-02-10 14:45:00', 3, 6, false, 35.00, 'Pharmacy'),
('2023-02-11 11:30:00', 3, 14, true, 120.00, 'Stock dividend'),
('2023-02-12 15:30:00', 3, 11, false, 30.00, 'Online course'),
('2023-02-13 13:00:00', 3, 12, false, 75.00, 'Charity donation'),
('2023-02-14 16:45:00', 3, 8, false, 70.00, 'Haircut'),
('2023-02-15 10:15:00', 3, 15, true, 250.00, 'Freelance work');

-- History data (15 entries)
INSERT INTO "History" (user_id, month_year, budget) VALUES
(1, '2023-01-01', 3000.00),
(1, '2023-02-01', 3200.00),
(1, '2023-03-01', 3300.00),
(2, '2023-01-01', 3500.00),
(2, '2023-02-01', 3600.00),
(2, '2023-03-01', 3700.00),
(3, '2023-01-01', 3200.00),
(3, '2023-02-01', 3300.00),
(3, '2023-03-01', 3400.00),
(4, '2023-01-01', 2800.00),
(4, '2023-02-01', 2900.00),
(5, '2023-01-01', 3100.00),
(5, '2023-02-01', 3200.00),
(6, '2023-01-01', 2700.00),
(7, '2023-01-01', 3000.00);

-- Recurrence data (15 entries)
INSERT INTO "Recurrence" (user_id, activity_id, interval_days, last_change) VALUES
(1, 1, 30, '2023-02-01'),
(1, 2, 30, '2023-02-02'),
(1, 4, 30, '2023-02-04'),
(2, 16, 30, '2023-02-01'),
(2, 17, 30, '2023-02-02'),
(2, 19, 30, '2023-02-04'),
(3, 31, 30, '2023-02-01'),
(3, 32, 30, '2023-02-02'),
(3, 34, 30, '2023-02-04'),
(1, 7, 90, '2023-02-07'),
(2, 22, 90, '2023-02-07'),
(3, 37, 90, '2023-02-07'),
(1, 8, 365, '2023-02-08'),
(2, 23, 365, '2023-02-08'),
(3, 38, 365, '2023-02-08');