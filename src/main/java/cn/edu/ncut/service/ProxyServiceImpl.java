package cn.edu.ncut.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2017/4/28.
 */
@Transactional
@Service("proxyService")
public class ProxyServiceImpl implements ProxyService{
    @Override
    public void save(String param) {
        System.out.println("Save Method");
    }

    @Override
    public void update(String param) {
        System.out.println("Update Method");
    }
}
