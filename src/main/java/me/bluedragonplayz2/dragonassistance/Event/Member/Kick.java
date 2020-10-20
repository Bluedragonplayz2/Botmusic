package me.bluedragonplayz2.dragonassistance.Event.Member;

import me.bluedragonplayz2.dragonassistance.MsSQL;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Kick extends ListenerAdapter {
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {
        if(MsSQL.SQL_sel("kmsgcheck:" + e.getGuild().getId()).equalsIgnoreCase("1")) {
            List<TextChannel> channels = e.getGuild().getTextChannelsByName("welcome", true);
            if (channels.isEmpty()) {
                List<TextChannel> channels1 = e.getGuild().getTextChannels();
                TextChannel textChannel1 = channels1.get(0);
                textChannel1.sendMessage("Create a text channel named welcome to use kick message").queue();
                textChannel1.sendMessage("else see &help").queue();
            }
            TextChannel textChannel = channels.get(0);
            if(MsSQL.SQL_sel("kick_msg:" + e.getGuild().getId()).equalsIgnoreCase("default")) {
                String name = e.getUser().getName().toLowerCase();
                String guild = e.getGuild().getName().toLowerCase();
                textChannel.sendMessage(name + "was booted in the butt by a very angry penguin" ).queue();
            }else{
                String kick_msg = MsSQL.SQL_sel("kick_msg:" + e.getGuild().getId());
                kick_msg = kick_msg.replaceAll("<Username>", e.getMember().getEffectiveName());
                kick_msg = kick_msg.replaceAll("<Guildname>", e.getGuild().getName());
                kick_msg = kick_msg.replaceAll("<Server>", e.getGuild().getName());
                if(!Objects.requireNonNull(e.getGuild().getOwner()).getEffectiveName().isEmpty()){
                    kick_msg = kick_msg.replaceAll("<Owner_name>", e.getGuild().getOwner().getEffectiveName());
                }else{
                    kick_msg = kick_msg.replaceAll("<Owner_name>", "The owner of the server");
                }
                textChannel.sendMessage(kick_msg).queue();
            }
        }
    }
}
