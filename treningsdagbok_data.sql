USE TRENINGSBOK;
-- ID, Dato mm/dd/yyyy, Tidspunkt hh:mi, Varighet hh:mi, Prestasjon, Form 
INSERT INTO OKT (Dato, Tidspunkt, Varighet, Prestasjon, Form) VALUES ('2019-03-14', '16:00', '01:45', 8, 5),('2019-03-15', '14:00', '01:30', 7, 4);

-- NotatID, OktID, Treningsformal
INSERT INTO NOTAT (OktID, Treningsformal) VALUES (1, 'Skal bli stor!');

-- OvelseID, Navn
INSERT INTO OVELSE VALUES 	(1, 'Benk'), (2, 'Pullups'), (3, 'Kneboy'),
 							(5, 'Jogging'), (6, 'Utfall');
-- OvelseID, Beskrivelse
INSERT INTO FRIOVELSE VALUES (6, 'Make that booty pop'), (2, 'Flex them lats'), (5, 'Jogging er esj');

-- ApparatID, Navn, Beskrivelse
INSERT INTO APPARAT (Navn, Beskrivelse) VALUES 	('Benk', 'fastmontert med vekstangstang'), ('Vektstang', 'med avtagbare vekter og stativ');

-- OvelseID, AntallKilo, AntallSett, ApparatID
INSERT INTO APPARATOVELSE VALUES (1, 50, 3, 1), (2, 70, 4, 1);

-- GruppeID, Navn
INSERT INTO OVELSESGRUPPE (Navn) VALUES ('Ben'), ('Styrketrening'), ('Cardio');

-- OvelseID, OktID
INSERT INTO OVELSEIOKT VALUES (1, 1), (2,1), (5,1), (3,2), (5,2), (6,2);

-- OvelseID, GruppeID
INSERT INTO INNGARI VALUES (3, 1), (6, 1), (1,2), (2,2), (3,2), (6,2), (5,3);