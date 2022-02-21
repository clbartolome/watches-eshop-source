INSERT INTO brand (id, name) VALUES 
  (1, 'Rolex'),
  (2, 'Patek Philippe'),
  (3, 'Omega'),
  (4, 'Vacheron Constantin'),
  (5, 'A.Lange and Sohne') ON CONFLICT DO NOTHING;

INSERT INTO watch (id, model, brand_id) VALUES 
  (1, 'Submariner', 1),
  (2, 'Oyster Perpetual', 1),
  (3, 'Celini', 1),
  (4, 'Nautilus', 2),
  (5, 'Aquanaut', 2),
  (6, 'Seamaster', 3),
  (7, 'Speedmaster', 3),
  (8, 'Overseas', 4),
  (9, 'Saxonia', 5) ON CONFLICT DO NOTHING;