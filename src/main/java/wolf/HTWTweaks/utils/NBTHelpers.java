package wolf.HTWTweaks.utils;

import net.minecraft.item.ItemStack;
import wolf.HTWTweaks.refs.Consts;
import java.util.UUID;

public class NBTHelpers {
	public static boolean hasTag( ItemStack itemStack, String tag ) {
		if( itemStack == null ) return false;
		if( itemStack.getTagCompound() == null ) return false;
		return itemStack.getTagCompound().hasKey(tag);
	}

	public static boolean hasItemUUID( ItemStack itemStack ) {
		return hasTag( itemStack, Consts.NBT_TAGS.UUID_LEAST_SIG_ITEM) && hasTag( itemStack, Consts.NBT_TAGS.UUID_MOST_SIG_ITEM);
	}

	public static long getLongFromItem( ItemStack itemStack, String tag ) {
		if( hasTag(itemStack, tag) ) return itemStack.getTagCompound().getLong(tag);
		return 0;
	}
	
	public static boolean UUIDMatches( ItemStack a, UUID b ) {
		if( hasItemUUID( a ) ) {
			return (getLongFromItem(a, Consts.NBT_TAGS.UUID_MOST_SIG_ITEM) == b.getMostSignificantBits() &&
					getLongFromItem(a, Consts.NBT_TAGS.UUID_LEAST_SIG_ITEM) == b.getLeastSignificantBits());
		}
		return false;
	}
}
