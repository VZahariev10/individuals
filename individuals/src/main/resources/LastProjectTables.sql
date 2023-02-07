create table agency(
agencyID int,
name varchar2(20),
constraint agency_pk PRIMARY KEY(agencyID)
);
/
create table individuals(
individualID int,
name VARCHAR2(50),
type VARCHAR2(40),
agencyID int,
constraint individuals_pk PRIMARY KEY(individualID),
constraint individuals_fk FOREIGN KEY(agencyID) references Agency(agencyID)
);
/
create table references(individualID int, 
type VARCHAR2(25),
externalref VARCHAR2(50),
constraint REFERENCES_fk FOREIGN KEY(individualID) references individuals(individualID)
);
/
create table debt(
individualID int,
amount number(8,2) DEFAULT (0) ,
startDate date,
constraint debt_fk FOREIGN KEY(individualID) references individuals(individualID)
);