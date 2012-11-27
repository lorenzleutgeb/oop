public class ANGesellschafter extends ANBediener {

	public ANGesellschafter(Integer ID) {
		super(ID);
		installer.put(SWSecurityLevel.LEVEL2, new SWRejecter());
		typ="Gesellschafter";
	}

	@Override
	public void installSoftware(SWSoftware software) {
		software.installSoftwareOnGesellschafter(this, softwareStorage, installer.get(software.getSecurityLevel()));
	}

}
