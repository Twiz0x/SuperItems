package fr.twizox.items.items.properties.effect;

import fr.twizox.items.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public abstract class AbstractEffectProperty {

    private final PotionEffectType type;
    private final int amplifier, duration;

    public AbstractEffectProperty(PotionEffectType type, int amplifier, int duration) {
        this.type = type;
        this.amplifier = amplifier;
        this.duration = duration;
    }

    public PotionEffectType getType() {
        return type;
    }

    public int getAmplifier() {
        return amplifier;
    }

    public int getDuration() {
        return duration;
    }

    protected boolean checkItem(final Player player) {
        if (!ItemManager.INSTANCE.isSuperItem(player.getItemInHand())) {
            player.getActivePotionEffects().removeIf(effect -> effect.getAmplifier() == getAmplifier() && effect.getType() == getType());
            return false;
        }

        return true;
    }

    protected void applyEffect(final Player player) {
        player.addPotionEffect(new PotionEffect(getType(), getDuration(), getAmplifier()));
    }
}
