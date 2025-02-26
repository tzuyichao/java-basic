CREATE TABLE IF NOT EXISTS employee (
    id VARCHAR(255) PRIMARY KEY,
    employee_name VARCHAR(100) NOT NULL,
    employee_email VARCHAR(100) NOT NULL,
    employee_phone BIGINT,
    employee_gender VARCHAR(10),
    employee_salary VARCHAR(50),
    employee_role VARCHAR(50)
);

INSERT INTO employee (id, employee_name, employee_email, employee_phone, employee_gender, employee_salary, employee_role)
VALUES ('1', 'John Doe', 'john.doe@example.com', 1234567890, 'Male', '50000', 'Engineer');

INSERT INTO employee (id, employee_name, employee_email, employee_phone, employee_gender, employee_salary, employee_role)
VALUES ('2', 'Jane Smith', 'jane.smith@example.com', 9876543210, 'Female', '60000', 'Manager');