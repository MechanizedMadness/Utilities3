package darkevilmac.utilities.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundList;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.util.ResourceLocation;
import paulscode.sound.SoundSystem;
import cpw.mods.fml.common.ObfuscationReflectionHelper;

public class SoundSystemUtils {

    public static SoundHandler getSoundHandler() {
        return Minecraft.getMinecraft().getSoundHandler();
    }

    public static SoundManager getSoundManager() {
        return ObfuscationReflectionHelper.getPrivateValue(SoundHandler.class, getSoundHandler(), "sndManager", "field_147694_f");
    }

    public static SoundSystem getSoundSystem() {
        return ObfuscationReflectionHelper.getPrivateValue(SoundManager.class, getSoundManager(), "sndSystem", "field_148620_e");
    }

    public static void addSound(ResourceLocation sound, SoundList list) {
        try {

            Method method = getSoundHandler().getClass().getDeclaredMethod("loadSoundResource", ResourceLocation.class, SoundList.class);
            method.setAccessible(true);
            method.invoke(getSoundHandler(), sound, list);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
