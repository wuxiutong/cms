package wxt.filter;

import wxt.admin.PermissionUtils;
import wxt.exceptions.CustomException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wuxiutong on 15/8/29.
 * 登陆检测，如果当前不存在登陆地用户则跳转到登陆界面，否则放行
 */
public class CheckUserLogin implements Filter {
    private String  encoding ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //设置getparam的参数为utf-8
        if(request.getMethod().toLowerCase().equals("get")) {
            request = new HttpServletRequestWrapper((HttpServletRequest) request) {
                public String getParameter(String str) {
                    try {
//                    return new String(super.getParameter(str).getBytes(super.getCharacterEncoding()),"UTF-8");
                        return new String(super.getParameter(str).getBytes("ISO-8859-1"), "UTF-8");
                    } catch (Exception e) {
                        return null;
                    }
                }
            };
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = request.getRequestURL().toString();
        String userid = (String) request.getSession().getAttribute("userid");
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        //如果当前session中存在userid
        if (null != userid && !"".equals(userid.trim())) {
            authorFilter(request, response, filterChain);
        } else {
            if (url.contains("BJUI") || url.contains("login") || url.contains("js/") || url.endsWith("login.jsp")) {
                filterChain.doFilter(request, response);
            } else {
                request.getRequestDispatcher("UserLogin").forward(request, response);
            }
        }
    }

    //权限过滤
    public void authorFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURL().toString();
        request.setCharacterEncoding("UTF-8");
        String servlet = request.getServletPath();
       // System.out.println("正在过滤：" + servlet);
        try {
            //校验用户权限 ,如果是系统管理员则直接放开
            if (servlet.contains("BaseServlet")) {
               // System.out.println("即将进行权限校验：" + servlet);
                if ("1".equals(request.getSession().getAttribute("userid").toString()) ||
                        PermissionUtils.checkPermission(request.getSession().getAttribute("userid").toString(), servlet.replace("/", ""))) {

                    //System.out.println("通过校验，即将跳转至servlet：" + servlet);
                    if (servlet.contains("/KhxxBaseServlet.")) {
                       // System.out.println("即将跳转KhxxBaseServlet"+ request.getParameter("keyword"));
                        request.getRequestDispatcher("KhxxBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/RegionBaseServlet.")) {
                       // System.out.println("即将跳转RegionBaseServlet");
                        request.getRequestDispatcher("RegionBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/BmxxBaseServlet.")) {
                        //System.out.println("即将跳转BmxxBaseServlet");
                        request.getRequestDispatcher("BmxxBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/ZyxxBaseServlet.")) {
                        //System.out.println("即将跳转ZyxxBaseServlet");
                        request.getRequestDispatcher("ZyxxBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/DwlxBaseServlet.")) {
                       // System.out.println("即将跳转DwlxBaseServlet");
                        request.getRequestDispatcher("DwlxBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/BlocBaseServlet.")) {
                        //System.out.println("即将跳转BlocBaseServlet");
                        request.getRequestDispatcher("BlocBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                   }  else if (servlet.contains("/CWBillBaseServlet.")) {
                        //System.out.println("即将跳转CWBillBaseServlet");
                        request.getRequestDispatcher("CWBillBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/RoleBaseServlet.")) {
                        //System.out.println("即将跳转RoleBaseServlet");
                        request.getRequestDispatcher("RoleBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    } else if (servlet.contains("/UserBaseServlet.")) {
                       // System.out.println("即将跳转UserBaseServlet");
                        request.getRequestDispatcher("UserBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    }else if (servlet.contains("/InitRptBaseServlet.")) {
                        //System.out.println("InitRptBaseServlet");
                        request.getRequestDispatcher("InitRptBaseServlet?requestServlet=" + servlet).forward(servletRequest, servletResponse);
                    }
                } else {
                    request.getSession().setAttribute("PermisiionMessge","您无权进行当前操作！");
                    request.getSession().setAttribute("PermisiionStatusCode","300");
                    System.out.println("校验未通过，你没有权限进行当前操作！" + servlet);
                    System.out.println("即将跳转NoPermissionServlet");
                    request.getRequestDispatcher("NoPermissionServlet").forward(servletRequest, servletResponse);
                }
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (CustomException e) {
            System.out.println("抓取到CustomerException" + servlet);
            request.getSession().setAttribute("PermisiionMessge",e.getMessage());
            request.getSession().setAttribute("PermisiionStatusCode","300");
            System.out.println("校验未通过，你没有权限进行当前操作！" + servlet);
            System.out.println("即将跳转NoPermissionServlet");
            request.getRequestDispatcher("NoPermissionServlet").forward(servletRequest, servletResponse);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("抓取到其他Exception！" + servlet);
            request.getSession().setAttribute("PermisiionMessge", e.getMessage());
            request.getSession().setAttribute("PermisiionStatusCode","300");
            System.out.println("即将跳转NoPermissionServlet");
            request.getRequestDispatcher("NoPermissionServlet").forward(servletRequest, servletResponse);
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
