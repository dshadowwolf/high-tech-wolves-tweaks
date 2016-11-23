package wolf.HTWTweaks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {

	  @Override
	  public World getClientWorld() {
	    return FMLClientHandler.instance().getClient().theWorld;
	  }

	  @Override
	  public boolean isAnEiInstalled() {
		  return false;
//	    return JeiAccessor.isJeiRuntimeAvailable();
	  }

	  @Override
	  public EntityPlayer getClientPlayer() {
	    return Minecraft.getMinecraft().thePlayer;
	  }

	  @Override
	  public void loadIcons() {
		  // EnderIO does some bits here, I don't know that it matters
//	    SmartModelAttacher.create();
//	    PaintRegistry.create();
	  }

	  @Override
	  public void preInit(FMLPreInitializationEvent event) {
	    super.preInit(event);
	    // tooltips, sounds, more models, etc...
	  }

	  @Override
	  public void init() {
	    super.init();
//	    SmartModelAttacher.registerColoredBlocksAndItems();
//	    MinecraftForge.EVENT_BUS.register(ClientNetworkManager.getInstance());
	  }

	  @Override
	  public double getReachDistanceForPlayer(EntityPlayer entityPlayer) {
	    if (entityPlayer instanceof EntityPlayerMP) {
	      return ((EntityPlayerMP) entityPlayer).interactionManager.getBlockReachDistance();
	    }
	    return super.getReachDistanceForPlayer(entityPlayer);
	  }

	  @Override
	  public void setInstantConfusionOnPlayer(EntityPlayer ent, int duration) {
	    ent.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, duration, 1, true, true));
	    Minecraft.getMinecraft().thePlayer.timeInPortal = 1;
	  }

	  @Override
	  public long getTickCount() {
	    return clientTickCount;
	  }

	  @Override
	  protected void onClientTick() {
	  }

	  //@Override
	  //public void markBlock(World worldObj, BlockPos pos, Vector4f color) {
	  //  Minecraft.getMinecraft().effectRenderer.addEffect(new MarkerParticle(worldObj, pos, color));
	  //}

	  @Override
	  protected void registerCommands() {
//	    ClientCommandHandler.instance.registerCommand(DebugCommand.CLIENT);
	  }

	  @Override
	  public boolean isDedicatedServer() {
	    return false;
	  }

	  @Override
	  public CreativeTabs getCreativeTab(ItemStack stack) {
	    return stack == null || stack.getItem() == null ? null : stack.getItem().getCreativeTab();
	  }

	  @Override
	  public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
	    itemIn.getSubItems(itemIn, tab, subItems);
	  }

	}



