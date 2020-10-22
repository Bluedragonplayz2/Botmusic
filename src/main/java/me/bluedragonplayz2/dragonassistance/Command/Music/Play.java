package me.bluedragonplayz2.dragonassistance.Command.Music;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.managers.AudioManager;


public class Play extends Command {
    public Play(){
        this.name = "play";
        this.help = "plays music";
    }
    @Override
    protected void execute(CommandEvent e) {
        final TextChannel channel = e.getTextChannel();
        final Member self = e.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();
        //noinspection ConstantConditions
        if (selfVoiceState.inVoiceChannel()){
            e.reply("I am already in a voice channel");
        }
        final Member member = e.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        if (!(memberVoiceState != null || memberVoiceState.getChannel() != null)){
            e.reply("You need to be in a voice chat for this to work");
        }
        if (!memberVoiceState.inVoiceChannel()) {
            e.reply("You need to be in a voice chat for this to work");
        }
        final AudioManager audioManager = e.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();
        audioManager.openAudioConnection(memberChannel);
        memberChannel.getName();
        e.reply("Connecting to" + memberChannel.getName());
        PlayerManager.getInstance()
            .loadAndPlay(channel, "https://www.youtube.com/watch?v=JiF3pbvR5G0");
    }
}
