create table customer
(
   ID   NUMBER(20,0) IDENTITY PRIMARY KEY,
   NAME VARCHAR2(40 CHAR) NOT NULL
);
create table service
(
   ID   NUMBER(20,0) IDENTITY PRIMARY KEY,
   NAME VARCHAR2(40 CHAR) NOT NULL
);
create table subscribe
(
    ID     NUMBER(20,0) IDENTITY PRIMARY KEY,
    CUS_ID NUMBER(20,0) NOT NULL,
    SER_ID NUMBER(20,0) NOT NULL
);
CREATE INDEX sub_cus ON subscribe (cus_id);
CREATE INDEX sub_ser ON subscribe (ser_id);