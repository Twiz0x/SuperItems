package fr.twizox.items.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public enum DataManager {

    INSTANCE;

    private final Map<UUID, SuperPlayerData> playerDatas = new HashMap<>();

    public SuperPlayerData getOrCreatePlayerData(UUID uuid) {
        SuperPlayerData superPlayerData = playerDatas.get(uuid);
        if (superPlayerData == null) {
            superPlayerData = new SuperPlayerData(Bukkit.getPlayer(uuid));
            playerDatas.put(uuid, superPlayerData);
        }
        return superPlayerData;
    }

    public SuperPlayerData getOrCreatePlayerData(Player player) {
        return getOrCreatePlayerData(player.getUniqueId());
    }

    public void removePlayerData(UUID uuid) {
        playerDatas.remove(uuid);
    }

    public void removePlayerData(Player player) {
        removePlayerData(player.getUniqueId());
    }

}
