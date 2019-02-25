-----------------------------------------------
-------------personal_identification-----------
-----------------------------------------------

INSERT INTO personal_identification (id, document_number, document_type) VALUES (1, '4475896X_U', 0);

-----------------------------------------------
------------------personal_data----------------
-----------------------------------------------

INSERT INTO personal_data (id, name, sub_name, personal_identification_id) VALUES (1, 'Carlos_U', 'Hernandez_U', 1);

-----------------------------------------------
---------------------Invoice-------------------
-----------------------------------------------

INSERT INTO invoice (id, creation_date, total, personal_data_id) VALUES (1, '2019-02-20 18:50:24.111000', 14, 1);

-----------------------------------------------
--------------------line_item------------------
-----------------------------------------------

INSERT INTO line_item (id, code, number_of_item, price_item, total, invoice_id) VALUES (1, '123456_U', 2, 7, 14, 1);

