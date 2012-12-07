/**
 * Zusicherungen:
 * * Die Nummer des Traktors ist eindeutig und unveraenderlich
 * * Traktoren haben entweder Diesel- oder Biogas-Motoren
 * * Ein Traktor kann entweder nur zum Saeen oder nur zum Duengen verwendet werden
 */
@Authors("Dominik")
public abstract class Traktor {
	private final int nummer;
	private int betriebsstunden;
	protected Number bisherigerVerbrauch;
	private Einsatzzweck einsatzzweck;

	@Authors("Dominik")
	public Traktor(int nummer, Einsatzzweck einsatzzweck) {
		this.nummer = nummer;
		this.einsatzzweck = einsatzzweck;
		betriebsstunden = 0;
		bisherigerVerbrauch = 0;
	}

	@Authors("Dominik")
	public int getNummer() {
		return nummer;
	}

	@Authors("Dominik")
	public void erhoeheBetriebsstunden(int stunden) {
		this.betriebsstunden += stunden;
	}

	@Authors("Dominik")
	public int getBetriebsstunden() {
		return betriebsstunden;
	}

	@Authors("Dominik")
	public void setEinsatzzweck(Einsatzzweck einsatzzweck) {
		this.einsatzzweck = einsatzzweck;
	}

	@Authors("Dominik")
	public int getAnzahlSaeschare() {
		if (einsatzzweck != null && einsatzzweck instanceof Saeen) {
			return ((Saeen) einsatzzweck).getSaeSchare();
		}
		else {
			return 0;
		}
	}

	@Authors("Dominik")
	public double getFassungskapazitaet() {
		if (einsatzzweck != null && einsatzzweck instanceof Duengen) {
			return ((Duengen) einsatzzweck).getFassungskapazitaet();
		}
		else {
			return 0f;
		}
	}

	@Authors("Dominik")
	public boolean tutSaeen() {
		return einsatzzweck != null && einsatzzweck instanceof Saeen;
	}

	@Authors("Dominik")
	public boolean tutDuengen() {
		return einsatzzweck != null && einsatzzweck instanceof Duengen;
	}
}
