
CREATE DATABASE db_s2_ETU003367;
USE db_s2_ETU003367;

CREATE TABLE ligne_credit (
    id INT AUTO_INCREMENT PRIMARY KEY,
    libelle VARCHAR(255) NOT NULL,
    montant DOUBLE NOT NULL
);

CREATE TABLE depense (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ligne_credit_id INT NOT NULL,
    date DATE NOT NULL,
    montant DOUBLE NOT NULL,
    FOREIGN KEY (ligne_credit_id) REFERENCES ligne_credit(id)
);


INSERT INTO ligne_credit (libelle, montant) VALUES 
('Budget Marketing', 5000000),
('Budget Développement', 8000000),
('Budget Formation', 2000000),
('Budget Matériel', 3000000),
('Budget Voyage', 1500000);


INSERT INTO depense (ligne_credit_id, date, montant) VALUES
(1, '2023-01-15', 1200000),
(1, '2023-02-20', 800000),
(2, '2023-01-10', 2500000),
(2, '2023-03-05', 1800000);


SELECT lc.id, lc.libelle, lc.montant, COALESCE(SUM(d.montant), 0) AS sommeDepense
FROM ligne_credit lc
LEFT JOIN depense d ON lc.id = d.ligne_credit_id
GROUP BY lc.id;

