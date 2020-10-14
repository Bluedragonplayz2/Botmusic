package me.bluedragonplayz2.dragonassistance.Event;


import me.bluedragonplayz2.dragonassistance.Listerner;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.TestOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Join extends ListenerAdapter {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Listerner.class);
    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent e) {

        String name = e.getUser().getName().toLowerCase();
        String guild = e.getGuild().getName().toLowerCase();
        List<TextChannel> channels =e.getGuild().getTextChannelsByName("join",true);
        if (channels.isEmpty()){
            List<TextChannel> channels1 = e.getGuild().getTextChannels();
            TextChannel textChannel1 = channels1.get(0);
            textChannel1.sendMessage("Create a text channel named join to use join message").queue();
            textChannel1.sendMessage("else see &help").queue();
            }
        TextChannel textChannel = channels.get(0);
        textChannel.sendMessage("welcome to " + guild + " " + name).queue();
    }
}


