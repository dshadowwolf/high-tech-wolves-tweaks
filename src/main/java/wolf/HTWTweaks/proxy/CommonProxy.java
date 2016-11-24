package wolf.HTWTweaks.proxy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

import wolf.HTWTweaks.recipes.RecipeManager;
import wolf.HTWTweaks.HTWTweaks;
import wolf.HTWTweaks.config.ConfigManager;
import wolf.HTWTweaks.blocks.blockManager;

public class CommonProxy {
   public static Configuration config;
   
   public void preInit(FMLPreInitializationEvent event) {
	   config = ConfigManager.init(event);
	   blockManager.init(event);
	   RecipeManager.init(event);
   }
   
   public void init( FMLInitializationEvent event ) {
	   NetworkRegistry.INSTANCE.registerGuiHandler(HTWTweaks.instance, new GuiProxy());
   }

   public void postInit( FMLPostInitializationEvent event ) {
	   if( config.hasChanged() ) {
		   config.save();
	   }
   }
}
