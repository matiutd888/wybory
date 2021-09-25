package wybory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MetodaLiczenia {
    private final String nazwa;

    public MetodaLiczenia(String nazwa) {
        this.nazwa = nazwa;
    }

    public abstract Map<Partia, Integer> znajdźLiczbęMandatów(Okręg okręg, Urna urna, List<Partia> partie);

    //Dodaje partii 'partia' mandat w mapie 'mandaty'
    protected void addMandat(Map<Partia, Integer> mandaty, Partia partia) {
        int liczbaMandatów = mandaty.get(partia);
        mandaty.put(partia, liczbaMandatów + 1);
    }

    //Iniciuje mapę reprezentującą mandaty
    protected Map<Partia, Integer> init(List<Partia> partie) {
        Map<Partia, Integer> głosy = new HashMap<>();
        for (Partia partia : partie)
            głosy.put(partia, 0);
        return głosy;
    }

    public String getNazwa() {
        return nazwa;
    }
}
