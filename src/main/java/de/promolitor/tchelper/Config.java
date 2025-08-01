package de.promolitor.tchelper;

import java.io.File;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class Config {

    private static Configuration configuration;
    private static Property aspectScaleProp;
    private static Property topDistanceProp;
    private static Property leftSideProp;

    public static Integer aspectScale;
    public static Integer topDistance;
    public static Boolean leftSide;

    public static void synchronizeConfiguration(File configFile) {
        configuration = new Configuration(configFile);
        aspectScaleProp = configuration.get(Configuration.CATEGORY_GENERAL, "AspectScale", 8,
            "How big the Overlay should be!", 1,64);
        topDistanceProp = configuration.get(Configuration.CATEGORY_GENERAL,"TopDistance", 20,
            "Distance between top and first Aspect-Path", 1, 400);
        leftSideProp = configuration.get(Configuration.CATEGORY_GENERAL,"LeftSide", true,
            "Switch to false to put the Aspect-Paths on the right side.");

        Config.load();

        FMLCommonHandler.instance().bus().register(new ChangeListener());

    }

    private static void load() {
        aspectScale = aspectScaleProp.getInt();
        topDistance = topDistanceProp.getInt();
        leftSide = leftSideProp.getBoolean();

        if(configuration.hasChanged()) configuration.save();
    }

    public static void setAspectScale(Integer value) {
        aspectScaleProp.set(value);
        aspectScale = aspectScaleProp.getInt();
        configuration.save();
    }

    public static void setTopDistance(Integer value) {
        topDistanceProp.set(value);
        topDistance = topDistanceProp.getInt();
        configuration.save();
    }

    public static void setLeftSide(Boolean value) {
        leftSideProp.set(value);
        leftSide = leftSideProp.getBoolean();
        configuration.save();
    }

    public static class ChangeListener {

        @SubscribeEvent
        public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
            if (eventArgs.modID.equals(TCHelperMain.MODID))
                load();
        }

    }
}
