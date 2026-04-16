DROP TABLE books;
DROP TABLE customers;
DROP TABLE orders;
 
CREATE DATABASE IF NOT EXISTS `final_project`;
USE `final_project`;
CREATE TABLE IF NOT EXISTS `books` (
    `ISBN` INT NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `genre` VARCHAR(25) NOT NULL,
    `publisher` VARCHAR(50) NOT NULL,
    `status` VARCHAR(15) NOT NULL,
    `quality` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
CREATE TABLE IF NOT EXISTS `customers` (
    `customerID` INT NOT NULL,
    `firstName` VARCHAR(50) NOT NULL,
    `lastName` VARCHAR(25) NOT NULL,
    `address` VARCHAR(50) NOT NULL,
    `phone` VARCHAR(15) NOT NULL,
    `email` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`customerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
 
CREATE TABLE IF NOT EXISTS orders (
    customerID INT NOT NULL,
    orderID INT NOT NULL,
    ISBN BIGINT NOT NULL,
    orderDate VARCHAR(20) NOT NULL,
    dueDate VARCHAR(20) NOT NULL,
    PRIMARY KEY (orderID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
 
INSERT INTO `books` (`ISBN`, `title`, `genre`, `publisher`, `status`, `quality`) VALUES
(123456781, 'The Silent Horizon', 'Fiction', 'HarperCollins', 'Available', 'Excellent'),
(234567892, 'Data Structures Simplified', 'Education', 'Pearson', 'Checked Out', 'Excellent'),
(345678903, 'Echoes of the Past', 'Historical Fiction', 'Penguin Books', 'Available', 'Fair'),
(456789014, 'The Lost Expedition', 'Adventure', 'Scholastic', 'Checked Out', 'Excellent'),
(567890125, 'Modern Economics', 'Non-Fiction', 'McGraw-Hill', 'Available', 'Fair'),
(678901236, 'Shadows in the Dark', 'Mystery', 'Simon & Schuster', 'Checked Out', 'Poor'),
(789012347, 'Classic Tales Reimagined', 'Literature', 'Oxford Press', 'Available', 'Excellent'),
(890123458, 'Global Politics Today', 'Political Science', 'Routledge', 'Checked Out', 'Fair'),
(901234569, 'Journey to the Stars', 'Science Fiction', 'Random House', 'Available', 'Excellent'),
(112233440, 'The Art of Thinking Clearly', 'Self-Help', 'W. W. Norton & Company', 'Available', 'Excellent');
 
INSERT INTO `customers`
(`customerID`, `firstName`, `lastName`, `address`, `phone`, `email`)
VALUES
(1, 'John', 'Smith', '123 Main St', '403-555-1234', 'john.smith@email.com'),
(2, 'Emily', 'Johnson', '456 Oak Ave', '403-555-5678', 'emily.j@email.com'),
(3, 'Michael', 'Brown', '789 Pine Rd', '403-555-9012', 'michael.b@email.com'),
(4, 'Sarah', 'Davis', '321 Maple Dr', '403-555-3456', 'sarah.d@email.com'),
(5, 'David', 'Wilson', '654 Cedar Ln', '403-555-7890', 'david.w@email.com'),
(6, 'Jessica', 'Taylor', '987 Birch Blvd', '403-555-2345', 'jessica.t@email.com'),
(7, 'Daniel', 'Anderson', '159 Elm St', '403-555-6789', 'daniel.a@email.com'),
(8, 'Ashley', 'Thomas', '753 Spruce Ct', '403-555-1122', 'ashley.t@email.com'),
(9, 'Matthew', 'Moore', '852 Aspen Way', '403-555-3344', 'matthew.m@email.com'),
(10, 'Olivia', 'Martin', '951 Willow Rd', '403-555-5566', 'olivia.m@email.com');
INSERT INTO orders (customerID, orderID, ISBN, orderDate, dueDate) VALUES
(1, 1001, 112233440, '2024-01-10', '2024-01-24'),
(2, 1002, 123456781, '2024-01-12', '2024-01-26'),
(3, 1003, 234567892, '2024-01-15', '2024-01-29'),
(4, 1004, 345678903, '2024-01-18', '2024-02-01'),
(5, 1005, 456789014, '2024-01-20', '2024-02-03'),
(6, 1006, 567890125, '2024-01-22', '2024-02-05'),
(7, 1007, 678901236, '2024-01-24', '2024-02-07'),
(8, 1008, 789012347, '2024-01-26', '2024-02-09'),
(9, 1009, 890123458, '2024-01-28', '2024-02-11'),
(10, 1010, 901234569, '2024-01-30', '2024-02-13');