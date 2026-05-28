CREATE DATABASE IF NOT EXISTS aeroroute_db;
USE aeroroute_db;

CREATE TABLE IF NOT EXISTS airports (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    iata_code VARCHAR(10) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(100),
    country VARCHAR(100),
    latitude DOUBLE NOT NULL,
    longitude DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS search_history (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    source_code VARCHAR(10) NOT NULL,
    destination_code VARCHAR(10) NOT NULL,
    distance_km DOUBLE NOT NULL,
    distance_miles DOUBLE NOT NULL,
    searched_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT IGNORE INTO airports (iata_code, name, city, country, latitude, longitude) VALUES
('FRA', 'Frankfurt Airport', 'Frankfurt', 'Germany', 50.0379, 8.5622),
('DEL', 'Indira Gandhi International Airport', 'Delhi', 'India', 28.5562, 77.1000),
('LHR', 'Heathrow Airport', 'London', 'United Kingdom', 51.4700, -0.4543),
('JFK', 'John F. Kennedy International Airport', 'New York', 'United States', 40.6413, -73.7781),
('DXB', 'Dubai International Airport', 'Dubai', 'United Arab Emirates', 25.2532, 55.3657),
('AMS', 'Amsterdam Schiphol Airport', 'Amsterdam', 'Netherlands', 52.3105, 4.7683),
('CDG', 'Charles de Gaulle Airport', 'Paris', 'France', 49.0097, 2.5479),
('SIN', 'Singapore Changi Airport', 'Singapore', 'Singapore', 1.3644, 103.9915),
('BLR', 'Kempegowda International Airport', 'Bengaluru', 'India', 13.1986, 77.7066),
('MAA', 'Chennai International Airport', 'Chennai', 'India', 12.9941, 80.1709);