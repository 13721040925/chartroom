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

		// ��������
		try {
			Map<String, String[]> map = req.getParameterMap();
			User user = new User();
			// ��װ����
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			User existUser = us.login(user);
			if (existUser == null) {
				// �û���¼ʧ��
				req.setAttribute("msg", "�û������������!");
				return "/index.jsp";
			} else {
				// ʹ�ü�����:HttpSessionBandingListener������JavaBean�ϵļ�����.
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
