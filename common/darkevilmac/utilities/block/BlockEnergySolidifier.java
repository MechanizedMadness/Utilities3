package darkevilmac.utilities.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.block.base.BlockUtilitiesContainer;
import darkevilmac.utilities.lib.GuiIDS;
import darkevilmac.utilities.lib.Strings;
import darkevilmac.utilities.tile.TileEntityEnergySolidifier;
import darkevilmac.utilities.utils.InvUtils;

public class BlockEnergySolidifier extends BlockUtilitiesContainer {

    protected BlockEnergySolidifier() {
        super(Material.iron);
        setBlockName(Strings.ENERGY_SOLIDIFIER_BLOCKNAME);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9) {
        player.openGui(Utilities.instance, GuiIDS.ENERGY_SOLIDIFIER_GUIID, world, x, y, z);
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int var2) {
        return new TileEntityEnergySolidifier();
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int par6) {
        if (!world.isRemote) {
            if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TileEntityEnergySolidifier) {
                TileEntityEnergySolidifier tile = (TileEntityEnergySolidifier) world.getTileEntity(x, y, z);
                InvUtils.dropInventoryInWorld(world, tile, x, y, z);
            }
        }
    }

}
