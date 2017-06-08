package cn.edu.ncut.controller;

import cn.edu.ncut.model.User;
import cn.edu.ncut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lh on 2017/4/14.
 */

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String getAuthorDetail() {
        Map<String, Object> result = new HashMap<>();
        List<User> userList = userService.getUserList();
        result.put("size", userList.size());
        return "/searchStatics/index";
    }

}
