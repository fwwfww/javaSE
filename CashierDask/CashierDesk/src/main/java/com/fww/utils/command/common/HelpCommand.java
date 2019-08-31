package com.fww.utils.command.common;

import com.fww.entity.Account;
import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Command;
import com.fww.utils.command.Commands;
import com.fww.utils.command.Subject;

import java.util.*;
@EntranceCommand
@CustomerCommand
@AdminCommand
@CommandMeta(
        comm = "BZ",
        name = "帮助",
        group = "公共命令"
)
public class HelpCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
        outPut("HelpCommand");
        Account account = subject.getAccount();
        if(account == null){
            entranceHelp();
        }else{
            switch (account.getType()){
                case ADMIN:
                    adminHelp();
                    break;
                case CUSTOMER:
                    customerHelp();
                    break;
                default:
            }
        }

    }

    private void customerHelp() {
        printf("欢迎",Commands.CUSROMERCOMMAND.values());
    }

    private void adminHelp() {
        printf("欢迎",Commands.ANDMINCOMMAND.values());
    }

    private void entranceHelp() {
        printf("欢迎",Commands.ENTRANCECOMMAND.values());
    }
    public void printf(String title, Collection<Command> commandCollection){
        outPut("**********************"+title+"**********************");
        Map<String, List<String>> map = new HashMap<>();
        for (Command command: commandCollection) {
               Class<?> cla = command.getClass();
               CommandMeta commandMeta = cla.getDeclaredAnnotation(CommandMeta.class);
               String group = commandMeta.group();
               List<String> list = map.get(group);
               if(list == null){
                   list = new ArrayList<>();
                   map.put(group, list);
               }
               list.add(commandMeta.name()+"("+commandMeta.comm()+")");
        }
        int i = 0;
        for (Map.Entry<String,List<String>> mm : map.entrySet() ) {
            i++;
            outPut(i+"."+mm.getKey());
            int j = 0;
            for (String item: mm.getValue() ) {
                j++;
                outPut("\t"+i+"."+j+item);
            }
        }
        outPut("输入菜单括号后面的编号（忽略大小写），进行下一步操作");
        outPut("************************************************");
    }
}
