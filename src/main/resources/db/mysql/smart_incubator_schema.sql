-- ---------------------------------------------------------------------------------
--  Database
--
--  Author:
--    Tsanda Andrej
--
--  Version for MySQL.
--
-- ---------------------------------------------------------------------------------

CREATE SCHEMA smart_incubator DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE smart_incubator ;


-- ---------------------------------------------------------------------------------
-- Table for storing modes.
-- ---------------------------------------------------------------------------------
CREATE TABLE modes
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mode_name VARCHAR(255) NOT NULL,
  activation_status TINYINT(1) NOT NULL,
  up_lim_temperature DECIMAL(5,2) NOT NULL,
  low_lim_temperature DECIMAL(5,2) NOT NULL,
  up_lim_humidity DECIMAL(5,2) NOT NULL,
  low_lim_humidity DECIMAL(5,2) NOT NULL,
  engine_speed INT NOT NULL,
  work_time INT NOT NULL,
  create_time TIMESTAMP NULL
)
ENGINE = InnoDB;


-- ---------------------------------------------------------------------------------
-- Table for storing readings from sensors (eg, temperature, humidity, etc.).
-- ---------------------------------------------------------------------------------
CREATE TABLE statistics
(
  id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  temperature_sensor_1 DECIMAL(5,2) NOT NULL,
  temperature_sensor_2 DECIMAL(5,2) NOT NULL,
  temperature_sensor_3 DECIMAL(5,2) NOT NULL,
  humidity_sensor_1 DECIMAL(5,2) NOT NULL,
  create_time TIMESTAMP NULL
)
ENGINE = InnoDB;