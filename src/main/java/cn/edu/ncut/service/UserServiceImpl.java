package cn.edu.ncut.service;

import cn.edu.ncut.common.translate.TransApi;
import cn.edu.ncut.dao.PubMapDao;
import cn.edu.ncut.dao.UserDao;
import cn.edu.ncut.dao.VMapDao;
import cn.edu.ncut.model.PubMap;
import cn.edu.ncut.model.User;
import cn.edu.ncut.model.dto.IntegeratedQueryObj;
import cn.edu.ncut.model.dto.MapDto;
import cn.edu.ncut.model.view.VMap;
import cn.edu.ncut.service.base.BaseServiceImpl;
import com.google.gson.Gson;
import lyj.participle.NlpirTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lh on 2017/4/14.
 */
@Transactional
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<Object> implements UserService {

    @Value("${com.hxqh.agp.appid}")
    private String APP_ID;

    @Value("${com.hxqh.agp.securitykey}")
    private String SECURITY_KEY;

    @Value("${com.hxqh.agp.lang.en}")
    private String langEn;

    @Value("${com.hxqh.agp.lang.zh}")
    private String langZh;

    @Autowired
    private PubMapDao pubMapDao;
    @Autowired
    private VMapDao vMapDao;
    @Autowired
    private UserDao userDao;

    public List<User> getUserList() {
        return userDao.findAll();
    }

    public void getParticiple() {
        String argu = "D:\\win64";
        // String system_charset = "GBK";//GBK----0
        String system_charset = "UTF-8";
        int charset_type = 1;

        int init_flag = NlpirTest.CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
        String nativeBytes = null;

        if (0 == init_flag) {
            nativeBytes = NlpirTest.CLibrary.Instance.NLPIR_GetLastErrorMsg();
            System.err.println("初始化失败！fail reason is " + nativeBytes);
            return;
        }

        String sInput = "据悉，质检总局已将最新有关情况再次通报美方，要求美方加强对输华玉米的产地来源、运输及仓储等环节的管控措施，有效避免输华玉米被未经我国农业部安全评估并批准的转基因品系污染。";

        //String nativeBytes = null;
        nativeBytes = participle(nativeBytes, sInput);
    }


    public String getParticiple(String s) {
        String argu = "D:\\win64";
        // String system_charset = "GBK";//GBK----0
        String system_charset = "UTF-8";
        int charset_type = 1;

        int init_flag = NlpirTest.CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
        String nativeBytes = null;

        if (0 == init_flag) {
            nativeBytes = NlpirTest.CLibrary.Instance.NLPIR_GetLastErrorMsg();
            System.err.println("初始化失败！fail reason is " + nativeBytes);
            return " ";
        }

        String sInput = s;

        //String nativeBytes = null;
        nativeBytes = participle(nativeBytes, sInput);
        return nativeBytes;
    }

    private String participle(String nativeBytes, String sInput) {
        try {
            nativeBytes = NlpirTest.CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);

            System.out.println("分词结果为： " + nativeBytes);

            NlpirTest.CLibrary.Instance.NLPIR_AddUserWord("要求美方加强对输 n");
            NlpirTest.CLibrary.Instance.NLPIR_AddUserWord("华玉米的产地来源 n");
            nativeBytes = NlpirTest.CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
            //System.out.println("增加用户词典后分词结果为： " + nativeBytes);

            NlpirTest.CLibrary.Instance.NLPIR_DelUsrWord("要求美方加强对输");
            nativeBytes = NlpirTest.CLibrary.Instance.NLPIR_ParagraphProcess(sInput, 1);
            //System.out.println("删除用户词典后分词结果为： " + nativeBytes);


            int nCountKey = 0;
            String nativeByte = NlpirTest.CLibrary.Instance.NLPIR_GetKeyWords(sInput, 10, false);

            //System.out.print("关键词提取结果是：" + nativeByte);

            nativeByte = NlpirTest.CLibrary.Instance.NLPIR_GetFileKeyWords("D:\\NLPIR\\feedback\\huawei\\5341\\5341\\产经广场\\2012\\5\\16766.txt", 10, false);

            //System.out.print("关键词提取结果是：" + nativeByte);


            NlpirTest.CLibrary.Instance.NLPIR_Exit();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nativeBytes;
    }


    /**
     * 翻译
     */
    @Override
    public String translate(String input) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = input;

        String str;
        try {
            str = api.getTransResult(query, "zh", "en");
            Gson gs = new Gson();
            Map<String, ArrayList> map = gs.fromJson(str, Map.class);
            //System.out.println(map.get("trans_result"));
            if (map == null)
                return "Err";
            String string = map.get("trans_result").toString();
            string = string.substring(2, string.length() - 2);
            // System.out.println(string);


            String[] split = string.split(",");
            String[] lang = split[1].split("=");

            if (lang.length >= 2) {
                System.out.println(lang[1]);
                return lang[1];
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "ERROR";
    }

    @Override
    public MapDto getMapData() {
        Map<String, Object> params = new HashMap<String, Object>();

        params.put("pressDemonstration", "出版社-示范");
        params.put("pressParticipate", "出版社-参与");
        params.put("issueDemonstration", "发行-示范");
        params.put("issueParticipate", "发行-参与");
        String pressDemonstration = "pubtype=:pressDemonstration ";
        String pressParticipate = "pubtype=:pressParticipate ";
        String issueDemonstration = "pubtype=:issueDemonstration ";
        String issueParticipate = "pubtype=:issueParticipate ";

        List<VMap> pressDemonstrationList = vMapDao.findAll(pressDemonstration, params, null);
        List<VMap> pressParticipateList = vMapDao.findAll(pressParticipate, params, null);
        List<VMap> issueDemonstrationList = vMapDao.findAll(issueDemonstration, params, null);
        List<VMap> issueParticipateList = vMapDao.findAll(issueParticipate, params, null);
        MapDto mapDto = new MapDto(pressDemonstrationList, pressParticipateList, issueDemonstrationList, issueParticipateList);
        return mapDto;
    }

    @Override
    public List<PubMap> integerateQuery(int firstResultNum, int lastResultNum, IntegeratedQueryObj integeratedQueryObj, String orderby) {
        List<PubMap> productObjList = pubMapDao.findAll(firstResultNum, lastResultNum,getwhere(integeratedQueryObj), null, null);
        if(productObjList.size()==0){
            return null;
        }
        else{
            return productObjList;
        }
    }

    @Override
    public Integer integerateQueryCount(IntegeratedQueryObj integeratedQueryObj) {
        return new Long(pubMapDao.getCount(getwhere(integeratedQueryObj), null)).intValue();
    }

    private String getwhere(IntegeratedQueryObj integeratedQueryObj)
    {
        StringBuilder wherebuilder = new StringBuilder();
        if (integeratedQueryObj.getRegionname()!= null && !"".equals(integeratedQueryObj.getRegionname().trim())) {
            StringBuilder stringBuilder = new StringBuilder(" (");
            stringBuilder.append(" Location ='").append(integeratedQueryObj.getRegionname()).append("')");
            wherebuilder.append(stringBuilder);
        }
        if (integeratedQueryObj.getProductstatus()!= null && !"".equals(integeratedQueryObj.getProductstatus().trim())) {
            StringBuilder stringBuilder = new StringBuilder("and (");
            stringBuilder.append(" pubtype in ").append(integeratedQueryObj.getProductstatus()).append(")");
            wherebuilder.append(stringBuilder);
        }
        return  wherebuilder.toString();
    }

}
