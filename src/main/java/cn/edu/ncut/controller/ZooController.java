package cn.edu.ncut.controller;

import cn.edu.ncut.model.User;
import cn.edu.ncut.service.ZooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lh on 2017/4/23.
 */
@Controller
@RequestMapping("/zoo")
public class ZooController {

    @Autowired
    private ZooService zooService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String testZoo() {
        return  zooService.select("select");
    }
}
