package treningsbok;

import treningsbok.DBManager;
import java.util.List;
import java.util.Properties;

public class SelectQueries {
	private static DBManager dbmanager;

	public SelectQueries(Properties p){
		dbmanager = new DBManager(p);
	}

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
		String ids = dbmanager.requestID(query);
		return(ids);
	}
	public static String getOvelseID(String navn){
		String query = "select OvelseID from ovelse where navn='" + navn + "';";
		String ids = dbmanager.requestID(query);
		return(ids);
	}

	public static String getOktID(String dato, String tidspunkt){
		String query = "select OktID from okt where dato='" + dato + "' and tidspunkt='" + tidspunkt + "';";
		String ids = dbmanager.requestID(query);
		return(ids);
	}

	public static List<String> getOkter() {
		String query = "select * from okt";
		return(dbmanager.requestDB(query));
	}

	public static List<String> getGrupper(){
		String query = "select * from ovelsesgruppe;";
		return(dbmanager.requestDB(query));
	}

	public static String getOvelserIGruppe(String gruppe){
		return " ";
	}

	public static String getSisteOkter(String antall){
		return " ";
	}

	public static String getResultatLogg(String startDato, String sluttDato){
		return " ";
	}

	public static String getUprovde(){
		return " ";
	}
} 
