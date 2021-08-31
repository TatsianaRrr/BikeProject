package controller.command;

import controller.command.impl.*;
import controller.command.impl.bike.*;
import controller.command.impl.order.*;
import controller.command.impl.user.*;
import controller.servlet.RequestParameterName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public final class CommandHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandHelper.class);

    private static final CommandHelper instance = new CommandHelper();
    private final Map<CommandName, Command> commandsRepository = new HashMap<>();

    private CommandHelper() {
        commandsRepository.put(CommandName.SIGN_IN, SignIn.getInstance());
        commandsRepository.put((CommandName.SIGN_UP), SignUp.getInstance());
        commandsRepository.put((CommandName.CHANGE_LOCALE), ChangeLocale.getInstance());
        commandsRepository.put(CommandName.WRONG_REQUEST, new WrongRequest());
        commandsRepository.put(CommandName.GET_ALL_USERS, GetAllUsers.getInstance());
        commandsRepository.put(CommandName.GET_USER_BY_LOGIN, GetUserByLogin.getInstance());
        commandsRepository.put(CommandName.UPDATE_USER, UpdateUser.getInstance());
        commandsRepository.put(CommandName.DELETE_USER, DeleteUser.getInstance());
        commandsRepository.put(CommandName.ADD_USER, AddUser.getInstance());
        commandsRepository.put(CommandName.GET_USER_BY_ID, GetUserById.getInstance());


        commandsRepository.put(CommandName.LOG_OUT, LogOut.getInstance());
        commandsRepository.put(CommandName.SHOW_USER_PAGE, ShowUserPage.getInstance());
        commandsRepository.put(CommandName.SHOW_ADMIN_PAGE, ShowAdminPage.getInstance());
        commandsRepository.put(CommandName.SHOW_USER_ACCOUNT, ShowUserAccount.getInstance());


        commandsRepository.put(CommandName.ADD_BIKE, AddBike.getInstance());
        commandsRepository.put(CommandName.GET_BIKE_BY_ID, GetBikeById.getInstance());
        commandsRepository.put(CommandName.UPDATE_BIKE, UpdateBike.getInstance());
        commandsRepository.put(CommandName.DELETE_BIKE, DeleteBike.getInstance());
        commandsRepository.put(CommandName.GET_ALL_BIKES, GetAllBikes.getInstance());
        commandsRepository.put(CommandName.GET_BIKES_BY_YEAR_DESC, GetBikesByYearDesc.getInstance());

        commandsRepository.put(CommandName.GET_ALL_ORDERS, GetAllOrders.getInstance());
        commandsRepository.put(CommandName.DELETE_ORDER, DeleteOrder.getInstance());
        commandsRepository.put(CommandName.ADD_ORDER, AddOrder.getInstance());
        commandsRepository.put(CommandName.GET_ORDER_BY_ID, GetOrderById.getInstance());
        commandsRepository.put(CommandName.GET_ORDERS_BY_USER_ID, GetOrdersByUserId.getInstance());
        commandsRepository.put(CommandName.GET_ORDERS_BY_BIKE_ID, GetOrdersByBikeId.getInstance());
    }

    public static CommandHelper getInstance() {
        return instance;
    }

    public Command getCommand(HttpServletRequest request) {
        Command iCommand = commandsRepository.get(CommandName.WRONG_REQUEST);
        String command = request.getParameter(RequestParameterName.COMMAND_NAME);
        LOGGER.debug("get command {}", command);
        try {
            CommandName commandName = CommandName.valueOf(command.toUpperCase().replace('-', '_'));
            iCommand = commandsRepository.get(commandName);
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", e.getMessage());
            LOGGER.error("wrong action in commandHelper", e);
        }
        return iCommand;
    }

    //enum contains types of command
    public enum CommandName {
        SIGN_IN, SIGN_UP, WRONG_REQUEST, GET_ALL_USERS, SHOW_USER_ACCOUNT, LOG_OUT, SHOW_USER_PAGE, SHOW_ADMIN_PAGE,
        GET_USER_BY_LOGIN, GET_ALL_BIKES, DELETE_BIKE, UPDATE_BIKE, GET_BIKE_BY_ID, GET_USER_BY_ID,
        ADD_BIKE, ADD_USER, UPDATE_USER, DELETE_USER, GET_ALL_ORDERS, DELETE_ORDER, ADD_ORDER, GET_ORDER_BY_ID, UPDATE_ORDER,
        GET_ORDERS_BY_BIKE_ID, GET_ORDERS_BY_USER_ID, GET_BIKES_BY_YEAR_DESC, CHANGE_LOCALE;

        CommandName() {
        }
    }
}
