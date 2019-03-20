package treningsbok;

import java.util.Scanner;
import java.util.List;
import java.util.Properties;
import treningsbok.SelectQueries;
import treningsbok.InputQueries;

public class Main {
	public static void main(String[] args) {
        String userName = "root";
        String password = "08121998";

        Properties p = new Properties();

        p.put("user", userName);
        p.put("password", password);

        InputQueries inputQuery = new InputQueries(p);
        SelectQueries selectQuery = new SelectQueries(p);
        
        String helloMsg =   "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n"+
                            "Velkommen til din treningsdagbok!\n"+
                            "~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        System.out.println(helloMsg);
        
        String input; 
        String inputMsg = "Hvilken operasjon ønsker du å utføre? Skriv [what] for oversikt over mulige operasjoner, eller [q] for å avslutte.\n>>";
        
        boolean exitFlag = false;
        while (!exitFlag) {

            input = getInput(inputMsg).toLowerCase();

            switch (input){

                case "q":
                    exitFlag = true;
                    System.out.println("Adios Amigos!.\n");
                    break;

                case "registrer apparat":
                	String navn = getInput("Hva heter det nye apparatet? ").toLowerCase();
                	String beskrivelse = getInput("Gi en beskrivelse av det nye apparatet: ");

                    if (inputQuery.RegistrerApparat(navn, beskrivelse)) {
                        System.out.println("Apparat regstrert.\n");
                    } else {
                        System.out.println("Apparat ble ikke registrert.\n");
                    };
                    break;

                case "registrer øvelse":
                    navn = getInput("Hva heter den nye øvelsen? ").toLowerCase();
                    if (inputQuery.RegistrerOvelse(navn)) {
                        System.out.println("Øvelse registrert.\n");
                        
                        String ovelseId = selectQuery.getOvelseID(navn);

                        String type = getInput("Er det en friøvelse eller en apparatøvelse?\n"+
                                               "Skriv 'f' for friøvelse eller 'a' for apparatøvelse: ").toLowerCase();

                        if (type.equals("f")){
                            beskrivelse = getInput("Gi en beskrivelse av den nye øvelsen: ");
                            
                            if (inputQuery.RegistrerFriovelse(ovelseId, beskrivelse)) {
                                System.out.println("Øvelse registrert som friøvelse.\n");
                            } else {
                                System.out.println("Øvelse ble ikke registrert som friøvelse og blir derfor slettet fra øvelser.\n");
                                inputQuery.DeleteOvelse(ovelseId);
                            };
                            
                        }
                        if(type.equals("a")){
                            String kilo = getInput("Hvor mange kilo brukes i øvelsen?: ");
                            String sett = getInput("Hvor mange sett gjøres i øvelsen?: ");
                            String apparat = getInput("Hvilket eksisterende apparat brukes til øvelsen)?: ").toLowerCase();
                            System.out.println("Henter ut id...\n");

                            String apparatID = selectQuery.getApparatID(apparat);

                            if (inputQuery.RegistrerApparatovelse(ovelseId, kilo, sett, apparatID)) {
                                System.out.println("Øvelse registrert som apparatovelse.\n");
                            } else {
                                System.out.println("Øvelse ble ikke registrert som apparatøvelse og blir derfor slettet fra øvelser.\n");
                                inputQuery.DeleteOvelse(ovelseId);
                            };
                        }
                        
                    } else {
                        System.out.println("Øvelse ble ikke registrert.\n");
                    };
                	break;

                case "registrer økt":
                    String dato = getInput("Oppgi dato på formen YYYY-MM-DD, f. eks. '2019-03-21': ");
                    String tidspunkt = getInput("Oppgi starttidspunkt på formen hh:mm:ss, f. eks. '23:59:59': ");
                    String varighet = getInput("Oppgi varighet på økten på formen hh:mm:ss, f. eks. '01:30:00': ");
                    String prestasjon = getInput("Oppgi din prestasjon under økten, et heltall mellom 1 og 10: ");
                    String form = getInput("Oppgi din form under økten, et heltall mellom 1 og 10: ");

                    if (inputQuery.RegistrerOkt(dato, tidspunkt, varighet, prestasjon, form)) {
                        System.out.println("Økt registrert.\n");
                    } else {
                        System.out.println("Økt ble ikke registrert.\n");
                    };
                    
                    String ovelse;
                    String ovelseID;
                    String oktID = selectQuery.getOktID(dato, tidspunkt);

                    while (true){

                        ovelse = getInput("Oppgi navn på en eksisterende øvelse i databasen for å knytte den til økten, eller trykk <enter> for å gå videre: ").toLowerCase();
                        if (ovelse.isEmpty()) {
                            break;
                        }
                        ovelseID = selectQuery.getOvelseID(ovelse);
                        if (inputQuery.RegistrerOvelseIOkt(ovelseID, oktID)) {
                            System.out.println("Øvelse lagt til.\n");
                        } else {
                            System.out.println("Øvelse ble ikke lagt til.\n");
                        };
                    }
                    String notat = getInput("Legg til notat til økten eller trykk <enter>: ");
                    if (!notat.isEmpty()){
                        if (inputQuery.RegistrerNotat(oktID, notat)) {
                            System.out.println("Notat lagt til.\n");
                        } else {
                            System.out.println("Notat ble ikke lagt til.\n");
                        };
                    }
                	break;
          	
                case "registrer øvelsesgruppe":
                    navn = getInput("Hva heter den nye øvelsesgruppen?: ");
                    if (inputQuery.RegistrerGruppe(navn)) {
                        System.out.println("Øvelsegruppe ble registrert.\n");
                    } else {
                        System.out.println("Øvelsegruppe ble ikke registrert.\n");
                    };
                    break;

                case "registrer øvelse i gruppe":

                    List<String> grupper = selectQuery.getGrupper();
                    System.out.println("Følgende øvelsesgrupper er registrert i databasen: ");
                    if(grupper!=null) {
                        System.out.println(grupper);
                    }else {
                        System.out.println("Vi klarte ikke hente ut øvelsesgruppene.");
                    }
                    
                    String gruppeID = getInput("Oppgi gruppeID til gruppen du vil behandle: ");

                    while (true){

                        ovelse = getInput("Oppgi en øvelse fra databasen som skal knyttes til gruppen, eller trykk <enter> for å gå videre: ").toLowerCase();
                        if (ovelse.isEmpty()) {
                            break;
                        }
                        ovelseID = selectQuery.getOvelseID(ovelse);
                        if (inputQuery.RegistrerInngarI(ovelseID, gruppeID)) {
                            System.out.println("Øvelse ble registrert i gruppen.\n");
                        } else {
                            System.out.println("Øvelse ble ikke registrert i gruppen.\n");
                        };
                    }
                    break;

                case "vis øvelsesgruppe":
                	String gruppe = getInput("Hvilken gruppe vil du vise? ");
                    gruppeID = selectQuery.getGruppeID(gruppe);
                    List<String> ovelser = selectQuery.getOvelserIGruppe(gruppeID);
                    if(ovelser!=null) {
                        System.out.println(ovelser);
                    }else {
                        System.out.println("Vi klarte ikke hente ut øvelses.");
                    }
                   	break;

                case "vis økter":
                	String antall = getInput("Hvor mange? ");
                	List<String> okter = selectQuery.getSisteOkter(antall);
                    System.out.println(okter);
                	break;

                case "vis resultatlogg":
                    String startDato = getInput("Oppgi intervallets startdato på formen YYYY-MM-DD: ");
                    String sluttDato = getInput("Oppgi intervallets sluttdato på formen YYYY-MM-DD: ");
                    navn = getInput("Oppgi navn på øvelsen du ønsker å sjekke loggen til: ");

                	List<String> resultat = selectQuery.getResultatLoggFriovelse(startDato, sluttDato, navn);
                	if (resultat.isEmpty()) {
                        resultat = selectQuery.getResultatLoggApparatovelse(startDato, sluttDato, navn);
                	}
                	
                    System.out.println("Følgende resultater er funnet:\n");
                    System.out.println(resultat);
                   	break;

                case "vis uprøvde":
                    ovelser = selectQuery.getUprovde();
                    System.out.println("Følgende øvelser er uprøvde:\n");
                    System.out.println(ovelser);
                	break;

                case "what":
                    System.out.println(
                    	"Følgende gyldige input finnes (oppgi kommando uten 'fnutter'):\n" +

                        "'registrer apparat'         Lar deg registrere nytt apparat med tilhørende data\n" +
                        "'registrer øvelse'          Lar deg registrere ny øvelse med tilhørende data\n" +
                        "'registrer økt'             Lar deg registrere ny økt med tilhørende data\n" +
                        "'registrer øvelsesgruppe'   Lar deg registrere ny øvelsesgruppe med tilhørende data\n" +
                        "'registrer øvelse i gruppe' Lar deg knytte eksisterende øvelser til en eksisterende øvelsesgruppe\n"+
                        "'vis øvelsesgruppe'         Lar deg spesifisere en gruppe og viser medlemsøvelser\n" +
                        "'vis økter'                 Lar deg spesifisere et antall siste gjennomførte økter for visning\n" +
                        "'vis resultatlogg'          Lar deg spesifisere øvelse og tidsintervall, og gir tilhørende resultatlogg\n" +
                        "'vis uprøvde'               Gir deg en oversikt over registrerte øvelser som ennå ikke har blit registrert i en økt\n\n" +
                        "'q'                         Avslutt"
                        );

                    break;
            }     
        }
    }
    public static String getInput(String something) {
        System.out.println(something);

        String input = "";

        try {
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine().toLowerCase();
        } catch(Exception e) {
            e.printStackTrace();
        }

        return input;
    }
}