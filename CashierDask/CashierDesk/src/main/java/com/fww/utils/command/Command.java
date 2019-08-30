package com.fww.utils.command;

import com.fww.utils.command.Subject;

import java.util.Scanner;

public interface Command {
    Scanner scanner = new Scanner(System.in);
    void execute(Subject subject);
}
