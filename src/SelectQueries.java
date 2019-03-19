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

	public static int getApparatID(String navn){
		String query = "select ApparatID from apparat where navn = '" + navn + "';"
		List<String> ids = DBManager.requestDB(query);
		return(ids.getIndex(0));
	}

} 