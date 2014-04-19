package darkevilmac.utilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.item.ItemPipeLinker;
import darkevilmac.utilities.lib.GuiIDS;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityItemNetworkBridge;
import darkevilmac.utilities.tile.TileEntityItemNetworkManager;
import darkevilmac.utilities.utils.InvUtils;

public class BlockItemNetworkBridge extends BlockUtilitiesContainer {

    protected BlockItemNetworkBridge() {
        super(Material.iron);
        setBlockName(Strings.ITEMNETWORK_BRIDGE_BLOCKNAME);
        setBlockTextureName(Reference.MOD_ID + ":" + Strings.ITEMNETWORK_BRIDGE_BLOCKNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        if (!world.isRemote) {
            if (player != null) {
                if (player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() != null
                        && player.getCurrentEquippedItem().getItem() instanceof ItemPipeLinker && player.getCurrentEquippedItem().hasTagCompound()
                        && player.getCurrentEquippedItem().getTagCompound().getInteger("dimID") == world.provider.dimensionId
                        && player.getCurrentEquippedItem().getTagCompound().getBoolean("hasManager")
                        && player.getCurrentEquippedItem().getTagCompound().getString("managerType").equals("item")) {
                    int managerXCoord = player.getCurrentEquippedItem().stackTagCompound.getInteger("managerXCoord");
                    int managerYCoord = player.getCurrentEquippedItem().stackTagCompound.getInteger("managerYCoord");
                    int managerZCoord = player.getCurrentEquippedItem().stackTagCompound.getInteger("managerZCoord");
                    if (world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) != null
                            && world.getTileEntity(managerXCoord, managerYCoord, managerZCoord) instanceof TileEntityItemNetworkManager) {
                        if (getTile(world, x, y, z) != null && getTile(world, x, y, z) instanceof TileEntityItemNetworkBridge) {
                            if (!((TileEntityItemNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord)).itemBridges
                                    .contains(((TileEntityItemNetworkBridge) world.getTileEntity(x, y, z)))) {
                                ((TileEntityItemNetworkManager) world.getTileEntity(managerXCoord, managerYCoord, managerZCoord)).itemBridges
                                        .add(((TileEntityItemNetworkBridge) world.getTileEntity(x, y, z)));
                                ((TileEntityItemNetworkBridge) world.getTileEntity(x, y, z)).setManager(((TileEntityItemNetworkManager) world.getTileEntity(managerXCoord,
                                        managerYCoord, managerZCoord)));
                                return true;
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
                    if (!player.isSneaking()) {
                        player.openGui(Utilities.instance, GuiIDS.ITEM_NETWORK_BRIDGE_GUIID, world, x, y, z);
                        return true;
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
        }
        return false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityItemNetworkBridge();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityItemNetworkBridge) {
                TileEntityItemNetworkBridge tile = (TileEntityItemNetworkBridge) world.getTileEntity(x, y, z);
                InvUtils.dropInventoryInWorld(world, tile, x, y, z);
                world.setTileEntity(x, y, z, null);
            }
        }
        super.breakBlock(world, x, y, z, block, par6);
    }

}
