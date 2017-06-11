package lxh.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lh on 2017/4/20.
 */
public class Realm extends AuthorizingRealm  {

    private static Map<String,String> userInfo = new HashMap<>();
    static {
        userInfo.put("zs","123");
        userInfo.put("ls","123");
        userInfo.put("ww","456");
    }

    @Override
    public void setName(String name) {
        super.setName("realm"); //类名小写

    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String usercode = (String)authenticationToken.getPrincipal();
        String pwd = null;

        for(Map.Entry<String,String> map :userInfo.entrySet())
        {
            if(usercode.equals(map.getKey()))
            {
                pwd= map.getValue();
                break;
            }
        }
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(usercode,pwd,this.getName());

        return simpleAuthenticationInfo;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }
}
