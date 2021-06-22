package net.themoviea.themovieapi_entity.entity.ai.goals;

import java.util.EnumSet;

import org.jetbrains.annotations.Nullable;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.PrioritizedGoal;

public class ScheduledGoal extends Goal {
	private final Goal goal;
	private final long time, duration;
	private boolean running;

	public ScheduledGoal(long time, long duration, Goal goal) {
		this.time = time;
		this.goal = goal;
		this.duration = duration;
	}
	
	@Override
	public boolean canStart() {
		return this.goal.canStart();
	}
	
	public boolean shouldContinue() {
		return this.goal.shouldContinue();
	}
	
	public boolean canStop() {
		return this.goal.canStop();
	}
	
	public void start() {
		if(!this.running) {
			this.running = true;
			if(this.goal instanceof RandomGoal) {
				((RandomGoal)this.goal).generateRandomized();
				this.goal.start();
			} else {
				this.goal.start();
			}
		}
	}

	public void stop() {
		if (this.running) {
			this.running = false;
			if(this.goal instanceof RandomGoal) {
				((RandomGoal)this.goal).resetRandomized();
				this.goal.stop();
			} else {
				this.goal.stop();
			}
		}
	}

	public void tick() {
		this.goal.tick();
	}

	public void setControls(EnumSet<Goal.Control> controls) {
		this.goal.setControls(controls);
	}

	public EnumSet<Goal.Control> getControls() {
		return this.goal.getControls();
	}

	public boolean isRunning() {
		return this.running;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public long getDuration() {
		return this.duration;
	}
	
	public Goal getGoal() {
		return this.goal;
	}
	
	public boolean equals(@Nullable Object object) {
		if (this == object) {
			return true;
		} else {
			return object != null && this.getClass() == object.getClass() ? this.goal.equals(((ScheduledGoal)object).goal) : false;
		}
	}

	public int hashCode() {
		return this.goal.hashCode();
	}
}

