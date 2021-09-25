package wybory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

public class Państwo {
    private final List<Okręg> scaloneOkręgi;
    private final List<Partia> partie;
    private final List<Działanie> działania;

    public Państwo(List<Okręg> okręgiNieScalone, List<Partia> partie, List<Działanie> działania, List<Pair<Integer, Integer>> paryDoScalenia) {
        this.scaloneOkręgi = scalOkręgi(paryDoScalenia, okręgiNieScalone);
        this.partie = List.copyOf(partie);
        this.działania = List.copyOf(działania);
    }

	//Scala okręgi, zwraca posortowaną numerami listę okręgów, w której okręgi, których numery
	//występują na liście 'paryDoScalenia' są reprezentowane przez jededn, scalony okręg.
    private List<Okręg> scalOkręgi(List<Pair<Integer, Integer>> paryDoScalenia, List<Okręg> okręgi) {
        boolean czyJużScalono[] = new boolean[okręgi.size()];
        ArrayList<Okręg> scaloneOkręgi = new ArrayList<>(okręgi.size());
        for (Pair<Integer, Integer> para : paryDoScalenia) {
            int f = para.first();
            int s = para.second();
            scaloneOkręgi.add(okręgi.get(f - 1).scalOkręgi(okręgi.get(s - 1)));
            czyJużScalono[f - 1] = true;
            czyJużScalono[s - 1] = true;
        }
        for (int i = 0; i < czyJużScalono.length; i++) {
            if (!czyJużScalono[i])
                scaloneOkręgi.add(okręgi.get(i));
        }
        Collections.sort(scaloneOkręgi, new Comparator<Okręg>() {
			@Override
			public int compare(Okręg o1, Okręg o2) {
				if (o1.getNumerOkręgu() > o2.getNumerOkręgu())
					return 1;
				if (o1.getNumerOkręgu() < o2.getNumerOkręgu())
					return -1;
				return 0;	
			}
		});
		
        return scaloneOkręgi;
    }

	//Funkcja w której partie po kolei wykonują jedno wybrane działanie,
	//(w wybranym przez siebie okręgu, zgodnie z ich strategiami),
	//do czasu, aż zabraknie wszystkim budżetu.
    public void przeprowadźKampanię() {
        boolean czyKoniec = false;
        while (!czyKoniec) {
            boolean czyJestPartiaZBudżetem = false;
            for (Partia partia : partie) {
                boolean res = partia.ruchKampanijny(new ArrayList<>(scaloneOkręgi), new ArrayList<>(działania));
                if (res)
                    czyJestPartiaZBudżetem = true;
            }
            czyKoniec = !czyJestPartiaZBudżetem;
        }
    }

    public Map<Okręg, Urna> przeprowadźGłosowania() {
        Map<Okręg, Urna> głosy = new HashMap<>();

        for (Okręg okręg : scaloneOkręgi) {
            Urna urna = okręg.zbierzGłosy();
            głosy.put(okręg, urna);
        }
        return głosy;
    }

    public void policzGłosyIWyświetlWyniki(MetodaLiczenia metodaLiczenia, Map<Okręg, Urna> głosy) {
        List<Integer> wszystkieMandaty = new ArrayList<>(partie.size());
        for (Partia partia : partie)
            wszystkieMandaty.add(0);

        Wypisywator wypisywator = new Wypisywator();
        System.out.println(metodaLiczenia.getNazwa());
        for (Okręg okręg : scaloneOkręgi) {
            System.out.println(okręg.getNumerOkręgu());
            Urna urna = głosy.get(okręg);
            okręg.wyświetlUrnę(urna, wypisywator);
            Map<Partia, Integer> mandaty = metodaLiczenia.znajdźLiczbęMandatów(okręg, urna, new ArrayList<>(partie));
            wypisywator.wyświetlMandaty(new ArrayList<>(partie), mandaty);
            for (int i = 0; i < partie.size(); i++) {
                Partia partia = partie.get(i);
                wszystkieMandaty.set(i, mandaty.get(partia) + wszystkieMandaty.get(i));
            }
        }
        for (int i = 0; i < partie.size(); i++)
            System.out.println(partie.get(i).getNazwa() + " " + wszystkieMandaty.get(i));
    }

    public List<Okręg> getScaloneOkręgi() {
        return new ArrayList<>(scaloneOkręgi);
    }
}
