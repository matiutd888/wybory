package wybory;

/*
4. Maksymalizujący jednocechowy wybiera tego spośród kandydatów wszystkich partii,
który ma najwyższy poziom wybranej przez niego cechy (jeśli taką wartość ma więcej
niż 1 kandydat, to wybór kandydata jest losowy).
 */
public class MaksymalizującyJednocechowy extends WyborcaJednocechowy {
    public MaksymalizującyJednocechowy(String imię, String nazwisko, int numerCechy) {
        super(imię, nazwisko, Typ.MAKSYMALIZUJĄCY, numerCechy);
    }

    @Override
    public String toString() {
        return "MaksymalizującyJednocechowy{" +
                super.toString() +
                '}';
    }
}
