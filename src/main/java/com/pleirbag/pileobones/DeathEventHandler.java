package com.pleirbag.pileobones;

import com.pleirbag.pileobones.block.Modblocks;
import com.pleirbag.pileobones.block.custom.BonePile;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.DecoratedPotBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class DeathEventHandler {

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		Level level = entity.level();

		if (entity instanceof Player) {
			Player player = (Player) entity;
			BlockPos deathPos = player.blockPosition();
			BlockPos.MutableBlockPos below = new BlockPos.MutableBlockPos();
			below.set(deathPos.below());
			BlockPos validPos = null;

			if (level.getBlockState(below).isSolidRender(level, below) && (level.getBlockState(deathPos).is(Blocks.AIR))){;
				level.setBlock(deathPos, Modblocks.PILE_O_BONES.get().defaultBlockState(), 3);
				if (validSkull(deathPos, level)){
					level.setBlock(deathPos.south(), Blocks.SKELETON_SKULL.defaultBlockState(), 3);
				}
				return;
			}
			int isValid = 0;
			for (int x = deathPos.getX() - 1; x <= deathPos.getX() + 1; x++){
				for (int z = deathPos.getZ() - 1; z <= deathPos.getZ() + 1; z++){
					below.set(x, deathPos.below().getY(), z);
					if (level.getBlockState(below).isSolidRender(level, below)
							&& level.getBlockState(below.above()).is(Blocks.AIR)) {
						validPos = below.above();
						isValid = 1;
						break;
					}
				}
			}
			if (isValid == 1){
				level.setBlock(validPos, Modblocks.PILE_O_BONES.get().defaultBlockState(), 3);
				if (validSkull(validPos, level)){
					level.setBlock(validPos.south(), Blocks.SKELETON_SKULL.defaultBlockState(), 3);
				}
				return;
			}
		}
	}
	private boolean validSkull(BlockPos deathPos, Level level){

		BlockPos southDown = deathPos.below().south();

		if (!ModConfigs.SkeletonHeadOnPlayerDeath.get()){
			return false;
		}
		Random rng = new Random();
		int chance = ModConfigs.ChanceOfSkull.get();

		if (rng.nextInt(100) > chance){
			return false;
		}

		if (level.getBlockState(deathPos.south()).isAir() && level.getBlockState(southDown).isSolidRender(level, southDown)) {
			return true;
		}
		return false;
	}
}