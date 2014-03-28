package darkevilmac.utilities.item;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import darkevilmac.utilities.item.base.ItemUtilities;
import darkevilmac.utilities.lib.Strings;

public class ItemTelePebble extends ItemUtilities {

    public ItemTelePebble() {
        super();
        setUnlocalizedName(Strings.TELEPEBBLE_UNLOCALIZEDNAME);
        setHasSubtypes(true);
        setMaxDamage(0);
        maxStackSize = 1;
    }

    @SideOnly(Side.CLIENT)
    private IIcon usedIcon;
    @SideOnly(Side.CLIENT)
    private IIcon unusedIcon;

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        usedIcon = iconRegister.registerIcon("utilities:" + "telePebble");
        unusedIcon = iconRegister.registerIcon("utilities:" + "telePebbleEmpty");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage) {
        if (damage == 10) {
            return usedIcon;
        } else {
            return unusedIcon;
        }
    }

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
                player.addChatComponentMessage(new ChatComponentText("An blue spiral appears on the pebble indicating that it has information..."));
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
                    player.setPositionAndUpdate(x, y, z);
                    player.addChatComponentMessage(new ChatComponentText("The pebble reacts to your use and teleports you to a place you remember."));
                }
            }

        }
        return stack;
    }

}
