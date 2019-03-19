public class Query {
	public static void RegistrerApparat(String navn, String beskrivelse) {
		String query = "insert into apparat (ApparatID, Navn, Beskrivelse) values("
				+ "NULL, " + navn + ", " + ", " + beskrivelse + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerFriovelse(String navn, String Beskrivelse) {
		String query = "insert into ovelse (OvelseID, Navn) values("
				+ "NULL, " + navn + ", " + Beskrivelse + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerApparatovelse(String navn, int kilo, int sett, int apparatID) {
		String query = "insert into ovelse (OvelseID, Navn, AntallKilo, AntallSett, ApparatID)"
				+ " values(" + "NULL, " + navn + ", " + kilo + ", " + sett + ", "
				+ apparatID + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerOkt(String Dato, String Tidspunkt, String Varighet,
			int Prestasjon, int Form) {
		String query = "insert into okt (OktID, Dato, Tidspunkt, Varighet, Prestasjon, Form) "
				+ "values(" + "NULL, " + Dato + ", " + Tidspunkt + ", " + Varighet + ", " +
				Prestasjon + ", " + Form + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerNotat(String beskrivelse, int oktID) {
		String query = "insert into notat (NotatID, Beskrivelse, OktID) values(" + "NULL, " 
				+ beskrivelse + ", " + oktID + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerGruppe(String navn) {
		String query = "insert into ovelsesgruppe(GruppeID, Navn) values(" 
				+ "NULL, " + navn + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerOvelseIOkt(int ovelseID, int oktID) {
		String query = "insert into ovelseiokt values(" + ovelseID + ", " + oktID + ");";
		//TODO: Pass to DB
	}
	public static void RegistrerInngarI(int ovelseID, int gruppeID) {
		String query = "insert into inngari values(" + ovelseID + ", " + gruppeID + ");";
		//TODO: Pass to DB
	}
}
