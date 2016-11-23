package wolfmod.tutorial.proxies;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class CommonProxy {
  public static Configuration config;
  
  public void preInit(FMLPreInitializationEvent e) {
      File directory = e.getModConfigurationDirectory();
      config = new Configuration(new File(directory.getPath(), "wolfmod-tut.cfg"));
      config.load();

      // register blocks, items, etc..
  }

  public void init(FMLInitializationEvent e) {
	  // register recipes
  }

  public void postInit(FMLPostInitializationEvent e) {
      if (config.hasChanged()) {
          config.save();
      }
  }
}
