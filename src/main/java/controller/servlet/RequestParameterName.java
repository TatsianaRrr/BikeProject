package controller.servlet;

public final class RequestParameterName {
    private String value;

    private RequestParameterName() {
    }

    public static final String COMMAND_NAME = "command";
    public static final String CHANGE_LOCALE = "change_locale";
    public static final String FILE_NAME = "filename";
    public static final String INFORMATION = "information";
    public static final String LOGIN = "login";
    public static final String ID_USER = "id_user";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PAGE = "page";
    public static final String COMMAND = "command";
    public static final String ROLE = "role";
    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String USER_LIST = "userList";//ключ к-й указан в jsp
    public static final String USER_ROLE = "userRole";

    public static final String BIKE_LIST = "bikeList";
    public static final String BIKE_ID = "bikeId";
    public static final String BIKE = "bike";
    public static final String BIKE_NAME = "bike_name";
    public static final String YEAR = "year";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String DELETED = "deleted";
    public static final String ORDERED = "ordered";
    public static final String IMAGE = "image";

    public static final String ORDER_LIST = "orderList";
    public static final String ORDER_ID = "orderId";
    public static final String ORDER = "order";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public RequestParameterName(String value) {
        this.value = value;
            }

}
