package com.fww.utils.command.common;

import com.fww.utils.annotation.AdminCommand;
import com.fww.utils.annotation.CommandMeta;
import com.fww.utils.annotation.CustomerCommand;
import com.fww.utils.annotation.EntranceCommand;
import com.fww.utils.command.AbstractCommand;
import com.fww.utils.command.Subject;
@EntranceCommand
@AdminCommand
@CustomerCommand
@CommandMeta(
        comm = "TC",
        name = "退出",
        group = "公共命令"
)
public class QuitCommand extends AbstractCommand {
    @Override
    public void execute(Subject subject) {
      outPut("欢迎您下次使用！");
      scanner.close();
      System.exit(0);
    }
}
