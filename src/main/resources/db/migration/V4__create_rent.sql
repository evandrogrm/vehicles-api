CREATE TABLE `rents` (
  `id` varchar(36) NOT NULL,
  `vehicle_id` varchar(36) NOT NULL,
  `user_id` varchar(36) NOT NULL,
  `rent_start_at` datetime NOT NULL,
  `rent_end_at` datetime NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_rent_vehicle_id` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`),
  CONSTRAINT `fk_rent_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);