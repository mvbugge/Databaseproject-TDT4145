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
        String inputMsg = "Hvilken operasjon �nsker du � utf�re? Skriv [what] for oversikt over mulige operasjoner, eller [q] for � avslutte.\n>>";
        
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

                case "registrer �velse":
                    navn = getInput("Hva heter den nye �velsen? ").toLowerCase();
                    if (inputQuery.RegistrerOvelse(navn)) {
                        System.out.println("�velse registrert.\n");
                        
                        String ovelseId = selectQuery.getOvelseID(navn);

                        String type = getInput("Er det en fri�velse eller en apparat�velse?\n"+
                                               "Skriv 'f' for fri�velse eller 'a' for apparat�velse: ").toLowerCase();

                        if (type.equals("f")){
                            beskrivelse = getInput("Gi en beskrivelse av den nye �velsen: ");
                            
                            if (inputQuery.RegistrerFriovelse(ovelseId, beskrivelse)) {
                                System.out.println("�velse registrert som fri�velse.\n");
                            } else {
                                System.out.println("�velse ble ikke registrert som fri�velse og blir derfor slettet fra �velser.\n");
                                inputQuery.DeleteOvelse(ovelseId);
                            };
                            
                        }
                        if(type.equals("a")){
                            String kilo = getInput("Hvor mange kilo brukes i �velsen?: ");
                            String sett = getInput("Hvor mange sett gj�res i �velsen?: ");
                            String apparat = getInput("Hvilket eksisterende apparat brukes til �velsen)?: ").toLowerCase();
                            System.out.println("Henter ut id...\n");

                            String apparatID = selectQuery.getApparatID(apparat);

                            if (inputQuery.RegistrerApparatovelse(ovelseId, kilo, sett, apparatID)) {
                                System.out.println("�velse registrert som apparatovelse.\n");
                            } else {
                                System.out.println("�velse ble ikke registrert som apparat�velse og blir derfor slettet fra �velser.\n");
                                inputQuery.DeleteOvelse(ovelseId);
                            };
                        }
                        
                    } else {
                        System.out.println("�velse ble ikke registrert.\n");
                    };
                	break;

                case "registrer �kt":
                    String dato = getInput("Oppgi dato p� formen YYYY-MM-DD, f. eks. '2019-03-21': ");
                    String tidspunkt = getInput("Oppgi starttidspunkt p� formen hh:mm:ss, f. eks. '23:59:59': ");
                    String varighet = getInput("Oppgi varighet p� �kten p� formen hh:mm:ss, f. eks. '01:30:00': ");
                    String prestasjon = getInput("Oppgi din prestasjon under �kten, et heltall mellom 1 og 10: ");
                    String form = getInput("Oppgi din form under �kten, et heltall mellom 1 og 10: ");

                    if (inputQuery.RegistrerOkt(dato, tidspunkt, varighet, prestasjon, form)) {
                        System.out.println("�kt registrert.\n");
                    } else {
                        System.out.println("�kt ble ikke registrert.\n");
                    };
                    
                    String ovelse;
                    String ovelseID;
                    String oktID = selectQuery.getOktID(dato, tidspunkt);

                    while (true){

                        ovelse = getInput("Oppgi navn p� en eksisterende �velse i databasen for � knytte den til �kten, eller trykk <enter> for � g� videre: ").toLowerCase();
                        if (ovelse.isEmpty())
                            break;

                        ovelseID = selectQuery.getOvelseID(ovelse);
                        if (inputQuery.RegistrerOvelseIOkt(ovelseID, oktID)) {
                            System.out.println("�velse lagt til.\n");
                        } else {
                            System.out.println("�velse ble ikke lagt til.\n");
                        };
                    }
                    String notat = getInput("Legg til notat til �kten eller trykk <enter>: ");
                    if (!notat.isEmpty()){
                        if (inputQuery.RegistrerNotat(oktID, notat)) {
                            System.out.println("Notat lagt til.\n");
                        } else {
                            System.out.println("Notat ble ikke lagt til.\n");
                        };
                    }
                	break;

                case "registrer �velsesgruppe":
                    navn = getInput("Hva heter den nye �velsesgruppen?: ");
                	inputQuery.RegistrerGruppe(navn);
                    System.out.println("Oppgave utf�rt.\n");
                    break;

                case "registrer �velse i gruppe":

                    List<String> grupper = selectQuery.getGrupper();
                    System.out.println("F�lgende �velsesgrupper er registrert i databasen: ");
                    System.out.println(grupper);
                    String gruppeID = getInput("Oppgi gruppeID til gruppen du vil behandle: ");

                    //String ovelse;
                    //String ovelseID;

                    while (true){

                        ovelse = getInput("Oppgi en �velse fra databasen som skal knyttes til gruppen, eller trykk <enter> for � g� videre: ").toLowerCase();
                        if ((ovelse == "") || (ovelse == " "))
                            break;

                        ovelseID = selectQuery.getOvelseID(ovelse);
                        inputQuery.RegistrerInngarI(ovelseID, gruppeID);
                        System.out.println("Lagt til...\n");
                    }

                    System.out.println("Oppgave utf�rt.\n");
                    break;
