package fr.twizox.items.items;

import fr.twizox.items.SuperItems;
import fr.twizox.items.items.sampleitems.HarvestHoe;
import fr.twizox.items.items.sampleitems.SuperPickaxe;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemManager {

    private final List<SuperItem> items = List.of(
            new SuperPickaxe(new ItemStack(Material.DIAMOND_PICKAXE)),
            new HarvestHoe(new ItemStack(Material.DIAMOND_HOE))
    );
    private final static ItemManager instance = new ItemManager();

    public static ItemManager getInstance() {
        return instance;
    }

    public void registerItem(SuperItem item) {
        items.add(item);
    }

    public List<SuperItem> getItems() {
        return new ArrayList<>(items);
    }

    public Optional<SuperItem> getItem(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta()) return Optional.empty();
        ItemMeta itemMeta = itemStack.getItemMeta();

        NamespacedKey namespacedKey = NamespacedKey.fromString("superitem", JavaPlugin.getPlugin(SuperItems.class));
        if (itemMeta.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING)) {
            String id = itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
            return items.stream().filter(superItem -> superItem.getId().equals(id)).findFirst();
        }
        return Optional.empty();
    }

    public boolean isSuperItem(ItemStack itemStack) {
        return getItem(itemStack).isPresent();
    }

}
