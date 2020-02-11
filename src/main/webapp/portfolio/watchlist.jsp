<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <!-- head -->
        <%@include file="/WEB-INF/jsp/include/head.jspf"  %>
        <script>
            var watch_id = ${sessionScope.watch_id};

            $(document).ready(function () {
                watchList();
            });

            function watchList() {
                $.get("/SpringMVC/mvc/portfolio/watch/" + watch_id, function (data, status) {
                    console.log(JSON.stringify(data));
                    // 請撰寫
                    $("#myTable tbody > tr").remove();
                    $.each(data.tStocks, function (i, item) {
                        var html = '<tr>'+
                                   '<td align="center">{0}</td><td>{1}</td><td>{2}</td>'+
                                   '<td align="right">{3}</td><td align="right">{4}</td><td align="right">{5}</td>'+
                                   '<td align="right">{6}</td><td align="right">{7}</td><td>{8}</td>'+
                                   '<td tstock_id="{9}">{10}</td>'+
                                   '</tr>';
                        var tstock_id = "";
                        var buybtn_html = " ";
                        if (item.classify.transaction) {
                            buybtn_html = '<button type="button" class="pure-button pure-button-primary">下單</button>';
                            tstock_id = item.id;
                        }
                        $('#myTable').append(String.format(html,
                                item.classify.name,
                                item.name,
                                item.symbol,
                                item.preClosed,
                                item.price,
                                item.change,
                                item.changeInPercent,
                                numberFormat(item.volumn),
                                getYMDHMS(item.transactionDate),
                                tstock_id,
                                buybtn_html));
                    });
                });
            }
        </script>
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/WEB-INF/jsp/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/WEB-INF/jsp/include/menu.jspf"  %>

            <div id="main">
                <div class="header">
                    <h1>Watch List</h1>
                    <h2 id="head2">Watch List</h2>
                </div>

                <table id="myTable" class="pure-table pure-table-bordered" width="100%">
                    <thead>
                        <tr>
                            <th>分類</th><th>名稱</th><th>代號</th>
                            <th>昨收</th><th>報價</th><th>漲跌</th>
                            <th>漲跌幅%</th><th>交易量</th><th>交易時間</th>
                            <th>買進</th>
                        </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>
        <!-- Foot -->
        <%@include file="/WEB-INF/jsp/include/foot.jspf"  %>

    </body>
</html>
