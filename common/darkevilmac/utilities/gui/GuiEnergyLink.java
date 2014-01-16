package darkevilmac.utilities.gui;

import darkevilmac.MotherOfPearl.lib.Reference;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Darkevilmac @ 5:32 AM on 01/14/2014
 */


/**
 * Not even close to done just started.
 */
public class GuiEnergyLink extends GuiScreen{



    ResourceLocation texture = new ResourceLocation(Reference.MOD_ID+"textures/gui/box.png");

    public final int textureSize = 256;
    public final int textureSizeX = textureSize;
    public final int textureSizeY = textureSize;

    public GuiEnergyLink(EntityPlayer player){

    }

    @Override
    public void drawScreen(int x,int y,float f){
        drawDefaultBackground();

        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
    }

}
