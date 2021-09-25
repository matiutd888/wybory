package wybory;

//Reprezentuje wyborcę porównującego kandydatów na podstawie jednej,
//konkretnej cechy.
public abstract class WyborcaJednocechowy extends WyborcaIterujący {
    private final Typ typ;
    private final int numerCechy;

    public WyborcaJednocechowy(String imię, String nazwisko, Typ typ, int numerCechy) {
        super(imię, nazwisko);
        this.typ = typ;
        this.numerCechy = numerCechy;
    }

    @Override
    protected int compareKandydatów(Kandydat k1, Kandydat k2) {
        int c1 = k1.getWartośćCechy(numerCechy);
        int c2 = k2.getWartośćCechy(numerCechy);
        return typ.compare(c1, c2);
    }

    @Override
    public int getZmianaWOcenieKandydata(Kandydat kandydat, Działanie działanie) {
        return 0;
    }

    @Override
    public void zastosujDziałanie(Działanie działanie) {
        ;
    }

    //Wyborca jednocechowy może wybierać kandydatów minimalizując
    //lub maksymalizując wartość interesującej go cechy wśród kandydatów.
    //Enum 'Typ' reprezentuje ów możliwości.
    //Udostępnia metodę 'compare' porównującą dwie liczby w sposób
    //różniący się dla typu 'Maksymalizującego' i 'Minimalizującego'.
    public static enum Typ {
        MAKSYMALIZUJĄCY(1), MINIMALIZUJĄCY(-1);
        //Liczba pomocnicza służąca do porównywania liczb,
        //przyjmuje wartość większą od zera dla typu maksymalizującego
        //i mniejszą od zera dla typu minimalizującego
        private int współczynnik;

        private Typ(int współczynnik) {
            this.współczynnik = współczynnik;
        }

        public int compare(int a, int b) {
            if (a * współczynnik > b * współczynnik)
                return 1;
            if (a * współczynnik < b * współczynnik)
                return -1;
            return 0;
        }
    }
}
