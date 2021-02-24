package net.themoviea.themovieapi_energy.api;

import java.util.Optional;

import net.minecraft.block.Material;
import net.minecraft.text.TranslatableText;

public class EnergyType {
	private Material material;
	private int maxTransferSize, minimumTransferSize;
	
	public EnergyType(Material material, int minimumTransferSize, int maxTransferSize) {
		this.material = material;
		this.minimumTransferSize = minimumTransferSize;
		this.maxTransferSize = maxTransferSize;
	}
	
	int getMinimumTransferSize() {
		return this.minimumTransferSize;
	}
	
	public int getMaximumTransferSize() {
		return this.maxTransferSize;
	}
	
	/** @return the material of the energy*/
	public Material getMaterial() {
		return this.material;
	}
	
	public TranslatableText getDisplayAmount(int amount) {
		return new TranslatableText("info.themovieapi.energy.amount", amount);
	}
	
	public boolean isHarmful(EnergyType type) {
		if(type.getMaterial() == Material.LAVA) {
			return true;
		}
		
		return false;
	}
	
	public boolean isCompatibleWith(EnergyType type) {
		return type == this;
	}
	
	public int convertFrom(EnergyType type, int amount) {
		return (type == this) ? amount : 0;
	}
	
	public int convertTo(EnergyType type, int amount) {
		return (type == this) ? amount : 0;
	}
	
	public Optional<Integer> convert(EnergyType sourceType, EnergyType targetType, int sourceAmount) {
		if(sourceType.isCompatibleWith(targetType)) {
			return Optional.of(sourceType.convertTo(targetType, sourceAmount));
		}
		if(targetType.isCompatibleWith(sourceType)) {
			return Optional.of(targetType.convertFrom(sourceType, sourceAmount));
		}
		return Optional.empty();
	} 
}
