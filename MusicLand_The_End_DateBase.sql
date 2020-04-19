-- --------------------------------------------------------
-- Sunucu:                       127.0.0.1
-- Sunucu sürümü:                10.3.12-MariaDB - mariadb.org binary distribution
-- Sunucu İşletim Sistemi:       Win64
-- HeidiSQL Sürüm:               9.4.0.5125
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- muzik için veritabanı yapısı dökülüyor
CREATE DATABASE IF NOT EXISTS `muzik` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;
USE `muzik`;

-- tablo yapısı dökülüyor muzik.album
CREATE TABLE IF NOT EXISTS `album` (
  `album_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `album_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`album_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.album: ~14 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` (`album_id`, `album_name`, `last_update`) VALUES
	(3, 'eqwrtqwq', '2019-04-04 09:09:16'),
	(4, 'dmewkfh', '2019-04-04 10:16:23'),
	(12, 'rty', '2019-04-20 12:16:19'),
	(13, 'sadwea', '2019-04-20 12:17:57'),
	(15, 'cdzc', '2019-04-26 19:59:20'),
	(17, 're34', '2019-05-04 18:09:45'),
	(18, 'wtfre', '2019-05-04 18:09:47'),
	(19, 'fer3', '2019-05-04 18:09:50'),
	(20, 'eg', '2019-05-04 18:09:53'),
	(21, 'erhu4', '2019-05-04 18:09:55'),
	(22, 'wutg', '2019-05-04 18:09:58'),
	(23, 'egt', '2019-05-04 18:10:00'),
	(24, 't54y', '2019-05-04 18:10:03');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.category
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`category_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.category: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` (`category_id`, `name`, `last_update`) VALUES
	(4, 'Classics', '2019-03-17 01:47:54'),
	(6, 'Documentary', '2019-03-17 01:47:54'),
	(7, 'Drama', '2019-03-17 01:47:54'),
	(8, 'Family', '2019-03-17 01:47:54'),
	(9, 'Foreign', '2019-03-17 01:47:54'),
	(10, 'Games', '2019-03-17 01:47:54'),
	(11, 'Horror', '2019-03-17 01:47:54'),
	(12, 'Music', '2019-03-17 01:47:54'),
	(162, 'jgtj', '2019-05-04 18:08:30'),
	(163, 'rtg', '2019-05-04 18:08:33'),
	(164, 'ghtrj', '2019-05-04 18:08:35');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.ceviri_dil
CREATE TABLE IF NOT EXISTS `ceviri_dil` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sarki_id` int(11) unsigned NOT NULL,
  `dil_id` int(10) unsigned NOT NULL,
  `sarki_Text` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `sarki_id` (`sarki_id`,`dil_id`),
  KEY `FK_ceviri_dil_dil` (`dil_id`),
  CONSTRAINT `FK_ceviri_dil_dil` FOREIGN KEY (`dil_id`) REFERENCES `dil` (`dil_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ceviri_dil_sarki` FOREIGN KEY (`sarki_id`) REFERENCES `sarki` (`sarki_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.ceviri_dil: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ceviri_dil` DISABLE KEYS */;
/*!40000 ALTER TABLE `ceviri_dil` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.dil
CREATE TABLE IF NOT EXISTS `dil` (
  `dil_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`dil_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.dil: ~10 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `dil` DISABLE KEYS */;
INSERT INTO `dil` (`dil_id`, `name`, `last_update`) VALUES
	(4, 'almanca', '2019-03-16 22:56:54'),
	(5, 'fransizca', '2019-03-16 22:57:06'),
	(27, 'arapcaa', '2019-04-08 00:34:25'),
	(40, 'farsca', '2019-05-04 18:08:54'),
	(41, 'isp', '2019-05-04 18:09:00'),
	(42, 'cin', '2019-05-04 18:09:09'),
	(43, 'jap', '2019-05-04 18:09:15'),
	(44, 'kor', '2019-05-04 18:09:19'),
	(45, 'srp', '2019-05-04 18:09:24'),
	(46, 'yun', '2019-05-04 18:09:30');
/*!40000 ALTER TABLE `dil` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.extra
CREATE TABLE IF NOT EXISTS `extra` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `sarki_id` int(11) unsigned NOT NULL,
  `sarki_puan` float unsigned DEFAULT NULL,
  `sarki_sure` float unsigned DEFAULT NULL,
  `ulke_id` int(10) unsigned NOT NULL,
  `sarkici_sirketi` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `sarki_id` (`sarki_id`),
  KEY `FK_extra_ulke` (`ulke_id`),
  CONSTRAINT `FK_extra_sarki` FOREIGN KEY (`sarki_id`) REFERENCES `sarki` (`sarki_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_extra_ulke` FOREIGN KEY (`ulke_id`) REFERENCES `ulke` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.extra: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `extra` DISABLE KEYS */;
/*!40000 ALTER TABLE `extra` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.kullanici_sarki_tablo
CREATE TABLE IF NOT EXISTS `kullanici_sarki_tablo` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `kullanici_id` int(10) unsigned NOT NULL,
  `sarki_id` int(10) unsigned NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `kullanici_id` (`kullanici_id`,`sarki_id`),
  CONSTRAINT `FK_kullanci_sarki_tablo_uyelik_yapan` FOREIGN KEY (`kullanici_id`) REFERENCES `uyelik_yapan` (`kullanici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=328 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.kullanici_sarki_tablo: ~15 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `kullanici_sarki_tablo` DISABLE KEYS */;
/*!40000 ALTER TABLE `kullanici_sarki_tablo` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.resim_document
CREATE TABLE IF NOT EXISTS `resim_document` (
  `id` int(11) unsigned DEFAULT NULL,
  `file_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_path` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.resim_document: ~6 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `resim_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `resim_document` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.sarki
CREATE TABLE IF NOT EXISTS `sarki` (
  `sarki_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `tmp_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8 DEFAULT NULL,
  `yil` year(4) DEFAULT NULL,
  `album_id` int(11) unsigned DEFAULT NULL,
  `dil_id` int(11) unsigned DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`sarki_id`),
  KEY `FK_sarki_album` (`album_id`),
  KEY `FK_sarki_dil` (`dil_id`),
  CONSTRAINT `FK_sarki_album` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_sarki_dil` FOREIGN KEY (`dil_id`) REFERENCES `dil` (`dil_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.sarki: ~5 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sarki` DISABLE KEYS */;
/*!40000 ALTER TABLE `sarki` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.sarkici
CREATE TABLE IF NOT EXISTS `sarkici` (
  `sarkici_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `ulke_id` int(10) unsigned DEFAULT NULL,
  `yas` int(11) unsigned DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`sarkici_id`),
  KEY `FK_sarkici_ulke` (`ulke_id`),
  CONSTRAINT `FK_sarkici_ulke` FOREIGN KEY (`ulke_id`) REFERENCES `ulke` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=233 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.sarkici: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sarkici` DISABLE KEYS */;
INSERT INTO `sarkici` (`sarkici_id`, `name`, `last_name`, `ulke_id`, `yas`, `last_update`) VALUES
	(217, 'dfdf', 'gudh', 26, 26, '2019-05-04 18:06:40'),
	(218, 'hgf', 'ghj', 27, 40, '2019-05-04 18:06:52'),
	(232, 'xxx', 'hjgcvesgh', 26, 0, '2019-05-27 13:48:20');
/*!40000 ALTER TABLE `sarkici` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.sarkici_album
CREATE TABLE IF NOT EXISTS `sarkici_album` (
  `sarkici_id` int(10) unsigned NOT NULL,
  `album_id` int(10) unsigned NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  KEY `FK__sarkici` (`sarkici_id`),
  KEY `FK_sarkici-album_album` (`album_id`),
  CONSTRAINT `FK__sarkici` FOREIGN KEY (`sarkici_id`) REFERENCES `sarkici` (`sarkici_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_sarkici-album_album` FOREIGN KEY (`album_id`) REFERENCES `album` (`album_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.sarkici_album: ~5 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sarkici_album` DISABLE KEYS */;
INSERT INTO `sarkici_album` (`sarkici_id`, `album_id`, `last_update`) VALUES
	(218, 23, '2019-05-27 14:25:40'),
	(218, 24, '2019-05-27 14:25:40'),
	(217, 3, '2019-05-27 16:30:21'),
	(217, 4, '2019-05-27 16:30:21'),
	(217, 12, '2019-05-27 16:30:21');
/*!40000 ALTER TABLE `sarkici_album` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.sarki_category
CREATE TABLE IF NOT EXISTS `sarki_category` (
  `sarki_id` int(11) unsigned NOT NULL,
  `category_id` int(11) unsigned NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`sarki_id`,`category_id`),
  KEY `FK_sarki_category_category` (`category_id`,`sarki_id`),
  CONSTRAINT `FK_sarki_category_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_sarki_category_sarki` FOREIGN KEY (`sarki_id`) REFERENCES `sarki` (`sarki_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.sarki_category: ~2 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sarki_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `sarki_category` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.sarki_document
CREATE TABLE IF NOT EXISTS `sarki_document` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_size` int(11) unsigned DEFAULT NULL,
  `file_path` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `file_type` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=133 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.sarki_document: ~5 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sarki_document` DISABLE KEYS */;
/*!40000 ALTER TABLE `sarki_document` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.sarki_sarkici
CREATE TABLE IF NOT EXISTS `sarki_sarkici` (
  `sarkici_id` int(11) unsigned NOT NULL,
  `sarki_id` int(11) unsigned NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`sarkici_id`,`sarki_id`),
  KEY `sarkici_id` (`sarkici_id`),
  KEY `sarki_id` (`sarki_id`),
  CONSTRAINT `FK_sarki_sarkici_sarki` FOREIGN KEY (`sarki_id`) REFERENCES `sarki` (`sarki_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_sarki_sarkici_sarkici` FOREIGN KEY (`sarkici_id`) REFERENCES `sarkici` (`sarkici_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.sarki_sarkici: ~0 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `sarki_sarkici` DISABLE KEYS */;
/*!40000 ALTER TABLE `sarki_sarkici` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.ulke
CREATE TABLE IF NOT EXISTS `ulke` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ulke` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `ulke` (`ulke`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.ulke: ~8 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `ulke` DISABLE KEYS */;
INSERT INTO `ulke` (`id`, `ulke`, `last_update`) VALUES
	(26, 'turkey', '2019-04-17 14:43:22'),
	(27, 'sdfqwefg', '2019-04-20 13:07:33'),
	(29, 'sdhf', '2019-04-20 13:28:55'),
	(30, 'csdcs', '2019-04-20 13:28:57'),
	(31, 'sdsa', '2019-04-20 13:28:59'),
	(35, 'jffd', '2019-05-01 01:50:42'),
	(36, 'rfhgr', '2019-05-01 01:50:45'),
	(38, 'fer?f', '2019-05-01 01:50:51');
/*!40000 ALTER TABLE `ulke` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.uyelik_yapan
CREATE TABLE IF NOT EXISTS `uyelik_yapan` (
  `kullanici_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` char(50) COLLATE utf8_unicode_ci NOT NULL,
  `kullanici` int(11) unsigned NOT NULL,
  PRIMARY KEY (`kullanici_id`),
  UNIQUE KEY `password` (`password`),
  UNIQUE KEY `username` (`username`),
  KEY `FK_uyelik_yapan_uyelik_yapan_bilgileri` (`kullanici`),
  CONSTRAINT `FK_uyelik_yapan_uyelik_yapan_bilgileri` FOREIGN KEY (`kullanici`) REFERENCES `uyelik_yapan_bilgileri` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=241 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.uyelik_yapan: ~27 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `uyelik_yapan` DISABLE KEYS */;
INSERT INTO `uyelik_yapan` (`kullanici_id`, `username`, `password`, `kullanici`) VALUES
	(135, 'said', 'said', 1),
	(145, 'aaaa', 'aaaa', 4),
	(146, 'bbbb', 'bbbb', 5),
	(148, 'dddd', 'dddd', 7),
	(149, 'eeee', 'eeee', 8),
	(150, 'ffff', 'ffff', 9),
	(152, 'gggg', 'gggg1', 11),
	(153, 'hhhh', 'hhhh', 12),
	(164, 'adil', 'adil', 23),
	(165, 'yasar', 'yasar', 24),
	(166, 'furkan', 'furkan', 25),
	(167, 'omer', 'omer1', 26),
	(215, 'yeguer', 'fewfw', 74),
	(216, 'plplpl', 'plplpl', 75),
	(217, 'qeqeqe', 'qeqeqe', 76),
	(218, 'rvrvrv', 'rvrvrv', 77),
	(219, 'azazaz', 'azazaz', 78),
	(220, 'trtrtf', 'trtrtf', 79),
	(221, 'vxzxcx', 'vxzxcx', 80),
	(222, 'cxncvd', 'cxncvd', 81),
	(223, 'popopo123', 'popopo', 82),
	(224, 'azyt', 'azyt', 83),
	(225, 'bnmk', 'bnmk', 84),
	(233, 'bgbgbg', 'bgbgbg', 92),
	(234, 'gdgdgd', 'gdgdgd', 93),
	(235, 'uytututu', 'shegfr46', 94),
	(236, 'gdeys', 'uewkgfue', 95),
	(239, 'giris', 'giris2019', 98),
	(240, 'sonon', 'sonon', 99);
/*!40000 ALTER TABLE `uyelik_yapan` ENABLE KEYS */;

-- tablo yapısı dökülüyor muzik.uyelik_yapan_bilgileri
CREATE TABLE IF NOT EXISTS `uyelik_yapan_bilgileri` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uye_id` int(11) unsigned NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `tel_no` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- muzik.uyelik_yapan_bilgileri: ~28 rows (yaklaşık) tablosu için veriler indiriliyor
/*!40000 ALTER TABLE `uyelik_yapan_bilgileri` DISABLE KEYS */;
INSERT INTO `uyelik_yapan_bilgileri` (`id`, `uye_id`, `name`, `last_name`, `tel_no`, `last_update`) VALUES
	(1, 1, 'said', 'yunus', '05061342145', '2019-05-10 11:25:40'),
	(4, 0, 'aaaa', 'ss', '434421', '2019-05-08 19:49:29'),
	(5, 0, 'bbbb', '', '5454', '2019-05-08 19:49:52'),
	(7, 0, 'dddd', '', '5435', '2019-05-08 19:50:49'),
	(8, 0, 'eeee', '', '656', '2019-05-08 19:51:08'),
	(9, 0, 'ffff', '', '32423', '2019-05-08 19:51:36'),
	(11, 0, 'gggg', '', '4343', '2019-05-08 19:55:43'),
	(12, 0, 'hhhh', '', '24343', '2019-05-08 19:56:26'),
	(22, 0, 'said', '', '84763', '2019-05-10 01:05:58'),
	(23, 1, 'adil', 'adil', '875388', '2019-05-10 11:28:56'),
	(24, 1, 'yasar', '', '3734', '2019-05-10 11:29:18'),
	(25, 1, 'furkan', '', '8569', '2019-05-10 11:29:37'),
	(26, 1, 'omer', '', '8365', '2019-05-10 11:29:58'),
	(74, 0, 'yeguer', '', '5749867', '2019-05-18 16:29:58'),
	(75, 0, 'jsdhc', '', '3457843', '2019-05-20 00:24:56'),
	(76, 0, 'qeqeqe', '', '48594', '2019-05-20 00:31:59'),
	(77, 0, 'rvrvrv', '', '8734956', '2019-05-20 00:39:34'),
	(78, 0, 'azazaz', '', '34759', '2019-05-20 00:41:21'),
	(79, 0, 'trtrtf', '', '5648', '2019-05-20 00:53:57'),
	(80, 0, 'vxzxcxv', '', '34678', '2019-05-20 01:08:12'),
	(81, 0, 'cxncvd', '', '87548', '2019-05-20 01:23:07'),
	(82, 0, 'popopo', '', '98954', '2019-05-20 03:26:00'),
	(83, 0, 'azyt', '', '5896', '2019-05-21 18:00:14'),
	(84, 0, 'bnmk', '', '409345', '2019-05-21 18:01:23'),
	(92, 0, 'bgbgbg', 'fdre', '9847089', '2019-05-24 15:48:12'),
	(93, 0, 'gdgdgd', '', '8945', '2019-05-24 20:34:51'),
	(94, 0, 'hsdgyus', '', '574895748', '2019-05-25 00:34:02'),
	(95, 0, 'cudsygfus', '', '875938', '2019-05-25 00:35:55'),
	(98, 0, 'giris', '', '904754809', '2019-05-27 13:03:23'),
	(99, 0, 'sonon', '', '6487353', '2019-05-27 16:41:47');
/*!40000 ALTER TABLE `uyelik_yapan_bilgileri` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
