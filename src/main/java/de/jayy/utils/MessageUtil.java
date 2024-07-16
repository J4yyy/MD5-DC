package de.jayy.utils;

import de.jayy.Main;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.time.Instant;

public class MessageUtil {
    public static MessageEmbed embedMessageReverse(boolean success, String md5, String clear) {

        String clearText = success ? "|| "+clear+" ||" : "Not Found";

        return new EmbedBuilder()
                .setColor(success ? 0x116D6E : 0xCD1818)
                .setTimestamp(Instant.now())
                .setFooter(Main.getJda().getSelfUser().getName())
                .setTitle("Reverse Lookup")
                .addField("MD5", md5, false)
                .addField("Clear", clearText, false)
                .build();
    }

    public static MessageEmbed embedMessageHashed(boolean success, String md5, String clear) {

        return new EmbedBuilder()
                .setColor(0xF39F5A)
                .setTimestamp(Instant.now())
                .setFooter(Main.getJda().getSelfUser().getName())
                .setTitle("Hashed Response")
                .addField("Clear", clear, false)
                .addField("MD5", md5, false)
                .build();
    }

    public static MessageEmbed embedMessageHashed(String md5, String clear) {

        return embedMessageHashed(true, md5, clear);
    }
}