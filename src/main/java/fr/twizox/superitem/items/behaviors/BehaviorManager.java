package fr.twizox.superitem.items.behaviors;

import fr.twizox.superitem.SuperItems;
import fr.twizox.superitem.items.properties.PropertyManager;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class BehaviorManager {

    private final PropertyManager propertyManager;
    private final JavaPlugin plugin = JavaPlugin.getPlugin(SuperItems.class);
    private final Map<BehaviorId, ItemBehavior> behaviors;

    public BehaviorManager(PropertyManager propertyManager) {
        this.propertyManager = propertyManager;
        this.behaviors = new HashMap<>();
    }

    public void addBehavior(ItemBehavior itemBehavior) {
        behaviors.put(itemBehavior.getBehaviorId(), itemBehavior);
    }

    public void addBehavior(String id, List<String> properties) {
        BehaviorId behaviorId = new BehaviorId(id);
        ItemBehavior itemBehavior = new ItemBehavior(behaviorId);
        properties.stream()
                .map(propertyManager::getProperty)
                .flatMap(Optional::stream)
                .forEach(itemBehavior::addProperty);
        addBehavior(itemBehavior);
    }

    public void addBehaviors(ConfigurationSection behaviors) {
        Objects.requireNonNull(behaviors, "Behaviors section not found!");
        behaviors.getKeys(false)
                .forEach(behaviorId -> addBehavior(behaviorId, behaviors.getStringList(behaviorId)));
    }

    public void removeBehavior(BehaviorId id) {
        behaviors.remove(id);
    }

    public void removeBehavior(ItemBehavior itemBehavior) {
        removeBehavior(itemBehavior.getBehaviorId());
    }


    public Optional<ItemBehavior> getBehavior(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta()) return Optional.empty();
        ItemMeta itemMeta = itemStack.getItemMeta();

        NamespacedKey namespacedKey = NamespacedKey.fromString("item_behavior", plugin);

        if (namespacedKey == null) return Optional.empty();
        String id = itemMeta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING);
        if (id == null) return Optional.empty();
        return Optional.ofNullable(behaviors.get(new BehaviorId(id)));
    }

    public Optional<ItemBehavior> getBehavior(String behaviorId) {
        return Optional.ofNullable(behaviors.get(new BehaviorId(behaviorId)));
    }

    public Map<BehaviorId, ItemBehavior> getBehaviors() {
        return new HashMap<>(behaviors);
    }

    public List<BehaviorId> getBehaviorIds() {
        return new ArrayList<>(behaviors.keySet());
    }


    public boolean isSuperItem(ItemStack itemStack) {
        return getBehavior(itemStack).isPresent();
    }

}
