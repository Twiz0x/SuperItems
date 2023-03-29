package fr.twizox.items.items.sampleitems;

import fr.twizox.items.items.SuperItem;
import fr.twizox.items.items.properties.farm.HarvestProperty;
import org.bukkit.inventory.ItemStack;

public class HarvestHoe extends SuperItem {

    public HarvestHoe(ItemStack itemStack) {
        super("super_hoe", itemStack);
        addProperty(new HarvestProperty());
    }

}
