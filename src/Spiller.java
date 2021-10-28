public class Spiller {
    //Attributter
    private String name;
    private int position;
    //Så hver spiller har en konto. Kontoen kan altså tilgås gennem hver spiller
    Konto konto = new Konto();

    //Constructor for at initalisere attributter
    public Spiller (String name, int balance, int position) {
        this.name = name;
        this.konto.setBalance(balance);
        this.position = position;
    }

    //getters og setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    //Metode til at opdatere en spillers position ved at bruge den nuværende position og de slåede terningers sum-værdi
    public int updatePosition(int facevalues, int currentPosition){
        int newPosition;
        newPosition = facevalues+currentPosition;
        setPosition(newPosition);

        return position;
    }
}
