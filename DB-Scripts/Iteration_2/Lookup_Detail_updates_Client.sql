INSERT INTO `belhopat_backoffice`.`LookupDetail`(`code`,`description`,`lookup_id`)
VALUES
('ACTIVE','Active',(SELECT id FROM `belhopat_backoffice`.`Lookup` WHERE lookupKey ='CLSTATS')),
('INACTIVE','Inactive',(SELECT id FROM `belhopat_backoffice`.`Lookup` WHERE lookupKey ='CLSTATS')),
('INACTIVE', 'Inactive', (SELECT id FROM `belhopat_backoffice`.`Lookup` WHERE lookupKey ='ESTATS')),
('BDM','Business Development Manager',(SELECT id FROM `belhopat_backoffice`.`Lookup` WHERE lookupKey ='DESIG'));