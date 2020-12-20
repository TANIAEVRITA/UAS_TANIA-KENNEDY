-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 10, 2020 at 05:31 AM
-- Server version: 10.4.14-MariaDB
-- PHP Version: 7.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `villa`
--

-- --------------------------------------------------------

--
-- Table structure for table `akun`
--

CREATE TABLE `akun` (
  `id` int(11) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `noTelp` int(13) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `kelamin` enum('Laki-Laki','Perempuan') NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `akun`
--

INSERT INTO `akun` (`id`, `nama`, `noTelp`, `alamat`, `kelamin`, `email`, `password`) VALUES
(1, 'a', 1, 'a', 'Laki-Laki', 'asd', 'asd');

-- --------------------------------------------------------

--
-- Table structure for table `checkin`
--

CREATE TABLE `checkin` (
  `namaTamu` varchar(30) NOT NULL,
  `noKamar` char(2) NOT NULL,
  `lokasi` varchar(20) NOT NULL,
  `tipeVilla` enum('Standard','Deluxe') NOT NULL,
  `lama` varchar(20) NOT NULL,
  `biaya` int(11) NOT NULL,
  `jumlahTamu` int(11) NOT NULL,
  `tanggalCheckIn` varchar(22) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `checkin`
--

INSERT INTO `checkin` (`namaTamu`, `noKamar`, `lokasi`, `tipeVilla`, `lama`, `biaya`, `jumlahTamu`, `tanggalCheckIn`) VALUES
('a', 'A1', 'Bali', 'Deluxe', '3 hari', 2000000, 2, '4/12/2020');

-- --------------------------------------------------------

--
-- Table structure for table `checkout`
--

CREATE TABLE `checkout` (
  `namaTamu` varchar(30) NOT NULL,
  `noKamar` char(2) NOT NULL,
  `lokasi` varchar(20) NOT NULL,
  `tipeVilla` enum('Standard','Deluxe') NOT NULL,
  `tanggalCheckIn` varchar(22) NOT NULL,
  `tanggalCheckOut` varchar(22) NOT NULL,
  `jumlahTamu` int(11) NOT NULL,
  `biaya` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `checkout`
--

INSERT INTO `checkout` (`namaTamu`, `noKamar`, `lokasi`, `tipeVilla`, `tanggalCheckIn`, `tanggalCheckOut`, `jumlahTamu`, `biaya`) VALUES
('a', 'A1', 'Bali', 'Deluxe', '21/1/2020', '21/12/2020', 2, 1000000);

-- --------------------------------------------------------

--
-- Table structure for table `entryvilla`
--

CREATE TABLE `entryvilla` (
  `noVilla` varchar(8) NOT NULL,
  `lokasi` varchar(20) NOT NULL,
  `tipe` enum('Standard','Deluxe') NOT NULL,
  `harga` varchar(50) NOT NULL,
  `kapasitas` int(11) NOT NULL,
  `fasilitas` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `entryvilla`
--

INSERT INTO `entryvilla` (`noVilla`, `lokasi`, `tipe`, `harga`, `kapasitas`, `fasilitas`) VALUES
('C1', 'Bandung', 'Standard', 'Rp8.000.000/Malam', 2, 'ac,wifi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `akun`
--
ALTER TABLE `akun`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `entryvilla`
--
ALTER TABLE `entryvilla`
  ADD PRIMARY KEY (`noVilla`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `akun`
--
ALTER TABLE `akun`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
