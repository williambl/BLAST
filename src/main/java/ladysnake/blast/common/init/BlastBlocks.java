package ladysnake.blast.common.init;

import ladysnake.blast.common.block.*;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import static ladysnake.blast.common.Blast.MODID;

public class BlastBlocks {

    public static Block GUNPOWDER_BLOCK;
    public static Block STRIPMINER;
    public static Block COLD_DIGGER;
    public static Block BONESBURRIER;
    public static Block REMOTE_DETONATOR;
    public static Block DRY_ICE;
    public static Block FOLLY_RED_PAINT;
    public static Block FRESH_FOLLY_RED_PAINT;
    public static Block DRIED_FOLLY_RED_PAINT;

    public static void init() {
        GUNPOWDER_BLOCK = registerBlock(new GunpowderBlock(FabricBlockSettings.create().mapColor(DyeColor.BLACK).strength(0.5F, 0.5f).sounds(BlockSoundGroup.SAND)), "gunpowder_block", ItemGroups.BUILDING_BLOCKS);
        STRIPMINER = registerBlock(new StripminerBlock(FabricBlockSettings.create().strength(2.5f, 2.5f).sounds(BlockSoundGroup.WOOD).nonOpaque(), BlastEntities.STRIPMINER), "stripminer", ItemGroups.REDSTONE);
        COLD_DIGGER = registerBlock(new StripminerBlock(FabricBlockSettings.create().strength(2.5f, 2.5f).sounds(BlockSoundGroup.WOOD).nonOpaque(), BlastEntities.COLD_DIGGER), "cold_digger", ItemGroups.REDSTONE);
        BONESBURRIER = registerBlock(new BonesburrierBlock(FabricBlockSettings.copyOf(Blocks.BONE_BLOCK)), "bonesburrier", ItemGroups.REDSTONE);
        REMOTE_DETONATOR = registerBlock(new RemoteDetonatorBlock(FabricBlockSettings.create().strength(2.5f, 2.5f).sounds(BlockSoundGroup.LANTERN).nonOpaque()), "remote_detonator", ItemGroups.REDSTONE);
        DRY_ICE = registerBlock(new DryIceBlock(FabricBlockSettings.create().mapColor(MapColor.LIGHT_GRAY).slipperiness(0.98F).strength(0.5F).sounds(BlockSoundGroup.GLASS).nonOpaque()), "dry_ice", ItemGroups.BUILDING_BLOCKS);
        FOLLY_RED_PAINT = registerBlock(new FollyRedPaintBlock(FabricBlockSettings.copyOf(Blocks.HONEY_BLOCK).ticksRandomly().strength(0.2f).mapColor(MapColor.BRIGHT_RED)), "folly_red_paint", ItemGroups.BUILDING_BLOCKS);
        FRESH_FOLLY_RED_PAINT = registerBlock(new FollyRedPaintBlock(FabricBlockSettings.copyOf(Blocks.HONEY_BLOCK).strength(0.2f).mapColor(MapColor.BRIGHT_RED)), "fresh_folly_red_paint", ItemGroups.BUILDING_BLOCKS);
        DRIED_FOLLY_RED_PAINT = registerBlock(new FollyRedPaintBlock(FabricBlockSettings.create().mapColor(MapColor.BRIGHT_RED).strength(0.4f).sounds(BlockSoundGroup.DRIPSTONE_BLOCK).mapColor(MapColor.BRIGHT_RED)), "dried_folly_red_paint", ItemGroups.BUILDING_BLOCKS);
    }

    private static Block registerBlock(Block block, String name, @Nullable RegistryKey<ItemGroup> itemGroupKey) {
        return registerBlock(block, name, itemGroupKey, true);
    }

    private static Block registerBlock(Block block, String name, @Nullable RegistryKey<ItemGroup> itemGroupKey, boolean registerBlockItem) {
        Registry.register(Registries.BLOCK, MODID + ":" + name, block);

        if (registerBlockItem) {
            var blockItem = new BlockItem(block, new Item.Settings());
            blockItem.appendBlocks(Item.BLOCK_ITEMS, blockItem);
            BlastItems.registerItem(blockItem, name, itemGroupKey);
        }
        return block;
    }

}
