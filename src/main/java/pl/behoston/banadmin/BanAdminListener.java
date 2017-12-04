package pl.behoston.banadmin;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class BanAdminListener implements Listener {
    private Plugin plugin;
    private Set<String> admins = new HashSet<>(Arrays.asList("chwast"));
    private String trapServer = "lobby";
    private String command = "gbanip %s Wykryto podszywanie się pod administrację!";
    private Logger logger;

    BanAdminListener(Plugin plugin) {
        this.plugin = plugin;
        this.logger = plugin.getLogger();

    }


    @EventHandler
    public void onServerConnectEvent(ServerConnectEvent event) {
        ProxiedPlayer player = event.getPlayer();
        String playerName = player.getName();
        String serverName = event.getTarget().getName();

        boolean isAdmin = admins.contains(playerName.toLowerCase());
        boolean isOnTrapServer = serverName.equalsIgnoreCase(trapServer);

        if (isAdmin && isOnTrapServer) {
            String playerIP = player.getAddress().getAddress().getHostAddress();
            System.out.println(playerIP);
            logger.info("Banningn IP: " + playerIP + " due to pretending to be an admin!");

            ProxyServer proxy = plugin.getProxy();
            proxy.getPluginManager().dispatchCommand(proxy.getConsole(), String.format(command, playerIP));
        }
    }

}
