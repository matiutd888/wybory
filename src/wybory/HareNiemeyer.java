package wybory;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HareNiemeyer extends MetodaLiczenia {
    private static final String nazwa = "Metoda Hare'a-Niemeyera";

    public HareNiemeyer() {
        super(nazwa);
    }

    @Override
    public Map<Partia, Integer> znajdźLiczbęMandatów(Okręg okręg, Urna urna, List<Partia> partie) {
        int suma = 0;
        int S = okręg.getLiczbaMandatów();
        PriorityQueue<Pair<Partia, Double>> reszty = new PriorityQueue<>(partie.size(), new Comparator<Pair<Partia, Double>>() {
            @Override
            public int compare(Pair<Partia, Double> o1, Pair<Partia, Double> o2) {
                if (o1.second() < o2.second())
                    return 1;
                if (o1.second() > o2.second())
                    return -1;
                return 0;
            }
        });
        Map<Partia, Integer> mandaty = init(partie);
        int Vt = urna.getŁącznaLiczbaGłosów();
        double współczynnik = ((double) S) / Vt;
        for (Partia partia : partie) {
            double iloraz = urna.getGłosyPartii(partia) * współczynnik;
            int ileMandatów = (int) Math.floor(iloraz);
            mandaty.put(partia, ileMandatów);
            suma += ileMandatów;
            reszty.add(new Pair<>(partia, iloraz - ileMandatów));
        }
        int doRozdania = S - suma;
        while (!reszty.isEmpty() && doRozdania > 0) {
            Partia partia = reszty.poll().first();
            addMandat(mandaty, partia);
            doRozdania--;
        }
        return mandaty;
    }

}
