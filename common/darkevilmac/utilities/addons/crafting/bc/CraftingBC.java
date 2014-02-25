package darkevilmac.utilities.addons.crafting.bc;

import net.minecraft.item.ItemStack;
import buildcraft.BuildCraftEnergy;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.addons.block.bc.ModBlocksBC;
import darkevilmac.utilities.crafting.ModCrafting;

public class CraftingBC {

    public static ItemStack redstoneEngineStack = new ItemStack(BuildCraftEnergy.engineBlock);

    public static ItemStack bcLinkStack = new ItemStack(ModBlocksBC.bcLink);

    public static void init() {

        GameRegistry.addRecipe(bcLinkStack, "zrz", "iqi", "zrz", 'z', ModCrafting.quartzStack, 'r', redstoneEngineStack, 'i', ModCrafting.ironBlockStack, 'q',
                ModCrafting.quartzBlockStack);

    }

}
