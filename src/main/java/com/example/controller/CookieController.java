package com.example.controller;

// CookieクラスとHttpServletResponseクラスをインポートします
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cookie")
public class CookieController {

    // cookieの保存はよくPOSTメソッドにより行いますが、解説のためGETメソッドを採用しています
    @GetMapping("/set")
    @ResponseBody
    public String setCookie(@RequestParam String value, HttpServletResponse res) {

        // cookie名をtest_cookieとして保存します
        res.addCookie(new Cookie("test_cookie", value));
        return "保存しました";
    }
    
    @GetMapping("/get")
    @ResponseBody

    // @CookieValueを付けたCookieクラスのオブジェクトをcookie名を指定して引数に指定することでcookieを取得できます。
    public String getCookie(@CookieValue("test_cookie") Cookie cookie) {

        // getValue()メソッドを呼び出すことでcookieの値を取得できます。
        return "cookieValue: " + cookie.getValue();
    }
    
    @GetMapping("/delete")
    @ResponseBody
    public String deleteCookie(@CookieValue("test_cookie") Cookie cookie, HttpServletResponse res) {

        // CookieオブジェクトのsetMaxAge()メソッドを利用します。
        //クッキーの残存期間を秒数で指定することができ、0を指定することで、Cookieを削除することができます。
        cookie.setMaxAge(0);
        res.addCookie(cookie);
        return "削除しました";
    }
}