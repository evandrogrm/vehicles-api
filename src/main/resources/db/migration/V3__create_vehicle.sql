CREATE TABLE IF NOT EXISTS `vehicles` (
  `id` varchar(36) NOT NULL,
  `model` varchar(255) NOT NULL,
  `mark_id` varchar(36) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_vehicle_mark_id` FOREIGN KEY (`mark_id`) REFERENCES `marks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;