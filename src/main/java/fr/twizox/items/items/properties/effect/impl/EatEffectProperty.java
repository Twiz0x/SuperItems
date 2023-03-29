package fr.twizox.items.items.properties.effect.impl;

import fr.twizox.items.items.properties.effect.AbstractEffectProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;

/**
 * @author Salers
 * made on fr.twizox.items.items.properties.effect.impl
 */
public class EatEffectProperty extends AbstractEffectProperty<PlayerItemConsumeEvent> {

    public EatEffectProperty(PotionEffect potionEffect) {
        super(potionEffect);
    }

    @Override
    public void handle(PlayerItemConsumeEvent event) {
        final Player player = event.getPlayer();

        applyEffect(player);
    }
}
