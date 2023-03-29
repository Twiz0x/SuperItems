package fr.twizox.items.items;

import fr.twizox.items.SuperItems;
import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public abstract class SuperItem {


    private final String id;
    private final ItemStack itemStack;
    private final List<ItemProperty> itemProperties = new ArrayList<>();

    public SuperItem(String id, ItemStack itemStack) {
        this.id = id;
        this.itemStack = itemStack;
    }

    public List<ItemProperty> getProperties() {
        return itemProperties;
    }

    public void addProperty(ItemProperty itemProperty) {
        itemProperties.add(itemProperty);
    }

    public String getId() {
        return id;
    }

    public ItemStack getItemStack() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.getPersistentDataContainer().set(
                NamespacedKey.fromString("superitem", JavaPlugin.getPlugin(SuperItems.class)),
                PersistentDataType.STRING,
                id);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public void giveItem(Player player) {
        player.getInventory().addItem(getItemStack());
    }

}
