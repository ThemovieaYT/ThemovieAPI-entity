package net.themoviea.themovieapi_energy.api;

public class EnergyAttribute {
	private EnergyType energyType = EnergyTypes.GRASS_ENERGY;
	private int maxEnergy;
	private int currentEnergy = 0;
	private int harm;
	
	private EnergyAttribute(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	
	public EnergyAttribute setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		if (currentEnergy > maxEnergy) currentEnergy = maxEnergy;
		return this;
	}
	
	public EnergyAttribute setCurrentEnergy(int amount) {
		if(amount <= maxEnergy) this.currentEnergy = amount;
		else this.currentEnergy = this.maxEnergy;
		return this;
	}

	public int getMaxEnergy() {
		return this.maxEnergy;
	}
	
	public int getCurrentEnergy() {
		return this.currentEnergy;
	}
	
	public boolean canInsertEnergy() {
		return true;
	}
	
	public int insertEnergy(EnergyType type, int amount) {
		
	}
	
	public void extractEnergy() {
		
	}
	
	public void getEnergyType() {
		
	}
}
