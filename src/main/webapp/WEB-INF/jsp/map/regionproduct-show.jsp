<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../commons/taglibs.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags/simpletable" prefix="simpletable" %>

<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="${ctx}/js/jquery-1.5.1.min.js" type="text/javascript"></script>
    <script type="text/javascript"
            src="${ctx}/widgets/simpletable/simpletable.js"></script>
    <script type="text/javascript" src="${ctx}/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(
                function () {
                    //设置表格样式，改变index为偶数行的背景颜色
                    $("#search-show tr:even").css("background-color", "#eff3f5");
                    // 分页需要依赖的初始化动作
                    window.simpleTable = new SimpleTable('queryForm',
                            "${page.thisPageNumber}", "${page.pageSize}",
                            "Location");
                });
        function checkAuthor(authorRealname) {
            //var organizationid = sender.parent().parent().find("select").val();
            //window.location="${ctx}/author/getAuthorByRealname?realname="+authorrealname;
            //console.log(authorRealname)
            $("#authorRealname").val(authorRealname)
            //$("#queryParams").attr("action",'');
            //$("#queryParams")[0].reset();
            $("#queryParams").submit()
        }

        function checkProduct(productid) {
            window.location = "${ctx}/query/getProductInfoByid?productid=" + productid;
        }
        $(document).ready(function () {
            $(".table_con tr:even").css("background-color", "#eff3f5");

        })
        function checkitem() {
            obj = document.getElementsByName("checkid");
            idlist = [];
            for (k in obj) {
                if (obj[k].checked)
                    idlist.push(obj[k].value);
            }
            return idlist;
        }
        function checkexcel() {
            if (checkitem().length == 0) {
                alert("请选择要导出项！");
            }
            else
                window.location = "${ctx}/export/excel?productlist=" + checkitem();
        }

        function openUrl(productid1) {

            var url = productid1;
            var a1 = document.getElementById(url);
            window.open(a1);
        }

        function excelall() {
            $("#queryParams3").submit()

        }
        function truewebsite(istc) {
            window.location = "${ctx}/query/istcbelongtoall?istc=" + istc;
        }
    </script>
    <script src="${ctx}/scripts/jquery.min.js"></script>
    <link rel="stylesheet" href="${ctx}/css/searchStatics/search-show.css"
          type="text/css"/>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/authorLibrary/Iframe.css"/>
    <link rel="stylesheet" href="${ctx}/css/bootstrap.min.css"
          type="text/css"/>
    <link href="${ctx}/widgets/simpletable/simpletable.css" type="text/css"
          rel="stylesheet">
    <title></title>

    <style>
        input[type="submit"], input[type="button"] {
            padding: 7px 15px;
            background-color: #3c6db0;
            text-align: center;
            border-radius: 5px;
            overflow: hidden;
            min-width: 80px;
            margin: 0px 0px 0px 20px;
            border: none;
            color: #FFF;
            box-shadow: 1px 1px 1px rgba(75, 75, 75, 0.3);
            float: left;
        }

        .table_con table tbody tr td {
            height: 30px;
            line-height: 25px;
            float: left;
            margin-top: 8px;
            padding: 0 5px;
            text-align: center;
            border-left: solid 1px #ccc;
        }


    </style>
</head>
<body>

<div id="layer">

    <form id="queryForm" name="queryForm"
          action="${ctx}/query/regionProductQuery" method="post">
        <h2 style="text-align: center; ">出版社列表</h2>

        <div class="table_con" style="font-size:16px;">
            <table>
                <tr class="tb_title" style="background-color: rgb(239, 243, 245);font-size:12px;height:45px;">
                    <td width="25%"  width="20%"><strong>地域</strong></td>
                    <td width="30%"  width="20%"><strong>出版社/发行</strong></td>
                    <td width="25%"  width="20%"><strong>状态</strong></td>
                </tr>
                <c:forEach items="${productObjList}" var="item" varStatus="pubtype">
                    <tr style="height: 45px;">
                        <td width="25%" style="font-size:13px"><c:out value='${item.location}'/></td>
                        <td width="30%" style="font-size:13px"><c:out value='${item.pubname}'/></td>
                        <%--<td width="6%"><a href="javascript:void(0);" onclick="checkProduct('${item.pubname}')"--%>
                                          <%--style="font-size:12px"><c:out value='${item.pubname}'/></a></td>--%>
                        <td width="25%" style="font-size:13px"><c:out value='${item.pubtype}'/></td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </form>
</div>
</body>
</html>