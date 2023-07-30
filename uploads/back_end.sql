-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 30, 2023 at 03:25 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `back_end`
--

-- --------------------------------------------------------

--
-- Table structure for table `users_entity`
--

CREATE TABLE `users_entity` (
  `id` varchar(36) NOT NULL,
  `create_date` datetime NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `lastname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `role` int(1) NOT NULL,
  `update_date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users_entity`
--

INSERT INTO `users_entity` (`id`, `create_date`, `email`, `firstname`, `lastname`, `password`, `phone`, `picture`, `role`, `update_date`) VALUES
('2c6db344952042d88ac898ea93f80e9fb488', '2023-07-28 22:12:41', 'test@gmail.com2', 'Test', 'Test', '$2a$10$/R7ijqVcc0MOqYxvdNKoCuWEc4RaaO89SFurs.JwJf/A72KHyexLa', '0610720330', NULL, 1, '2023-07-28 22:12:41'),
('fc2719af9a8547a8b2cb1ae48611f5b77682', '2023-07-28 17:03:24', 'test@gmail.com2', 'Testtest', 'Test', '$2a$10$RIcSDBWPFIl440f6xYoOue8UEm2Libk3El0xrfYKwX3tZ62r/bE1C', '0610720330', '2023-07-29_16-01-08.png', 1, '2023-07-29 16:01:08');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users_entity`
--
ALTER TABLE `users_entity`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
