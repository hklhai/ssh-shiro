package cn.edu.ncut.dao;

import cn.edu.ncut.common.basedao.DaoSupport;
import cn.edu.ncut.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userDao")
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

}
