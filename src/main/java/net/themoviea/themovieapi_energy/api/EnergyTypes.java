package net.themoviea.themovieapi_energy.api;

import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.themovieapi_energy.ThemovieAPIEnergy;

public class EnergyTypes {
	public static final EnergyType GRASS_ENERGY = register("grass_energy", new EnergyType(Material.PLANT, 0, 20));
	
	public static void init() {
		
	}
	
	public static EnergyType register(String name, EnergyType type) {
		Registry.register(ThemovieAPIEnergy.ENERGY_TYPE, new Identifier("themovieapienergy", name), type);
		return type;
	}
}
