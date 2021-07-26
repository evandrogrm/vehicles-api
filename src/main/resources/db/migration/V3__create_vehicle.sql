CREATE TABLE `vehicles` (
  `id` varchar(36) NOT NULL,
  `mark_id` varchar(36) NOT NULL,
  `model` varchar(255) NOT NULL,
  `fabrication_year` int NOT NULL,
  `image` mediumblob,
  `color` varchar(100) DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_vehicle_mark_id` FOREIGN KEY (`mark_id`) REFERENCES `marks` (`id`)
);