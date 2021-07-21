package net.themoviea.themovieapi_entity.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.world.World;
import net.themoviea.themovieapi_entity.entity.ai.CustomGoalSelector;
import net.themoviea.themovieapi_entity.entity.ai.SimpleBrain;

public class SimpleBrainEntity extends PathAwareEntity {
	private SimpleBrain brain;
	protected final CustomGoalSelector customGoalSelector;
	
	protected SimpleBrainEntity(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
		this.initializeBrain();
		this.customGoalSelector = new CustomGoalSelector(world.getProfilerSupplier());
	}
	
	protected void initScheduledGoals() {
		
	}

	private void initializeBrain() {
		SimpleBrain brain = new SimpleBrain(this);
		this.brain = brain;
	}
	
	public SimpleBrain getSimpleBrain() {
		return this.brain;
	}
	
	@Override
	public void tickMovement() {
		this.tickNewAi();
		if(this != null) {
			this.customGoalSelector.tick(this.world);
		}
	}
}
