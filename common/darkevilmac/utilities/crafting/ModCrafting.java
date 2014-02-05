package darkevilmac.utilities.crafting;

import java.util.ArrayList;

import buildcraft.BuildCraftEnergy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.block.ModBlocks;
import darkevilmac.utilities.item.ModItems;

public class ModCrafting {

    private static ItemStack stringStack = new ItemStack(Item.silk);
    private static ItemStack paperStack = new ItemStack(Item.paper);
    private static ItemStack leatherStack = new ItemStack(Item.leather);
    private static ArrayList<ItemStack> rubberStack = OreDictionary.getOres("itemRubber");
    private static ItemStack copperStack = OreDictionary.getOres("ingotCopper").get(0);
    private static ItemStack nameTagStack = new ItemStack(Item.nameTag);

    private static ItemStack leadStack = new ItemStack(Item.leash);
    private static ItemStack ironHelmStack = new ItemStack(Item.helmetIron);
    private static ItemStack goldHelmStack = new ItemStack(Item.helmetGold);
    private static ItemStack diamondHelmStack = new ItemStack(Item.helmetDiamond);
    private static ItemStack ironLegsStack = new ItemStack(Item.legsIron);
    private static ItemStack goldLegsStack = new ItemStack(Item.legsGold);
    private static ItemStack diamondLegsStack = new ItemStack(Item.legsDiamond);
    private static ItemStack ironHorseArmorStack = new ItemStack(Item.horseArmorIron);
    private static ItemStack goldHorseArmorStack = new ItemStack(Item.horseArmorGold);
    private static ItemStack saddleStack = new ItemStack(Item.saddle);
    private static ItemStack diamondHorseArmorStack = new ItemStack(Item.horseArmorDiamond);
    private static ItemStack ironBlockStack = new ItemStack(Block.blockIron);
    private static ItemStack quartzBlockStack = new ItemStack(Block.blockNetherQuartz);
    private static ItemStack goldBlockStack = new ItemStack(Block.blockGold);
    private static ItemStack diamondBlockStack = new ItemStack(Block.blockDiamond);
    private static ItemStack netherrackStack = new ItemStack(Block.netherrack);
    private static ItemStack lapisStack = new ItemStack(Item.dyePowder, 1, 4);
    private static ItemStack redstoneStack = new ItemStack(Item.redstone);
    private static ItemStack ironStack = new ItemStack(Item.ingotIron);
    private static ItemStack quartzStack = new ItemStack(Item.netherQuartz);
    private static ItemStack goldStack = new ItemStack(Item.ingotGold);
    private static ItemStack diamondStack = new ItemStack(Item.diamond);
    private static ItemStack stoneStack = new ItemStack(Block.stone);

    private static ItemStack redstoneEngineStack = new ItemStack(BuildCraftEnergy.engineBlock);

    private static ItemStack ic2LinkStack = new ItemStack(ModBlocks.ic2Link);
    private static ItemStack steamLinkStack = new ItemStack(ModBlocks.steamLink);
    private static ItemStack bcLinkStack = new ItemStack(ModBlocks.bcLink);

    private static ItemStack telePebbleStack = new ItemStack(ModItems.telePebble);

    public static void init() {
        GameRegistry.addRecipe(ironHorseArmorStack, "  h", "ici", "lil", 'h', ironHelmStack, 'l', ironLegsStack, 'i', ironStack, 'c', leatherStack);
        GameRegistry.addRecipe(goldHorseArmorStack, "  h", "ici", "lil", 'h', goldHelmStack, 'l', goldLegsStack, 'i', goldStack, 'c', leatherStack);
        GameRegistry.addRecipe(diamondHorseArmorStack, "  h", "ici", "lil", 'h', diamondHelmStack, 'l', diamondLegsStack, 'i', diamondStack, 'c', leatherStack);
        GameRegistry.addRecipe(saddleStack, "lll", "lil", "b b", 'l', leatherStack, 'i', ironStack, 'b', ironBlockStack);
        GameRegistry.addRecipe(nameTagStack, "  s", "ps ", "pp ", 'p', paperStack, 's', stringStack);
        GameRegistry.addRecipe(telePebbleStack, "psp", "sls", "psp", 'p', paperStack, 's', stoneStack, 'l', lapisStack);
        GameRegistry.addRecipe(ic2LinkStack, "rbr", "iqi", "brb", 'r', redstoneStack, 'b', copperStack, 'i', ironBlockStack, 'q', quartzBlockStack);
        GameRegistry.addRecipe(steamLinkStack, "ibi", "rqr", "ini", 'i', ironStack, 'b', ironBlockStack, 'r', redstoneStack, 'q', quartzStack ,'n', netherrackStack);
        GameRegistry.addRecipe(bcLinkStack, "zrz", "iqi", "zrz", 'z', quartzStack, 'r', redstoneEngineStack, 'i', ironBlockStack, 'q', quartzBlockStack);
    }

}
