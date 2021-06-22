package net.themoviea.themovieapi_entity.entity.ai.goals;

import net.minecraft.entity.ai.goal.Goal;

public class ShouldRunOneTimeGoal extends Goal {
	private final TaskOnceGoal goal;
	private boolean running, hasRan;
	
	public ShouldRunOneTimeGoal(TaskOnceGoal goal) {
		this.goal = goal;
	}

	@Override
	public boolean canStart() {
		return this.goal.canStart();
	}

	@Override
	public boolean canStop() {
		return this.goal.canStop();
	}
	
	@Override
	public void start() {
		if(!this.hasRan) {
			if(!this.running) {
				this.running = true;
				this.goal.start();
				this.hasRan = true;
			}
		}
	}
	
	@Override
	public void stop() {
		if(this.running) {
			this.running = false;
			this.goal.stop();
		}
	}
	
	@Override
	public void tick() {
		this.goal.tick();
	}
	
	public boolean isRunning() {
		return this.running;
	}
	
	public boolean isTaskComplete() {
		return this.goal.isTaskComplete();
	}
}

