public class Terning {
    //Attributter
    private int faceValue;
    private int faceValueSum;

    //toString metode for at printe terningeslagene korrekt
    @Override
    public String toString() {
        return "Terningeslaget er:" + faceValue;

    }

    //getters og setters
    public int getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(int faceValue) {
        this.faceValue = faceValue;
    }

    public int getFaceValueSum() {
        return faceValueSum;
    }

    public void setFaceValueSum(int faceValueSum) {
        this.faceValueSum = faceValueSum;
    }

    //Metode til at slå med terningerne. Der returneres et terninge par (2 int værdier)
    public TerningePar diceRoll (){
        Terning terning1 = new Terning();
        Terning terning2 = new Terning();

        terning1.setFaceValue((int)(Math.random()*6)+1);
        terning2.setFaceValue ((int)(Math.random()*6)+1);

        setFaceValueSum(terning1.getFaceValue()+terning2.getFaceValue());
        return new TerningePar (terning1.getFaceValue(), terning2.getFaceValue());




    }

    //inder klasse til Terning
    private static class TerningePar {

        //Attributter
        private int t1;
        private int t2;

        //toString metode til at printe terning-parret korrekt i stedet for en mærkelig "@fjrhj4873" værdi
        @Override
        public String toString() {
            return t1+ " og "+t2;
        }

        //Constructor til at initialiserer attributter
        public TerningePar (int t1, int t2){

            this.t1=t1;
            this.t2=t2;


        }
    }
}
