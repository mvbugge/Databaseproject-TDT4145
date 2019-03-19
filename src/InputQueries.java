package treningsbok;

public class InputQueries {
	public static void RegistrerApparat(String navn, String beskrivelse) {
		String query = "insert into apparat (ApparatID, Navn, Beskrivelse) values("
				+ "NULL, '" + navn + "', '" + beskrivelse + "');";
		//TODO: Pass to DB
	}
	public static void RegistrerFriovelse(String navn, String beskrivelse) {
		String query = "insert into friovelse (OvelseID, Navn) values("
				+ "NULL, '" + navn + "', '" + beskrivelse + "');";
		//TODO: Pass to DB
	}
	public static void RegistrerApparatovelse(String navn, String beskrivelse, String kilo, String sett, String apparatID) {
		String query = "insert into apparatovelse (OvelseID, Navn, Beskrivelse, AntallKilo, AntallSett, ApparatID)"
				+ " values(" + "NULL, '" + navn + "', '" + beskrivelse + "', '" + kilo + "', '" + sett + "', '"
				+ apparatID + "'');";
		//TODO: Pass to DB
	}
	public static void RegistrerOkt(String dato, String tidspunkt, String varighet,
			String prestasjon, String form) {
		String query = "insert into okt (OktID, Dato, Tidspunkt, Varighet, Prestasjon, Form) "
				+ "values(" + "NULL, '" + dato + "', '" + tidspunkt + "', '" + varighet + "', '" +
				prestasjon + "', '" + form + "');";
		//TODO: Pass to DB
	}
	public static void RegistrerNotat(String beskrivelse, String oktID) {
		String query = "insert into notat (NotatID, Beskrivelse, OktID) values(" + "NULL, '" 
				+ beskrivelse + "', '" + oktID + "');";
		//TODO: Pass to DB
	}
	public static void RegistrerGruppe(String navn) {
		String query = "insert into ovelsesgruppe(GruppeID, Navn) values(" 
				+ "NULL, '" + navn + "');";
		//TODO: Pass to DB
	}
	public static void RegistrerOvelseIOkt(String ovelseID, String oktID) {
		String query = "insert into ovelseiokt values('" + ovelseID + "', '" + oktID + "');";
		//TODO: Pass to DB
	}
	public static void RegistrerInngarI(String ovelseID, String gruppeID) {
		String query = "insert into inngari values('" + ovelseID + "', '" + gruppeID + "');";
		//TODO: Pass to DB
	}
}
