package me.leoko.advancedban.nukkit.listener;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import lombok.RequiredArgsConstructor;
import me.leoko.advancedban.AdvancedBan;
import me.leoko.advancedban.AdvancedBanLogger;
import me.leoko.advancedban.nukkit.NukkitAdvancedBanPlayer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@RequiredArgsConstructor
public class ConnectionListener implements Listener {
    private final AdvancedBan advancedBan;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onConnect(PlayerAsyncPreLoginEvent event) {
        try {
            Optional<String> result = advancedBan.onPreLogin(event.getName(), event.getUuid(), InetAddress.getByName(event.getAddress()));
            if (result.isPresent()) {
                event.setKickMessage(result.get());
                event.setLoginResult(PlayerAsyncPreLoginEvent.LoginResult.KICK);
            }
        } catch (UnknownHostException e) {
            AdvancedBanLogger.getInstance().warn("Error whilst resolving player's address");
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDisconnect(PlayerQuitEvent event) {
        advancedBan.getPlayer(event.getPlayer().getUniqueId()).ifPresent(advancedBan::onDisconnect);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(PlayerJoinEvent event) {
        advancedBan.onLogin(new NukkitAdvancedBanPlayer(event.getPlayer(), advancedBan));
    }
}
