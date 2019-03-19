package treningsbok;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {



        boolean exitFlag = false;
        String input; 
        String helloMsg = 	"~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n"+
        					"Velkommen til din treningsdagbok!\n"+
        					"~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        String inputMsg = "Hvilken operasjon ønsker du å utføre? Skriv [what] for oversikt over mulige operasjoner, eller [q] for å avslutte.\n>>";


        System.out.println(helloMsg);

        while (!exitFlag) {

            input = getInput(inputMsg).toLowerCase();

            switch (input){

                case "q":
                    exitFlag = true;
                    break;

                case "registrer apparat":
                	String navn = getInput("Hva heter det nye apparatet? ").toLowerCase();
                	String beskrivelse = getInput("Gi en beskrivelse av det nye apparatet: ");

                    InputQueries.RegistrerApparat(navn, beskrivelse);
                    System.out.println("Oppgave utført.\n");
                    break;
                
                case "registrer øvelse":
                    String navn = getInput("Hva heter den nye øvelsen? ").toLowerCase();
                    String beskrivelse = getInput("Gi en beskrivelse av den nye øvelsen: ");

                    String type = getInput("Er det en friøvelse eller en apparatøvelse?\n"+
                                           "Skriv 'f' for friøvelse eller 'a' for apparatøvelse: ").toLowerCase();
                    if (type == 'f'){
                        InputQueries.RegistrerFriovelse(navn, beskrivelse);
                        System.out.println("Oppgave utført.\n");

                    }
                    else{
                        String kilo = getInput("Hvor mange kilo brukes i øvelsen?: ");
                        String sett = getInput("Hvor mange sett gjøres i øvelsen?: ");
                        String apparat = getInput("Hvilket eksisterende apparat brukes til øvelsen?: ").toLowerCase();
                        String apparatID = SelectQueries.getApparatID(apparat);

                        InputQueries.RegistrerApparatovelse(navn, beskrivelse, kilo, sett, apparatID);
                        System.out.println("Oppgave utført.\n");
                    }
                    
                	break;

                case "registrer økt":
                    String dato = getInput("Oppgi dato på formen YYYY-MM-DD, f. eks. '2019-03-21': ");
                    String tidspunkt = getInput("Oppgi starttidspunkt på formen hh:mm:ss, f. eks. '23:59:59': ");
                    String varighet = getInput("Oppgi varighet på økten på formen hh:mm:ss, f. eks. '01:30:00': ");
                    String prestasjon = getInput("Oppgi din prestasjon under økten, et heltall mellom 1 og 10: ");
                    String form = getInput("Oppgi din form under økten, et heltall mellom 1 og 10: ");

                    InputQueries.RegistrerOkt(dato, tidspunkt, varighet, prestasjon, form);            
                    System.out.println("Oppgave utført.\n");
                	break;

                case "registrer øvelsesgruppe":
                    String navn = getInput("Hva heter den nye øvelsesgruppen?: ");
                	InputQueries.RegistrerGruppe(navn);
                    System.out.println("Oppgave utført.\n");
                    break;

                case "registrer øvelse i gruppe":

                    String grupper = SelectQueries.getGrupper();
                    System.out.println("Følgende øvelsesgrupper er registrert i databasen: ");
                    System.out.println(grupper);
                    String gruppeID = getInput("Oppgi gruppeID til gruppen du vil behandle: ");

                    String ovelse;
                    Sring ovelseID;

                    while (1){

                        ovelse = getInput("Oppgi en øvelse fra databasen som skal knyttes til gruppen, eller trykk <enter> for å gå videre: ").toLowerCase();
                        if ((ovelse == '') || (ovelse == ' ')):
                            break;

                        ovelseID = SelectQueries.getOvelseID(ovelse);
                        InputQueries.RegistrerInngarI(ovelseID, gruppeID);
                        System.out.println("Lagt til...\n");
                    }

                    System.out.println("Oppgave utført.\n");
                    break;



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

                case "vis øvelsesgruppe":
//                	String alleOvelsesGrupper = InputQueries.VisAlleOvelsesgrupper();
//                	System.out.println("Følgende grupper finnes i systemet:\n"+alleOvelsesGrupper);

                	String gruppe = getInput("Hvilken gruppe vil du vise? ");
                	SelectQueries.getOvelserIGruppe(gruppe);
                    //DENNE MÅ LAGES
                	break;

                case "vis økter":
                	int antall = getInput("Hvor mange? ");
                    SelectQueries.getSisteOkter(antall);
                    //DENNE MÅ LAGES
                	break;

                case "vis resultatlogg":
                    String startDato = getInput("Oppgi intervallets startdato på formen YYYY-MM-DD: ");
                    String sluttDato = getInput("Oppgi intervallets sluttdato på formen YYYY-MM-DD: ");

                    String resultat = SelectQueries.getResultatLogg(startDato, sluttDato);
                    System.out.println("Følgende resultater er funnet:\n");
                    System.out.println(resultat);
                   	break;


                case "vis uprøvde":
                    String ovelser = SelectQueries.getUprovde();
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
                        "'registrer øvelse i økt'    Lar deg knytte eksisterende øvelser til en eksisterende økt\n" +
                        "'vis øvelsesgruppe'         Lar deg spesifisere en gruppe og viser medlemsøvelser\n" +
                        "'vis økter'                 Lar deg spesifisere et antall siste gjennomførte økter for visning\n" +
                        "'vis resultatlogg'          Lar deg spesifisere øvelse og tidsintervall, og gir tilhørende resultatlogg\n" +
                        "'vis uprøvde'               Gir deg en oversikt over registrerte øvelser som ennå ikke har blit registrert i en økt\n\n"
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

