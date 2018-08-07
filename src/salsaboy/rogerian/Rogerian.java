package salsaboy.rogerian;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.EventListener;
import javax.security.auth.login.LoginException;
import java.util.Scanner;

public class Rogerian implements EventListener {
    public static boolean forDiscord = false;
    public static final String prefix = "?";
    
    public static JDA jda;
    public static Rogerian rogerian = new Rogerian();
    public static void main(String[] args) throws LoginException, InterruptedException {
        if (!forDiscord) {
            while (true) {
                Scanner reader = new Scanner(System.in);
    
                System.out.println(Convo.reply(reader.nextLine()));
            }
        } else {
            jda = new JDABuilder(AccountType.BOT)
                .setGame(Game.listening("to your problems"))
                .setToken(args[0])
                .addEventListener(rogerian)
                .buildBlocking();
        }
    }
    
    @Override
    public void onEvent(Event event) {
        if (event instanceof MessageReceivedEvent) {
            if (((MessageReceivedEvent) event).getMessage().toString().startsWith(prefix)) {
            }
        }
    }
}
