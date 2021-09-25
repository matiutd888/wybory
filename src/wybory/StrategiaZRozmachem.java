package wybory;

public class StrategiaZRozmachem extends Strategia {
    @Override
    public String toString() {
        return "R";
    }

    @Override
    protected boolean compare(Partia partia, Działanie d1, Okręg o1, Działanie d2, Okręg o2) {
        return d1.getCenaZadziałaniaNaOkręgu(o1) > d2.getCenaZadziałaniaNaOkręgu(o2);
    }
}
