SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

-- Base de datos: `tienda`

CREATE TABLE IF NOT EXISTS `articulos` (
  `codigo` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `cod_marca` int(11) NOT NULL,
  `modelo` varchar(50) NOT NULL,
  `precio` decimal(8,2) NOT NULL,
  `descuento` int(2) DEFAULT NULL COMMENT 'En %',
  `descripcion` text NOT NULL,
  `cod_familia` char(5) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo_familia` (`cod_familia`),
  KEY `cod_marca` (`cod_marca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=7;

INSERT INTO `articulos` (`codigo`, `cod_marca`, `modelo`, `precio`, `descuento`, `descripcion`, `cod_familia`) VALUES
(0001, 3, 'Presario CQ57-302SS', '296.00', 5, 'Procesador : AMD Fusion Dual-Core E300\r\nRAM : 4 GB DDR3\r\nTarjeta gráfica : AMD Radeon HD 6310M\r\nTamaño de monitor : 15,6\r\nDisco duro : 320 Gb\r\nSistema Operativo : Windows® 7 Premium Family Edition 64 bit', 'PORT'),
(0002, 2, 'Envy 14-1120es', '605.00', 10, 'El notebook Envy 14-1120es de HP dispone de un potente procesador Intel Core i5-460M y disfruta del sistema de explotación Microsoft Windows® 7 Edition Familiale Premium.', 'PORT'),
(0003, 2, 'Pavilion G6-1212ss', '390.00', NULL, 'Procesador : Intel Core i5-2430M\r\nRAM : 4 GB DDR3\r\nTarjeta gráfica : Intel HD\r\nTamaño de monitor : 15,6\r\nDisco duro : 500 Gb\r\nSistema Operativo : Windows® 7 Premium Family Edition 64 bit', 'PORT'),
(0004, 1, 'iPad 2', '360.00', 2, 'WiFi 16 Gb blanco\r\niOS 5, Pantalla LED Multi-Touch 9,7", Cámara delantera SD & trasera ', 'TAB'),
(0005, 1, 'Ipad', '300.00', NULL, 'WiFi 16 GB negro\r\niOS 5, Pantalla Retina alta resolución 9,7", Cámara de fotos iSight 5 megapíxeles', 'TAB'),
(0006, 4, 'Galaxy Tab 2', '350.00', NULL, 'WiFi 16 GB \r\nAndroid 4.0 (Ice Cream Sandwich), 10.1" LCD Touchscreen', 'TAB');

CREATE TABLE IF NOT EXISTS `familias` (
  `codigo` char(5) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `familias` (`codigo`, `nombre`, `descripcion`) VALUES
('PORT', 'PORTÁTILES', 'Una computadora portátil es un ordenador personal móvil o transportable, que pesa normalmente entre 1 y 3 kg. Los ordenadores portátiles son capaces de realizar la mayor parte de las tareas que realiz'),
('TAB', 'TABLETS', 'Una tablet (del inglés: tablet o tablet computer) es un tipo de computadora portátil, de mayor tamaño que un smartphone o una PDA, integrado en una pantalla táctil (sencilla o multitáctil) con la que ');

CREATE TABLE IF NOT EXISTS `marcas` (
  `codigo` int(4) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `nombre` (`nombre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=5;

INSERT INTO `marcas` (`codigo`, `nombre`) VALUES
(1, 'APPLE'),
(3, 'COMPAQ'),
(2, 'HP'),
(4, 'SAMSUNG');

ALTER TABLE `articulos`
  ADD CONSTRAINT `articulos_ibfk_2` FOREIGN KEY (`cod_familia`) REFERENCES `familias` (`codigo`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `articulos_ibfk_1` FOREIGN KEY (`cod_marca`) REFERENCES `marcas` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
