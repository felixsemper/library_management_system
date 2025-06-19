-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 19, 2025 at 02:17 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity_logs`
--

CREATE TABLE `activity_logs` (
  `id` int(11) NOT NULL,
  `description` text NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `author` varchar(255) NOT NULL,
  `isbn` varchar(13) NOT NULL,
  `category` varchar(100) NOT NULL,
  `publication_year` int(11) DEFAULT NULL,
  `publisher` varchar(255) DEFAULT NULL,
  `cover_image` varchar(255) DEFAULT NULL,
  `total_copies` int(11) NOT NULL,
  `available_copies` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `title`, `author`, `isbn`, `category`, `publication_year`, `publisher`, `cover_image`, `total_copies`, `available_copies`, `description`, `location`) VALUES
(61, 'The Great Gatsby', 'F. Scott Fitzgerald', '978-074327356', 'Fiction', 1925, 'Scribner', 'great_gatsby.jpg', 10, 8, 'A novel about the American Dream.', 'Shelf A-1'),
(62, 'To Kill a Mockingbird', 'Harper Lee', '978-044631078', 'Fiction', 1960, 'J.B. Lippincott & Co.', 'mockingbird.jpg', 15, 12, 'A story of racial injustice.', 'Shelf A-2'),
(63, '1984', 'George Orwell', '978-045152493', 'Dystopian', 1949, 'Secker & Warburg', '1984.jpg', 12, 10, 'A tale of totalitarian surveillance.', 'Shelf B-1'),
(64, 'Pride and Prejudice', 'Jane Austen', '978-014143951', 'Romance', 1813, 'T. Egerton', 'pride_prejudice.jpg', 20, 18, 'A classic love story.', 'Shelf B-2'),
(65, 'The Catcher in the Rye', 'J.D. Salinger', '978-031676948', 'Fiction', 1951, 'Little, Brown and Company', 'catcher_rye.jpg', 10, 7, 'A story of teenage rebellion.', 'Shelf C-1');

-- --------------------------------------------------------

--
-- Table structure for table `loans`
--

CREATE TABLE `loans` (
  `id` int(11) NOT NULL,
  `book_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL,
  `request_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `due_date` date DEFAULT NULL,
  `status` enum('Pending','Approved','Rejected','Returned','ReadyForPickup') DEFAULT 'Pending',
  `priority` enum('Normal','Mendesak','Prioritas Tinggi') DEFAULT 'Normal',
  `notes` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `id` int(11) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `membership_type` enum('Pelajar','Fakultas','Umum','Premium') NOT NULL,
  `birth_date` date DEFAULT NULL,
  `address` text DEFAULT NULL,
  `status` enum('Aktif','Ditangguhkan','Kedaluwarsa') DEFAULT 'Aktif',
  `fines` decimal(10,2) DEFAULT 0.00,
  `registration_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `user_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `first_name`, `last_name`, `email`, `phone`, `membership_type`, `birth_date`, `address`, `status`, `fines`, `registration_date`, `user_id`) VALUES
(12, 'John', 'Doe', 'member@library.com', '081234567890', '', '1990-05-15', '123 Main St', '', 0.00, '2023-01-09 17:00:00', 1);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('admin','member') NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `role`, `name`, `created_at`) VALUES
(1, 'admin3@library.com', 'admin789', 'admin', 'Admin', '2025-06-19 10:12:45'),
(2, 'member2@library.com', 'member456', 'member', 'John doe', '2025-06-19 10:12:45');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity_logs`
--
ALTER TABLE `activity_logs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indexes for table `loans`
--
ALTER TABLE `loans`
  ADD PRIMARY KEY (`id`),
  ADD KEY `book_id` (`book_id`),
  ADD KEY `member_id` (`member_id`);

--
-- Indexes for table `members`
--
ALTER TABLE `members`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity_logs`
--
ALTER TABLE `activity_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=66;

--
-- AUTO_INCREMENT for table `loans`
--
ALTER TABLE `loans`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `members`
--
ALTER TABLE `members`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity_logs`
--
ALTER TABLE `activity_logs`
  ADD CONSTRAINT `activity_logs_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL;

--
-- Constraints for table `loans`
--
ALTER TABLE `loans`
  ADD CONSTRAINT `loans_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `loans_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `members` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `members`
--
ALTER TABLE `members`
  ADD CONSTRAINT `members_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE SET NULL;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
