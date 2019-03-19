package treningsbok;

public class Main {

	public static void main(String[] args) {



        boolean exitFlag = false;
        String input; 
        String helloMsg = 	"~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n"+
        					"Velkommen til din treningsdagbok!\n"+
        					"~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~\n";

        String inputMsg = "Hvilken operasjon ønsker du å utføre? Skriv [what] for oversikt over mulige operasjoner, eller [q] for å avslutte.\n>>"


        System.out.println(helloMsg);

        while (!exitFlag) {

            input = getInput(inputMsg).toLowerCase();

            switch (input){

                case "q":
                    exitFlag = true;
                    break;

                case "registrer apparat":
                	String navn = getInput("Hva heter det nye apparatet? ");
                	String beskrivelse = getInput("Gi en beskrivelse av det nye apparatet: ")

                    InputQueries.RegistrerApparat(navn, beskrivelse);
                    System.out.println("Oppgave utført.\n");
                    break;
                
                case "registrer øvelse":
                    String navn = getInput("Hva heter den nye øvelsen? ");
                    String beskrivelse = getInput("Gi en beskrivelse av den nye øvelsen: ");
                    String type = getInput("Er det en friøvelse eller en apparatøvelse?\n"+
                                           "Skriv 'f' for friøvelse eller 'a' for apparatøvelse: ").toLowerCase();
                    if (type == 'f'){
                        InputQueries.RegistrerFriovelse(navn, beskrivelse);
                        System.out.println("Oppgave utført.\n");

                    }
                    else{
                        int kilo = getInput("Hvor mange kilo brukes i øvelsen?: ");
                        int sett = getInput("Hvor mange kilo brukes i øvelsen?: ");
                        String apparat = getInput("Hvilket eksisterende apparat brukes til øvelsen?: ").toLowerCase();
                        int apparatID = SelectQueries.getApparatID(apparat);

                        InputQueries.RegistrerFriovelse(navn, beskrivelse);
                        System.out.println("Oppgave utført.\n");
                    }
                    
                	break;

                case "registrer økt":
                    String dato = getInput("Oppgi dato på formen YYYY-MM-DD, f. eks. '2019-03-21': ");
                    String tidspunkt = getInput("Oppgi starttidspunkt på formen hh:mm:ss, f. eks. '23:59:59': ");
                    String varighet = getInput("Oppgi varighet på økten på formen hh:mm:ss, f. eks. '01:30:00': ");
                    int prestasjon = getInput("Oppgi din prestasjon under økten, et heltall mellom 1 og 10: ");
                    int form = getInput("Oppgi din form under økten, et heltall mellom 1 og 10: ");

                    InputQueries.RegistrerOkt(dato, tidspunkt, varighet, prestasjon, form); 
                    System.out.println("Oppgave utført.\n");
                	break;

                case "registrer øvelsesgruppe":
                	InputQueries.RegistrerOvelsesgruppe();
                	break;

                case "vis øvelsesgruppe":
//                	String alleOvelsesGrupper = InputQueries.VisAlleOvelsesgrupper();
//                	System.out.println("Følgende grupper finnes i systemet:\n"+alleOvelsesGrupper);

                	String gruppe = getInput("Hvilken gruppe vil du vise? ");
                	InputQueries.VisOvelsesgruppe(gruppe);
                	break;

                case "vis økter":
                	int antall = getInput("Hvor mange? ");
                    InputQueries.VisOkter(antall);
                	break;

                case "vis resultatlogg":
                    InputQueries.VisResultatLogg();
                	break;


                case "vis uprøvde":
                	InputQueries.VisUrpovde();
                	break;


                case "what":
                    System.out.println(
                    	"Følgende gyldige input finnes (oppgi kommando uten 'fnutter'):\n" +

                        "'registrer apparat'       Lar deg registrere nytt apparat med tilhørende data\n" +
                        "'registrer øvelse'        Lar deg registrere ny øvelse med tilhørende data\n" +
                        "'registrer økt'           Lar deg registrere ny økt med tilhørende data\n" +
                        "'registrer øvelsesgruppe' Lar deg registrere ny øvelsesgruppe med tilhørende data\n" +
                        "'vis øvelsesgruppe'       Lar deg spesifisere en gruppe og viser medlemsøvelser\n" +
                        "'vis økter'               Lar deg spesifisere et antall siste gjennomførte økter for visning\n" +
                        "'vis resultatlogg'        Lar deg spesifisere øvelse og tidsintervall, og gir tilhørende resultatlogg\n" +
                        "'vis uprøvde'             Gir deg en oversikt over registrerte øvelser som ennå ikke har blit registrert i en økt\n\n"
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

