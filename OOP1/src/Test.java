import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import service.Manager;
import service.ServiceException;
import entity.Event;
import entity.Member;

/**
 * Fuehrt saemtliche Tests aus. Darf nur auf den Manager zugreifen.
 * 
 * @author Simon
 */
public class Test {

	private static Manager manager;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		manager = new Manager();
		Date start = new Date(System.currentTimeMillis());

		boolean result = true;
		System.out.println("Starte Tests (" + start + ")");

		try {
			if (!testMitglieder()) {
				result = false;
			}
		} catch (InterruptedException e) {
			System.out.println("INTERRUPTED");
			return;
		}

		if (!testProben()) {
			result = false;
		}
		if (!testAuftritte()) {
			result = false;
		}
		if (!testVerdienst()) {
			result = false;
		}

		if (result) {
			System.out.println("SUCCESS - ALL TESTS PASSED");
		}
		else {
			System.out.println("ERROR - TEST(S) FAILED");
		}

	}

	public static boolean testVerdienst()
	{
		boolean result = true;
		float money = manager.getEarnings(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime());
		if (money == 750 - 15)
		{
			System.out.print("PASSED - ");
		} else {
			System.out.print("FAILED - ");
			result = false;
		}
		System.out.println(" Verdienst (Gagen - Kosten) - soll=" + (750 - 15) + " ist=" + money);
		System.out.println("\n\n");
		return result;
	}

	public static boolean testAuftritte()
	{
		boolean result = true;

		System.out.println("-----------------------------------------------");
		System.out.println("AUFTRITTE TESTS");
		try {
			manager.addPerformance("Vienna Unplugged", "24.12.2012", "20:00", 60, 400);
			manager.addPerformance("Escape", "02.05.2012", "21:15", 90, 350);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Es wurden 2 Auftritte gepseichert: ");
		List<Event> performances = manager.listPerformances(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime());
		for (Event i : performances)
		{
			System.out.println(" " + i);
		}

		if (manager.getPerformanceSalary(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime()) == 750)
		{
			System.out.print("PASSED - ");
		} else {
			System.out.print("FAILED - ");
			result = false;
		}
		System.out.println("Gagen aller Auftritte - soll=750 ist="
				+ manager.getPerformanceSalary(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime()));

		System.out.println("AUFTRITTE TESTS ENDE");
		System.out.println("-----------------------------------------------");

		return result;

	}

	public static boolean testProben()
	{
		boolean result = true;

		System.out.println("-----------------------------------------------");
		System.out.println("PROBEN TESTS");

		System.out.println("....... Proben einf�gen");
		try {
			manager.addPractice("Garage von Jonny", "22.12.2012", "13:45", 90, 0);
			manager.addPractice("Keller von Jimmy", "11.11.2011", "11:11", 120, 3);
			manager.addPractice("Proberaum im 20.", "21.12.2012", "13:45", 90, 12);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("Es wurden 3 Proben gespeichert: ");

		List<Event> practices = manager.listPractices(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime());
		for (Event i : practices)
		{
			System.out.println(" " + i);
		}
		System.out.println("Proben im Jahr 2012:");
		practices = manager.listPractices(new GregorianCalendar(2012, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime());
		for (Event i : practices)
		{
			System.out.println(" " + i);
		}

		if (manager.getPracticeCosts(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime()) == 15)
		{
			System.out.print("PASSED - ");
		} else {
			System.out.print("FAILED - ");
			result = false;
		}
		System.out.println("Kosten aller Proben - soll=15 ist="
				+ manager.getPracticeCosts(new GregorianCalendar(2011, 1, 1).getTime(), new GregorianCalendar(2013, 1, 1).getTime()));
		System.out.println("PROBEN TESTS ENDE");
		System.out.println("-----------------------------------------------");

		return result;
	}

	public static boolean testMitglieder() throws InterruptedException
	{
		Date zwischenzeit;
		boolean result = true;
		System.out.println("-----------------------------------------------");
		System.out.println("MITGLIEDER TESTS");
		System.out.println("....... Fuege neue Mitglieder hinzu...");

		manager.addMember("Simon", "0900 666 666", "Gitarre");
		manager.addMember("John Wayne", "01/41414141", "Maultrommel");
		manager.addMember("Hans Wurst", "123456789", "Triangel");

		if (manager.listCurrentMembers().size() == 3) {
			System.out.print("PASSED - ");
		} else {
			System.out.print("FAILED - ");
			result = false;
		}
		System.out.println("Abfragen aktiver Mitglieder - soll=3 ist=" + manager.listCurrentMembers().size());

		//----------------------------------------------------------------------------------
		System.out.println("....... Warte 1 Sekunde...");

		Thread.sleep(1000);

		System.out.println("....... Zwischenzeit speichern");
		zwischenzeit = new Date(System.currentTimeMillis());
		System.out.println("....... Warte 1 Sekunde...");
		Thread.sleep(1000);
		//----------------------------------------------------------------------------------
		System.out.println("....... Loesche Mitglied 'John Wayne'");

		manager.removeMember("John Wayne");
		if (manager.listCurrentMembers().size() == 2) {
			System.out.print("PASSED - ");
		} else {
			System.out.print("FAILED - ");
			result = false;
		}
		System.out.println("Abfragen aktiver Mitglieder - soll=2 ist=" + manager.listCurrentMembers().size());

		if (manager.listMembers(zwischenzeit).size() == 3) {
			System.out.print("PASSED - ");
		} else {
			System.out.print("FAILED - ");
			result = false;
		}
		System.out.println("Abfrage der Mitglieder vor dem Loeschen - soll=3 ist=" + manager.listMembers(zwischenzeit).size()
				+ " ... (Mitglieder zum Zeitpunkt: " + zwischenzeit + ").");

		System.out.println("\nAuflisten von aktiven Mitgliedern: ");
		List<Member> member = manager.listCurrentMembers();
		for (Member it : member)
		{
			System.out.println(" " + it.toString());
		}
		System.out.println("\nMITGLIEDER TESTS ENDE\n-----------------------------------------------");
		return result;
	}

}
