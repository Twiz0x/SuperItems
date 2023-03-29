package fr.twizox.items.items;

import fr.twizox.items.SuperItems;
import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ItemBehavior {


    private final String behaviorId;
    private final List<ItemProperty> itemProperties = new ArrayList<>();

    public ItemBehavior(String behaviorId) {
        this(behaviorId, new ItemProperty[0]);
    }

    public ItemBehavior(String behaviorId, ItemProperty<?>... itemProperties) {
        this.behaviorId = behaviorId;
        this.itemProperties.addAll(Arrays.asList(itemProperties));
    }

    public String getBehaviorId() {
        return behaviorId;
    }

    public List<ItemProperty> getProperties() {
        return itemProperties;
    }

    public void addProperty(ItemProperty itemProperty) {
        itemProperties.add(itemProperty);
    }

    public Stream<ItemProperty> getApplicableProperties(Event event) {
        return itemProperties.stream().filter(itemProperty -> itemProperty.isApplicable(event));
    }

    public void apply(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return;
        itemMeta.getPersistentDataContainer().set(
                NamespacedKey.fromString("item_behavior", JavaPlugin.getPlugin(SuperItems.class)),
                PersistentDataType.STRING,
                behaviorId);
        item.setItemMeta(itemMeta);
    }

}
