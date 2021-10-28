import java.util.ArrayList;

public class Felt {

    //Attributter
    private String name;
    private int influenceOnBalance;
    private boolean extraTurn;
    private int position;
    private int id;
    private String description;

    //Constructor for at initialisere attributter
    public Felt(int id, String name, int influenceOnBalance, boolean extraTurn, int position, String description){
        this.id = id;
        this.name = name;
        this.influenceOnBalance = influenceOnBalance;
        this.extraTurn = extraTurn;
        this.position = position;
        this.description = description;
    }

//    public ArrayList<Felt> createFelts(){
//        ArrayList<Felt> felter = new ArrayList<Felt>();
//
//        Felt maldiverne = new Felt(0, "Maldiverne", 0, false, 1, "Velkommen til maldiverne. Du har nu mistet hele din formue. Din balance er på: ");
//        Felt tower = new Felt(1, "tower", 250, false, 2, "Du har fundet 250kr i tårnet! Din balance er på: ");
//        Felt crater = new Felt(2, "Crater", -100, false, 3, "Øv, du har tabt 100kr i krateret. Din balance er på: ");
//        Felt palace_gates = new Felt(3, "Palace Gates", 100, false, 4, "Du har lige fundet 100kr ved paladsets porte! Din balance er på: ");
//        Felt cold_desert = new Felt(4, "Cold Desert", -20, false, 5, "Du har mistet 20kr i den kolde ørken.. Din balance er på: ");
//        Felt walled_city = new Felt(5, "Walled City", 180, false, 6,"Du har lige fundet 180kr i Walled City! Din balance er på: ");
//        Felt monastery = new Felt(6, "Monastery", 0, false, 7, "Du har hverken mistet eller fundet noget i klosteret.. Heldigt eller uheldigt? Din balance er på: ");
//        Felt black_cave = new Felt(7, "Black Cave", -70, false, 8, "Du har gjort 70kr væk i den sorte hule.. Din balance er på: ");
//        Felt huts_in_the_mountain = new Felt(8, "Huts In The Mountain", 60, false, 9, "Du har fundet 60kr i hytterne på bjerget! Din balance er på: ");
//        Felt the_werewall = new Felt(9, "The Werewall", -80, true, 10, "Du har fået stjålet 80kr ved varulve-væggen.. Din balance er på: ");
//        Felt the_pit = new Felt(10, "The Pit", -50, false, 11,"Har du lige tabt 50kr i hullet?.. Det var godt nok uheldigt.. Din balance er på: ");
//        Felt goldmine = new Felt(11, "Goldmine", 650, false, 12, "Du har fundet guld til 650kr i minen! Din balance er på: ");
//
//        felter.add(maldiverne);
//        felter.add(tower);
//        felter.add(crater);
//        felter.add(palace_gates);
//        felter.add(cold_desert);
//        felter.add(walled_city);
//        felter.add(monastery);
//        felter.add(black_cave);
//        felter.add(huts_in_the_mountain);
//        felter.add(the_werewall);
//        felter.add(the_pit);
//        felter.add(goldmine);
//
//        return felter;
//    }

    //getters og setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInfluenceOnBalance() {
        return influenceOnBalance;
    }

    public void setInfluenceOnBalance(int influenceOnBalance) {
        this.influenceOnBalance = influenceOnBalance;
    }

    public boolean getExtraTurn() {
        return extraTurn;
    }

    public void setExtraTurn(boolean extraTurn) {
        this.extraTurn = extraTurn;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
