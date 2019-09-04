package com.fww.utils.command;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.account.AccountBrowseCommand;
import com.fww.utils.command.account.AccountPasswordResetCommand;
import com.fww.utils.command.account.AccountStatusResetCommand;
import com.fww.utils.command.account.CustomerBrowse;
import com.fww.utils.command.common.AboutSystemCommand;
import com.fww.utils.command.common.HelpCommand;
import com.fww.utils.command.common.QuitCommand;
import com.fww.utils.command.entrance.LoginCommand;
import com.fww.utils.command.entrance.RegisterCommand;
import com.fww.utils.command.goods.GoodsBrowseCommand;
import com.fww.utils.command.goods.GoodsPutUpCommand;
import com.fww.utils.command.goods.GoodsSoldOutCommand;
import com.fww.utils.command.goods.GoodsUpdateCommand;
import com.fww.utils.command.order.OrderBrowseCommand;
import com.fww.utils.command.order.OrderPaidCommand;

import java.util.*;

public class Commands {
    //对命令进行分类
    public static final Map<String,Command> CUSROMERCOMMAND = new HashMap();
    public static final Map<String,Command>  ANDMINCOMMAND =  new HashMap();
    public static final Map<String,Command>  ENTRANCECOMMAND = new HashMap();
    public static final Command HELPCOMMAND;
    public static final Set<Command> COMMANDS = new HashSet<>();
    static {
        Collections.addAll(COMMANDS,
                new AccountBrowseCommand(),
                new AccountPasswordResetCommand(),
                new AccountStatusResetCommand(),
                new AccountStatusResetCommand(),
                new AboutSystemCommand(),
                new CustomerBrowse(),
                HELPCOMMAND = new HelpCommand(),
                new QuitCommand(),
                new LoginCommand(),
                new RegisterCommand(),
                new GoodsBrowseCommand(),
                new GoodsSoldOutCommand(),
                new GoodsUpdateCommand(),
                new GoodsPutUpCommand(),
                new OrderBrowseCommand(),
                new OrderPaidCommand()
                );
        for (Command command: COMMANDS) {
            Class<?> cla = command.getClass();
            AdminCommand adminCommand = cla.getDeclaredAnnotation(AdminCommand.class);
            CustomerCommand customerCommand = cla.getDeclaredAnnotation(CustomerCommand.class);
            EntranceCommand entranceCommand = cla.getDeclaredAnnotation(EntranceCommand.class);
            CommandMeta commandMeta = cla.getDeclaredAnnotation(CommandMeta.class);
            if(commandMeta == null){
                continue;
            }
            String commandStr = commandMeta.comm();
            if(adminCommand != null){
                ANDMINCOMMAND.put(commandStr,command);
            }
            if(customerCommand != null){
                CUSROMERCOMMAND.put(commandStr,command);
            }
            if(entranceCommand != null){
                ENTRANCECOMMAND.put(commandStr,command);
            }

        }
    }
    public static Command getHelpcommand(){
        return HELPCOMMAND;
    }


    public static Command getEntrancecommand(String commandCode) {
        return getCommand(commandCode,ENTRANCECOMMAND);
    }

    public static Command getCustomerCommand(String commandCode) {
        return getCommand(commandCode,CUSROMERCOMMAND);
    }

    private static Command getCommand(String commandCode, Map<String,Command> command) {
        return   command.getOrDefault(commandCode,HELPCOMMAND);
    }


    public static Command getAdminCommand(String commandCode) {
        return getCommand(commandCode,ANDMINCOMMAND);
    }
}
