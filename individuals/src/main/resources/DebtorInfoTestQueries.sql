insert into agency (agencyID, name) values (-4, 'Agency4');
insert into agency (agencyID, name) values (-5, 'Agency5');
insert into agency (agencyID, name) values (-6, 'Agency6');

insert into individuals (individualId, name, type, agencyID) values (-4, 'Misho', 'legal', -4);
insert into individuals (individualId, name, type, agencyID) values (-5, 'Misho', 'legal', -5);
insert into individuals (individualId, name, type, agencyID) values (-6, 'Misho', 'legal', -6);

insert into references (individualId, type, externalRef) values (-4, 'reference', 'externalRef');
insert into references (individualId, type, externalRef) values (-5, 'reference', 'externalRef');
insert into references (individualId, type, externalRef) values (-6, 'reference', 'externalRef');

insert into debt values (-4, 1234.32, sysdate);
insert into debt values (-5, 1234.32, sysdate);
insert into debt values (-6, 1234.32, sysdate);