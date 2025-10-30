INSERT INTO supplier (name, contact_person, phone) VALUES 
('Textiles del Sur S.A.C.', 'Carlos Mendoza', '987654321'),
('Importaciones Hilos y Botones', 'Maria Fernandez', '999888777');

INSERT INTO material (name, description, stock_quantity, low_stock_threshold, expiration_date, supplier_id) VALUES
('Tela de Algodón Pima', 'Rollo de 100m, color blanco', 50, 10, '2026-12-31', 1),
('Hilo Poliéster Negro', 'Cono de 5000m', 150, 20, NULL, 2),
('Botones de Nácar', 'Caja de 1000 unidades', 8, 10, NULL, 2), 
('Tinte Rojo Carmesí', 'Galón de 5L', 20, 5, '2025-10-01', 1); 

INSERT INTO purchase_order (order_date, status, supplier_id) VALUES
('2025-10-01 10:00:00', 'RECEIVED', 1), 
('2025-10-28 15:30:00', 'PENDING', 2);  

INSERT INTO order_detail (quantity, unit_price, purchase_order_id, material_id) VALUES
(20, 150.00, 1, 1), 
(50, 25.50, 2, 2), 
(5, 80.00, 2, 3);  