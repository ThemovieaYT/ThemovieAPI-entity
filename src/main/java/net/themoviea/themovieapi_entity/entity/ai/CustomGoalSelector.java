package net.themoviea.themovieapi_entity.entity.ai;

import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;

import com.google.common.collect.Sets;

import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.World;
import net.themoviea.themovieapi_entity.entity.ai.goals.RandomGoal;
import net.themoviea.themovieapi_entity.entity.ai.goals.ScheduledGoal;
import net.themoviea.themovieapi_entity.entity.ai.goals.ShouldRunOneTimeGoal;
import net.themoviea.themovieapi_entity.entity.ai.goals.TaskOnceGoal;

public class CustomGoalSelector {
	/**
	 * ScheduledGoal is for goals that should run according to the schedule. It's like the
	 * Schedule class, but less complicated and easier.
	 * Main purpose of this is to simplify how Villagers do activities according to their schedule.
	 */
	private final Set<ScheduledGoal> scheduledGoals = Sets.newLinkedHashSet();
	/**
	 * ShouldRunGoal is for goals that should run no matter what.
	 * For example, finding a block workstation and turning into that profession.
	 * The main reason I made this is because the way villagers find and store BlockPos are really
	 * complicated.
	 */
	private final Set<ShouldRunOneTimeGoal> shouldRunOneTimeGoals = Sets.newLinkedHashSet();
	private final Supplier<Profiler> profiler;
	
	public CustomGoalSelector(Supplier<Profiler> profiler) {
		this.profiler = profiler;
	}

	public long getWorldTick(World world) {
		return world.getTimeOfDay();
	}
	
	public void addScheduled(long time, long duration, Goal goal) {
		this.scheduledGoals.add(new ScheduledGoal(time, duration, goal));
	}
	
	public void addShouldRun(TaskOnceGoal goal) {
		this.shouldRunOneTimeGoals.add(new ShouldRunOneTimeGoal(goal));
	}

	public void tick(World world) {
		Profiler profiler = (Profiler)this.profiler.get();
		profiler.push("scheduledGoalUpdate");
		this.scheduledGoals.stream().filter((prioritizedGoal) -> {
			return !prioritizedGoal.isRunning();
		}).filter(ScheduledGoal::canStart).forEach((scheduledGoal) -> {
			scheduledGoal.getControls().forEach((control) -> {
			});
			if(getWorldTick(world) >= scheduledGoal.getTime() && getWorldTick(world) < scheduledGoal.getTime() + scheduledGoal.getDuration()) {
				scheduledGoal.start();
			}
		});
		profiler.pop();
		profiler.push("scheduledGoalTick");
		this.getRunningScheduledGoals().forEach(ScheduledGoal::tick);
		this.getRunningScheduledGoals().forEach((scheduledGoal) -> {
			if(getWorldTick(world) >= scheduledGoal.getTime() + scheduledGoal.getDuration()) {
				scheduledGoal.stop();
			} else if(getWorldTick(world) >= scheduledGoal.getTime() && getWorldTick(world) < scheduledGoal.getTime() + scheduledGoal.getDuration() && scheduledGoal.canStart()) {
				scheduledGoal.start();
				if(scheduledGoal.getGoal() instanceof FollowTargetGoal) {
					System.out.println("This might repeat!");
				}
			}
		});
		profiler.pop();
		profiler.push("shouldRunOneTimeGoalUpdate");
		this.shouldRunOneTimeGoals.stream().filter((shouldRunOneTimeGoal) -> {
			return !shouldRunOneTimeGoal.isRunning();
		}).filter(ShouldRunOneTimeGoal::canStart).forEach((shouldRunOneTimeGoal) -> {
			shouldRunOneTimeGoal.start();
		});
		profiler.pop();
		profiler.push("shouldRunOneTimeGoalTick");
		this.getRunningShouldRunOneTimeGoals().forEach(ShouldRunOneTimeGoal::tick);
		this.getRunningShouldRunOneTimeGoals().forEach((shouldRunOneTimeGoal) -> {
			if(shouldRunOneTimeGoal.isTaskComplete()) {
				shouldRunOneTimeGoal.stop();
			}
		});
		
	}
	
	public Stream<ScheduledGoal> getRunningScheduledGoals() {
		return this.scheduledGoals.stream().filter(ScheduledGoal::isRunning);
	}
	
	public Stream<ShouldRunOneTimeGoal> getRunningShouldRunOneTimeGoals() {
		return this.shouldRunOneTimeGoals.stream().filter(ShouldRunOneTimeGoal::isRunning);
	}
}
