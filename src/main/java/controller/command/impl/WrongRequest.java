package controller.command.impl;

import controller.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WrongRequest implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return null;//todo
    }
}
