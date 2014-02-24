package darkevilmac.utilities.addons.block.railcraft;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import darkevilmac.utilities.crafting.ModCrafting;

public class CraftingRailcraft {

    public static ItemStack steamLinkStack = new ItemStack(ModBlocksRailcraft.steamLink);

    public static void init() {

        GameRegistry.addRecipe(steamLinkStack, "ibi", "rqr", "ini", 'i', ModCrafting.ironStack, 'b', ModCrafting.ironBlockStack, 'r', ModCrafting.redstoneStack, 'q',
                ModCrafting.quartzStack, 'n', ModCrafting.netherrackStack);

    }

}
