package cn.itcast.action;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.itcast.service.UserService;
import cn.itcast.utils.BaseServlet;
import cn.itcast.vo.User;

public class UserServlet extends BaseServlet {
	public String login(HttpServletRequest req, HttpServletResponse res) {

		// 接收数据
		try {
			Map<String, String[]> map = req.getParameterMap();
			User user = new User();
			// 封装数据
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			User existUser = us.login(user);
			if (existUser == null) {
				// 用户登录失败
				req.setAttribute("msg", "用户名或密码错误!");
				return "/index.jsp";
			} else {
				// 使用监听器:HttpSessionBandingListener作用在JavaBean上的监听器.
				req.getSession().setAttribute("existUser", existUser);
				ServletContext application = getServletContext();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
