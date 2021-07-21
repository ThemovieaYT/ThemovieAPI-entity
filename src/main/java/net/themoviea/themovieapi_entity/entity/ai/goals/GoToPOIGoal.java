package net.themoviea.themovieapi_entity.entity.ai.goals;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.BlockPos;
import net.themoviea.themovieapi_entity.entity.SimpleBrainEntity;
import net.themoviea.themovieapi_entity.entity.ai.SimpleBrain;

public class GoToPOIGoal extends Goal {
	public SimpleBrainEntity entity;
	public BlockState state;
	
	public GoToPOIGoal(SimpleBrainEntity entity) {
		this.entity = entity;
	}

	@Override
	public boolean canStart() {
		SimpleBrain brain = entity.getSimpleBrain();
		if(brain != null) {
			if(brain.POIBlockStateList.get(0) != null) {
				if(brain.POIBlockStateList.get(0) == this.entity.world.getBlockState(brain.pointOfInterest.get(brain.POIBlockStateList.get(0)))) {
					return true;
				} else {
					brain.pointOfInterest.remove(brain.POIBlockStateList.get(0));
					brain.POIBlockStateList.remove(0);
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public void start() {
		BlockPos pos = entity.getSimpleBrain().pointOfInterest.get(entity.getSimpleBrain().POIBlockStateList.get(0));
		entity.getNavigation().startMovingTo(pos.getX(), pos.getY(), pos.getZ(), 1.0D);
	}
}
