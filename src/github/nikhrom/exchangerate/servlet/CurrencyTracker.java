package github.nikhrom.exchangerate.servlet;

import github.nikhrom.exchangerate.dto.Currency;
import github.nikhrom.exchangerate.service.CurrencyService;
import github.nikhrom.exchangerate.service.GifService;
import github.nikhrom.exchangerate.util.JspHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/track")
public class CurrencyTracker extends HttpServlet {

    static final CurrencyService CURRENCY_SERVICE = CurrencyService.getInstance();
    static final GifService GIF_SERVICE = GifService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String gifUrl = CURRENCY_SERVICE.rateIsHigherThanYesterday(Currency.valueOf(req.getParameter("currency")))?
                GIF_SERVICE.getGifUrlByRateHigherTag():
                GIF_SERVICE.getGifUrlByRateLowerTag();

        req.setAttribute("gifUrl", gifUrl);
        req.getRequestDispatcher(JspHelper.getPath("track"))
            .forward(req, resp);
    }
}
