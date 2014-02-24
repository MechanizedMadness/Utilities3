package darkevilmac.utilities.crafting;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.item.ModItems;

public class ModCrafting {

    public static ItemStack stringStack = new ItemStack((Item) Item.itemRegistry.getObject("string"));
    public static ItemStack paperStack = new ItemStack((Item) Item.itemRegistry.getObject("paper"));
    public static ItemStack leatherStack = new ItemStack((Item) Item.itemRegistry.getObject("leather"));
    public static ItemStack nameTagStack = new ItemStack((Item) Item.itemRegistry.getObject("name_tag"));

    public static ItemStack leadStack = new ItemStack((Item) Item.itemRegistry.getObject("leash"));
    public static ItemStack ironHelmStack = new ItemStack((Item) Item.itemRegistry.getObject("iron_helmet"));
    public static ItemStack goldHelmStack = new ItemStack((Item) Item.itemRegistry.getObject("gold_helmet"));
    public static ItemStack diamondHelmStack = new ItemStack((Item) Item.itemRegistry.getObject("diamond_helmet"));
    public static ItemStack ironLegsStack = new ItemStack((Item) Item.itemRegistry.getObject("iron_leggings"));
    public static ItemStack goldLegsStack = new ItemStack((Item) Item.itemRegistry.getObject("gold_leggings"));
    public static ItemStack diamondLegsStack = new ItemStack((Item) Item.itemRegistry.getObject("diamond_leggings"));
    public static ItemStack ironHorseArmorStack = new ItemStack((Item) Item.itemRegistry.getObject("iron_horse_armor"));
    public static ItemStack goldHorseArmorStack = new ItemStack((Item) Item.itemRegistry.getObject("gold_horse_armor"));
    public static ItemStack diamondHorseArmorStack = new ItemStack((Item) Item.itemRegistry.getObject("diamond_horse_armor"));
    public static ItemStack saddleStack = new ItemStack((Item) Item.itemRegistry.getObject("saddle"));
    public static ItemStack ironBlockStack = new ItemStack((Block) Block.blockRegistry.getObject("iron_block"));
    public static ItemStack quartzBlockStack = new ItemStack((Block) Block.blockRegistry.getObject("quartz_block"));
    public static ItemStack goldBlockStack = new ItemStack((Block) Block.blockRegistry.getObject("gold_block"));
    public static ItemStack diamondBlockStack = new ItemStack((Block) Block.blockRegistry.getObject("diamond_block"));
    public static ItemStack netherrackStack = new ItemStack((Block) Block.blockRegistry.getObject("netherrack"));
    public static ItemStack lapisStack = new ItemStack((Item) Item.itemRegistry.getObject("dye"), 1, 4);
    public static ItemStack redstoneStack = new ItemStack((Item) Item.itemRegistry.getObject("redstone"));
    public static ItemStack ironStack = new ItemStack((Item) Item.itemRegistry.getObject("iron_ingot"));
    public static ItemStack quartzStack = new ItemStack((Item) Item.itemRegistry.getObject("quartz"));
    public static ItemStack goldStack = new ItemStack((Item) Item.itemRegistry.getObject("gold_ingot"));
    public static ItemStack diamondStack = new ItemStack((Item) Item.itemRegistry.getObject("diamond"));
    public static ItemStack stoneStack = new ItemStack((Block) Block.blockRegistry.getObject("stone"));

    public static ItemStack telePebbleStack = new ItemStack(ModItems.telePebble);

    public static void init() {
        GameRegistry.addRecipe(ModCrafting.ironHorseArmorStack, "  h", "ici", "lil", 'h', ModCrafting.ironHelmStack, 'l',
                ModCrafting.ironLegsStack, 'i', ModCrafting.ironStack, 'c', ModCrafting.leatherStack);
        GameRegistry.addRecipe(ModCrafting.goldHorseArmorStack, "  h", "ici", "lil", 'h', ModCrafting.goldHelmStack, 'l',
                ModCrafting.goldLegsStack, 'i', ModCrafting.goldStack, 'c', ModCrafting.leatherStack);
        GameRegistry.addRecipe(ModCrafting.diamondHorseArmorStack, "  h", "ici", "lil", 'h', ModCrafting.diamondHelmStack, 'l',
                ModCrafting.diamondLegsStack, 'i', ModCrafting.diamondStack, 'c', ModCrafting.leatherStack);
        GameRegistry.addRecipe(ModCrafting.saddleStack, "lll", "lil", "b b", 'l', ModCrafting.leatherStack, 'i', ModCrafting.ironStack,
                'b', ModCrafting.ironBlockStack);
        GameRegistry.addRecipe(ModCrafting.nameTagStack, "  s", "ps ", "pp ", 'p', ModCrafting.paperStack, 's', ModCrafting.stringStack);

        GameRegistry.addRecipe(ModCrafting.telePebbleStack, "psp", "sls", "psp", 'p', ModCrafting.paperStack, 's', ModCrafting.stoneStack,
                'l', ModCrafting.lapisStack);
    }

}
