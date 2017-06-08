package cn.edu.ncut.service;

import cn.edu.ncut.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lh on 2017/4/14.
 */
@Transactional
@Service("zooService")
public class ZooServiceImpl extends BaseServiceImpl<Object> implements ZooService{

    @Override
    public String select(String param) {
        return param;
    }

    @Override
    public String update(String param) {
        return param;
    }
}
