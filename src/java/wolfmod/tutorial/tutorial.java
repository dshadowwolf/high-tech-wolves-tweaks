package wolfmod.tutorial;

import wolfmod.tutorial.proxies.CommonProxy;
import wolfmod.tutorial.commands.WarpCommand;
import wolfmod.tutorial.commands.PwarpCommand;
import wolfmod.tutorial.commands.HomeCommand;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = tutorial.MODID, name = tutorial.MODNAME, version = tutorial.VERSION)
public class tutorial {

    public static final String MODID = "tutorial";
    public static final String MODNAME = "Tutorial Mod";
    public static final String VERSION = "1.0.0";
    
    @SidedProxy(clientSide="wolfmod.tutorial.proxy.ClientProxy", serverSide="wolfmod.tutorial.proxy.ServerProxy")
    public static CommonProxy proxy;
    
    @Mod.Instance
    public static tutorial instance = new tutorial();
        
    public static Logger logger;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
       logger = e.getModLog();
       proxy.preInit(e);
    }
        
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
    	proxy.init(e);
    }
        
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
    	proxy.postInit(e);
    }
    
    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent e) {
    	e.registerServerCommand(new WarpCommand());
    	e.registerServerCommand(new PwarpCommand());
    	e.registerServerCommand(new HomeCommand());
    }
}