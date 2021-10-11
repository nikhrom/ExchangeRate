package github.nikhrom.exchangerate.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JspHelper {

    private final static String PATH = "/WEB-INF/jsp/%s.jsp";

    public static String getPath(String name){
        return String.format(PATH, name);
    }

}
