package net.themoviea.themovieapi_entity.entity;

import net.fabricmc.loader.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import net.themoviea.themovieapi_entity.entity.ai.goals.GoToPOIGoal;
import net.themoviea.themovieapi_entity.entity.ai.goals.RandomGoal;
import net.themoviea.themovieapi_entity.entity.ai.goals.StorePOIGoal;

public class TestEntity extends SimpleBrainEntity {

	public TestEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}
	
	@Override
	protected void initGoals() {
		this.goalSelector.add(2, new StorePOIGoal(this, Blocks.FLETCHING_TABLE.getDefaultState()));
		this.goalSelector.add(1, new GoToPOIGoal(this));
	}
	
	@Override
	protected void initScheduledGoals() {
		//this.customGoalSelector.addScheduled(1000, 20, new StorePOIGoal(this, Blocks.FLETCHING_TABLE.getDefaultState()));
		//this.customGoalSelector.addScheduled(1020, 80, new GoToPOIGoal(this));
	}
	
	@SuppressWarnings({ "deprecation", "resource" })
	@Override
	public void tick() {
		super.tick();
		System.out.println(this.getSimpleBrain().pointOfInterest.toString());
	}

}
