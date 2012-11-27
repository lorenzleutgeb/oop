public abstract class ANBeschuetzer extends ANAndroide {

	public ANBeschuetzer(Integer ID) {
		super(ID);
		installer.put(SWSecurityLevel.LEVEL4, new SWInstaller());
	}

	@Override
	public void checkSkin()
	{
		getSkin().vonBeschuetzerGetragen(this);
	}

	@Override
	public void checkHauptTyp(ANAndroide a)
	{
		a.checkHauptTypFromBeschuetzer();
	}

	@Override
	protected void checkHauptTypFromBediener()
	{
		System.out.println("! Haupttyp darf nicht veraendert werden. (setze ID auf null)");
		this.setInvalid();

	}

	@Override
	protected void checkHauptTypFromSchwerarbeiter()
	{
		System.out.println("! Haupttyp darf nicht veraendert werden. (setze ID auf null)");
		this.setInvalid();
	}

	@Override
	protected void checkHauptTypFromBeschuetzer()
	{
		System.out.println("OK - Haupttyp unveraendert");
	}
}