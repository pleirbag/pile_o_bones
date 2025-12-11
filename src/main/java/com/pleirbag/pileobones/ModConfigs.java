package com.pleirbag.pileobones;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PileOBones.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModConfigs {
	public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

	public static ForgeConfigSpec.BooleanValue SkeletonHeadOnPlayerDeath;
	public static ForgeConfigSpec.IntValue ChanceOfSkull;

	static {
		SkeletonHeadOnPlayerDeath = builder.comment("If set to true theres a chance that the pile of bones also generates a skeleton skull")
				.define("SkeletonHeadOnPlayerDeath", true);

		ChanceOfSkull = builder.comment("if SkeletonHeadOnPlayerDeath, whats the chance %100 that the skull appears if theres a free spot")
				.defineInRange("ChanceOfSkull", 10, 0, 100);
	}
	static final ForgeConfigSpec BUILD = builder.build();

}
