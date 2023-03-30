package fr.twizox.items.items;

import fr.twizox.items.SuperItems;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum BehaviorManager {

    INSTANCE;

    private final Map<String, ItemBehavior> behaviors = new HashMap<>();

    public void addBehavior(ItemBehavior itemBehavior) {
        behaviors.put(itemBehavior.getBehaviorId(), itemBehavior);
    }

    public void removeBehavior(String id) {
        behaviors.remove(id);
    }

    public void removeBehavior(ItemBehavior itemBehavior) {
        behaviors.remove(itemBehavior.getBehaviorId());
    }

    public Optional<ItemBehavior> getBehavior(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta()) return Optional.empty();
        ItemMeta itemMeta = itemStack.getItemMeta();

        NamespacedKey namespacedKey = NamespacedKey.fromString("item_behavior", JavaPlugin.getPlugin(SuperItems.class));

        if (namespacedKey != null && itemMeta.getPersistentDataContainer().has(namespacedKey, PersistentDataType.STRING)) {
            String id = itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
            return Optional.ofNullable(behaviors.get(id));
        }
        return Optional.empty();
    }

    public Optional<ItemBehavior> getBehavior(String behaviorId) {
        return Optional.ofNullable(behaviors.get(behaviorId));
    }

    public boolean isSuperItem(ItemStack itemStack) {
        return getBehavior(itemStack).isPresent();
    }

}
