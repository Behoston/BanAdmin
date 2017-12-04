package pl.behoston.banadmin;

import net.md_5.bungee.api.plugin.Plugin;

public class BanAdminPlugin extends Plugin {

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new BanAdminListener(this));
    }
}
