DROP TABLE IF EXISTS ORDERS;
DROP TABLE IF EXISTS ITEMS;

CREATE TABLE ORDERS (
       id INT AUTO_INCREMENT  PRIMARY KEY,
       order_number INT NOT NULL,
       customer_id INT NOT NULL,
       total_amount DOUBLE NOT NULL,
       quantity INT NOT NULL,
       item_id INT NOT NULL
);

CREATE TABLE ITEMS (
    item_id INT PRIMARY KEY,
    item_name VARCHAR(200) NOT NULL,
    item_price DOUBLE NOT NULL
);

INSERT INTO ITEMS (item_id, item_name, item_price) VALUES
(1001, 'Item1', 50.00),
(1002, 'Item2', 67.23),
(1003, 'Item3', 14.33);

