CREATE TABLE orders (
    order_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id BIGINT,
    product_name VARCHAR(255),
    quantity INT,
    status VARCHAR(50),
    order_date TIMESTAMP
);