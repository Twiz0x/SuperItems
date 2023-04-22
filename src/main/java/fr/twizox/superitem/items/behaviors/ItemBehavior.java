package fr.twizox.superitem.items.behaviors;

import fr.twizox.superitem.SuperItems;
import fr.twizox.superitem.items.properties.ItemProperty;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemBehavior {

    private final BehaviorId behaviorId;
    private final List<ItemProperty<?>> itemProperties;

    public ItemBehavior(BehaviorId behaviorId) {
        this(behaviorId, new ArrayList<>());
    }

    public ItemBehavior(BehaviorId behaviorId, ItemProperty<?>... itemProperties) {
        this(behaviorId, Arrays.asList(itemProperties));
    }

    public ItemBehavior(BehaviorId behaviorId, List<ItemProperty<?>> itemProperties) {
        this.behaviorId = behaviorId;
        this.itemProperties = itemProperties;
    }

    public BehaviorId getBehaviorId() {
        return behaviorId;
    }

    public List<ItemProperty<?>> getProperties() {
        return itemProperties;
    }

    public void addProperty(ItemProperty<?> itemProperty) {
        itemProperties.add(itemProperty);
    }

    public void apply(ItemStack item) {
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta == null) return;

        itemMeta.getPersistentDataContainer().set(
                NamespacedKey.fromString("item_behavior", JavaPlugin.getPlugin(SuperItems.class)),
                PersistentDataType.STRING,
                behaviorId.toString());
        item.setItemMeta(itemMeta);
    }

    @Override
    public String toString() {
        return "ItemBehavior{" +
                "behaviorId='" + behaviorId + '\'' +
                ", itemProperties=" + itemProperties +
                '}';
    }
}
