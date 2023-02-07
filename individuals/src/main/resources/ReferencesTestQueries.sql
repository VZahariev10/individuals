insert into agency (agencyID, name) values (-4, 'AgencyToTest');
insert into individuals (individualId, name, type, agencyID) values (-4, 'Misho', 'legal', -4);
insert into individuals (individualId, name, type, agencyID) values (-95, 'Misho', 'legal', -4);
insert into references (individualId, type, externalRef) values (-4, 'reference', 'externalRef');
