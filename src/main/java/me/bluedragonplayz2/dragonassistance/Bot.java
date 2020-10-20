package me.bluedragonplayz2.dragonassistance;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import me.bluedragonplayz2.dragonassistance.Command.Massage.Eight_ball;
import me.bluedragonplayz2.dragonassistance.Command.Ping;
import me.bluedragonplayz2.dragonassistance.Command.Punishment.Ban;
import me.bluedragonplayz2.dragonassistance.Command.Punishment.kick;
import me.bluedragonplayz2.dragonassistance.Event.Setup.Guild_join;
import me.bluedragonplayz2.dragonassistance.Event.Member.Join;
import me.bluedragonplayz2.dragonassistance.Event.Member.Kick;
import me.bluedragonplayz2.dragonassistance.Event.Setup.Setup;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import javax.security.auth.login.LoginException;
import java.io.Console;
import java.net.UnknownHostException;
import java.util.EnumSet;

public class Bot {
    static void bot() throws LoginException, UnknownHostException {
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
                .addCommand(new Eight_ball())
                .addCommand(new kick())
                .addCommand(new Ban())
                .setHelpWord("help");
        CommandClient client = builder.build();
        String token;
        if (java.net.Inet4Address.getLocalHost().getHostAddress().equalsIgnoreCase(Config.get("IP"))) {
            token = Config.get("token");
        } else {
            token = Config.get("alt_token");
        }
        JDABuilder.create(token, intents)
                .addEventListeners(new Listerner())
                .addEventListeners(new Join())
                .addEventListeners(new Kick())
                .addEventListeners(new Sandbox())
                .addEventListeners(new Guild_join())
                .addEventListeners(new Setup())
                .addEventListeners(client)
                .build();
    }
    public static void main (String[]args) throws LoginException, UnknownHostException {
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
