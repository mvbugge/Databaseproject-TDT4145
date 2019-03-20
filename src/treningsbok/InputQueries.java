package treningsbok;

import treningsbok.DBManager;
import java.util.Properties;

public class InputQueries {
	private static DBManager dbmanager;
	
	public InputQueries(Properties p) {
		dbmanager = new DBManager(p);
		dbmanager.connect();
		};
		
	public static boolean RegistrerApparat(String navn, String beskrivelse) {
		String query = "insert into apparat (ApparatID, Navn, Beskrivelse) values("
				+ "NULL, '" + navn + "', '" + beskrivelse + "');";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerOvelse(String navn) {
		String query = "insert into ovelse (Navn) values('"
				+ navn + "');";
		return dbmanager.sendDB(query);
	}
	
	public static boolean DeleteOvelse(String ovelseID) {
		String query = "delete from ovelse where OvelseID = " + ovelseID + ";";
		return dbmanager.sendDB(query);		
	}
	public static boolean RegistrerFriovelse(String ovelseId, String beskrivelse) {
		String query = "insert into friovelse (OvelseID, Beskrivelse) values("
				+ ovelseId + ", '" + beskrivelse + "');";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerApparatovelse(String ovelseId, String kilo, String sett, String apparatID) {
		String query = "insert into apparatovelse (OvelseID, AntallKilo, AntallSett, ApparatID) values("
				+ ovelseId +", " + kilo + ", " + sett + ", " + apparatID + ");";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerOkt(String dato, String tidspunkt, String varighet,
			String prestasjon, String form) {
		String query = "insert into okt (OktID, Dato, Tidspunkt, Varighet, Prestasjon, Form) "
				+ "values(" + "NULL, '" + dato + "', '" + tidspunkt + "', '" + varighet + "', '" +
				prestasjon + "', '" + form + "');";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerNotat(String oktID, String beskrivelse) {
		String query = "insert into notat (OktID, Treningsformal) values(" 
				+ oktID + ", '" + beskrivelse + "');";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerGruppe(String navn) {
		String query = "insert into ovelsesgruppe(GruppeID, Navn) values(" 
				+ "NULL, '" + navn + "');";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerOvelseIOkt(String ovelseID, String oktID) {
		String query = "insert into ovelseiokt values(" + ovelseID + ", " + oktID + ");";
		return dbmanager.sendDB(query);
	}
	public static boolean RegistrerInngarI(String ovelseID, String gruppeID) {
		String query = "insert into inngari values(" + ovelseID + ", " + gruppeID + ");";
		return dbmanager.sendDB(query);
	}
}
