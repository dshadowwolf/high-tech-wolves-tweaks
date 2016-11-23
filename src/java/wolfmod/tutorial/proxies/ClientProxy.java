package wolfmod.tutorial.proxies;

import wolfmod.tutorial.proxies.CommonProxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);

//        OBJLoader.INSTANCE.addDomain(ModTut.MODID);
//        ModelLoaderRegistry.registerLoader(new BakedModelLoader());

        // Typically initialization of models and such goes here:
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);

        // Initialize our input handler so we can listen to keys
//        MinecraftForge.EVENT_BUS.register(new InputHandler());
//        KeyBindings.init();

//        ModBlocks.initItemModels();
    }
}
