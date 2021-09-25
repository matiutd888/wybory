package wybory;

import java.util.List;

public class Partia {
    private final String nazwa;
    private final Strategia strategia;
    private int budżet;
    
    //Każda partia ma ściśle zdefiniowany numer listy.
    //Odpowiada on kolejności, w jakiej opis odpowiedniej partii pojawia się na wejściu.
    private int numerListy;

    public Partia(String nazwa, int budżet, Strategia strategia, int numerListy) {
        this.nazwa = nazwa;
        this.budżet = budżet;
        this.strategia = strategia;
        this.numerListy = numerListy;
    }

    public int getBudżet() {
        return budżet;
    }

    public void wydajPieniądze(int cena) {
        budżet -= cena;
    }

    public int getNumerListy() {
        return numerListy;
    }

    //Zwraca wartość 'true' jeśli udało się znaleźć i wykonać
    //działanie na jakie pozwala budżet,
    //wartość 'false w przeciwnym przypadku.
    public boolean ruchKampanijny(List<Okręg> okręgi, List<Działanie> działania) {
        return strategia.wybierzIZastosujDziałanie(this, okręgi, działania);
    }

    public String getNazwa() {
        return nazwa;
    }

    @Override
    public String toString() {
        return "Partia{" +
                "nazwa=" + nazwa +
                ", budżet=" + budżet +
                ", numerListy=" + numerListy +
                ", strategia=" + strategia +
                '}';
    }

}
