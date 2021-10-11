package github.nikhrom.exchangerate.servlet;

import github.nikhrom.exchangerate.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/track")
public class CurrencyTracker extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("gif", "https://media1.giphy.com/media/cZ7rmKfFYOvYI/200.gif");
        req.getRequestDispatcher(JspHelper.getPath("track"))
            .forward(req, resp);
    }
}
