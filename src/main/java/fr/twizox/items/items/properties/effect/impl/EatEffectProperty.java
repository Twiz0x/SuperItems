package fr.twizox.items.items.properties.effect.impl;

import fr.twizox.items.items.properties.ItemProperty;
import fr.twizox.items.items.properties.effect.AbstractEffectProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Salers
 * made on fr.twizox.items.items.properties.effect.impl
 */
public class EatEffectProperty extends AbstractEffectProperty implements ItemProperty<PlayerItemConsumeEvent> {

    public EatEffectProperty(PotionEffectType type, int amplifier, int duration) {
        super(type, amplifier, duration);
    }

    @Override
    public void handle(PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();

        if (!checkItem(player)) return;

        applyEffect(player);

    }
}
