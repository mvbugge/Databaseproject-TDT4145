package main;

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

            input = ConsoleManager.getInput(inputMsg).toLowerCase();

            switch (input){

                case "q":
                    exitFlag = true;
                    break;

                case "registrer apparat":
                    ConsoleManager.RegistrerApparat();
                    break;
                
                case "registrer øvelse":
                    ConsoleManager.RegistrerOvelse();
                	break;

                case "registrer økt":
                    ConsoleManager.RegistrerOkt();
                	break;

                case "registrer øvelsesgruppe":
                	ConsoleManager.RegistrerOvelsesgruppe();
                	break;

                case "vis øvelsesgruppe":
//                	String alleOvelsesGrupper = ConsoleManager.VisAlleOvelsesgrupper();
//                	System.out.println("Følgende grupper finnes i systemet:\n"+alleOvelsesGrupper);

                	String gruppe = ConsoleManager.getInput("Hvilken gruppe vil du vise? ");
                	ConsoleManager.VisOvelsesgruppe(gruppe);
                	break;

                case "vis økter":
                	Int antall = ConsoleManager.getInput("Hvor mange? ");
                    ConsoleManager.VisOkter(antall);
                	break;

                case "vis resultatlogg":
                    ConsoleManager.VisResultatLogg();
                	break;


                case "vis uprøvde":
                	ConsoleManager.VisUrpovde();
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
                        )

                    break;
            }
            /*
            if(input.equals("slutt da") || input.equals("slutt")) {
                finished = true;
            } else if (input.equals("ny treningsøkt")){
                ConsoleManager.makeTreningsøkt();
            }
            */
        }
    }
}