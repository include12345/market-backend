package com.lihebin.market.wx.service.impl;

import com.google.code.kaptcha.Producer;
import com.lihebin.market.enums.CodeEnum;
import com.lihebin.market.exception.BackendException;
import com.lihebin.market.wx.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by lihebin on 2021/4/14.
 */
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private Producer kaptchaProducer;

    @Override
    public String getKaptcha(HttpServletRequest request) {
        // 生成验证码
        String text = kaptchaProducer.createText();
        BufferedImage image = kaptchaProducer.createImage(text);
        HttpSession session = request.getSession();
        session.setAttribute("kaptcha", text);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpeg", outputStream);
            String base64 = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            return "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
        } catch (IOException e) {
            throw new BackendException(CodeEnum.KAPTCHA_NULL);
        }
    }
}
