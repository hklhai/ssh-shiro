package cn.edu.ncut.service;

import cn.edu.ncut.model.PubMap;
import cn.edu.ncut.model.User;
import cn.edu.ncut.model.dto.IntegeratedQueryObj;
import cn.edu.ncut.model.dto.MapDto;
import cn.edu.ncut.service.base.BaseService;

import java.util.List;

/**
 * Created by lh on 2017/4/14.
 */
public interface UserService extends BaseService {


    public abstract List<User> getUserList();

    public void getParticiple();

    public String getParticiple(String str);

    public abstract String translate(String input);

    public abstract MapDto getMapData();

    public abstract List<PubMap> integerateQuery(int firstResultNum, int lastResultNum, IntegeratedQueryObj integeratedQueryObj, String orderby);
    public abstract  Integer integerateQueryCount(IntegeratedQueryObj integeratedQueryObj);

}
