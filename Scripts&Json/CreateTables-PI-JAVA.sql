CREATE TABLE EXPENSE (
    ID INT AUTO_INCREMENT PRIMARY KEY,
    AMOUNT DOUBLE,
    DESCRIPTION VARCHAR(20),
    CATEGORY_ID INT,
    DATE DATE,
    FOREIGN KEY (CATEGORY_ID) REFERENCES EXPENSE_CAT(CATEGORY_ID)
);

CREATE TABLE EXPENSE_CAT (
    CATEGORY_ID INT AUTO_INCREMENT PRIMARY KEY,
    CATEGORY_NAME VARCHAR(50)
);