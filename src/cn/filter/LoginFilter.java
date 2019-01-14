package cn.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.catalina.User;

@WebFilter(filterName = "LoginFilter", 
urlPatterns = "/*", 
initParams = {
		@WebInitParam(name = "loginUI", value = "/login.jsp"),
		@WebInitParam(name = "loginProcess", value = "/public"),
		@WebInitParam(name = "encoding", value = "utf-8")
})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }
    
private FilterConfig config;
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
	}


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

		String loginUI = config.getInitParameter("loginUI");
		String loginProcess = config.getInitParameter("loginProcess");
		String encoding = config.getInitParameter("encoding");
		
 
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		
		// 设置请求的字符集（post请求方式有效）
		request.setCharacterEncoding(encoding);
		
		// 不带http://域名:端口的地址
		String uri = request.getRequestURI();
		if (uri.contains("/layui")||uri.contains(loginUI) || uri.contains(loginProcess)) {
			// 请求的登录，放行
			chain.doFilter(request, response);
		} else {
			if (request.getSession().getAttribute("s_stuID") == null) {
				// 重定向到登录页面
				response.sendRedirect(request.getContextPath() + loginUI);
			} else {
				// 已经登录，放行
				chain.doFilter(request, response);
			}
		}
	}
}
