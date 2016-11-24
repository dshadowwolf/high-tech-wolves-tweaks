package wolf.HTWTweaks;


/*
 * net.minecraft.tileentity.TileEntityFurnace
 * TileEntityFurnace.isItemFuel(ItemStack)  <--- is this a fuel ?
 */
import java.util.Locale;
import javax.annotation.Nonnull;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import static wolf.HTWTweaks.HTWTweaks.MODID;
import static wolf.HTWTweaks.HTWTweaks.MOD_NAME;
import static wolf.HTWTweaks.HTWTweaks.VERSION;
import wolf.HTWTweaks.proxy.CommonProxy;

@Mod(modid = MODID, name = MOD_NAME, version = VERSION, dependencies = "after:endercore;after:Waila;after:JEI@[3.11.1,)", guiFactory = "wolf.HTWTweaks.config.ConfigFactory")
public class HTWTweaks {
  public static final @Nonnull String MODID = "HTWTweaks";
  public static final @Nonnull String DOMAIN = MODID.toLowerCase(Locale.US);
  public static final String MOD_NAME = "HTWTweaks";
  public static final String VERSION = "@VERSION@";
  
  @Instance(MODID)
  public static HTWTweaks instance;
  
  @SidedProxy(clientSide = "wolf.HTWTweaks.ClientProxy", serverSide = "wolf.HTWTweaks.CommonProxy")
  public static CommonProxy proxy;
  
  public static Logger logger;
  
  @EventHandler
  public void preInit( FMLPreInitializationEvent event ) {
	  logger = event.getModLog();
	  proxy.preInit(event);
  }
  
  @EventHandler
  public void init( FMLInitializationEvent event ) {
	  proxy.init(event);
	  instance = this;
  }

  @EventHandler
  public void postInit( FMLPostInitializationEvent event ) {
	  proxy.postInit(event);
  }
    
  @EventHandler
  public void serverLoad( FMLServerStartingEvent event ) {
	  // register server-side commands
  }
  
}
