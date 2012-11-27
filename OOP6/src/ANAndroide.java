import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public abstract class ANAndroide {
	private SKSkin skin;
	protected SWSoftwareStorage softwareStorage;
	private Integer ID;
	protected Map<SWSecurityLevel, SWInstaller> installer = new HashMap<SWSecurityLevel, SWInstaller>();
	private ArrayList<String> history=new ArrayList<String>();

	public ANAndroide(Integer ID)
	{
		this.ID = ID;
		softwareStorage = new SWSoftwareStorage();
		installer.put(SWSecurityLevel.LEVEL1, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL2, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL3, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL4, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL5, new SWRejecter());
	}

	public abstract void checkHauptTyp(ANAndroide a);

	protected abstract void checkHauptTypFromBediener();
	protected abstract void checkHauptTypFromSchwerarbeiter();
	protected abstract void checkHauptTypFromBeschuetzer();


	public void checkSoftwareLevel(SWSecurityLevel securityLevel) {
		installer.get(securityLevel).validateAndroide(this);
	}

	public void setSkin(SKSkin skin)
	{
		this.skin = skin;
	}

	public SKSkin getSkin()
	{
		return skin;
	}

	public Integer getID()
	{
		return ID;
	}

	public void setInvalid()
	{
		this.ID = null;
	}

	/**
	 * prueft, ob der angelegte Skin der Androiden-Verordnung entspricht
	 */
	public abstract void checkSkin();

	@Override
	public String toString()
	{
		return new String("ID-" + ID + "; Skin-" + skin + "; Software-" + softwareStorage.getSoftware());
	}

	public abstract void installSoftware(SWSoftware software);

	public void limitSecurityLevel(SWSecurityLevel securityLevel) {
		installer.put(SWSecurityLevel.LEVEL1, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL2, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL3, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL4, new SWRejecter());
		installer.put(SWSecurityLevel.LEVEL5, new SWRejecter());

		installer.put(securityLevel, new SWInstaller());
	}
	
	
	/**
	 * Speichert die konfiguartion des Uebergebenen Androiden in die History.
	 * @param a 
	 */
	protected void addToHistory(ANAndroide a)
	{
		history.add(a.toString());
	}

	/**
	 * Kopiert die History des uebergebenen Androiden und fuegt die eigene Konfiguration dazu.
	 * @param a alte History
	 */
	protected void copyHistory(ANAndroide a)
	{
		this.history=a.history;
		this.addToHistory(this);
	}
	
	public void printHistory()
	{
		StringBuilder sb=new StringBuilder();
		for(String it:history)
		{
			sb.append(it);
			sb.append(System.getProperty("line.separator"));
		}
		System.out.println(sb.toString());
	}
}