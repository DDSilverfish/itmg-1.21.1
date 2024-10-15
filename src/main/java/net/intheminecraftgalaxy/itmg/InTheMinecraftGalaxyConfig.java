package net.intheminecraftgalaxy.itmg;

import java.io.*;
import java.util.Properties;

public class InTheMinecraftGalaxyConfig {
    private static final String CONFIG_FILE_PATH = "config/InTheMinecraftGalaxy.properties";
    private static final Properties properties = new Properties();

    public static double maxHeart = 30.0;
    public static double heartDamage = 2.0;
    public static double minHeart = 2.0;
    public static double maxHeartItemGen = 20.0;

    public static int timberAxeDurability = 250;
    public static int snowSwordDurability = 10;
    public static int snowSwordEffectDuration = 10;
    public static int veinMiningPickaxeDurability = 200;

    // Load config from file
    public static void loadConfig() {
        File configFile = new File(CONFIG_FILE_PATH);

        // If config file does not exist, create it with default values
        if (!configFile.exists()) {
            saveDefaultConfig();
        }

        // Load the properties from the file
        try (InputStream input = new FileInputStream(configFile)) {
            properties.load(input);
            maxHeart = Double.parseDouble(properties.getProperty("maxHeart", "30.0"));
            heartDamage = Double.parseDouble(properties.getProperty("heartDamage", "2.0"));
            minHeart = Double.parseDouble(properties.getProperty("minHeart", "2.0"));
            maxHeartItemGen = Double.parseDouble(properties.getProperty("maxHeartItemGen", "20.0"));

            timberAxeDurability = Integer.parseInt(properties.getProperty("timberAxeDurability", "250"));

            snowSwordDurability = Integer.parseInt(properties.getProperty("snowSwordDurability", "10"));
            snowSwordDurability = Integer.parseInt(properties.getProperty("snowSwordEffectDuration", "50"));

            veinMiningPickaxeDurability = Integer.parseInt(properties.getProperty("veinMiningPickaxeDurability", "200"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save the default config if the file doesn't exist
    private static void saveDefaultConfig() {
        File configFile = new File(CONFIG_FILE_PATH);

        // Create config folder if it doesn't exist
        configFile.getParentFile().mkdirs();

        // Set default properties
        properties.setProperty("maxHeart", Double.toString(maxHeart));
        properties.setProperty("heartDamage", Double.toString(heartDamage));
        properties.setProperty("minHeart", Double.toString(minHeart));
        properties.setProperty("maxHeartItemGen", Double.toString(maxHeartItemGen));

        properties.setProperty("timberAxeDurability", Integer.toString(timberAxeDurability));

        properties.setProperty("snowSwordDurability", Integer.toString(snowSwordDurability));
        properties.setProperty("snowSwordEffectDuration", Integer.toString(snowSwordDurability));

        properties.setProperty("veinMiningPickaxeDurability", Integer.toString(veinMiningPickaxeDurability));

        // Save properties to the file
        try (OutputStream output = new FileOutputStream(configFile)) {
            properties.store(output, "LifeSteal Mod Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
