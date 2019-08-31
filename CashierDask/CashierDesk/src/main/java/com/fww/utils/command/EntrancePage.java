package com.fww.utils.command;

import com.fww.entity.Account;
import com.fww.utils.command.common.HelpCommand;

public class EntrancePage extends AbstractCommand {
    public static void main(String[] args) {
       Subject subject = new Subject();
       new EntrancePage().execute(subject);
    }

    @Override
    public void execute(Subject subject) {
       Commands.getHelpcommand().execute(subject);
        while(true){
            outPut(">>>");
            String str = scanner.nextLine();
            String commandCode = str.trim().toUpperCase();
            Account account = subject.getAccount();
            if(account == null){
                Commands.getEntrancecommand(commandCode).execute(subject);
            }else{
                switch (account.getType()){
                    case CUSTOMER:
                        Commands.getCustomerCommand(commandCode).execute(subject);
                        break;
                    case ADMIN:
                        Commands.getAdminCommand(commandCode).execute(subject);
                        break;
                }
            }
        }
    }
}
