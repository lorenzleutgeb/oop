/**
 * Zusicherungern:
 * * Der Name des Bauernhofs ist eindeutig und unveraenderlich
 */
@Authors("Dominik")
public class Bauernhof {
	private final String name;
	private TraktorenListe traktoren = new TraktorenListe();

	@Authors("Dominik")
	public Bauernhof(String name) {
		this.name = name;
	}

	@Authors("Dominik")
	public String getName() {
		return name;
	}

	@Authors("Dominik")
	public void addTraktor(Traktor traktor) {
		traktoren.add(traktor);
	}

	@Authors("Dominik")
	public void removeTraktor(Traktor traktor) {
		traktoren.remove(traktor);
	}

	@Authors("Dominik")
	public void erhoeheDieselVerbrauch(int nummer, Integer diesel) {
		Traktor traktor = traktoren.get(nummer);
		if (traktor != null && traktor instanceof DieselTraktor) {
			((DieselTraktor) traktor).erhoeheVerbrauch(diesel);
		}
	}

	@Authors("Dominik")
	public void erhoeheBiogasVerbrauch(int nummer, Float biogas) {
		Traktor traktor = traktoren.get(nummer);
		if (traktor != null && traktor instanceof BiogasTraktor) {
			((BiogasTraktor) traktor).erhoeheVerbrauch(biogas);
		}
	}

	@Authors("Dominik")
	public void setEinsatzzweck(int nummer, Einsatzzweck einsatzzweck) {
		Traktor traktor = traktoren.get(nummer);
		if (traktor != null) {
			traktor.setEinsatzzweck(einsatzzweck);
		}
	}

	@Authors("Dominik")
	public float getDurchschnittBetriebsstunden(EinsatzzweckGruppierung gruppierung) {
		boolean saeen = gruppierung.equals(EinsatzzweckGruppierung.Gesamt) || gruppierung.equals(EinsatzzweckGruppierung.Saeen);
		boolean duengen = gruppierung.equals(EinsatzzweckGruppierung.Gesamt) || gruppierung.equals(EinsatzzweckGruppierung.Duengen);
		int summe = 0;
		int anzahl = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if ((traktor.tutDuengen() && duengen) || (traktor.tutSaeen() && saeen)) {
				anzahl += 1;
				summe += traktor.getBetriebsstunden();
			}
		} while ((current = current.getNext()) != null);

