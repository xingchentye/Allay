package org.allaymc.api.plugin;

import java.net.MalformedURLException;
import java.nio.file.Path;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface PluginLoader {

    PluginDescriptor loadDescriptor();

    PluginContainer loadPlugin();

    interface PluginLoaderFactory {
        boolean canLoad(Path pluginPath);

        PluginLoader create(Path pluginPath);
    }
}
