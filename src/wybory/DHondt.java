package wybory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class DHondt extends MetodaLiczenia {
    private static final String nazwa = "Metoda D'Hondta";

    public DHondt() {
        super(nazwa);
    }

    @Override
    public Map<Partia, Integer> znajdźLiczbęMandatów(Okręg okręg, Urna urna, List<Partia> partie) {
        int liczbaPartii = partie.size();
        int liczbaMandatów = okręg.getLiczbaMandatów();
        List<IlorazWyborczy> ilorazy = new ArrayList<>(liczbaMandatów * liczbaPartii);
        for (Partia partia : partie) {
            int G = urna.getGłosyPartii(partia);
            for (int i = 0; i < liczbaMandatów; i++) {
                double wartość = ((double) G) / (i + 1);
                ilorazy.add(new IlorazWyborczy(wartość, partia));
            }
        }
        Collections.sort(ilorazy, IlorazWyborczy.comparator);
        Map<Partia, Integer> mandaty = init(partie);
        for (int i = 0; i < liczbaMandatów; i++)
            addMandat(mandaty, ilorazy.get(i).getPartia());
        return mandaty;
    }
}
