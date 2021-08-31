package controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Perform a command from http request.
//request the operand to use for getting difference values.
//response the operand to use for getting difference values.
//return path of jsp page
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}