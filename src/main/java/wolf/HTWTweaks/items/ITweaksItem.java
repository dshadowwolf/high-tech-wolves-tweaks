/**
 * 
 */
package wolf.HTWTweaks.items;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import wolf.HTWTweaks.refs.DataTypes;

/**
 * @author madman
 *
 */
public abstract class ITweaksItem extends Item implements IBauble {

	private DataTypes type = DataTypes.NO_DATA;
	
	public ITweaksItem() {
	}

	public ITweaksItem( String name ) {
		this.setUnlocalizedName(name);
		this.setRegistryName(name);

		this.setMaxStackSize(1);
	}
	
	public int getSize() {
		return 0;
	}
	
	public DataTypes getDataType() {
		return type;
	}
	
	public void setDataType(DataTypes newType) {
		this.type = newType;
	}
	
	@Override
	public BaubleType getBaubleType(ItemStack arg0) {
		return BaubleType.TRINKET;
	}
	
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer, EnumHand hand) {
        if (!world.isRemote) {
            IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(entityPlayer);
            for (int i = 0; i < baubles.getSlots(); i++)
                if (baubles.getStackInSlot(i) == null && baubles.isItemValidForSlot(i, itemStack, entityPlayer)) {
                    baubles.setStackInSlot(i, itemStack.copy());
                    if (!entityPlayer.capabilities.isCreativeMode) {
                        entityPlayer.inventory.setInventorySlotContents(entityPlayer.inventory.currentItem, null);
                    }
                    onEquipped(itemStack, entityPlayer);
                    break;
                }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStack);
    }

    @Override
    public boolean canEquip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public boolean canUnequip(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }
}
