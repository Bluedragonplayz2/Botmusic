package me.bluedragonplayz2.dragonassistance.JDAcommand;

import java.util.List;

public interface ICommand {
    void handle(Commandcontext ctx);
    String getName();
    default List<String> getAliases(){
        return List.of();

    }
}
