/*
 * Drop all Tables. Order based on Relation
 */

DROP TABLE IF EXISTS demo.t_txfr_profile;

DROP TABLE IF EXISTS demo.t_acct_txn;
DROP TABLE IF EXISTS demo.t_acct;

DROP TABLE IF EXISTS demo.t_acct_txn_subtype;
DROP TABLE IF EXISTS demo.t_acct_txn_type;
DROP TABLE IF EXISTS demo.t_cust;
DROP TABLE IF EXISTS demo.t_txfr_type;
DROP TABLE IF EXISTS demo.t_txfr_schd_frq;
DROP TABLE IF EXISTS demo.t_acct_type;
CREATE DATABASE demo;
CREATE TABLE demo.t_acct_txn_type (
  acct_txn_type_id INT AUTO_INCREMENT,
  acct_txn_type_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY( acct_txn_type_id )
);
ALTER TABLE demo.t_acct_txn_type AUTO_INCREMENT=100;

CREATE TABLE demo.t_acct_txn_subtype (
  acct_txn_subtype_id INT AUTO_INCREMENT,
  acct_txn_type_id INT NOT NULL,
  acct_txn_subtype_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY( acct_txn_subtype_id ),
  FOREIGN KEY ( acct_txn_type_id ) REFERENCES demo.t_acct_txn_type( acct_txn_type_id )
);
ALTER TABLE demo.t_acct_txn_subtype AUTO_INCREMENT=200;

CREATE TABLE demo.t_cust (
  cust_id INT AUTO_INCREMENT,
  cust_name VARCHAR(256) NOT NULL,
  PRIMARY KEY( cust_id )
);
ALTER TABLE demo.t_cust AUTO_INCREMENT=300;

CREATE TABLE demo.t_txfr_type (
  txfr_type_id INT AUTO_INCREMENT,
  txfr_type_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY( txfr_type_id )
);
ALTER TABLE demo.t_txfr_type AUTO_INCREMENT=400;

CREATE TABLE demo.t_txfr_schd_frq (
  txfr_schd_frq_id INT AUTO_INCREMENT,
  txfr_schd_frq_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY( txfr_schd_frq_id )
);
ALTER TABLE demo.t_txfr_schd_frq AUTO_INCREMENT=500;

CREATE TABLE demo.t_acct_type (
  acct_type_id INT AUTO_INCREMENT,
  acct_type_desc VARCHAR(256) NOT NULL,
  PRIMARY KEY( acct_type_id )
);
ALTER TABLE demo.t_acct_type AUTO_INCREMENT=600;


CREATE TABLE demo.t_acct (
  acct_id INT AUTO_INCREMENT,
  acct_no VARCHAR(256) NOT NULL,
  cust_id INT NOT NULL,
  acct_type_id INT NOT NULL,
  acct_allw_schd_txn CHAR(1) DEFAULT 'N',  
  PRIMARY KEY( acct_id ),
  FOREIGN KEY ( cust_id ) REFERENCES demo.t_cust( cust_id ),
  FOREIGN KEY ( acct_type_id ) REFERENCES demo.t_acct_type( acct_type_id )
);
ALTER TABLE demo.t_acct AUTO_INCREMENT=700;

CREATE TABLE demo.t_acct_txn (
  acct_txn_id INT AUTO_INCREMENT,
  acct_txn_subtype_id INT NOT NULL,
  acct_id INT NOT NULL,
  acct_txn_amt DECIMAL(16,2) DEFAULT 0.00,
  acct_txn_date DATETIME DEFAULT current_timestamp,
  PRIMARY KEY( acct_txn_id ),
  FOREIGN KEY ( acct_txn_subtype_id ) REFERENCES demo.t_acct_txn_subtype( acct_txn_subtype_id ),
  FOREIGN KEY ( acct_id ) REFERENCES demo.t_acct( acct_id ) 
);
ALTER TABLE demo.t_acct_txn AUTO_INCREMENT=800;


CREATE TABLE demo.t_txfr_profile (
  txfr_profile_id INT AUTO_INCREMENT,
  txfr_amt DOUBLE(16,2) DEFAULT 0.00,
  txfr_date DATETIME DEFAULT current_timestamp,
  txfr_memo VARCHAR(256),
  txfr_from_acct_id INT NOT NULL,
  txfr_to_acct_id INT NOT NULL,
  txfr_type_id INT NOT NULL,
  txfr_schd_frq_id INT NOT NULL,
  PRIMARY KEY( txfr_profile_id ),
  FOREIGN KEY ( txfr_from_acct_id ) REFERENCES demo.t_acct( acct_id ),
  FOREIGN KEY ( txfr_to_acct_id ) REFERENCES demo.t_acct( acct_id ),
  FOREIGN KEY ( txfr_type_id ) REFERENCES demo.t_txfr_type( txfr_type_id ),
  FOREIGN KEY ( txfr_schd_frq_id ) REFERENCES demo.t_txfr_schd_frq( txfr_schd_frq_id )
);
ALTER TABLE demo.t_txfr_profile AUTO_INCREMENT=900;