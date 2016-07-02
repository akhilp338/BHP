-- phpMyAdmin SQL Dump
-- version 4.2.12deb2+deb8u1build0.15.04.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 30, 2016 at 07:36 PM
-- Server version: 5.6.28-0ubuntu0.15.04.1
-- PHP Version: 5.6.4-4ubuntu6.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `belhopat_backoffice`
--

ALTER table `TimeZone` DROP COLUMN timeZoneCode;
ALTER TABLE `TimeZone` ADD COLUMN UTC_OFFSET varchar(10) DEFAULT '+00:00';

--
-- Dumping data for table `TimeZone`
--

INSERT INTO `TimeZone` (`id`, `UTC_OFFSET`) VALUES
(1, '+00:00'),
(2, '+01:00'),
(3, '+02:00'),
(4, '+03:00'),
(5, '+03:30'),
(6, '+04:00'),
(7, '+04:30'),
(8, '+05:00'),
(9, '+05:30'),
(10, '+05:45'),
(11, '+06:00'),
(12, '+06:30'),
(13, '+07:00'),
(14, '+08:00'),
(15, '+08:45'),
(16, '+09:00'),
(17, '+09:30'),
(18, '+10:00'),
(19, '+10:30'),
(20, '+11:00'),
(21, '+11:30'),
(22, '+12:00'),
(23, '+12:45'),
(24, '+13:00'),
(25, '+14:00'),
(26, '-01:00'),
(27, '-02:00'),
(28, '-03:00'),
(29, '-03:30'),
(30, '-04:00'),
(31, '-04:30'),
(32, '-05:00'),
(33, '-06:00'),
(34, '-07:00'),
(35, '-08:00'),
(36, '-09:00'),
(37, '-09:30'),
(38, '-10:00'),
(39, '-11:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
