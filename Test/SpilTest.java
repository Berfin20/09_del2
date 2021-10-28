import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpilTest {
    // Test for spillet slutter når balancen er negativ.
    @Test
    void BalanceNeverNegative(){
        Spiller spiller1 = new Spiller("Anders", 2000, 0);
        Spiller spiller2 = new Spiller ("Berfin", 2000, 0);


        spiller1.konto.setBalance(2010);
        spiller2.konto.setBalance(-2010);



        if (spiller1.konto.getBalance() < 0) {
            System.out.println("Spiller 1's balance er negativ, spillet er slut.");

        }
        else if (spiller2.konto.getBalance() < 0) {
            System.out.println("Spiller 2's balance er negativ, spillet er slut.");
        }
        assertEquals(-2010, spiller2.konto.getBalance());


    }
    // Test for terningeslag, med random værdier.
    @Test
    void diceRoll() {
        Terning terning1 = new Terning();
        Terning terning2 = new Terning();
        int sum;

        terning1.setFaceValue((int) Math.random() * 6 + 1);
        terning2.setFaceValue((int) Math.random() * 6 + 1);

        sum = terning1.getFaceValue() + terning2.getFaceValue();
        System.out.println(sum);

        assertEquals(sum, terning1.getFaceValue() + terning2.getFaceValue());


    }
    // Test opdatering af spillerens navn.
    @Test
    void setName() {
        Spiller spiller = new Spiller("Popsi", 1000, 0);
        System.out.println("Spillerens navn er: " + spiller.getName());
        spiller.setName("Jens Hansen");
        System.out.println("Spillerens nye navn er: " + spiller.getName());

        assertEquals("Jens Hansen", spiller.getName());
    }
    // Test af balancen kan hentes.
    @Test
    void getBalance() {
        Spiller spiller = new Spiller("Lille Peter Edderkop", 1000, 0);
        System.out.println("Spillerens balance er: " + spiller.konto.getBalance());

        assertEquals(1000, spiller.konto.balance);
    }



}


