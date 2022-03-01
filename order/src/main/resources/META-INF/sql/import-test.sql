-- Import Script for testing and local dev

CREATE TABLE IF NOT EXISTS  watch_order ( 
  id serial PRIMARY KEY, 
  detail VARCHAR (255), 
  shipping_code VARCHAR ( 255 ), 
  shipping_status VARCHAR ( 50 ));

INSERT INTO watch_order(id, detail, shipping_code, shipping_status ) VALUES (1, 'Carlos Lopez - Rolex Oyster Perpetual (10000 €) - 10/05/2022', null, 'PENDING CONFIRMATION');
INSERT INTO watch_order(id, detail, shipping_code, shipping_status ) VALUES (2, 'Carlos Lopez - Rolex Submariner (15000 €) - 11/05/2022', 'ES3301103', 'DELIVERED');

ALTER SEQUENCE IF EXISTS hibernate_sequence RESTART WITH 3;