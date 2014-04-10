package darkevilmac.utilities.fluid;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class ItemBucketFluidEnergy extends ItemBucket {

    public ItemBucketFluidEnergy() {
        super(ModFluids.fluidEnergy.getBlock());
        setContainerItem(Items.bucket);
        setUnlocalizedName(Strings.BUCKET_FLUID_ENERGY_UNLOCALIZEDNAME);
        setTextureName(Reference.MOD_ID + ":" + Strings.BUCKET_FLUID_ENERGY_UNLOCALIZEDNAME);
        setCreativeTab(Utilities.modTab);
    }

}
