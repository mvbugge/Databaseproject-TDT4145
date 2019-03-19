package treningsbok;

import java.util.List;

public class SelectQueries {
	public static List<String> getOvelser(){
		String query = "select * from ovelse;";
		return(DBManager.requestDB(query));
	}

	public static List<string> getOkt(){
		String query = "select * from okt;";
		return(DBManager.requestDB(query));
	}

	public static List<String> getNotat(){
		String query = "select * from notat;";
		return(DBManager.requestDB(query));
	}

	public static List<String> getApparat(){
		String query = "select * from apparat;";
		return(DBManager.requestDB(query));
	}

	public static String getApparatID(String navn){
		String query = "select ApparatID from apparat where navn='" + navn + "';";
		List<String> ids = DBManager.requestDB(query);
		return(ids.getIndex(0));
	}
	public static String getOvelseID(String navn){
		String query = "select OvelseID from ovelse where navn='" + navn + "';";
		List<String> ids = DBManager.requestDB(query);
		return(ids.getIndex(0));
	}

	public static String getOktID(String dato, String tidspunkt){
		String query = "select OktID from okt where dato='" + dato + "' and tidspunkt='" + tidspunkt + "';"
		List<String> ids = DBManager.requestDB(query);
	}

	public static List<String> getOkter() {
		String query = "select * from okt";
		return(DBManager.requestDB(query));
	}

	public static List<String> getGrupper(){
		String query = "select * from ovelsesgruppe;";
		return(DBManager.requestDB(query));
	}
} 