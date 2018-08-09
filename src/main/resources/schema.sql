create table ADVERTISER
(
   ADVERTISER_ID  number(6) not null PRIMARY KEY,
   NAME varchar(255) not null,
   CONTACT_NAME varchar(255) not null,
   STATUS  varchar(32) not null,
   CREDIT_LIMIT number(10,2),
   CREATED_DATE date default sysdate,
   LAST_MODIFIED_DATE date
);

CREATE SEQUENCE ADVERTISER_SEQ
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

