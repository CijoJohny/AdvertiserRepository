create table ADVERTISER
(
   ADVERTISER_ID  number(6) not null,
   NAME varchar(255) not null,
   CONTACT_NAME varchar(255) not null,
   CREDIT_LIMIT number(8,2),
   CREATED_DATE date default sysdate,
   LAST_MODIFIED_DATE date
);

CREATE SEQUENCE advertiser_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

