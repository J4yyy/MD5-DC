package de.jayy;

import de.jayy.data.DataStorage;
import de.jayy.data.RedisStorage;
import de.jayy.interactions.InteractionRegistry;
import de.jayy.listener.InteractionListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main extends InteractionRegistry {

    private static JDA jda;

    private static final ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor(r -> {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    });

    public static void main(String[] args) throws InterruptedException {
        DataStorage storage = new RedisStorage();


        jda = JDABuilder.create(List.of(GatewayIntent.values()))
                .setToken(System.getenv("DC_TOKEN"))
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.watching(formatNumberCount(storage.getMD5Count()) + "  MD5 Hashes"))
                .addEventListeners(new InteractionListener())
                .build();

        jda.awaitReady();

        register(storage);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            jda.shutdown();
        }));
        cycleActivity(storage);
    }

    private static String formatNumberCount(int count) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        return numberFormat.format(count);
    }

    public static JDA getJda() {
        return jda;
    }

    private static void cycleActivity(DataStorage storage) {
        final boolean[] watching = {true};
        threadPool.scheduleWithFixedDelay(() -> {
            jda.getPresence().setActivity(Activity.watching(formatNumberCount(storage.getMD5Count()) + "  MD5 Hashes"));
        }, 0, 60, TimeUnit.SECONDS);
    }
}