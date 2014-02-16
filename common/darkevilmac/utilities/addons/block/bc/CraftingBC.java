package darkevilmac.utilities.addons.block.bc;

import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.crafting.ModCrafting;
import net.minecraft.item.ItemStack;
import buildcraft.BuildCraftEnergy;

public class CraftingBC {

    public static ItemStack redstoneEngineStack = new ItemStack(BuildCraftEnergy.engineBlock);

    public static ItemStack bcLinkStack = new ItemStack(ModBlocksBC.bcLink);

    public static void init() {

        GameRegistry.addRecipe(bcLinkStack, "zrz", "iqi", "zrz", 'z', ModCrafting.quartzStack, 'r', redstoneEngineStack, 'i', ModCrafting.ironBlockStack, 'q',
                ModCrafting.quartzBlockStack);

    }

}
