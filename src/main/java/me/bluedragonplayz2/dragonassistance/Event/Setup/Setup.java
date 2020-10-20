package me.bluedragonplayz2.dragonassistance.Event.Setup;

import me.bluedragonplayz2.dragonassistance.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Setup extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent e) {
        if(!e.getAuthor().isBot()){
            if(e.getMessage().getContentRaw().toLowerCase().equalsIgnoreCase("&setup")){
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
    }
}
