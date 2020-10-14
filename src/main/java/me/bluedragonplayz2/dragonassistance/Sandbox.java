package me.bluedragonplayz2.dragonassistance;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Sandbox extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if(e.getMessage().getContentRaw().equalsIgnoreCase("&sandbox")) {
            MsSQL mssql = new MsSQL();
            mssql.SQL_test();
            String reply = MsSQL.SQL_sel("welcome_msg:" + e.getGuild().getId());
            e.getChannel().sendMessage(reply).queue();
        }
    }
}
