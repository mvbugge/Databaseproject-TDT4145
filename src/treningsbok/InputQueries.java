package treningsbok;

import treningsbok.DBManager;
import java.util.Properties;

public class InputQueries {
	private static DBManager dbmanager;
	
	public InputQueries(Properties p) {
		dbmanager = new DBManager(p);
		dbmanager.connect();
		};
		
	public static void RegistrerApparat(String navn, String beskrivelse) {
		String query = "insert into apparat (ApparatID, Navn, Beskrivelse) values("
				+ "NULL, '" + navn + "', '" + beskrivelse + "');";
		dbmanager.sendDB(query);
	}
	public static void RegistrerFriovelse(String navn, String beskrivelse) {
		String query = "insert into friovelse (OvelseID, Navn) values("
				+ "NULL, '" + navn + "', '" + beskrivelse + "');";
		dbmanager.sendDB(query);
	}
	public static void RegistrerApparatovelse(String navn, String beskrivelse, String kilo, String sett, String apparatID) {
		String query = "insert into apparatovelse (OvelseID, Navn, Beskrivelse, AntallKilo, AntallSett, ApparatID)"
				+ " values(" + "NULL, '" + navn + "', '" + beskrivelse + "', '" + kilo + "', '" + sett + "', '"
				+ apparatID + "'');";
		dbmanager.sendDB(query);
	}
	public static void RegistrerOkt(String dato, String tidspunkt, String varighet,
			String prestasjon, String form) {
		String query = "insert into okt (OktID, Dato, Tidspunkt, Varighet, Prestasjon, Form) "
				+ "values(" + "NULL, '" + dato + "', '" + tidspunkt + "', '" + varighet + "', '" +
				prestasjon + "', '" + form + "');";
		dbmanager.sendDB(query);
	}
	public static void RegistrerNotat(String oktID, String beskrivelse) {
		String query = "insert into notat (OktID, Treningsformal) values(" 
				+ oktID + ", '" + beskrivelse + "');";
		dbmanager.sendDB(query);
	}
	public static void RegistrerGruppe(String navn) {
		String query = "insert into ovelsesgruppe(GruppeID, Navn) values(" 
				+ "NULL, '" + navn + "');";
		dbmanager.sendDB(query);
	}
	public static void RegistrerOvelseIOkt(String ovelseID, String oktID) {
		String query = "insert into ovelseiokt values(" + ovelseID + ", " + oktID + ");";
		dbmanager.sendDB(query);
	}
	public static void RegistrerInngarI(String ovelseID, String gruppeID) {
		String query = "insert into inngari values(" + ovelseID + ", " + gruppeID + ");";
		dbmanager.sendDB(query);
	}
}
