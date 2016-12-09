package wolf.HTWTweaks.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;
import wolf.HTWTweaks.refs.DataTypes;
import wolf.HTWTweaks.refs.Consts;
import wolf.HTWTweaks.utils.NBTHelpers;

import java.util.UUID;

public abstract class ITweaksContainer implements IInventory, INBTSerializable {
	public ItemStack parentItem;
	public static int INV_SIZE = 0;
	protected ItemStack[] inventory;
	public ItemStack[] itemSlots = new ItemStack[64];
    public String customName;
    public String baseName;
    public NBTTagCompound tag;
    public ItemStack item;
    boolean hasCompound = false;
    
    public ITweaksContainer(ItemStack itemStack) {
    	ITweaksItem item = (ITweaksItem) itemStack.getItem();
    	int size = item.getSize();
    
    	hasCompound = item.getDataType() == DataTypes.NO_DATA;
    	if( size == 0 ) size = 1;
    	inventory = new ItemStack[size];
    	
    	if( !hasCompound ) {
    		// we haven't, apparently, stored data here yet.
    		if( !itemStack.hasTagCompound()) {
    			// the item stack doesn't have a compound NBT tag yet!
    			itemStack.setTagCompound(new NBTTagCompound());
    		}
    		tag = itemStack.getTagCompound();
    		this.item = itemStack;
    		readFromNBT(itemStack.getTagCompound());
    	} else {
    		// we've got the data already, somehow...
    		itemSlots = new ItemStack[size];
    		if( itemStack.hasTagCompound() == false ) {
    			itemStack.setTagCompound(new NBTTagCompound());
    		}
    		tag = itemStack.getTagCompound();
    		this.item = itemStack;
    		stackSize = 64;
    	}
    }
    
    public void onGuiSaved( EntityPlayer player ) {
    	parentItem = findParentItemStack(player);
    	
    	if( parentItem != null ) {
    		save();
    	}
    }
    
    public ItemStack findParentItemStack( EntityPlayer player ) {
    	if( NBTHelpers.hasItemUUID(parentItem) ) {
    		UUID parentItemUUID = new UUID( NBTHelpers.getLongFromItem(parentItem, Consts.NBT_TAGS.UUID_MOST_SIG_ITEM),
    				                        NBTHelpers.getLongFromItem(parentItem, Consts.NBT_TAGS.UUID_LEAST_SIG_ITEM) );
    		for( int i = 0; i < player.inventory.getSizeInventory(); i++ ) {
    			ItemStack tempStack = player.inventory.getStackInSlot(i);
    			
    			if( NBTHelpers.UUIDMatches( tempStack, parentItemUUID ) ) {
    				return tempStack;
    			}
    		}
    	}
    	return null;
    }
    
    public void save() {
    	NBTTagCompound cTag = parentItem.getTagCompound();
    	
    	if( cTag == null ) {
    		cTag = new NBTTagCompound();
    		
    		UUID uuid = UUID.randomUUID();
    		cTag.setLong(Consts.NBT_TAGS.UUID_MOST_SIG_ITEM, uuid.getMostSignificantBits());
    		cTag.setLong(Consts.NBT_TAGS.UUID_LEAST_SIG_ITEM, uuid.getLeastSignificantBits());
    	}
    	
    	writeToNBT(cTag);
    	parentItem.setTagCompound(cTag);
    }
    
    @Override
    public int getSizeInventory() {
    	if( !hasCompound ) return inventory.length;
    	return 1;
    }
    
    @Override
    public ItemStack getStackInSlot(int slotIndex) {
    	if( !hasCompound )
    		return inventory[slotIndex];
    	else
    		return slotIndex == 64 ? null : itemSlots[slotIndex];
    }
    
    @Override
    public ItemStack decrStackSize(int slotIndex, int decrementAmount) {
    	// call getStackInSlot, if null, return null
    	// otherwise check to see if the stack has more than the decrement
    	// if it doesn't, just null the slot
    	// otherwise, split the stack and markDirty()
    	ItemStack work = getStackInSlot(slotIndex);
    	if( work == null ) return null;
    	if( work.stackSize <= decrementAmount ) setInventorySlotContents(slotIndex,null);
    	else {
    		work = work.splitStack(decrementAmount);
    		if( work.stackSize == 0 ) setInventorySlotContents(slotIndex,null);
    	}
    	markDirty();
    	return work;
    }
    
    @Override
    public ItemStack removeStackFromSlot(int i) {
    	if( hasCompound ) return getStackInSlot(i);
    	else {
    		ItemStack w = null;
    		if( inventory[i] != null ) {
    			w = inventory[i];
    			inventory[i] = null;
    			
    		}
    		return w;
    	}
    }
    
    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
    	if( !hasCompound ) {
    		inventory[i] = itemStack;
    		if((itemStack != null) && (itemStack.stackSize > getInventoryStackLimit())) {
    			itemStack.stackSize = getInventoryStackLimit();
    		}
    	} else {
    		if( i != 64 ) {
    			itemSlots[i] = itemStack;

    		}
    	}
    	markDirty();
    }
    
    public void processInv() {
    	if( itemSlots[0] != null ) itemSlots[0] = null;
    }
    
    @Override
    public int getInventoryStackLimit() {
    	return 64;
    }
    
    @Override
    public void markDirty() {
    	writeToNBT(tag);
    	setNBT(item);
    }
    
    public void setNBT(ItemStack item) {
    	item.setTagCompound(tag);
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
    	// should actually put an ownership check in here, but...
    	return true;
    }
    
    @Override
    public void openInventory(EntityPlayer player) {
    	readFromNBT(tag);
    }

    @Override
    public void closeInventory(EntityPlayer entityPlayer) {
        writeToNBT(tag);
    }

    @Override
    public boolean isItemValidForSlot(int slotIndex, ItemStack itemStack) {
        if (!hasCompound)
            return true;
        else
            return slotIndex <= 64;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {

    }
    
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
    	
    }
    
    public void writeToNBT(NBTTagCompound nbtTagCompound) {

    }
}
