package filter;

import entity.Kullanici;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class KullaniciLoginFilterControllers implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getRequestURI();
        
        Kullanici admin = (Kullanici) req.getSession().getAttribute("valid_admin");
        if (admin == null) {
            admin = (Kullanici) req.getSession().getAttribute("valid_user");
            if (admin != null) {
                if (url.contains("admin") || url.contains("menu.xhtml") || url.contains("templateAdmin.xhtml") ) {
                    res.sendRedirect(req.getContextPath() + "/site/kullanici/sarkilar/sarkilar.xhtml?faces-redirect=true");
                } else if (url.contains("logout.xhtml")) {
                    req.getSession().invalidate();
                    res.sendRedirect(req.getContextPath() + "/index.xhtml?faces-redirect=true");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                if (url.contains("site")) {
                    res.sendRedirect(req.getContextPath() + "/login.xhtml?faces-redirect=true");
                } else {
                    chain.doFilter(request, response);
                }
            }
        } 
        else {
            if (url.contains("logout")) {
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/index.xhtml?faces-redirect=true");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

}
