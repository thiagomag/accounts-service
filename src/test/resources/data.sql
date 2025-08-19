-- Usuário 1: João da Silva (Admin)
INSERT INTO users(email, password, name, cpf, subscription_status, subscription_end_date) VALUES('joao.silva@email.com', '$2a$10$kNTFFYm5yhvd7Stp8zUM6e7BEQiPQhGqdcPvkWLCvGD7ajf5HBx/a', 'João da Silva', '11122233344', 'INACTIVE', null);
INSERT INTO address(street, number, complement, neighborhood, city, state, country, postal_code, user_id) VALUES('Rua das Flores', '123', 'Apto 101', 'Centro', 'São Paulo', 'SP', 'Brasil', '01000-000', 1);
INSERT INTO telephones(country_code, area_code, number, type, user_id) VALUES('55', '11', '987654321', 'MOBILE', 1);
INSERT INTO user_roles(user_id, role_id) VALUES(1, 1); -- ROLE_ADMIN

-- Usuário 2: Maria Oliveira (Basic)
INSERT INTO users(email, password, name, cpf, subscription_status, subscription_end_date) VALUES('maria.oliveira@email.com', '$2a$10$.8N4fTbOAB3VqTH1WC7QQ.5ku5.Pe1M5e0gQ0la2Ci7e0AZ8qtnaa', 'Maria Oliveira', '55566677788', 'ACTIVE', '2024-12-31');
INSERT INTO address(street, number, neighborhood, city, state, country, postal_code, user_id) VALUES('Avenida Principal', '456', 'Bairro Novo', 'Rio de Janeiro', 'RJ', 'Brasil', '20000-000', 2);
INSERT INTO telephones(country_code, area_code, number, type, user_id) VALUES('55', '21', '912345678', 'MOBILE', 2);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2); -- ROLE_BASIC

-- Usuário 3: Pedro Santos (Basic)
INSERT INTO users(email, password, name, cpf, subscription_status, subscription_end_date) VALUES('pedro.santos@email.com', '$2a$10$ScwPhiLt2dziAX5AcXcNOeWHfHC4g5dpekOGR.sF5ym7BGQ/kd3TO', 'Pedro Santos', '99988877766', 'ACTIVE', '2025-01-15');
INSERT INTO address(street, number, complement, neighborhood, city, state, country, postal_code, user_id) VALUES('Travessa dos Sonhos', '789', 'Casa B', 'Vila Feliz', 'Belo Horizonte', 'MG', 'Brasil', '30000-000', 3);
INSERT INTO telephones(country_code, area_code, number, type, user_id) VALUES('55', '31', '998761234', 'MOBILE', 3);
INSERT INTO user_roles(user_id, role_id) VALUES(3, 2); -- ROLE_BASIC

-- Usuário 4: Ana Costa (Basic)
INSERT INTO users(email, password, name, cpf, subscription_status, subscription_end_date) VALUES('ana.costa@email.com', '$2a$10$kNTFFYm5yhvd7Stp8zUM6e7BEQiPQhGqdcPvkWLCvGD7ajf5HBx/a', 'Ana Costa', '22233344455', 'INACTIVE', null);
INSERT INTO address(street, number, neighborhood, city, state, country, postal_code, user_id) VALUES('Rua do Sol', '321', 'Apto 202', 'Salvador', 'BA', 'Brasil', '40000-000', 4);
INSERT INTO telephones(country_code, area_code, number, type, user_id) VALUES('55', '71', '987654321', 'MOBILE', 4);
INSERT INTO user_roles(user_id, role_id) VALUES(4, 2); -- ROLE_BASIC