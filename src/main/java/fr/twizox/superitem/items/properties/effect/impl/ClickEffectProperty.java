package fr.twizox.superitem.items.properties.effect.impl;

import fr.twizox.superitem.items.properties.effect.AbstractEffectProperty;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * @author Salers
 * made on fr.twizox.superitem.items.properties.effect.impl
 */
public class ClickEffectProperty extends AbstractEffectProperty<PlayerInteractEvent> {

    private final boolean consumeItem;
    private final int cooldown;


    public ClickEffectProperty(PotionEffect potionEffect, boolean consumeItem, int cooldown) {
        super(potionEffect);
        this.consumeItem = consumeItem;
        this.cooldown = cooldown;
    }

    public ClickEffectProperty() {
        this(new PotionEffect(PotionEffectType.SPEED, 200, 1),
                false, 300);
    }

    @Override
    public void handle(PlayerInteractEvent event) {
        if (event.getAction() == Action.PHYSICAL) return;

        final Player player = event.getPlayer();
        applyEffect(player);

        ItemStack item = player.getInventory().getItemInMainHand();
        if (consumeItem) item.setAmount(item.getAmount() - 1);
        player.setCooldown(item.getType(), cooldown);
    }

}
