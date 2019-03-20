import java.util.Scanner;
import java.util.List;
import java.util.Properties;
import treningsbok.SelectQueries;
import treningsbok.InputQueries;

public class Main {
	public static void main(String[] args) {
        String userName = getInput("Oppgi database brukernavn: ");
        String password = getInput("Oppgi database passord");

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
                    break;

                case "registrer apparat":
                	String navn = getInput("Hva heter det nye apparatet? ").toLowerCase();
                	String beskrivelse = getInput("Gi en beskrivelse av det nye apparatet: ");

                    inputQuery.RegistrerApparat(navn, beskrivelse);
                    System.out.println("Oppgave utført.\n");
                    break;

                case "registrer øvelse":
                    navn = getInput("Hva heter den nye øvelsen? ").toLowerCase();
                    beskrivelse = getInput("Gi en beskrivelse av den nye øvelsen: ");

                    String type = getInput("Er det en friøvelse eller en apparatøvelse?\n"+
                                           "Skriv 'f' for friøvelse eller 'a' for apparatøvelse: ").toLowerCase();
                    if (type == "f"){
                        inputQuery.RegistrerFriovelse(navn, beskrivelse);
                        System.out.println("Oppgave utført.\n");

                    }
                    else{
                        String kilo = getInput("Hvor mange kilo brukes i øvelsen?: ");
                        String sett = getInput("Hvor mange sett gjøres i øvelsen?: ");
                        String apparat = getInput("Hvilket eksisterende apparat brukes til øvelsen?: ").toLowerCase();
                        String apparatID = selectQuery.getApparatID(apparat);

                        inputQuery.RegistrerApparatovelse(navn, beskrivelse, kilo, sett, apparatID);
                        System.out.println("Oppgave utført.\n");
                    }
                	break;

                case "registrer økt":
                    String dato = getInput("Oppgi dato på formen YYYY-MM-DD, f. eks. '2019-03-21': ");
                    String tidspunkt = getInput("Oppgi starttidspunkt på formen hh:mm:ss, f. eks. '23:59:59': ");
                    String varighet = getInput("Oppgi varighet på økten på formen hh:mm:ss, f. eks. '01:30:00': ");
                    String prestasjon = getInput("Oppgi din prestasjon under økten, et heltall mellom 1 og 10: ");
                    String form = getInput("Oppgi din form under økten, et heltall mellom 1 og 10: ");

                    inputQuery.RegistrerOkt(dato, tidspunkt, varighet, prestasjon, form);

                    //Legg til øvelser i økt
                    String ovelse;
                    String ovelseID;
                    String oktID = selectQuery.getOktID(dato, tidspunkt);

                    while (true){

                        ovelse = getInput("Oppgi en eksisterende øvelse fra databasen for å knytte den til økten, eller trykk <enter> for å gå videre: ").toLowerCase();
                        if ((ovelse == "") || (ovelse == " "))
                            break;

                        ovelseID = selectQuery.getOvelseID(ovelse);
                        inputQuery.RegistrerOvelseIOkt(ovelseID, oktID);
                        System.out.println("Lagt til...\n");
                    }
                    String notat = getInput("Legg til notat til økten eller trykk <enter>: ");
                    if (notat != ""){
                        inputQuery.RegistrerNotat(oktID, notat);
                    }
                    System.out.println("Oppgave utført.\n");
                	break;

                case "registrer øvelsesgruppe":
                    navn = getInput("Hva heter den nye øvelsesgruppen?: ");
                	inputQuery.RegistrerGruppe(navn);
                    System.out.println("Oppgave utført.\n");
                    break;

                case "registrer øvelse i gruppe":

                    List<String> grupper = selectQuery.getGrupper();
                    System.out.println("Følgende øvelsesgrupper er registrert i databasen: ");
                    System.out.println(grupper);
                    String gruppeID = getInput("Oppgi gruppeID til gruppen du vil behandle: ");

                    //String ovelse;
                    //String ovelseID;

                    while (true){

                        ovelse = getInput("Oppgi en øvelse fra databasen som skal knyttes til gruppen, eller trykk <enter> for å gå videre: ").toLowerCase();
                        if ((ovelse == "") || (ovelse == " "))
                            break;

                        ovelseID = selectQuery.getOvelseID(ovelse);
                        inputQuery.RegistrerInngarI(ovelseID, gruppeID);
                        System.out.println("Lagt til...\n");
                    }

                    System.out.println("Oppgave utført.\n");
                    break;
/*
                case "registrer øvelse i økt":
                    String okter = SelectQueries.getOkter();
                    System.out.println("Følgende økter er registrert i databasen: ");
                    System.out.println(okter);
                    String oktID = getInput("Oppgi øktID til økten du vil behandle: ");

                    String ovelse;
                    Sring ovelseID;

                    while (1){

                        ovelse = getInput("Oppgi en øvelse fra databasen som ble utført, eller trykk <enter> for å gå videre: ").toLowerCase();
                        if ((ovelse == '') || (ovelse == ' ')):
                            break;

                        ovelseID = SelectQueries.getOvelseID(ovelse);
                        InputQueries.RegistrerOvelseIOkt(ovelseID, oktID);
                        System.out.println("Lagt til...\n");
                    }
                    System.out.println("Oppgave utført.\n");
                    break;
*/
                case "vis øvelsesgruppe":
//                	String alleOvelsesGrupper = InputQueries.VisAlleOvelsesgrupper();
//                	System.out.println("Følgende grupper finnes i systemet:\n"+alleOvelsesGrupper);

                	String gruppe = getInput("Hvilken gruppe vil du vise? ");
                	String ovelser = selectQuery.getOvelserIGruppe(gruppe);
                    System.out.println(ovelser);
                    //DENNE MÅ LAGES
                	break;

                case "vis økter":
                	String antall = getInput("Hvor mange? ");
                    String okter = selectQuery.getSisteOkter(antall);
                    System.out.println(okter);
                    //DENNE MÅ LAGES
                	break;

                case "vis resultatlogg":
                    String startDato = getInput("Oppgi intervallets startdato på formen YYYY-MM-DD: ");
                    String sluttDato = getInput("Oppgi intervallets sluttdato på formen YYYY-MM-DD: ");

                    String resultat = selectQuery.getResultatLogg(startDato, sluttDato);
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
                        "'registrer øvelse i gruppe' Lar deg knytte eksisterende øvelser til en eksisterende øvelsesgruppe"+
 //                     "'registrer øvelse i økt'    Lar deg knytte eksisterende øvelser til en eksisterende økt\n" +
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