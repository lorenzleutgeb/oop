package entity;

import java.util.Date;

/**
 * Ein Bandmitglied.
 * 
 * @author Lorenz
 */
public class Member extends BandObject {
	/**
	 * Telefonnummer des Bandmitglieds.
	 */
	private String phoneNumber;

	/**
	 * Instrument, dass das Mitglied in der Band spielt.
	 */
	private String instrument;

	/**
	 * Erzeugt ein neues Mitglied einer Band mit angegebenen Eigenschaften.
	 * 
	 * @param name
	 *            Name des Mitglieds
	 * @param phoneNumber
	 *            Telefonnummer des Mitglieds.
	 * @param instrument
	 *            Instrument, dass das Mitglied in der Band spielt.
	 */
	public Member(String name, String phoneNumber, String instrument) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.instrument = instrument;
		this.start = new Date();
		this.end = null;
	}

	/**
	 * Gibt die Telefonnummer des Bandmitglieds zurueck.
	 * 
	 * @return die Telefonnummer des Bandmitglieds
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Gibt den Namen des Instruments zurueck, dass das Mitglied spielt.
	 * 
	 * @return den Namen des Instruments
	 */
	public String getInstrument() {
		return instrument;
	}

	@Override
	public String toString() {
		return name + " (" + phoneNumber + ") spielt(e) von " + start + " bis "
				+ (end != null ? end : "jetzt") + " " + instrument + ".";
	}
}