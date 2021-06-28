package net.themoviea.themovieapi_entity.entity.ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SimpleBrain {
	private List<BlockPos> POIBlockPosList = new ArrayList<BlockPos>();
	public List<BlockState> POIBlockStateList = new ArrayList<BlockState>();
	public Map<BlockState, BlockPos> pointOfInterest = Maps.newHashMap();
	private LivingEntity entity;
	
	public SimpleBrain(LivingEntity entity) {
		this.entity = entity;
	}
	
	private int getEntityPos(String option) {
		if(option == "X" || option == "x") {
			return this.entity.getBlockPos().getX();
		} else if(option == "Y" || option == "y") {
			return this.entity.getBlockPos().getY();
		} else if(option == "Z" || option == "z") {
			return this.entity.getBlockPos().getZ();
		} else {
			return 0;
		}
	}
	
	public void searchPointOfInterest(BlockState state, World world, int xRange, int yRange, int zRange) {
		for(int z = this.getEntityPos("z") - zRange; z <= this.getEntityPos("z") + zRange; z++) {
			for(int x = this.getEntityPos("x") - xRange; x <= this.getEntityPos("x") + xRange; x++) {
				for(int y = this.getEntityPos("y") - yRange; y <= this.getEntityPos("y") + yRange; y++) {
					if(world.getBlockState(new BlockPos(x, y, z)) == state) {
						this.POIBlockPosList.add(new BlockPos(x, y, z));
						this.POIBlockStateList.add(state);
					}
				}
			}
		}
		
		for(int i = 0; i < this.POIBlockPosList.size(); i++) {
			this.pointOfInterest.put(this.POIBlockStateList.get(i), this.POIBlockPosList.get(i));
		}
	}
}
