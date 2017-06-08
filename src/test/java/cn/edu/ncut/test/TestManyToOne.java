package cn.edu.ncut.test;

import cn.edu.ncut.dao.ModelDao;
import cn.edu.ncut.dao.RoleDao;
import cn.edu.ncut.dao.RolemodelDao;
import cn.edu.ncut.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Ocean Lin on 2017/5/10.
 */

@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestManyToOne {


    @Resource
    private ModelDao modelDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RolemodelDao rolemodelDao;
    @Resource
    private UserDao userDao;

    @Test
    public void testSaveModel() {
//        ModelObj modelObj = new ModelObj(1,"系统管理","系统管理",1,0,null);
//        modelObjDao.save(modelObj);
//        ModelObj modelObj = new ModelObj(null,"用户管理","用户管理",1,0,1);
//        modelObjDao.save(modelObj);
//        ModelObj modelObj1 = new ModelObj(null,"角色管理","角色管理",1,0,1);
//        modelObjDao.save(modelObj1);


//        ModelObj m = new ModelObj(null, "网站管理", "网站管理", 1, 0, null);
//        modelObjDao.save(m);
//        ModelObj modelObj = new ModelObj(null, "主页", "主页", 1, 0, 4);
//        modelObjDao.save(modelObj);
//        ModelObj modelObj1 = new ModelObj(null, "最新动态", "最新动态", 1, 0, 4);
//        modelObjDao.save(modelObj1);
//        ModelObj modelObj2= new ModelObj(null, "关于我们", "关于我们", 1, 0, 4);
//        modelObjDao.save(modelObj2);
    }

    @Test
    public void testSaveRole() {
//        RoleObj roleObj = new RoleObj("网站管理","网站管理",1,1);
//        RoleObj roleObj1 = new RoleObj("系统管理","系统管理",1,2);
//        roleDao.save(roleObj);
//        roleDao.save(roleObj1);
    }

    /**
     * 设置系统管理员角色拥有系统管理与网站管理
     */
    @Test
    public void testRelative() {
//        RoleObj roleObj = roleDao.find(1);//系统管理员角色
//
//        ModelObj m1 = modelDao.find(1);//系统管理权限
//        ModelObj m2 = modelDao.find(4);//网站管理权限
//
//        RolemodelObj rolemodelObj = new RolemodelObj();
//        rolemodelObj.setTbModel(m1);
//        rolemodelObj.setTbRole(roleObj);
//
//        RolemodelObj rolemodelObj2 = new RolemodelObj();
//        rolemodelObj2.setTbModel(m2);
//        rolemodelObj2.setTbRole(roleObj);
//
//        rolemodelDao.save(rolemodelObj);
//        rolemodelDao.save(rolemodelObj2);
    }

    /**
     * 测试根据角色获取多个权限
     */
    @Test
    public void testGetRoleAndModel() {
//        RoleObj roleObj = roleDao.find(1);//系统管理员角色
//        List<RolemodelObj> tbRolemodels = roleObj.getTbRolemodels();
//        for (RolemodelObj rm : tbRolemodels) {
//            System.out.println(rm.getTbModel().getModelname());
//        }
    }
}