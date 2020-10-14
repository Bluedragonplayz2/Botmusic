package me.bluedragonplayz2.dragonassistance.JDAcommand;

import me.duncte123.botcommons.commands.ICommandContext;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;

public class Commandcontext implements ICommandContext {

    private final GuildMessageReceivedEvent event;
    private final List<String> args;

    public Commandcontext(GuildMessageReceivedEvent event, List<String> args) {
        this.event = event;
        this.args = args;
    }

    public Guild getGuild() {
        return this.getEvent().getGuild();
    }


    public GuildMessageReceivedEvent getEvent() {
        return this.event;
    }

    public List<String> getArgs() {
        return this.args;
    }
}
