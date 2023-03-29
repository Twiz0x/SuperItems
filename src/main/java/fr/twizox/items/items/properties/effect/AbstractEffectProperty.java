package fr.twizox.items.items.properties.effect;

import fr.twizox.items.items.BehaviorManager;
import fr.twizox.items.items.properties.ItemProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.potion.PotionEffect;

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
}
