package darkevilmac.utilities.events;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;

public class EatSaplingEvent {

    private int saplingItemID;
    private int userItemID;

    public static ItemStack makeItem(Block b) {
        ItemStack item = new ItemStack(b);
        return item;
    }

    public static ItemStack makeItemItem(Item i) {
        ItemStack item = new ItemStack(i);
        return item;
    }

    public static ItemStack makeItem2(Block b, int n) {
        ItemStack item = new ItemStack(b, n);
        return item;
    }

    @ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.entityPlayer.getCurrentEquippedItem() != null) {
            if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR) {
                saplingItemID = Block.sapling.blockID;
                userItemID = event.entityPlayer.getCurrentEquippedItem().itemID;
                if (userItemID == saplingItemID) {
                    if (event.entityPlayer.worldObj.getBlockMaterial(event.x, event.y, event.z) != Material.grass) {
                        if (event.entityPlayer.worldObj.getBlockMaterial(event.x, event.y, event.z) != Material.ground) {
                            if (event.entityPlayer.canEat(true)) {
                                event.entityPlayer.addPotionEffect(new PotionEffect(2, 1, 2));
                                event.entityPlayer.getFoodStats().addStats(1, 4);
                                event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer, "random.burp", 0.5F,
                                        event.entityPlayer.worldObj.rand.nextFloat() * 0.1F + 0.9F);
                                event.entityPlayer.setCurrentItemOrArmor(
                                        0,
                                        makeItem2(Block.sapling,
                                                --event.entityPlayer.getCurrentEquippedItem().stackSize));
                            }
                        }
                    }
                }
            }
        }
    }
}
