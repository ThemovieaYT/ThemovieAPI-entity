package net.themoviea.themovieapi_entity.entity.ai.goals;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.Goal;
import net.themoviea.themovieapi_entity.entity.SimpleBrainEntity;
import net.themoviea.themovieapi_entity.entity.ai.SimpleBrain;

public class StorePOIGoal extends Goal {
	public final SimpleBrainEntity entity;
	public final BlockState state;
	
	public StorePOIGoal(SimpleBrainEntity entity, BlockState state) {
		this.entity = entity;
		this.state = state;
	}

	@Override
	public boolean canStart() {
		SimpleBrain brain = entity.getSimpleBrain();
		if(brain != null) {
			if(brain.pointOfInterest != null) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void start() {
		SimpleBrain brain = entity.getSimpleBrain();
		brain.searchPointOfInterest(state, entity.getEntityWorld(), 10, 10, 10);
		super.start();
	}

}
