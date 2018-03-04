<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <style type="text/css">
        body,html,#container{
            height: 100%;
            margin: 0px;
            font-size: 12px;
        }
        .panel {
            color: #333;
            padding: 6px;
            border: 1px solid silver;
            box-shadow: 3px 4px 3px 0px silver;
            position: absolute;
            background-color: #eee;
            top: 10px;
            right: 10px;
            border-radius: 5px;
            overflow: hidden;
            line-height: 20px;
        }
        #input{
            width: 250px;
            height: 25px;
        }
    </style>
    <title>地理编码</title>

</head>
<body>
<div id="container" tabindex="0"></div>
<div class ='panel'>
    输入地址显示位置:</br>
    <input id = 'input' value = '阜通东大街8号'/>
    <div id = 'message'></div>
    <div>起点：<span id = 'loc-start'></span></div>
    <input type="button" onclick="btn_start()" value="设为起点"/>
    <div>终点：<span id = 'loc-end'></span></div>
    <input type="button" onclick="btn_end()" value="设为终点"/>
    <br/>
    <input type="button" onclick="btn_submit()" value="提交"/>

</div>
<script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.2&key=231689d731641597dd40555f8a9acf8c"></script>
<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript">
    var map = new AMap.Map('container',{
        resizeEnable: true,
        zoom: 13,
        center: [116.39,39.9]
    });
    AMap.plugin('AMap.Geocoder',function(){
        var geocoder = new AMap.Geocoder({
            city: "010"//城市，默认：“全国”
        });
        var marker = new AMap.Marker({
            map:map,
            bubble:true
        })
        var input = document.getElementById('input');
        input.onchange = function(e){
            var address = input.value;
            geocoder.getLocation(address,function(status,result){
                if(status=='complete'&&result.geocodes.length){
                    marker.setPosition(result.geocodes[0].location);
                    map.setCenter(marker.getPosition())
                    document.getElementById('message').innerHTML = result.geocodes[0].location
                }else{
                    document.getElementById('message').innerHTML = '获取位置失败'
                }
            })
        }
        input.onchange();

    });

    function btn_start() {
        document.getElementById('loc-start').innerHTML = document.getElementById('message').innerHTML;
    }

    function btn_end() {
        document.getElementById('loc-end').innerHTML = document.getElementById('message').innerHTML;
    }

    function btn_submit() {
        jQuery.ajax({
            type: "POST",
            url: "/map/submit",
            data: "start="+jQuery("#loc-start").html()+"&end="+jQuery("#loc-end").html(),
            success: function(data){
                alert("success")
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert("系统异常")
            }
        });
    }
</script>
<script type="text/javascript" src="https://webapi.amap.com/demos/js/liteToolbar.js"></script>

</body>
</html>