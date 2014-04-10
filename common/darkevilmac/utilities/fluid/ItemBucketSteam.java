package darkevilmac.utilities.fluid;

import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import darkevilmac.utilities.Utilities;
import darkevilmac.utilities.lib.Reference;
import darkevilmac.utilities.lib.Strings;

public class ItemBucketSteam extends ItemBucket {

    public ItemBucketSteam() {
        super(ModFluids.fluidSteam.getBlock());
        setContainerItem(Items.bucket);
        setUnlocalizedName(Strings.BUCKET_STEAM_UNLOCALIZEDNAME);
        setTextureName(Reference.MOD_ID + ":" + Strings.BUCKET_STEAM_UNLOCALIZEDNAME);
        setCreativeTab(Utilities.modTab);
    }

}
