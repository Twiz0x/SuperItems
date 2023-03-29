package fr.twizox.items.items.sampleitems;

import fr.twizox.items.items.SuperItem;
import fr.twizox.items.items.properties.block.ExcavatorProperty;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SuperPickaxe extends SuperItem {

    public SuperPickaxe(ItemStack itemStack) {
        super("super_pickaxe", itemStack);
        addProperty(new ExcavatorProperty(1, 0, List.of(Material.STONE)));
    }

}
