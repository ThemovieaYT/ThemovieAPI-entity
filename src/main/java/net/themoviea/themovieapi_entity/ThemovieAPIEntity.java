package net.themoviea.themovieapi_entity;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.themoviea.themovieapi_entity.entity.TestEntity;

public class ThemovieAPIEntity implements ModInitializer {
	public static final EntityType<TestEntity> TEST_ENTITY = Registry.register(Registry.ENTITY_TYPE, new Identifier("themovieapientity", "test_entity"), FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TestEntity::new).dimensions(EntityDimensions.fixed(0.6f, 1.8f)).build());
	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(TEST_ENTITY, TestEntity.createMobAttributes());
	}
}
