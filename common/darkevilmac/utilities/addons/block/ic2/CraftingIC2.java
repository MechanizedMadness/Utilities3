package darkevilmac.utilities.addons.block.ic2;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.crafting.ModCrafting;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class CraftingIC2 {

    private static ItemStack rubberStack = OreDictionary.getOres("itemRubber").get(0);
    private static ItemStack copperStack = OreDictionary.getOres("ingotCopper").get(0);

    private static ItemStack ic2LinkStack = new ItemStack(ModBlocksIC2.ic2Link);

    public static void init() {

        GameRegistry.addRecipe(ic2LinkStack, "rbr", "iqi", "brb", 'r', ModCrafting.redstoneStack, 'b', copperStack, 'i', ModCrafting.ironBlockStack, 'q',
                ModCrafting.quartzBlockStack);

    }

}
