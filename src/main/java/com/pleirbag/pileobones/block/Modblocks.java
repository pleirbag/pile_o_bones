package com.pleirbag.pileobones.block;

import com.pleirbag.pileobones.PileOBones;
import com.pleirbag.pileobones.block.custom.BonePile;
import com.pleirbag.pileobones.item.Moditems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class Modblocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, PileOBones.MOD_ID);

	public static final RegistryObject<Block> PILE_O_BONES = registerBlock("bone_pile",
			() -> new BonePile(BlockBehaviour.Properties.of().strength(1f).sound(SoundType.BONE_BLOCK).noOcclusion()));

	private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
		RegistryObject<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends  Block> void registerBlockItem(String name, RegistryObject<T> block){
		Moditems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}
	

	public static void register(IEventBus eventBus){
		BLOCKS.register(eventBus);
	}
}
