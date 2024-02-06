-- Productos en product_master
INSERT INTO product_master (id, name, price, category, enable, date_created)
VALUES
    ('f47f28f5-2547-4a4c-bb84-7459d21055cf', 'Smartphone Samsung Galaxy S22', 799.99, 'Electrónicos', true, '2024-01-21'),
    ('f6e3f577-8a33-4e90-82f8-1b590c409c7e', 'Laptop HP Pavilion', 899.99, 'Electrónicos', true, '2024-02-15'),
    ('e0b66c5c-390b-4e78-89a9-9a7e5f12f844', 'Sofá de Cuero Moderno', 499.99, 'Muebles', true, '2024-03-10'),
    ('b7e1774d-e2e2-4d22-9f6c-920f8a72c87c', 'Televisor LED 55 Pulgadas', 1199.99, 'Electrónicos', true, '2024-04-05'),
    ('db19c9d9-3f9c-4dfe-9e9a-9baf0e070ba0', 'Camisa Polo Ralph Lauren', 69.99, 'Ropa', true, '2024-05-20'),
    ('a57d687d-78f4-4ff4-9c36-696926bafed3', 'Mesa de Comedor Extendible', 349.99, 'Muebles', true, '2024-06-15'),
    ('4e9e6a0b-91cb-4340-b9c4-75db67a0a4aa', 'Auriculares Inalámbricos Sony', 129.99, 'Electrónicos', true, '2024-07-01'),
    ('cf09ff31-7132-4ac6-8f3d-10c54f64e4a7', 'Vestido Floral Primaveral', 89.99, 'Ropa', true, '2024-08-18'),
    ('f59f847a-49a9-4c7b-9fc1-9b88cbe9d042', 'Juego de Sábanas de Algodón', 49.99, 'Hogar', true, '2024-09-23'),
    ('7c38a55c-ec66-4d38-a662-99157ff8425d', 'Cámara DSLR Canon EOS Rebel', 699.99, 'Electrónicos', true, '2024-10-10');

-- Detalles de productos en product_detail con relación a product_master

-- Detalles de productos en product_detail con relación a product_master
INSERT INTO product_detail (id, description, id_product_master)
VALUES
    ('8c9d1e2a-3b4c-5d6e-7f8a-9b2c3d4e5f6a', 'Smartwatch diseñado para monitorizar la actividad física y la salud', 'f47f28f5-2547-4a4c-bb84-7459d21055cf'),
    ('ab1c2d3e-4f5a-6b7c-8d9e-0f1a2b3c4d5e', 'Potente laptop con procesador Intel Core i7 y pantalla Full HD.', 'f6e3f577-8a33-4e90-82f8-1b590c409c7e'),
    ('cd1e2a3b-4c5d6e7f-8a9b-2c3d4e5f6a7b', 'Sofá elegante y cómodo para tu sala de estar.', 'e0b66c5c-390b-4e78-89a9-9a7e5f12f844'),
    ('ef1a2b3c-4d5e6f7a-8b9c-0d1e2a3b4c5d', 'Disfruta de imágenes nítidas y colores vibrantes con este televisor.', 'b7e1774d-e2e2-4d22-9f6c-920f8a72c87c'),
    ('12ab34cd-56ef-78ab-9c0d-12ab34cd56ef', 'Camisa clásica con el logo icónico de Ralph Lauren.', 'db19c9d9-3f9c-4dfe-9e9a-9baf0e070ba0'),
    ('56ef78ab-9c0d-12ab-34cd-56ef78ab9c0d', 'Mesa espaciosa perfecta para cenas familiares.', 'a57d687d-78f4-4ff4-9c36-696926bafed3'),
    ('78ab9c0d-12ab-34cd-56ef-78ab9c0d12ab', 'Auriculares con cancelación de ruido para una experiencia auditiva inmersiva.', '4e9e6a0b-91cb-4340-b9c4-75db67a0a4aa'),
    ('9c0d12ab-34cd-56ef-78ab-9c0d12ab34cd', 'Vestido elegante y cómodo para cualquier ocasión.', 'cf09ff31-7132-4ac6-8f3d-10c54f64e4a7'),
    ('abab12cd-56ef78ab-9c0d-12ab34cd56ef', 'Juego de sábanas suaves y duraderas para un descanso reparador.', 'f59f847a-49a9-4c7b-9fc1-9b88cbe9d042'),
    ('cd12ab34-56ef-78ab-9c0d-12ab34cd56ef', 'Captura momentos inolvidables con esta cámara de alta calidad.', '7c38a55c-ec66-4d38-a662-99157ff8425d');
