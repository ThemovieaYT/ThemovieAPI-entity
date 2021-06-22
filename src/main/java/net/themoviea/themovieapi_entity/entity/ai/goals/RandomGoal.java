package net.themoviea.themovieapi_entity.entity.ai.goals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import com.google.common.collect.ImmutableList;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.world.World;

public class RandomGoal extends Goal {
	private List<Goal> goalList = new ArrayList<Goal>();
	private List<Goal> randomizedGoalList = new ArrayList<Goal>();
	private boolean isRunning;
	private long tickDuration;
	private int index = 0;
	
	public RandomGoal(List<Goal> goalList, long tickDuration) {
		this.goalList = goalList;
		this.tickDuration = tickDuration;
	}
	
	public List<Goal> getRandomizedGoalList() {
		return this.randomizedGoalList;
	}
	
	public List<Goal> getGoalList() {
		return this.goalList;
	}
	
	public void generateRandomized() {
		int i = 0;
		for(int x = 0; x < this.goalList.size(); x++) {
			i = ThreadLocalRandom.current().nextInt(0, this.goalList.size() - 1);
			this.randomizedGoalList.add(this.goalList.get(i));
		}
	}
	
	public void resetRandomized() {
		for(int x = 0; x < this.goalList.size(); x++) {
			this.randomizedGoalList.remove(x);
		}
	}

	@Override
	public boolean canStart() {
		return false;
	}
	
	@Override
	public void start() {
		this.start(0);
	}
	
	public void start(int i) {
		this.randomizedGoalList.get(i).start();
		this.isRunning = true;
	}
	
	@Override
	public void stop() {
		this.stop(this.goalList.size() - 1);
	}
	
	public void stop(int i) {
		this.randomizedGoalList.get(i).stop();
	}
	
	@Override
	public void tick() {
		long duration = this.tickDuration;
		if(this.index < this.goalList.size() - 1) {
			if(this.tickDuration != 0) {
				this.tickDuration = this.tickDuration - 1;
			} else {
				this.stop(this.index);
				this.index = this.index + 1;
				this.tickDuration = duration;
				this.start(this.index);
			}
		} else {
			this.stop();
		}
	}
}

