package me.bluedragonplayz2.dragonassistance.Command.Massage;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Eight_ball extends Command {
    public Eight_ball(){
        this.name = "8ball";
        this.help = "roll the celestial dice";
    }
    protected void execute(CommandEvent e) {
        List<String> list = new ArrayList<>();
        list.add("It is certain");
        list.add("It is decidedly so");
        list.add("Without a doubt");
        list.add("Yes – definitely");
        list.add("You may rely on it");
        list.add("As I see it, yes");
        list.add("Most likely");
        list.add("Outlook good");
        list.add("Yes");
        list.add("Signs point to yes");
        list.add("Reply hazy, try again");
        list.add("Ask again later");
        list.add("Better not tell you now");
        list.add("Cannot predict now");
        list.add("Concentrate and ask again");
        list.add("Don't count on it");
        list.add("My reply is no");
        list.add("My sources say no");
        list.add("Outlook not so good");
        list.add("Very doubtful");
        Random random = new Random();
        int upperbound = 19;
        int int_random = random.nextInt(upperbound);
        String reply = list.get(int_random);
        e.reply(reply);
    }
}
