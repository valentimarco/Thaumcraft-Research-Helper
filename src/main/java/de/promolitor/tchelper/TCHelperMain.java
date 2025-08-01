package de.promolitor.tchelper;

import de.promolitor.tchelper.commands.CommandSetScale;
import de.promolitor.tchelper.resolver.AspectCalculation;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = TCHelperMain.MODID, version = Tags.VERSION, name = TCHelperMain.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class TCHelperMain {

	public static final String VERSION = "1.4";
	public static final String MODID = "tchelper";
	public static final String MODNAME = "TCResearch-Helper";
	public static final Logger LOG = LogManager.getLogger(MODID);

	/*
	 * @Instance(MODID)
	 * public static TCHelperMain instance;
	 */
	protected static final Minecraft mc = Minecraft.getMinecraft();
	public static final int GuiMainID = 0;
	public static boolean debugging = true;

	@SideOnly(Side.CLIENT)
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());
		MinecraftForge.EVENT_BUS.register(new KeyHandler());
		ClientCommandHandler.instance.registerCommand(new CommandSetScale());
	}

	@SideOnly(Side.CLIENT)
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@SideOnly(Side.CLIENT)
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		  AspectCalculation.createCosts();
		  MinecraftForge.EVENT_BUS.register(new RenderGuiHandler());
          for (var p  : AspectCalculation.cost.entrySet()) {
            LOG.info(p.getKey().getTag() + "=" + p.getValue());
          }



	}

}