/*
                case "registrer �velse i �kt":
                    String okter = SelectQueries.getOkter();
                    System.out.println("F�lgende �kter er registrert i databasen: ");
                    System.out.println(okter);
                    String oktID = getInput("Oppgi �ktID til �kten du vil behandle: ");
                    String ovelse;
                    Sring ovelseID;
                    while (1){
                        ovelse = getInput("Oppgi en �velse fra databasen som ble utf�rt, eller trykk <enter> for � g� videre: ").toLowerCase();
                        if ((ovelse == '') || (ovelse == ' ')):
                            break;
                        ovelseID = SelectQueries.getOvelseID(ovelse);
                        InputQueries.RegistrerOvelseIOkt(ovelseID, oktID);
                        System.out.println("Lagt til...\n");
                    }
                    System.out.println("Oppgave utf�rt.\n");
                    break;
*/
                case "vis �velsesgruppe":
//                	String alleOvelsesGrupper = InputQueries.VisAlleOvelsesgrupper();
//                	System.out.println("F�lgende grupper finnes i systemet:\n"+alleOvelsesGrupper);

                	String gruppe = getInput("Hvilken gruppe vil du vise? ");
                	String ovelser = selectQuery.getOvelserIGruppe(gruppe);
                    System.out.println(ovelser);
                    //DENNE M� LAGES
                	break;

                case "vis �kter":
                	String antall = getInput("Hvor mange? ");
                    String okter = selectQuery.getSisteOkter(antall);
                    System.out.println(okter);
                    //DENNE M� LAGES
                	break;

                case "vis resultatlogg":
                    String startDato = getInput("Oppgi intervallets startdato p� formen YYYY-MM-DD: ");
                    String sluttDato = getInput("Oppgi intervallets sluttdato p� formen YYYY-MM-DD: ");

                    String resultat = selectQuery.getResultatLogg(startDato, sluttDato);
                    System.out.println("F�lgende resultater er funnet:\n");
                    System.out.println(resultat);
                   	break;

                case "vis upr�vde":
                    ovelser = selectQuery.getUprovde();
                    System.out.println("F�lgende �velser er upr�vde:\n");
                    System.out.println(ovelser);
                	break;

                case "what":
                    System.out.println(
                    	"F�lgende gyldige input finnes (oppgi kommando uten 'fnutter'):\n" +

                        "'registrer apparat'         Lar deg registrere nytt apparat med tilh�rende data\n" +
                        "'registrer �velse'          Lar deg registrere ny �velse med tilh�rende data\n" +
                        "'registrer �kt'             Lar deg registrere ny �kt med tilh�rende data\n" +
                        "'registrer �velsesgruppe'   Lar deg registrere ny �velsesgruppe med tilh�rende data\n" +
                        "'registrer �velse i gruppe' Lar deg knytte eksisterende �velser til en eksisterende �velsesgruppe\n"+
 //                     "'registrer �velse i �kt'    Lar deg knytte eksisterende �velser til en eksisterende �kt\n" +
                        "'vis �velsesgruppe'         Lar deg spesifisere en gruppe og viser medlems�velser\n" +
                        "'vis �kter'                 Lar deg spesifisere et antall siste gjennomf�rte �kter for visning\n" +
                        "'vis resultatlogg'          Lar deg spesifisere �velse og tidsintervall, og gir tilh�rende resultatlogg\n" +
                        "'vis upr�vde'               Gir deg en oversikt over registrerte �velser som enn� ikke har blit registrert i en �kt\n\n" +
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