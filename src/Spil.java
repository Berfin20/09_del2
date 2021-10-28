import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.Scanner;

public class Spil {
    //Ekstra (unødvendig) design/brugeroplevelse beslutning. Skrifttype, -farve og baggrundsfarve på tekst.
    public static final String SORT_SKRIFT = "\u001B[30m";
    public static final String GUL_BAGGRUND = "\u001B[43m";
    public static final String FED_SKRIFT = "\033[0;1m";
    public static final String ROED_BAGGRUND = "\u001B[41m";
    private Clip clip;

    public static void main(String[] args) {
        Spil spil = new Spil();
        spil.welcome();
    }

    //Felt instantieres
    Felt maldiverne = new Felt(0, "Maldiverne", 0, false, 1, "Velkommen til maldiverne. Du har nu mistet hele din formue. Din balance er på: ");
    Felt tower = new Felt(1, "tower", 250, false, 2, "Du har fundet 250kr i tårnet! Din balance er på: ");
    Felt crater = new Felt(2, "Crater", -100, false, 3, "Øv, du har tabt 100kr i krateret. Din balance er på: ");
    Felt palace_gates = new Felt(3, "Palace Gates", 100, false, 4, "Du har lige fundet 100kr ved paladsets porte! Din balance er på: ");
    Felt cold_desert = new Felt(4, "Cold Desert", -20, false, 5, "Du har mistet 20kr i den kolde ørken.. Din balance er på: ");
    Felt walled_city = new Felt(5, "Walled City", 180, false, 6,"Du har lige fundet 180kr i Walled City! Din balance er på: ");
    Felt monastery = new Felt(6, "Monastery", 0, false, 7, "Du har hverken mistet eller fundet noget i klosteret.. Heldigt eller uheldigt? Din balance er på: ");
    Felt black_cave = new Felt(7, "Black Cave", -70, false, 8, "Du har gjort 70kr væk i den sorte hule.. Din balance er på: ");
    Felt huts_in_the_mountain = new Felt(8, "Huts In The Mountain", 60, false, 9, "Du har fundet 60kr i hytterne på bjerget! Din balance er på: ");
    Felt the_werewall = new Felt(9, "The Werewall", -80, true, 10, "Du har fået stjålet 80kr ved varulve-væggen.. Din balance er på: ");
    Felt the_pit = new Felt(10, "The Pit", -50, false, 11,"Har du lige tabt 50kr i hullet?.. Det var godt nok uheldigt.. Din balance er på: ");
    Felt goldmine = new Felt(11, "Goldmine", 650, false, 12, "Du har fundet guld til 650kr i minen! Din balance er på: ");

    //Metode til at give en velkomst til spillerne
    public void welcome() {
        //Metode afspilAudio til at afspille baggrundsmusik for spillet
        afspilAudio("src/musik.wav");
        pokemonTekstPrint(FED_SKRIFT + "Velkommen til del 2 af CDIO projektet.", 40);
        pokemonTekstPrint("Tast 's' for at starte spillet.", 40);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        //Hvis der bliver tastet s som input uanset om det er et stort eller småt bogstav, vil metoden runGame() blive kørt
        if (input.equalsIgnoreCase("s")) {
            runGame();
        }
    }

    //Metode som samler hele spillet, her bliver bl.a. spiller og terning instantieret
    public void runGame() {
        Spiller spiller1 = new Spiller("player 1", 1000, 0);
        Spiller spiller2 = new Spiller("player 2", 1000, 0);
        Terning terninger = new Terning();
        Scanner scanner = new Scanner(System.in);
        introduction(spiller1, spiller2);

        //Sørger for at spillet kører indtil en af spillerne får over 3000 point
        while (spiller1.konto.getBalance() < 3000 || spiller2.konto.getBalance() < 3000) {
            String input = scanner.next();
            //Spiller 1's input
            if (input.equalsIgnoreCase("a")) { playerTurn(spiller1, terninger); }
            //Spiller 2's input
            if (input.equalsIgnoreCase("l")) { playerTurn(spiller2, terninger); }
            //For at afslutte spillet
            if (input.equalsIgnoreCase("e")) {
                pokemonTekstPrint("Spillet er blevet afsluttet. Tak fordi I spillede!", 50);
                break; }
            //For at genstarte spillet
            if (input.equalsIgnoreCase("r")) {
                pokemonTekstPrint("Spillet bliver genstartet. Vent et øjeblik.", 50);
                pause(3000);
                stopAudio();
                welcome(); }
            //Hvis spiller 1 får = 3000 eller >3000 point køres denne del
            if (spiller1.konto.getBalance() >= 3000) {
                stopAudio();
                afspilAudio("src/vindermusik.wav");
                String vindertekst;
                vindertekst = "VINDEREN ER " + spiller1.getName() + "! tillykke shab :)";
                pokemonTekstPrint(GUL_BAGGRUND + SORT_SKRIFT + vindertekst.toUpperCase(), 50);
                break;
                //Hvis i stedet spiller 2 får = 3000 eller >3000 point køres denne del
            } else if (spiller2.konto.getBalance() >= 3000) {
                stopAudio();
                afspilAudio("src/vindermusik.wav");
                String vindertekst2;
                vindertekst2 = "VINDEREN ER " + spiller2.getName() + "! tillykke shab :)";
                pokemonTekstPrint(GUL_BAGGRUND + SORT_SKRIFT + vindertekst2.toUpperCase(), 50);
                break; }
            //Hvis spiller 1 får en negativ balance køres dette
            if (spiller1.konto.getBalance() < 0) {
                stopAudio();
                afspilAudio("src/taberMusik.wav");
                pokemonTekstPrint(ROED_BAGGRUND + spiller1.getName() + ", din lille loser, man. Du har tabt til din yngre", 50);
                pokemonTekstPrint("Er det en ommer din taber? Tast 'r' for at genstarte spillet, ellers tast 'e'", 50);
                //Hvis i stedet spiller 2 får en negativ balance køres dette
            } else if (spiller2.konto.getBalance() < 0) {
                stopAudio();
                afspilAudio("src/taberMusik.wav");
                pokemonTekstPrint(ROED_BAGGRUND + spiller2.getName() + ", din lille loser, man. Du har tabt til din yngre", 50);
                pokemonTekstPrint("Er det en ommer din taber? Tast 'r' for at genstarte spillet, ellers tast 'e'", 50);
            }
        }
    }

