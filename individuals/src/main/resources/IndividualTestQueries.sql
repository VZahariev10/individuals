insert into agency (agencyID, name) values (-5, 'Agency5');
insert into individuals (individualId, name, type, agencyID) values (-5, 'Misho', 'legal', -5);


insert into individuals (individualId, name, type, agencyID) values (-7, 'Misho', 'legal', -5);
insert into references (individualId, type, externalRef) values (-7, 'reference', 'externalRef');

insert into individuals (individualId, name, type, agencyID) values (-8, 'Misho', 'legal', -5);
insert into debt values (-8, 1234.32, sysdate);