package treningsbok;

import java.util.List;

public class SelectQueries {
	
	private static DBManager dbmanager;
	
	public SelectQueries() {
		dbmanager = new DBManager();
	};
	
	public static List<String> getOvelser(){
		String query = "select * from ovelse;";
		return(dbmanager.requestDB(query));
	}

	public static List<String> getOkt(){
		String query = "select * from okt;";
		return(dbmanager.requestDB(query));
	}

	public static List<String> getNotat(){
		String query = "select * from notat;";
		return(dbmanager.requestDB(query));
	}

	public static List<String> getApparat(){
		String query = "select * from apparat;";
		return(dbmanager.requestDB(query));
	}

	public static String getApparatID(String navn){
		String query = "select ApparatID from apparat where navn='" + navn + "';";
		List<String> ids = dbmanager.requestDB(query);
		return(ids.get(0));
	}
	public static String getOvelseID(String navn){
		String query = "select OvelseID from ovelse where navn='" + navn + "';";
		List<String> ids = dbmanager.requestDB(query);
		return(ids.get(0));
	}

	public static String getOktID(String dato, String tidspunkt){
		String query = "select OktID from okt where dato='" + dato + "' and tidspunkt='" + tidspunkt + "';";
		List<String> ids = dbmanager.requestDB(query);
		return(ids.get(0));
	}

	public static List<String> getOkter() {
		String query = "select * from okt";
		return(dbmanager.requestDB(query));
	}

	public static List<String> getGrupper(){
		String query = "select * from ovelsesgruppe;";
		return(dbmanager.requestDB(query));
	}
    /*
	public static String getOvelserIGruppe(String gruppe) {
		//DO SOMETHING
	};
	
	public static String getSisteOkter(String antall) {
		//DO SOMETHING
	};

	public static String getResultatLogg(String startDato, String sluttDato){
		//DO SOMETHING
	};

	public static String getUprovde(){
		//DO SOMETHING
	};
    */

} 