		return ((float) summe) / anzahl;
	}

	@Authors("Dominik")
	public float getDurchschnittBetriebsstunden(TraktorGruppierung gruppierung) {
		boolean diesel = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Diesel);
		boolean biogas = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Biogas);
		int summe = 0;
		int anzahl = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if ((traktor instanceof DieselTraktor && diesel) || (traktor instanceof BiogasTraktor && biogas)) {
				anzahl += 1;
				summe += traktor.getBetriebsstunden();
			}
		} while ((current = current.getNext()) != null);

		return ((float) summe) / anzahl;
	}

	@Authors("Dominik")
	public float getDurchschnittDieselverbrauch(EinsatzzweckGruppierung gruppierung) {
		boolean saeen = gruppierung.equals(EinsatzzweckGruppierung.Gesamt) || gruppierung.equals(EinsatzzweckGruppierung.Saeen);
		boolean duengen = gruppierung.equals(EinsatzzweckGruppierung.Gesamt) || gruppierung.equals(EinsatzzweckGruppierung.Duengen);
		int summe = 0;
		int anzahl = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if (traktor instanceof DieselTraktor && ((traktor.tutDuengen() && duengen) || (traktor.tutSaeen() && saeen))) {
				anzahl += 1;
				summe += ((DieselTraktor) traktor).getBisherigerVerbrauch();
			}
		} while ((current = current.getNext()) != null);

		return ((float) summe) / anzahl;
	}

	@Authors("Dominik")
	public float getDurchschnittBiogasverbrauch(EinsatzzweckGruppierung gruppierung) {
		boolean saeen = gruppierung.equals(EinsatzzweckGruppierung.Gesamt) || gruppierung.equals(EinsatzzweckGruppierung.Saeen);
		boolean duengen = gruppierung.equals(EinsatzzweckGruppierung.Gesamt) || gruppierung.equals(EinsatzzweckGruppierung.Duengen);
		float summe = 0;
		int anzahl = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if (traktor instanceof BiogasTraktor && ((traktor.tutDuengen() && duengen) || (traktor.tutSaeen() && saeen))) {
				anzahl += 1;
				summe += ((BiogasTraktor) traktor).getBisherigerVerbrauch();
			}
		} while ((current = current.getNext()) != null);

		return summe / anzahl;
	}

	@Authors("Dominik")
	public int getMinimumSaescharen(TraktorGruppierung gruppierung) {
		boolean diesel = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Diesel);
		boolean biogas = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Biogas);
		int minimum = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if ((traktor instanceof DieselTraktor && diesel) || (traktor instanceof BiogasTraktor && biogas)) {
				minimum = Math.min(minimum, traktor.getAnzahlSaeschare());
			}
		} while ((current = current.getNext()) != null);

		return minimum;
	}

	@Authors("Dominik")
	public int getMaximumSaescharen(TraktorGruppierung gruppierung) {
		boolean diesel = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Diesel);
		boolean biogas = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Biogas);
		int maximum = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if ((traktor instanceof DieselTraktor && diesel) || (traktor instanceof BiogasTraktor && biogas)) {
				maximum = Math.max(maximum, traktor.getAnzahlSaeschare());
			}
		} while ((current = current.getNext()) != null);

		return maximum;
	}

	@Authors("Dominik")
	public float getDurchschnittFassungskapazitaet(TraktorGruppierung gruppierung) {
		boolean diesel = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Diesel);
		boolean biogas = gruppierung.equals(TraktorGruppierung.Gesamt) || gruppierung.equals(TraktorGruppierung.Biogas);
		float summe = 0;
		int anzahl = 0;

		Bauernhof.TraktorenListe.TraktorEintrag current = traktoren.getHead();
		do {
			Traktor traktor = current.getTraktor();
			if ((traktor instanceof DieselTraktor && diesel) || (traktor instanceof BiogasTraktor && biogas)) {
				anzahl += 1;
				summe += traktor.getFassungskapazitaet();
			}
		} while ((current = current.getNext()) != null);

		return summe / anzahl;
	}

	/**
	 * Hier wurde das Interface Iterable nicht implementiert, da Generizitaet laut Angabe verboten ist.
	 * 
	 * Zusicherungen:
	 * * Jeder Traktor darf nur einmal in der Liste vorkommen (Nummer ist eindeutig)
	 */
	@Authors("Dominik")
	public class TraktorenListe {
		TraktorEintrag head = null;

		@Authors("Dominik")
		public Traktor get(int nummer) {
			TraktorEintrag current = head;
			do {
				if (current.getTraktor().getNummer() == nummer) {
					return current.getTraktor();
				}
			} while ((current = current.getNext()) != null);
			return null;
		}

		@Authors("Dominik")
		public void add(Traktor traktor) {
			if (head != null) {
				if (!contains(traktor)) {
					TraktorEintrag newHead = new TraktorEintrag(traktor);
					newHead.add(head);
					head = newHead;
				}
			} else {
				head = new TraktorEintrag(traktor);
			}
		}

		@Authors("Dominik")
		public void remove(Traktor traktor) {
			if (head.getTraktor().getNummer() != traktor.getNummer()) {
				TraktorEintrag current = head;
				TraktorEintrag last = null;
				while ((current = current.getNext()) != null) {
					if (current.getTraktor().getNummer() == traktor.getNummer()) {
						last.setNext(current.getNext());
						return;
					}
					last = current;
				}
			} else {
				head = head.getNext();
			}
		}

		@Authors("Dominik")
		private boolean contains(Traktor traktor) {
			if (head != null) {
				TraktorEintrag current = head;
				do {
					if (current.getTraktor().getNummer() == traktor.getNummer()) {
						return true;
					}
				} while ((current = current.getNext()) != null);
				return false;
			} else {
				return false;
			}
		}

		@Authors("Dominik")
		public TraktorEintrag getHead() {
			return head;
		}

		@Authors("Dominik")
		public class TraktorEintrag {
			private Traktor traktor;
			private TraktorEintrag next = null;

			@Authors("Dominik")
			public TraktorEintrag(Traktor traktor) {
				this.traktor = traktor;
			}

			@Authors("Dominik")
			public Traktor getTraktor() {
				return traktor;
			}

			@Authors("Dominik")
			public TraktorEintrag getNext() {
				return next;
			}

			@Authors("Dominik")
			public void add(TraktorEintrag traktorEintrag) {
				if (next == null) {
					next = traktorEintrag;
				} else {
					next.add(traktorEintrag);
				}
			}

			@Authors("Dominik")
			public void setNext(TraktorEintrag traktorEintrag) {
				next = traktorEintrag;
			}
		}
	}
}