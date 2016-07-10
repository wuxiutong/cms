package wxt.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import wxt.com.reflection.Reflect;

/**
 * Created by wuxiutong on 16/7/10.
 */
public class MainFilter implements Filter{
private String encoding;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //public void execute(Object request,Object response){
            HttpServletRequest httpServletRequest  = (HttpServletRequest)request;
            HttpServletResponse httpServletResponse  = (HttpServletResponse)response;
            String url = ((HttpServletRequest) request).getRequestURL().toString();
            String action = url.substring(url.lastIndexOf("/")+1);
            String actionStr = action.replace(".action","");
                   actionStr = actionStr.replace(".do","");
        try {
            Reflect.dispatcher(actionStr, request, response);
        }catch (Exception e){
            System.out.println("mainFilter error:"+e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}
