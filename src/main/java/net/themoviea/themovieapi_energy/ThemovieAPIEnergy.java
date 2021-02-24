package net.themoviea.themovieapi_energy;

import com.mojang.serialization.Lifecycle;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.themoviea.themovieapi_energy.api.EnergyType;

public class ThemovieAPIEnergy implements ModInitializer {
	
	public static final RegistryKey<Registry<EnergyType>> ENERGY_TYPE_KEY;
	public static final Registry<EnergyType> ENERGY_TYPE;
	
	static {
		ENERGY_TYPE_KEY = RegistryKey.ofRegistry(new Identifier("themovieapienergy", "energy_type"));
		MutableRegistry<EnergyType> temp = new DefaultedRegistry<>("themovieapienergy:grass_energy", ENERGY_TYPE_KEY, Lifecycle.experimental());
		ENERGY_TYPE = temp;
	}
	
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		System.out.println("ThemovieAPI energy initialized!");
	}
}
