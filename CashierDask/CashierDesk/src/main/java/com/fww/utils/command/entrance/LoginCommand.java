package com.fww.utils.command.entrance;

import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;
@EntranceCommand
@CommandMeta(
        comm = "DL",
        name = "登录",
        group = "入口命令"
)
public class LoginCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {


    }
}
