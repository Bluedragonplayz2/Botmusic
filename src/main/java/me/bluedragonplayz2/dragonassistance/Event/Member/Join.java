package me.bluedragonplayz2.dragonassistance.Event.Member;
import me.bluedragonplayz2.dragonassistance.MsSQL;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.List;
import java.util.Objects;
public class Join extends ListenerAdapter {
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        if(MsSQL.SQL_sel("wmsgcheck:" + e.getGuild().getId()).equalsIgnoreCase("1")) {
            List<TextChannel> channels = e.getGuild().getTextChannelsByName("welcome", true);
            if (channels.isEmpty()) {
                List<TextChannel> channels1 = e.getGuild().getTextChannels();
                TextChannel textChannel1 = channels1.get(0);
                textChannel1.sendMessage("Create a text channel named join to use join message").queue();
                textChannel1.sendMessage("else see &help").queue();
            }
            TextChannel textChannel = channels.get(0);
            if(MsSQL.SQL_sel("welcome_msg:" + e.getGuild().getId()).equalsIgnoreCase("default")) {
                String name = e.getUser().getName().toLowerCase();
                String guild = e.getGuild().getName().toLowerCase();
                textChannel.sendMessage("welcome to " + guild + " " + name).queue();
            }else{
                String msg = MsSQL.SQL_sel("welcome_msg:" + e.getGuild().getId());
                msg = msg.replaceAll("<Username>", e.getMember().getEffectiveName());
                msg = msg.replaceAll("<Guildname>", e.getGuild().getName());
                msg = msg.replaceAll("<Server>", e.getGuild().getName());
                if(!Objects.requireNonNull(e.getGuild().getOwner()).getEffectiveName().isEmpty()){
                    msg = msg.replaceAll("<Owner_name>", e.getGuild().getOwner().getEffectiveName());
                }else{
                    msg = msg.replaceAll("<Owner_name>", "The owner of the server");
                }
                textChannel.sendMessage(msg).queue();
            }
        }
    }
}


