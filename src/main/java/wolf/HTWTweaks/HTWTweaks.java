package wolf.HTWTweaks;

import java.util.Locale;
import javax.annotation.Nonnull;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCMessage;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartedEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppedEvent;
import net.minecraftforge.fluids.*;

import static wolf.HTWTweaks.HTWTweaks.MODID;
import static wolf.HTWTweaks.HTWTweaks.MOD_NAME;
import static wolf.HTWTweaks.HTWTweaks.VERSION;

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
  
  //public static final PacketHandler packetPipeline = new PacketHandler();
  
  public static Fluid fluids;
  
  @EventHandler
  public void preInit( FMLPreInitializationEvent event ) {
	  // power handler, config pre-init, proxy load icons, setup fluids
	  // etc...
	  
	  // EnderIO also has a couple dummy-blocks it creates here to
	  // contain models for all other blocks
	  // should also guard against 'tlauncher' - which sells apparently stolen accounts
	  
	  // register with Waila!
	  FMLInterModComms.sendMessage("Waila",  "register",  "wolf.HTWTweaks.integration.waila.WailaCompat.load");
	  
	  // add materials to ore dictionary
	  
	  // call proxy preInit
  }
  
  @EventHandler
  public void init( FMLInitializationEvent event ) {
	  // init items, config, GUI here
	  // packet handler, etc..
	  // also register some other bits related to ores here
	  // and init the proxy at the end of everything
	  instance = this;
  }

  @EventHandler
  public void postInit( FMLPostInitializationEvent event ) {
	  // finalize config load, register loot table and enchantments
	  // setup rest of recipes
	  // register any fluids
	  // start cross-mod integration
  }
  
  @EventHandler
  public void loadComplete( FMLLoadCompleteEvent event ) {
	  // catch any inter-mod comms sent during postInit or earlier
  }
  
  @EventHandler
  public void serverStarted( FMLServerStartedEvent event ) {
	  // register server channel
  }
  
  @EventHandler
  public void serverStopped( FMLServerStoppedEvent event ) {
	  // do any server channel cleanup and data save
  }
  
  @EventHandler
  public void onImc( IMCEvent evt ) {
	  // handle IMC events
  }
  
  
}
