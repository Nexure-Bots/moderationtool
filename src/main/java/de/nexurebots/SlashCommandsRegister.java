/*

  ███╗   ██╗███████╗██╗  ██╗██╗   ██╗██████╗ ███████╗    ██████╗  ██████╗ ████████╗███████╗
  ████╗  ██║██╔════╝╚██╗██╔╝██║   ██║██╔══██╗██╔════╝    ██╔══██╗██╔═══██╗╚══██╔══╝██╔════╝
  ██╔██╗ ██║█████╗   ╚███╔╝ ██║   ██║██████╔╝█████╗      ██████╔╝██║   ██║   ██║   ███████╗
  ██║╚██╗██║██╔══╝   ██╔██╗ ██║   ██║██╔══██╗██╔══╝      ██╔══██╗██║   ██║   ██║   ╚════██║
  ██║ ╚████║███████╗██╔╝ ██╗╚██████╔╝██║  ██║███████╗    ██████╔╝╚██████╔╝   ██║   ███████║
  ╚═╝  ╚═══╝╚══════╝╚═╝  ╚═╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝    ╚═════╝  ╚═════╝    ╚═╝   ╚══════╝

Made by Nexure Bots - dsc.gg/nexure-bots

 */

package de.nexurebots;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class SlashCommandsRegister extends ListenerAdapter {

    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = createCommandData();
        event.getGuild().updateCommands().addCommands(commandData).queue();
    }

    private List<CommandData> createCommandData() {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(
                Commands.slash("kick", "Kickt einen Nutzer.")
                        .addOption(OptionType.USER, "user", "Der zu kickende Nutzer", true)
                        .addOption(OptionType.STRING, "grund", "Grund für den Kick", false)
        );

        commandData.add(
                Commands.slash("ban", "Bannt einen Nutzer.")
                        .addOption(OptionType.USER, "user", "Der zu bannende Nutzer", true)
                        .addOption(OptionType.STRING, "grund", "Grund für den Bann", false)
        );


        return commandData;
    }
}
