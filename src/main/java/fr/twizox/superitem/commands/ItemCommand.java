package fr.twizox.superitem.commands;

import fr.twizox.superitem.items.behaviors.BehaviorManager;
import fr.twizox.superitem.items.behaviors.ItemBehavior;
import fr.twizox.superitem.items.properties.PropertyManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ItemCommand implements CommandExecutor {

    private final PropertyManager propertyManager = Bukkit.getServer().getServicesManager().load(PropertyManager.class);
    private final BehaviorManager behaviorManager = Bukkit.getServer().getServicesManager().load(BehaviorManager.class);

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cOnly players can use this command");
            return true;
        }

        if (args.length == 0) {
            sender.sendMessage("§cUsage: /item <apply|properties|behaviors|reload>");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "properties" -> {
                sender.sendMessage("§aList of properties:");
                propertyManager.getPropertiesIds().forEach(property -> sender.sendMessage("§e- " + property));
            }
            case "behaviors" -> {
                sender.sendMessage("§aList of behaviors:");
                behaviorManager.getBehaviors().forEach((behavior, itemBehavior) -> sender.sendMessage("§e- " + itemBehavior));
            }
            case "reload" -> {
                sender.sendMessage("§aNot implemented yet");
            }
            case "apply" -> {
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /item apply <property>");
                    return true;
                }
                ItemBehavior itemBehavior = behaviorManager.getBehavior(args[1]).orElse(null);
                if (itemBehavior == null) {
                    sender.sendMessage("§cBehavior not found");
                    return true;
                }
                itemBehavior.apply(player.getInventory().getItemInMainHand());
                player.sendMessage(behaviorManager.getBehavior(player.getInventory().getItemInMainHand()).map(ItemBehavior::toString).orElse("§cNo behavior found"));
            }
        }

        return true;
    }

}
