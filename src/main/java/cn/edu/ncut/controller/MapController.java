package cn.edu.ncut.controller;

import cn.edu.ncut.common.Page;
import cn.edu.ncut.common.PageModel;
import cn.edu.ncut.model.PubMap;
import cn.edu.ncut.model.User;
import cn.edu.ncut.model.dto.IntegeratedQueryObj;
import cn.edu.ncut.model.dto.MapDto;
import cn.edu.ncut.model.view.VMap;
import cn.edu.ncut.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lh on 2017-6-6 11:14:18
 */

@Controller
@RequestMapping("/map")
public class MapController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public ModelAndView getAuthorDetail() {
        HashMap<String, String> result = new HashMap<String, String>();
        MapDto mapDto = userService.getMapData();

        result.put("pressdemonstration", returnString(mapDto.getPressDemonstration()));
        result.put("pressparticipate", returnString(mapDto.getPressParticipate()));
        result.put("issuedemonstration", returnString(mapDto.getIssueDemonstration()));
        result.put("issueparticipate", returnString(mapDto.getIssueParticipate()));
        return new ModelAndView("map/region-map", result);
    }

    private String returnString(List<VMap> list) {
        Map<String, Integer> cnMap = structureCNMap();
        Map<String, Integer> dbMap = new HashMap<String, Integer>();

        for (VMap mapDto : list) {

            dbMap.put(mapDto.getLocation(), mapDto.getCounter().intValue());
        }
        cnMap.putAll(dbMap);
        String returnString = dealStringMap(cnMap);
        return returnString;
    }

    private Map<String, Integer> structureCNMap() {
        Map<String, Integer> cnMap = new HashMap<String, Integer>();
        cnMap.put("北京", 0);
        cnMap.put("天津", 0);
        cnMap.put("上海", 0);
        cnMap.put("重庆", 0);
        cnMap.put("河北", 0);
        cnMap.put("河南", 0);
        cnMap.put("云南", 0);
        cnMap.put("辽宁", 0);
        cnMap.put("黑龙江", 0);
        cnMap.put("湖南", 0);
        cnMap.put("安徽", 0);
        cnMap.put("山东", 0);
        cnMap.put("新疆", 0);
        cnMap.put("江苏", 0);
        cnMap.put("浙江", 0);
        cnMap.put("江西", 0);
        cnMap.put("湖北", 0);
        cnMap.put("广西", 0);
        cnMap.put("甘肃", 0);
        cnMap.put("山西", 0);
        cnMap.put("内蒙古", 0);
        cnMap.put("陕西", 0);
        cnMap.put("吉林", 0);
        cnMap.put("福建", 0);
        cnMap.put("贵州", 0);
        cnMap.put("广东", 0);
        cnMap.put("青海", 0);
        cnMap.put("西藏", 0);
        cnMap.put("四川", 0);
        cnMap.put("宁夏", 0);
        cnMap.put("海南", 0);
        cnMap.put("台湾", 0);
        cnMap.put("香港", 0);
        cnMap.put("澳门", 0);
        return cnMap;
    }

    private String dealStringMap(Map<String, Integer> cnMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : cnMap.keySet()) {
            stringBuilder.append("{name: '");
            stringBuilder.append(s);
            stringBuilder.append("',value:");
            stringBuilder.append(cnMap.get(s));
            stringBuilder.append("},");
        }
        String string = stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
        return string;
    }


    @RequestMapping(value = "/list")
    public ModelAndView regionProductQuery(HttpServletRequest request, HttpServletResponse response, @ModelAttribute IntegeratedQueryObj integeratedQueryObj) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        HashMap<String, Object> result = new HashMap<String, Object>();
        String pageNumbers = request.getParameter("pageNumber");
        String pageSizes = request.getParameter("pageSize");
        String orderby = request.getParameter("sortColumns");
        String regionname = request.getParameter("regionname");
        String tlinfo = request.getParameter("tlinfo");
        if (tlinfo != null) {
            tlinfo = "(" + tlinfo + ")";
            if (tlinfo.contains("newbook") || tlinfo.contains("reprint"))
                integeratedQueryObj.setIstc("flagnoistc");
        }
        integeratedQueryObj.setProductstatus(tlinfo);
        //将tlinfo 按+号分割，得出数组，然后传值查询即可
//		String tlArray = tlinfo.split("+");
        if (null != regionname) {
            integeratedQueryObj.setRegionname(regionname);
        }
        PageModel pagemodel = processPageModel(pageNumbers, pageSizes);
        List<PubMap> productObjList = userService.integerateQuery(pagemodel.getFirstResultNumber(),
                pagemodel.getPageSize(), integeratedQueryObj, orderby);
        Page page = processPage(pageNumbers, pageSizes, integeratedQueryObj);
        result.put("regionname", integeratedQueryObj.getRegionname());
        result.put("productObjList", productObjList);
        result.put("page", page);
        if (tlinfo.length() > 1)
            result.put("tlinfo", tlinfo.substring(1, tlinfo.length() - 1));

        return new ModelAndView("map/regionproduct-show", result);
    }

    private Page processPage(String pageNumbers, String pageSizes, IntegeratedQueryObj integeratedQueryObj) {
        Page page = new Page();
        if (pageNumbers == null || pageNumbers.equals("")) {
            pageNumbers = "1";
        }
        if (pageSizes == null || pageSizes.equals("")) {
            pageSizes = "15";
        }
        int pageNumber = Integer.parseInt(pageNumbers);
        int pageSize = Integer.parseInt(pageSizes);
        page.pageNumber = pageNumber;
        page.pageSize = pageSize;
        page.totalCount = userService.integerateQueryCount(integeratedQueryObj);
        return page;
    }

    private PageModel processPageModel(String pageNumbers, String pageSizes) {
        PageModel page = new PageModel();
        if (pageNumbers == null || pageNumbers.equals("")) {
            pageNumbers = "1";
        }
        if (pageSizes == null || pageSizes.equals("")) {
            pageSizes = "15";
        }
        page.setFirstResultNumber(
                page.getfirstResultNumber(Integer.parseInt(pageNumbers), Integer.parseInt(pageSizes)));
        page.setPageSize(Integer.parseInt(pageSizes));

        return page;
    }

}