    //Denne metode gemmer spillernes navne og introducerer deres dedikerede input taster
    public void introduction(Spiller s1, Spiller s2) {
        pokemonTekstPrint("For at afslutte spillet når som helst, kan I taste 'e'. For at genstarte når som helst, kan I taste 'r'", 40);
        pokemonTekstPrint("Dette spil kræver to spillere. Hvad skal vi kalde dig, spiller 1?: ", 40);
        Scanner scanner = new Scanner(System.in);
        String name1 = scanner.next();
        s1.setName(name1);
        pause(200);
        pokemonTekstPrint("Velkommen, " + s1.getName() + "!", 50);
        pokemonTekstPrint("Hvad skal vi kalde dig, spiller 2?: ", 50);
        String name2 = scanner.next();
        s2.setName(name2);
        pause(200);
        pokemonTekstPrint("Velkommen, " + s2.getName() + "!", 50);
        pokemonTekstPrint(name1 + ", når du vil slå, skal du bruge tasten 'a'. " + name2 + ", når du vil slå, skal du bruge tasten 'l'.", 40);
        pokemonTekstPrint(name1 + ", du starter.", 40);
    }

    //Metode til at vælge hvis tur det er til at spille
    public void playerTurn(Spiller s, Terning t) {
        //metode til at slå med den givne spillers givne terninger
        t.diceRoll();
        pokemonTekstPrint("Du har slået: " + t.diceRoll(), 70);
        s.updatePosition(t.getFaceValueSum(), s.getPosition());
        if (s.getPosition() <= 12) {
            pokemonTekstPrint("Din position er nu: " + s.getPosition(), 70);
        }
        /*
        For at sikre spillerne kan rykke rundt på felterne i en cyklus skrives denne del, hvor 12 ( som er antallet af felter)
       bliver trukket fra den samlede position. Dette gør, at spillerne også kan ramme felt 1
        */
        if (s.getPosition() > 12) {
            s.setPosition(s.getPosition() - 12);
            pokemonTekstPrint("Din position er nu: " + s.getPosition(), 70);
        }
        flows(s, t);
    }

    /*
    Alle de forskellige flows i spillet. Spillerens position sammenlignes med feltets position. Hvis de er ens, køres den tilhørende kode
     */
    public void flows(Spiller s, Terning t) {
        if (s.getPosition() == maldiverne.getPosition()) {
            s.konto.setBalance(0);
            pokemonTekstPrint(maldiverne.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == tower.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + tower.getInfluenceOnBalance());
            pokemonTekstPrint( tower.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == crater.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + crater.getInfluenceOnBalance());
            pokemonTekstPrint(crater.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == palace_gates.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + palace_gates.getInfluenceOnBalance());
            pokemonTekstPrint(palace_gates.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == cold_desert.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + cold_desert.getInfluenceOnBalance());
            pokemonTekstPrint(cold_desert.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == walled_city.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + walled_city.getInfluenceOnBalance());
            pokemonTekstPrint(walled_city.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == monastery.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + monastery.getInfluenceOnBalance());
            pokemonTekstPrint(monastery.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == black_cave.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + black_cave.getInfluenceOnBalance());
            pokemonTekstPrint(black_cave.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == huts_in_the_mountain.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + huts_in_the_mountain.getInfluenceOnBalance());
            pokemonTekstPrint(huts_in_the_mountain.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == the_werewall.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + the_werewall.getInfluenceOnBalance());
            pokemonTekstPrint(the_werewall.getDescription() + s.konto.getBalance(), 70);
            pokemonTekstPrint("Men bare rolig, du får også en ekstra tur!", 70);
            playerTurn(s, t); } else if (s.getPosition() == the_pit.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + the_pit.getInfluenceOnBalance());
            pokemonTekstPrint(the_pit.getDescription() + s.konto.getBalance(), 70);

        } else if (s.getPosition() == goldmine.getPosition()) {
            s.konto.setBalance(s.konto.getBalance() + goldmine.getInfluenceOnBalance());
            pokemonTekstPrint(goldmine.getDescription() + s.konto.getBalance(), 70);
        }
    }

    //Ekstra (unødvendig) design/brugeroplevelse beslutning. Gør at spillet kan pause i et antal millisekunder før der blive printet mere
    public static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /*
    Ekstra (unødvendig) design/brugeroplevelse beslutning. Lidt ligesom pokémon spil på gameboy, hvor
    tekst printes på en anderledes måde
     */
    public void pokemonTekstPrint(String tekst, long ms) {
        for (int bogstaver = 0; bogstaver < tekst.length(); bogstaver++) {
            System.out.print(tekst.charAt(bogstaver));
            pause((int) ms);
        }
        System.out.println(" ");
    }

    //Stopper kørende lydklip
    public void stopAudio() {
        clip.stop();
    }

    //Afspiller et lydklip fra et en given filplacering
    public void afspilAudio(String filepath) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Lydfilen kan ikke afspilles.");
            e.printStackTrace();
        }
    }
}



