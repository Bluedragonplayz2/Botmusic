package me.bluedragonplayz2.dragonassistance;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.bluedragonplayz2.dragonassistance.Command.Ping;
import me.bluedragonplayz2.dragonassistance.Event.Guild_join;
import me.bluedragonplayz2.dragonassistance.Event.Join;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import java.io.Console;
import java.util.EnumSet;

public class Bot {
    static void bot() throws LoginException {
        EnumSet<GatewayIntent> intents = EnumSet.of(
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_BANS,
                GatewayIntent.GUILD_EMOJIS,
                GatewayIntent.GUILD_INVITES,
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_VOICE_STATES
        );
        CommandClientBuilder builder = new CommandClientBuilder();
        builder.setActivity(Activity.watching("How to kill treble"))
            .setOwnerId(Config.get("owner_id"))
            .setPrefix(Config.get("prefix"))
            .addCommand(new Ping())
            .setHelpWord("help");
        CommandClient client = builder.build();
        JDABuilder.create(Config.get("token"), intents)
                .addEventListeners(new Listerner())
                .addEventListeners(new Join())
                .addEventListeners(new Sandbox())
                .addEventListeners(new Guild_join())
                .addEventListeners(client)
                .build();
    }
    public static void main (String[]args) throws LoginException {
        MsSQL mssql = new MsSQL();
        mssql.MsSql();
        bot();
        Console cnsl= System.console();
        if (cnsl == null) {
            System.out.println(
                    "No console available");
            return;
        }
        String input = "start";
        while (!input.equals("stop"))
        {
            input = cnsl.readLine();
        }
        System.exit(0);

    }
}
