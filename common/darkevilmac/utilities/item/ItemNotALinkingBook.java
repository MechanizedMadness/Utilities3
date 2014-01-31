package darkevilmac.utilities.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import darkevilmac.utilities.lib.Strings;

public class ItemNotALinkingBook extends ItemUtilities {

    public ItemNotALinkingBook(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
        this.setUnlocalizedName(Strings.NOT_A_LINKINGBOOK_UNLOCALIZEDNAME);
        maxStackSize = 1;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt.getBoolean("HasInfo") == false) {
            nbt.setInteger("x", player.serverPosX);
            nbt.setInteger("y", player.serverPosY);
            nbt.setInteger("z", player.serverPosZ);
            nbt.setInteger("WorldId", world.provider.dimensionId);

            nbt.setBoolean("HasInfo", true);
            player.addChatMessage("Location has been set for the book.");
        } else {
            World tpWorld;
            WorldProvider tpWorldProvider = null;
            tpWorldProvider.setDimension(nbt.getInteger("WorldId"));
            tpWorld = tpWorldProvider.worldObj;
            player.setWorld(tpWorld);
            player.setPosition(nbt.getInteger("x"), nbt.getInteger("y"), nbt.getInteger("z"));
            player.addChatMessage("Teleporting you to the location set in the book.");
        }
        return true;
    }

}
