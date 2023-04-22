package fr.twizox.superitem.items.properties.effect;

import fr.twizox.superitem.items.behaviors.BehaviorManager;
import fr.twizox.superitem.items.behaviors.ItemBehavior;
import fr.twizox.superitem.items.properties.ItemProperty;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;
import java.util.Optional;

public abstract class AbstractEffectProperty<T extends Event> implements ItemProperty<T> {

    private final PotionEffect potionEffect;

    public AbstractEffectProperty(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    protected boolean checkItem(final Player player, final ItemStack itemStack) {
        BehaviorManager behaviorManager = Bukkit.getServicesManager().load(BehaviorManager.class);
        Objects.requireNonNull(behaviorManager, "BehaviorManager is not registered");

        Optional<ItemBehavior> behavior = behaviorManager.getBehavior(itemStack);
        return behavior.isPresent() && behavior.get().getProperties().stream().anyMatch(this::equals);
    }

    protected void applyEffect(final Player player) {
        player.addPotionEffect(potionEffect);
    }

    protected void removeEffect(final Player player) {
        if (player.getActivePotionEffects().stream().anyMatch(this::effectEquals))
            player.removePotionEffect(potionEffect.getType());
    }

    protected boolean effectEquals(final PotionEffect effect) {
        return effect.getType().equals(potionEffect.getType()) && effect.getAmplifier() == potionEffect.getAmplifier();
    }

    public static PotionEffect getPotionEffect(ConfigurationSection section) {
        String typeString = section.getString("type");
        Objects.requireNonNull(typeString, "Missing effect type in section " + section.getName());
        PotionEffectType type = PotionEffectType.getByName(typeString);
        Objects.requireNonNull(type, "Invalid effect type in section " + section.getName());
        return new PotionEffect(
                type,
                section.getInt("duration", 200),
                section.getInt("amplifier", 2)
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        return obj instanceof AbstractEffectProperty<?> &&
                obj.getClass().equals(getClass()) &&
                potionEffect.equals(((AbstractEffectProperty<?>) obj).getPotionEffect());
    }

}
