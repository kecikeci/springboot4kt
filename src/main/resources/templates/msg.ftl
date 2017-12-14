<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>简单留言板</title>
    <link rel="stylesheet" href="//apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css">
    <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

<div class="container">
    <h3 class="text-center">简单留言板</h3>
    <div class="row">
        <div class="col-sm-10 col-sm-offset-1" style="margin-top: 30px">
            <textarea id="content" class="form-control" placeholder="说点什么吧..." rows="5"></textarea>
            <input id="nickname" type="text" class="form-control" style="margin-top: 10px" placeholder="昵称">
            <button id="submit" type="button" style="margin-top: 10px" class="btn btn-primary btn-block" onclick="submit()">留言</button>
        </div>
        <div class="col-sm-10 col-sm-offset-1" style="margin-top: 30px">
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" <#if type==1>class="active"</#if>><a href="/?type=1">最新留言</a></li>
                <li role="presentation" <#if type==2>class="active"</#if>><a href="/?type=2">最赞留言</a></li>
            </ul>
            <div class="tab-content" style="margin-top: 20px">
                <#list page.list as list>
                    <div style="margin-top: 10px;height: 170px">
                        <div class="col-sm-2 text-center">
                            <img style="max-width: 100%;max-height: 125px" class="img-circle" src="${list.face}">
                            <strong style="display: block;margin-top: 5px">${list.nick_name}</strong>
                        </div>
                        <div class="col-sm-10">
                            <p style="min-height: 120px;padding: 5px">
                                ${list.content!}
                            </p>
                            <div class="text-right" style="margin-top: 5px">
                                <a onclick="praise('${list.id}',this)" href="javascript:;">赞(<spen>${list.praise}</spen>)</a>
                                <span style="margin-left: 10px;color: #cccccc">${list.add_time?string("yyyy-MM-dd HH:mm")}</span>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>

            <#if page.total &gt; page.pageSize>
            <div class="text-center" style="margin-top: 30px">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <li>
                            <a href="/?pageNum=${page.navigateFirstPage}&type=${type}" aria-label="Previous">
                                <span aria-hidden="true">首页</span>
                            </a>
                        </li>
                        <#list page.navigatepageNums as list>
                            <li><a href="/?pageNum=${list}&type=${type}">${list}</a></li>
                        </#list>
                        <li>
                            <a href="/?pageNum=${page.navigateLastPage}&type=${type}" aria-label="Next">
                                <span aria-hidden="true">末页</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            </#if>

            <div class="text-center" style="margin-top: 30px">
                © <a href="https://4xx.me" target="_blank">For XX</a> 提供技术支持
            </div>

        </div>
    </div>
</div>

<script src="//apps.bdimg.com/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="//apps.bdimg.com/libs/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<script>

    function submit() {
        var nick_name = $.trim($("#nickname").val());
        var content = $.trim($("#content").val());
        if (!nick_name || !content){
            alert("不能为空")
            return
        }
        var face = "/image/face"+(parseInt(Math.random()*4,10)+1)+".jpg";
        $.post(
            "/addMessage.json",
            {nick_name:nick_name,content:content,face:face},
            function(json){
                if (json.code==1){
                    alert("留言成功");
                    window.location.href="/"
                }
            }
            ,"json"
        ).error(function(){
            alert("系统繁忙，请稍后再试");
        });
    }
    function praise(id,_this) {
        $(_this).removeAttr('onclick')
        $.post(
                "/praise.json",
                {id:id},
                function(json){
                    if (json.code==1){
                        $(_this).find("spen").text(parseInt($(_this).find("spen").text())+1)
                    }
                }
                ,"json"
        ).error(function(){
            alert("系统繁忙，请稍后再试");
        });
    }


</script>

</body>
</html>