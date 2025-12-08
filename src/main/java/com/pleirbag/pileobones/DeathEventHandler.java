package com.pleirbag.pileobones;

import com.pleirbag.pileobones.block.Modblocks;
import com.pleirbag.pileobones.block.custom.BonePile;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.RegistryObject;

public class DeathEventHandler {

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {
		LivingEntity entity = event.getEntity();
		Level level = entity.level();

		if (entity instanceof Player) {
			Player player = (Player) entity;
			BlockPos deathPos = player.blockPosition();
			BlockPos below = deathPos.below();
			BlockPos placePos = null;

			if (level.getBlockState(below).isSolidRender(level, below) && (level.getBlockState(deathPos).is(Blocks.AIR))){
				Block blockToPlace = Modblocks.PILE_O_BONES.get();
				level.setBlock(deathPos, blockToPlace.defaultBlockState(), 3);
			}
			int isSolidBelow = 0;
				for (int x = below.getX() - 1; x <= below.getX() + 1; x++){
					for (int z = below.getZ() - 1; z <= below.getZ() + 1; z++){
						if (level.getBlockState(new BlockPos(x, below.getY(), z)).isSolidRender(level, below)) {
							placePos = new BlockPos(x, deathPos.getY(), z);
							isSolidBelow = 1;
							break;
						}
					}
				}
			if (isSolidBelow == 1 && (level.getBlockState(placePos).is(Blocks.AIR))){
				Block blockToPlace = Modblocks.PILE_O_BONES.get();
				level.setBlock(placePos, blockToPlace.defaultBlockState(), 3);
			}
		}
	}
}