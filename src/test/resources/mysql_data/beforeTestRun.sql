CREATE TABLE `users` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NULL,
  PRIMARY KEY (`ID`)
);
CREATE TABLE `marks` (
  `id` varchar(36) NOT NULL,
  `name` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL,
  `updated_at` datetime NULL,
  PRIMARY KEY (`id`)
);
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

INSERT INTO users (id, name, email, created_at, updated_at)
VALUES('0cee6037-1d9f-435a-9e6b-ff0e90b90b15', 'user', 'user@ciandt.com', '2021-07-26 06:32:49', '2021-07-26 06:32:49');

INSERT INTO users (id, name, email, created_at, updated_at)
VALUES('0cee6037-1d9f-435a-9e6b-ff0e90b90b16', 'user2', 'user2@ciandt.com', '2021-07-26 06:32:49', '2021-07-26 06:32:49');

INSERT INTO users (id, name, email, created_at, updated_at)
VALUES('0cee6037-1d9f-435a-9e6b-ff0e90b90b17', 'user3', 'user3@ciandt.com', '2021-07-26 06:32:49', '2021-07-26 06:32:49');


INSERT INTO marks (id, name, created_at, updated_at)
VALUES('e8f790b1-07b1-4bf3-b2d2-d21036c94130', 'Fiat', '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('ead114e1-31ff-4656-9a59-43bc70212460', 'e8f790b1-07b1-4bf3-b2d2-d21036c94130', 'Uno', 2013, 'Branco', 100000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('ead114e1-31ff-4656-9a59-43bc70212461', 'e8f790b1-07b1-4bf3-b2d2-d21036c94130', 'Palio', 2015, 'Azul', 80000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('ead114e1-31ff-4656-9a59-43bc70212462', 'e8f790b1-07b1-4bf3-b2d2-d21036c94130', 'Siena', 2016, 'Prata', 60000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO marks (id, name, created_at, updated_at)
VALUES('e8f790b1-07b1-4bf3-b2d2-d21036c94131', 'Volkswagen', '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f381', 'e8f790b1-07b1-4bf3-b2d2-d21036c94131', 'Gol', 2010, 'Cinza', 80000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f391', 'e8f790b1-07b1-4bf3-b2d2-d21036c94131', 'Fox', 2018, 'Preto', 60000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f394', 'e8f790b1-07b1-4bf3-b2d2-d21036c94131', 'Saveiro', 2014, 'Branco', 90500, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO marks (id, name, created_at, updated_at)
VALUES('e8f790b1-07b1-4bf3-b2d2-d21036c94132', 'Toyota', '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('9181afc5-abc2-4fe8-8715-6a78a7f1364c', 'e8f790b1-07b1-4bf3-b2d2-d21036c94132', 'Corolla', 2018, 'Branco', 30000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('9181afc5-abc2-4fe8-8715-6a78a7f1364d', 'e8f790b1-07b1-4bf3-b2d2-d21036c94132', 'Hilux', 2015, 'Preto', 60000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('9181afc5-abc2-4fe8-8715-6a78a7f1364e', 'e8f790b1-07b1-4bf3-b2d2-d21036c94132', 'Etios', 2016, 'Prata', 75000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO marks (id, name, created_at, updated_at)
VALUES('e8f790b1-07b1-4bf3-b2d2-d21036c94133', 'Renault', '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('87ac1bef-1f77-4a51-b398-2d79b6e6e35f', 'e8f790b1-07b1-4bf3-b2d2-d21036c94133', 'Clio', 2016, 'Branco', 85000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('87ac1bef-1f77-4a51-b398-2d79b6e6e35g', 'e8f790b1-07b1-4bf3-b2d2-d21036c94133', 'Duster', 2019, 'Prata', 10000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO marks (id, name, created_at, updated_at)
VALUES('e8f790b1-07b1-4bf3-b2d2-d21036c94134', 'Hyundai', '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f382', 'e8f790b1-07b1-4bf3-b2d2-d21036c94134', 'HB20', 2020, 'Branco', 13000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f102', 'e8f790b1-07b1-4bf3-b2d2-d21036c94134', 'Tucson', 2012, 'Preto', 90000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO marks (id, name, created_at, updated_at)
VALUES('e8f790b1-07b1-4bf3-b2d2-d21036c94135', 'Chevrolet', '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f375', 'e8f790b1-07b1-4bf3-b2d2-d21036c94135', 'Onix', 2015, 'Cinza', 80000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');

INSERT INTO vehicles (id, mark_id, model, fabrication_year, color, mileage, created_at, updated_at)
VALUES('2de3c69e-dc5d-4404-9c37-7e2dbe20f376', 'e8f790b1-07b1-4bf3-b2d2-d21036c94135', 'Vectra', 2014, 'Preto', 70000, '2021-07-25 22:46:43', '2021-07-25 22:46:43');


INSERT INTO rents (id, vehicle_id, user_id, rent_start_at, rent_end_at, created_at, updated_at)
VALUES('15d81443-88ae-4f59-a1cd-020bc9edb47a', '2de3c69e-dc5d-4404-9c37-7e2dbe20f381', '0cee6037-1d9f-435a-9e6b-ff0e90b90b16', '2021-08-02 00:00:00', '2021-08-03 00:00:00', '2021-07-26 20:36:20', '2021-07-26 20:36:20');

INSERT INTO rents (id, vehicle_id, user_id, rent_start_at, rent_end_at, created_at, updated_at)
VALUES('15d81443-88ae-4f59-a1cd-020bc9edb47b', '2de3c69e-dc5d-4404-9c37-7e2dbe20f382', '0cee6037-1d9f-435a-9e6b-ff0e90b90b15', '2021-08-04 00:00:00', '2021-08-05 00:00:00', '2021-07-26 20:36:20', '2021-07-26 20:36:20');
