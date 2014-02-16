package darkevilmac.utilities.crafting;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import buildcraft.BuildCraftEnergy;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.addons.block.bc.ModBlocksBC;
import darkevilmac.utilities.addons.block.ic2.ModBlocksIC2;
import darkevilmac.utilities.addons.block.railcraft.ModBlocksRailcraft;
import darkevilmac.utilities.item.ModItems;

public class ModCrafting {

    public static ItemStack stringStack = new ItemStack(Item.silk);
    public static ItemStack paperStack = new ItemStack(Item.paper);
    public static ItemStack leatherStack = new ItemStack(Item.leather);
    public static ItemStack nameTagStack = new ItemStack(Item.nameTag);

    public static ItemStack leadStack = new ItemStack(Item.leash);
    public static ItemStack ironHelmStack = new ItemStack(Item.helmetIron);
    public static ItemStack goldHelmStack = new ItemStack(Item.helmetGold);
    public static ItemStack diamondHelmStack = new ItemStack(Item.helmetDiamond);
    public static ItemStack ironLegsStack = new ItemStack(Item.legsIron);
    public static ItemStack goldLegsStack = new ItemStack(Item.legsGold);
    public static ItemStack diamondLegsStack = new ItemStack(Item.legsDiamond);
    public static ItemStack ironHorseArmorStack = new ItemStack(Item.horseArmorIron);
    public static ItemStack goldHorseArmorStack = new ItemStack(Item.horseArmorGold);
    public static ItemStack saddleStack = new ItemStack(Item.saddle);
    public static ItemStack diamondHorseArmorStack = new ItemStack(Item.horseArmorDiamond);
    public static ItemStack ironBlockStack = new ItemStack(Block.blockIron);
    public static ItemStack quartzBlockStack = new ItemStack(Block.blockNetherQuartz);
    public static ItemStack goldBlockStack = new ItemStack(Block.blockGold);
    public static ItemStack diamondBlockStack = new ItemStack(Block.blockDiamond);
    public static ItemStack netherrackStack = new ItemStack(Block.netherrack);
    public static ItemStack lapisStack = new ItemStack(Item.dyePowder, 1, 4);
    public static ItemStack redstoneStack = new ItemStack(Item.redstone);
    public static ItemStack ironStack = new ItemStack(Item.ingotIron);
    public static ItemStack quartzStack = new ItemStack(Item.netherQuartz);
    public static ItemStack goldStack = new ItemStack(Item.ingotGold);
    public static ItemStack diamondStack = new ItemStack(Item.diamond);
    public static ItemStack stoneStack = new ItemStack(Block.stone);

    public static ItemStack telePebbleStack = new ItemStack(ModItems.telePebble);

    public static void init() {
        GameRegistry.addRecipe(ModCrafting.ironHorseArmorStack, "  h", "ici", "lil", 'h', ModCrafting.ironHelmStack, 'l', ModCrafting.ironLegsStack, 'i',
                ModCrafting.ironStack, 'c', ModCrafting.leatherStack);
        GameRegistry.addRecipe(ModCrafting.goldHorseArmorStack, "  h", "ici", "lil", 'h', ModCrafting.goldHelmStack, 'l', ModCrafting.goldLegsStack, 'i',
                ModCrafting.goldStack, 'c', ModCrafting.leatherStack);
        GameRegistry.addRecipe(ModCrafting.diamondHorseArmorStack, "  h", "ici", "lil", 'h', ModCrafting.diamondHelmStack, 'l', ModCrafting.diamondLegsStack,
                'i', ModCrafting.diamondStack, 'c', ModCrafting.leatherStack);
        GameRegistry.addRecipe(ModCrafting.saddleStack, "lll", "lil", "b b", 'l', ModCrafting.leatherStack, 'i', ModCrafting.ironStack, 'b',
                ModCrafting.ironBlockStack);
        GameRegistry.addRecipe(ModCrafting.nameTagStack, "  s", "ps ", "pp ", 'p', ModCrafting.paperStack, 's', ModCrafting.stringStack);
        
        GameRegistry.addRecipe(ModCrafting.telePebbleStack, "psp", "sls", "psp", 'p', ModCrafting.paperStack, 's', ModCrafting.stoneStack, 'l',
                ModCrafting.lapisStack);
    }

}
