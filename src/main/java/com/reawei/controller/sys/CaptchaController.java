package com.reawei.controller.sys;

import com.baomidou.kisso.annotation.Action;
import com.baomidou.kisso.annotation.Login;
import com.baomidou.kisso.annotation.Permission;
import com.reawei.common.MyCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * <p>
 * 验证码服务
 * </p>
 * 
 * @author hubin
 * @Date 2016-01-06
 */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController {

	/**
	 * 生成图片
	 */
	@ResponseBody
	@Login(action = Action.Skip)
	@Permission(action = Action.Skip)
	@RequestMapping("/image")
	public void image(String ctoken) {
		try {
			MyCaptcha.getInstance().generate(request, response.getOutputStream(), ctoken);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
