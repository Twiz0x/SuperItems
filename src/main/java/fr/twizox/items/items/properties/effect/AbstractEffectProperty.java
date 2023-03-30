package fr.twizox.items.items.properties.effect;

import fr.twizox.items.items.BehaviorManager;
import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class AbstractEffectProperty<T extends Event> implements ItemProperty<T> {

    private final PotionEffect potionEffect;

    public AbstractEffectProperty(PotionEffect potionEffect) {
        this.potionEffect = potionEffect;
    }

    public PotionEffect getPotionEffect() {
        return potionEffect;
    }

    protected boolean checkItem(final Player player) {
        if (!BehaviorManager.INSTANCE.isSuperItem(player.getInventory().getItemInMainHand())) {
            player.getActivePotionEffects().removeIf(effect -> effect.getType() == potionEffect.getType() && effect.getAmplifier() == potionEffect.getAmplifier());
            return false;
        }
        return true;
    }

    protected void applyEffect(final Player player) {
        player.addPotionEffect(potionEffect);
    }

    protected static PotionEffect getPotionEffect(ConfigurationSection section) {

        PotionEffectType type = PotionEffectType.getByName(section.getString("effect"));
        if (type == null) throw new IllegalArgumentException("Invalid effect type in section " + section.getName());

        return new PotionEffect(
                type,
                section.getInt("duration", 200),
                section.getInt("amplifier", 2)
        );
    }
}
