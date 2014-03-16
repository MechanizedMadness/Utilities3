package darkevilmac.utilities.block;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.item.ItemPipeLinker;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergyNetworkBridge;
import darkevilmac.utilities.tile.TileEntityEnergyNetworkManager;

public class BlockEnergyNetworkBridge extends BlockUtilitiesContainer {

    public BlockEnergyNetworkBridge() {
        super(Material.iron);
        setBlockName(Strings.ENERGYNETWORK_BRIDGE_BLOCKNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (player != null) {
                if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() != null
                        && player.getCurrentEquippedItem().getItem() instanceof ItemPipeLinker && player.getCurrentEquippedItem().hasTagCompound()
                        && player.getCurrentEquippedItem().getTagCompound().getBoolean("hasManager")
                        && player.getCurrentEquippedItem().getTagCompound().getString("managerType").equals("energy")) {
                    int managerXCoord = player.getCurrentEquippedItem().stackTagCompound.getInteger("managerXCoord");
                    int managerYCoord = player.getCurrentEquippedItem().stackTagCompound.getInteger("managerYCoord");
                    int managerZCoord = player.getCurrentEquippedItem().stackTagCompound.getInteger("managerZCoord");
                    if (world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) != null
                            && world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) instanceof TileEntityEnergyNetworkManager) {
                        if (getTile(world, x, y, z) != null && getTile(world, x, y, z) instanceof TileEntityEnergyNetworkBridge) {
                            if (!((TileEntityEnergyNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord)).energyBridges
                                    .contains(((TileEntityEnergyNetworkBridge) world.getTileEntity(x, y, z)))) {
                                ((TileEntityEnergyNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord)).energyBridges
                                        .add(((TileEntityEnergyNetworkBridge) world.getTileEntity(x, y, z)));
                            }
                        }
                    } else {
                        player.getCurrentEquippedItem().setItemDamage(0);

                        player.getCurrentEquippedItem().getTagCompound().setBoolean("hasManager", false);
                        player.getCurrentEquippedItem().getTagCompound().removeTag("managerXCoord");
                        player.getCurrentEquippedItem().getTagCompound().removeTag("managerYCoord");
                        player.getCurrentEquippedItem().getTagCompound().removeTag("managerZCoord");
                        player.getCurrentEquippedItem().getTagCompound().removeTag("managerType");
                    }
                } else {
                    if (world.getBlockMetadata(x, y, z) == 0) {
                        world.setBlockMetadataWithNotify(x, y, z, 1, 2);
                        player.addChatComponentMessage(new ChatComponentText("Inputting to network"));
                        return true;
                    } else if (world.getBlockMetadata(x, y, z) == 1) {
                        world.setBlockMetadataWithNotify(x, y, z, 0, 2);
                        player.addChatComponentMessage(new ChatComponentText("Outputting from network"));
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergyNetworkBridge();
    }

}
