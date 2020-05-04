package filter;

import service.Active;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@WebFilter(
        urlPatterns = {"/*"},
        initParams = @WebInitParam(name = "active", value = "true"),
        filterName = "AuthFilter",
        description = "Filter all URLs for Authorisation"
)
public class AuthFilter implements Filter {

    private FilterConfig config = null;
    private static ArrayList<String> pages;
    private Long check = null;

    public AuthFilter()
    {
        if (pages == null)
            pages = new ArrayList<String>();
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(config.getInitParameter("active").equalsIgnoreCase("true")){
            HttpServletRequest req = (HttpServletRequest)request;
            String page = req.getRequestURI().substring(req.getContextPath().length());
            if(Active.getInstance().getActive() == null & !page.equals("/add")){
                request.getRequestDispatcher("/login").forward(request, response);
                return;
            } else if(Active.getInstance().getActive() != null){
                if(Active.getInstance().getActive().getRole().equals("admin")){
                    if(page.equals("/") | page.equals("")){
                        request.getRequestDispatcher("/admin").forward(request, response);
                    } else {
                        chain.doFilter(request, response);
                    }
                    return;
                } else if(Active.getInstance().getActive().getRole().equals("user") && !page.equals("/login") & !page.equals("/user")){
                    request.getRequestDispatcher("/user").forward(request, response);
                    return;
                }
            }
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {
        config = null;
    }
}
