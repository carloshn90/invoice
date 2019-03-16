-----------------------------------------------
-------------CUSTOMER_IDENTIFICATION-----------
-----------------------------------------------

INSERT INTO CUSTOMER_IDENTIFICATION (ID, DOCUMENT_NUMBER, DOCUMENT_TYPE) VALUES (1, '4475896X_U', 0);

-----------------------------------------------
--------------------CUSTOMER-------------------
-----------------------------------------------

INSERT INTO CUSTOMER (ID, name, SUB_NAME, CUSTOMER_IDENTIFICATION_ID) VALUES (1, 'Carlos_U', 'Hernandez_U', 1);

-----------------------------------------------
---------------------INVOICE-------------------
-----------------------------------------------

INSERT INTO INVOICE (ID, CREATION_DATE, TOTAL, CUSTOMER_ID) VALUES (1, '2019-02-20 18:50:24.111000', 14, 1);

-----------------------------------------------
--------------------LINE_ITEM------------------
-----------------------------------------------

INSERT INTO LINE_ITEM (ID, CODE, NUMBER_OF_ITEM, PRICE_ITEM, TOTAL, INVOICE_ID) VALUES (1, '123456_U', 2, 7, 14, 1);

-----------------------------------------------
---------------------ARTICLE-------------------
-----------------------------------------------

INSERT INTO ARTICLE (ID, CODE, PRICE, UNIT, ACTIVE, NAME, DESCRIPTION) VALUES (1, '1236758ASM', 12.36, 0, true, 'Tomatoes', 'Salad Tomatoes');
