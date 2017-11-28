
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2">
    <meta http-equiv="description" content="this is my page">
    <style type="text/css">
        *{margin:0;padding: 0;}
        html,body{width:100%;height:100%;}
        body{background:url(img/bg.jpg) no-repeat center;}
        .media{width:534px;height:400px; margin:40px auto 0; transform-style: preserve-3d;
            transform: perspective(600px) rotateY(25deg);
            position:relative;
            overflow: hidden;
        }
        #login{
            width: 200px;
        height:50px;
        background:blue;
            margin:60px auto 0;

            text-align:center;
            line-height: 50px;
            color: #fff;
        }
        #canvas{
            display: none;
        }
        #scan{
            position:absolute;
            width:100%;
            height:100%;
            background:url("img/scan.png");
            background-size: cover;
        }
    </style>
</head>
<body>
<div class="media">
    <video id="video" width="500" height="400" src="" autoplay="autoplay"></video>
    <canvas id="canvas" width="500" height="400"></canvas>
    <div id="scan"></div>
</div>
<div id ="login">登陆</div>
<script src="js/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
    var video=document.getElementById('video');
    var btn=document.getElementById('login');
    var context = canvas.getContext("2d");
    var getUserMedia= (navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia)
        getUserMedia.call(navigator,{video:true,audio:false},function(localMediaStream){
            video.src=window.URL.createObjectURL(localMediaStream);
            video.onloadedmedata=function () {
                video.play();
            }
        },function(e){console.log("获取视频失败"+e)});
    btn.onclick = function () {
        scan();
        //点击按钮时，获取面部信息
        context.drawImage(video,0,0,534,400);
        var imgSrc=document.getElementById('canvas').toDataURL("img/png");
        var faceBase = imgSrc.split(",")[1];
        $.ajax({
            url:"login",
            type:"post",
            data:{"faceBase":faceBase},
            success:function(result){
                var score = evel(result);
                if(score>=90){
                    window.location.href="http://www.baidu.com";
                }
                else{
                    alert("登陆失败");
                }
            }
        })

    }
    function scan() {
        var box = $(".media");
        $("#scan").css({"bottom": box.height()}).animate({bottom: 0}, 2000, function () {
            $(this).css({"bottom": box.height()})
        })
    }
    window.setInterval(scan,2000);
</script>
</body>
</html>
