package fr.twizox.superitem.items.behaviors;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import fr.twizox.superitem.SuperItems;
import fr.twizox.superitem.items.properties.PropertyManager;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

@Singleton
public class BehaviorManager {

    private final PropertyManager propertyManager;
    private final Map<String, ItemBehavior> behaviors;

    @Inject
    public BehaviorManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
        this.behaviors = new HashMap<>();
    }

    public void addBehavior(ItemBehavior itemBehavior) {
        behaviors.put(itemBehavior.getBehaviorId(), itemBehavior);
    }

    public void addBehavior(String behaviorId, List<String> properties) {
        ItemBehavior itemBehavior = new ItemBehavior(behaviorId);
        properties.stream()
                .filter(propertyManager::hasProperty)
                .map(propertyManager::getProperty)
                .forEach(itemBehavior::addProperty);
        addBehavior(itemBehavior);
    }

    public void addBehaviors(ConfigurationSection behaviors) {
        if (behaviors == null)
            throw new IllegalArgumentException("Behaviors section not found!");
        behaviors.getKeys(false)
                .forEach(behaviorId -> addBehavior(behaviorId, behaviors.getStringList(behaviorId)));
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

    public Map<String, ItemBehavior> getBehaviors() {
        return new HashMap<>(behaviors);
    }

    public List<String> getBehaviorIds() {
        return new ArrayList<>(behaviors.keySet());
    }


    public boolean isSuperItem(ItemStack itemStack) {
        return getBehavior(itemStack).isPresent();
    }

}
