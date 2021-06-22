package net.themoviea.themovieapi_entity;

import com.mojang.serialization.Lifecycle;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.DefaultedRegistry;
import net.minecraft.util.registry.MutableRegistry;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class ThemovieAPIEntity implements ModInitializer {
	
	@Override
	public void onInitialize() {

		System.out.println("ThemovieAPI entity initialized!");
	}
}
