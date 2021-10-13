package github.nikhrom.exchangerate.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PathHelper{

    private final static String JSP_PATH = "/WEB-INF/jsp/%s.jsp";
    private final static String GIF_PATH = "/gif/%s.gif";

    public static String getJspPath(String name){
        return String.format(JSP_PATH, name);
    }

    public static String getGifPath(String name){
        return String.format(GIF_PATH, name);
    }
}
