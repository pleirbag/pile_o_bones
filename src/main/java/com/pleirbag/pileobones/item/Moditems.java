package com.pleirbag.pileobones.item;

import com.pleirbag.pileobones.PileOBones;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Moditems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PileOBones.MOD_ID);

	//public static final RegistryObject<Item> BONE_PILE = ITEMS.register("bone_pile", () -> new Item(new Item.Properties()));

	public static void  register(IEventBus eventBus){
		ITEMS.register(eventBus);
	}
}
