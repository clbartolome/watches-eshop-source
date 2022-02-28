INSERT INTO brand (id, name) VALUES 
  (1, 'Rolex'),
  (2, 'Patek Philippe'),
  (3, 'Omega'),
  (4, 'Vacheron Constantin'),
  (5, 'A.Lange and Sohne') ON CONFLICT DO NOTHING;

INSERT INTO watch (id, model, brand_id, price) VALUES 
  (1, 'Submariner', 1, 14300),
  (2, 'Oyster Perpetual', 1, 5900),
  (3, 'Celini', 1, 17000),
  (4, 'Nautilus', 2, 26970),
  (5, 'Aquanaut', 2, 20230),
  (6, 'Seamaster', 3, 5700),
  (7, 'Speedmaster', 3, 6700),
  (8, 'Overseas', 4, 32300),
  (9, 'Saxonia', 5, 17600) ON CONFLICT DO NOTHING;