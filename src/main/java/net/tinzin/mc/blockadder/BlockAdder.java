package net.tinzin.mc.blockadder;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BlockAdder implements ModInitializer {
	public static final String MODID = "blockadder";

	public static final Logger logger = LogManager.getLogger();

	@Override
	public void onInitialize() {
		
	}

	private void registerBlock(String blockName, FabricBlockSettings settings){
		//TODO: include custom model, rotation mode, bounding box features, and also actually parse the yaml config etc etc
	    Block block = new Block(settings.build());
        Registry.register(Registry.BLOCK, new Identifier("justdecorativeblocks", blockName.toLowerCase()), block);
        Registry.register(Registry.ITEM, blockName, new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
