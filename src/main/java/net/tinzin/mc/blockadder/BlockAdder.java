package net.tinzin.mc.blockadder;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.SyntaxError;
import io.github.cottonmc.staticdata.StaticData;
import io.github.cottonmc.staticdata.StaticDataItem;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class BlockAdder implements ModInitializer {
	public static final String MODID = "blockadder";

	public static final Logger logger = LogManager.getLogger();

	@Override
	public void onInitialize() {
		Jankson jankson = Jankson.builder().build();
		//TODO: yaml support?
		Set<StaticDataItem> data = StaticData.getAll("blockadder.json5");
		for (StaticDataItem item : data) {
			try {
				JsonObject json = jankson.load(item.createInputStream());
				for (String key : json.keySet()) {
					JsonObject entry = json.getObject(key);
					registerBlock(key, BlockParser.INSTANCE.createBlock(entry));
				}
			} catch (IOException | SyntaxError e) {
				logger.error("[BlockAdder] Could not parse blockadder file at " + item.getIdentifier().toString() + " - " + e.getMessage());
			}
		}
	}

	private void registerBlock(String blockName, Block block){
		logger.info("[BlockAdder|Debug] Registering block blockadder:" + blockName);
        Registry.register(Registry.BLOCK, new Identifier(MODID, blockName.toLowerCase()), block);
        Registry.register(Registry.ITEM, blockName, new BlockItem(block, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
    }
}
