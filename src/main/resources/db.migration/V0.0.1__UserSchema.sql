CREATE TABLE `client` (
                          `id` int(11) NOT NULL AUTO_INCREMENT,
                          `first_name` varchar(45) NOT NULL,
                          `last_name` varchar(45) DEFAULT NULL,
                          `address1` varchar(64) DEFAULT NULL,
                          `phone` varchar(20) DEFAULT NULL,
                          `email` varchar(100) DEFAULT NULL,
                          `notes` varchar(500) DEFAULT NULL,
                          `user_id` int(11) NOT NULL,
                          `address2` varchar(64) DEFAULT NULL,
                          PRIMARY KEY (`id`),
                          UNIQUE KEY `id_UNIQUE` (`id`),
                          KEY `user_id_idx` (`user_id`),
                          CONSTRAINT `user_id_client` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `invoice` (
                           `id` int(11) NOT NULL AUTO_INCREMENT,
                           `user_id` int(11) NOT NULL,
                           `date` date DEFAULT NULL,
                           `notes` varchar(500) DEFAULT NULL,
                           `title` varchar(100) NOT NULL,
                           `client_id` int(11) NOT NULL,
                           `tax` decimal(6,3) DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           UNIQUE KEY `id_UNIQUE` (`id`),
                           KEY `user_id_invoice_idx` (`user_id`),
                           KEY `client_id_invoice_idx` (`client_id`),
                           CONSTRAINT `client_id_invoice` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                           CONSTRAINT `user_id_invoice` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `invoice_line` (
                                `id` int(11) NOT NULL AUTO_INCREMENT,
                                `invoice_id` int(11) NOT NULL,
                                `item` varchar(100) NOT NULL,
                                `price` decimal(10,2) NOT NULL,
                                `notes` varchar(200) DEFAULT NULL,
                                `quantity` decimal(10,3) NOT NULL,
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `id_UNIQUE` (`id`),
                                KEY `invoice_id_line_idx` (`invoice_id`),
                                CONSTRAINT `invoice_id_line` FOREIGN KEY (`invoice_id`) REFERENCES `invoice` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `user` (
                        `id` int(11) NOT NULL AUTO_INCREMENT,
                        `username` varchar(45) NOT NULL,
                        `email` varchar(100) NOT NULL,
                        `password` varchar(100) NOT NULL,
                        `first_name` varchar(45) DEFAULT NULL,
                        `last_name` varchar(45) DEFAULT NULL,
                        `phone` varchar(20) DEFAULT NULL,
                        `address1` varchar(64) DEFAULT NULL,
                        `address2` varchar(64) DEFAULT NULL,
                        PRIMARY KEY (`id`),
                        UNIQUE KEY `iduser_UNIQUE` (`id`),
                        UNIQUE KEY `username_UNIQUE` (`username`),
                        UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `user_role` (
                             `id` int(11) NOT NULL AUTO_INCREMENT,
                             `user_id` int(11) NOT NULL,
                             `user_role` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`),
                             UNIQUE KEY `id_UNIQUE` (`id`),
                             KEY `id_idx` (`user_id`),
                             CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
