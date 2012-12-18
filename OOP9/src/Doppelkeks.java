/**
 * Ein Doppelkeks besteht aus zwei (einfachen) Keksen, die durch eine Fuellung zusammengeklebt sind.
 * Die beiden Kekse müssen sich nicht gleichen, die Doppelkeksbackmaschine stellt jedoch nur solche
 * her, bei denen das der Fall ist.
 * 
 * Zusicherungen:
 * * Die Fuellung des Doppelkekses kann entweder Schokolade oder Marmelade sein.
 * 
 * @author Dominik
 * 
 */
public class Doppelkeks extends KeksBasis {
	private Fuellung fuellung;
	private Keks basis;
	private Keks deckel;

	public Doppelkeks(Fuellung fuellung, Keks basis, Keks deckel) {
		this.fuellung = fuellung;
		this.basis = basis;
		this.deckel = deckel;
	}
	
	@Override
	public String toString() {
		return new String("Doppelkeks bestehend aus: \n -"+basis.toString()+"\n -Fuellung: "+fuellung+"\n -"+deckel.toString());
	}
}
