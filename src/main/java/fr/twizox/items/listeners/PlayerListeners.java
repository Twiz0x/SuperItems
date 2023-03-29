package fr.twizox.items.listeners;

import fr.twizox.items.data.DataManager;
import fr.twizox.items.data.SuperPlayerData;
import fr.twizox.items.items.ItemManager;
import fr.twizox.items.items.SuperItem;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Optional;

public class PlayerListeners implements Listener {


    private void handleEvent(Player player, Event event) {
        SuperPlayerData data = DataManager.getInstance().getOrCreatePlayerData(player);
        Optional<SuperItem> superItem = ItemManager.getInstance().getItem(player.getInventory().getItemInMainHand());
        superItem.ifPresent(item -> item.getProperties().stream().filter(property -> property.isApplicable(event)).forEach(property -> property.handle(event)));
    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        handleEvent(event.getPlayer(), event);
    }

    @EventHandler
    public void onSwitch(PlayerItemHeldEvent event) {
        handleEvent(event.getPlayer(), event);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SuperItem superItem = ItemManager.getInstance().getItems().get(0);
        SuperItem superItem2 = ItemManager.getInstance().getItems().get(1);
        player.getInventory().setItem(0, superItem.getItemStack());
        player.getInventory().setItem(1, superItem.getItemStack());
    }

}
