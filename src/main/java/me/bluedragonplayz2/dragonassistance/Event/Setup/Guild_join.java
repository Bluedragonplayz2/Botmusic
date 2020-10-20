package me.bluedragonplayz2.dragonassistance.Event.Setup;

import me.bluedragonplayz2.dragonassistance.Config;
import me.bluedragonplayz2.dragonassistance.MsSQL;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;
import java.util.Objects;

public class Guild_join extends ListenerAdapter {
    @Override
    public void onGuildJoin( GuildJoinEvent e) {
        MsSQL mssql = new MsSQL();
        mssql.SQL_test();
        mssql.SQL_ins("Guild_ID:" + e.getGuild().getId());
        List<TextChannel> channels = e.getGuild().getTextChannels();

        if (channels.isEmpty()) {
            if(e.getGuild().getSelfMember().hasPermission(Permission.MANAGE_CHANNEL)){
                e.getGuild().createTextChannel("General").queue();
            }else{
                String server = e.getGuild().getName();
                Objects.requireNonNull(e.getGuild().getOwner()).getUser().openPrivateChannel().queue((channel) ->
                        channel.sendMessage("Cannot initiate setup in your server:" + server+
                                ". Create a textchannel then type command [" + Config.get("prefix") +
                                "setup ] to begin"
                        ).queue());
            }
        }
    }
}
