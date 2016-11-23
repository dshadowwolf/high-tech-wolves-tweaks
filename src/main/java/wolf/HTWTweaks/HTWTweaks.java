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

@Mod(modid = "HTWTweaks", name = "HTWTweaks", version = "@VERSION@", dependencies = "after:endercore;after:Waila;after:JEI@[3.11.1,)", guiFactory = "wolf.HTWTweaks.config.ConfigFactory")
public class HTWTweaks {
  public static final @Nonnull String MODID = "HTWTweaks";
  public static final @Nonnull String DOMAIN = MODID.toLowerCase(Locale.US);
  public static final String MOD_NAME = "HTWTweaks";
  public static final String VERSION = "@VERSION@";
  
}
