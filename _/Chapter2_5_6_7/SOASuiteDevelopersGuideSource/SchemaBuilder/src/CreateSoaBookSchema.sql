DROP TABLE PAYROLLITEM;

CREATE TABLE PAYROLLITEM
(
EMPLOYEEID VARCHAR2(8) NOT NULL,
PAYMENTDATE DATE NOT NULL,
PAYMENTAMOUNT VARCHAR2(8),
BATCHID VARCHAR2(8) NOT NULL
)
;

DROP TABLE BATCH;

CREATE TABLE BATCH
(
ID VARCHAR2(8) NOT NULL,
BATCHDATE DATE
)
;

DROP TABLE EMPLOYEE;

CREATE TABLE EMPLOYEE
(
ID VARCHAR2(8) NOT NULL,
FIRSTNAME VARCHAR2(20),
LASTNAME VARCHAR2(20)
)
;

ALTER TABLE PAYROLLITEM
ADD CONSTRAINT PAYROLLITEM_PK PRIMARY KEY
(
BATCHID,
EMPLOYEEID
)
 DISABLE
;

ALTER TABLE BATCH
ADD CONSTRAINT BATCH_PK PRIMARY KEY
(
ID
)
 DISABLE
;

ALTER TABLE EMPLOYEE
ADD CONSTRAINT EMPLOYEE_PK PRIMARY KEY
(
ID
)
 DISABLE
;

ALTER TABLE PAYROLLITEM
ADD CONSTRAINT PAYROLLITEM_BATCH_FK1 FOREIGN KEY
(
BATCHID
)
REFERENCES BATCH
(
ID
) DISABLE
;

ALTER TABLE PAYROLLITEM
ADD CONSTRAINT PAYROLLITEM_EMPLOYEE_FK1 FOREIGN KEY
(
EMPLOYEEID
)
REFERENCES EMPLOYEE
(
ID
) DISABLE
;
