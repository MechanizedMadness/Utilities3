package darkevilmac.utilities.events;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EatSaplingEvent {

    public static Item saplingItem;
    public static Item userItem;

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

    @SubscribeEvent
    public void onPlayerInteract(PlayerInteractEvent event) {
        /*
         * if (event.entityPlayer.getCurrentEquippedItem() != null) { if
         * (event.action == Action.RIGHT_CLICK_BLOCK || event.action ==
         * Action.RIGHT_CLICK_AIR) { Item saplingItem = (Item)
         * Block.blockRegistry.getObject("sapling"); Item userItem = (Item)
         * event.entityPlayer.getCurrentEquippedItem().getItem(); if (userItem
         * == saplingItem) { if (event.entityPlayer.worldObj.getBlock(event.x,
         * event.y, event.z).getMaterial() != Material.grass) { if
         * (event.entityPlayer.worldObj.getBlock(event.x, event.y,
         * event.z).getMaterial() != Material.ground) { if
         * (event.entityPlayer.canEat(true)) {
         * event.entityPlayer.addPotionEffect(new PotionEffect(2, 1, 2));
         * event.entityPlayer.getFoodStats().addStats(1, 4);
         * event.entityPlayer.worldObj.playSoundAtEntity(event.entityPlayer,
         * "random.burp", 0.5F, event.entityPlayer.worldObj.rand.nextFloat() *
         * 0.1F + 0.9F); event.entityPlayer.setCurrentItemOrArmor( 0,
         * EatSaplingEvent.makeItem2((Block)
         * Block.blockRegistry.getObject("sapling"),
         * --event.entityPlayer.getCurrentEquippedItem().stackSize)); }
         * 
         * } } } } }
         */
    }
}
