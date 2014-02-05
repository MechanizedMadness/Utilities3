package darkevilmac.utilities.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class ItemTelePebble extends ItemUtilities {

    public ItemTelePebble(int id) {
        super(id);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
        this.setUnlocalizedName(Strings.TELEPEBBLE_UNLOCALIZEDNAME);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        maxStackSize = 1;
    }

    @SideOnly(Side.CLIENT)
    private Icon usedIcon;
    @SideOnly(Side.CLIENT)
    private Icon unusedIcon;

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        usedIcon = iconRegister.registerIcon(Reference.MOD_TEXTURE_ID + "telePebble");
        unusedIcon = iconRegister.registerIcon(Reference.MOD_TEXTURE_ID + "telePebbleEmpty");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Icon getIconFromDamage(int damage) {
        if (damage == 10) {
            return usedIcon;
        } else {
            return unusedIcon;
        }
    }

    @SuppressWarnings({ "unused", "null" })
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (!stack.hasTagCompound())
            stack.setTagCompound(new NBTTagCompound());
        World originWorld = world;

        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt.getBoolean("HasInfo") == false) {

            nbt.setDouble("x", player.posX);
            nbt.setDouble("y", player.posY);
            nbt.setDouble("z", player.posZ);

            nbt.setInteger("WorldId", originWorld.provider.dimensionId);
            nbt.setBoolean("HasInfo", true);
            stack.setItemDamage(10);
            if (!originWorld.isRemote) {
                player.addChatMessage("An blue spiral appears on the pebble indicating that it has information...");
            }
        } else {
            int x = (int) nbt.getDouble("x");
            int y = (int) nbt.getDouble("y");
            int z = (int) nbt.getDouble("z");
            World destinationWorld = DimensionManager.getWorld(nbt.getInteger("WorldId"));
            if (world.provider.dimensionId != nbt.getInteger("WorldId")) {
                if (!world.isRemote)
                    player.travelToDimension(nbt.getInteger("WorldId"));
            }
            if ((int) player.posX != x || (int) player.posY != y || (int) player.posZ != z) {
                if (!world.isRemote) {
                    player.setPositionAndUpdate((double) x, (double) y, (double) z);
                    player.addChatMessage("The pebble reacts to your use and teleports you to a place you remember.");
                }
            }

        }
        return stack;
    }

}
