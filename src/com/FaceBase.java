package com;

import com.baidu.aip.face.AipFace;
import com.tz.IOPdemo.sysmanage.utils.GetToken;
import com.tz.IOPdemo.sysmanage.utils.HttpUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
@WebServlet(name = "/login")
public class FaceBase extends HttpServlet {
    private static final String APP_ID = "10446467";
    private static final String API_KEY = "ZEluRwORxfNOetsei4yNODLB";
    private static final String SECRET_KEY = "zehO8F9KL54Xi4ZT65GlKWaIS7amWZN8";
    private static final String MATH_URL = "https://aip.baidubce.com/rest/2.0/face/v2/match";
    private static String accessToken;

    static {

        accessToken = GetToken.getToken(API_KEY, SECRET_KEY);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String faceBase = request.getParameter("faceBase");
String database = "iVBORw0KGgoAAAANSUhEUgAAAfQAAAGQCAYAAABYs5LGAAAgAElEQVR4Xuy92ZbkVq4lSBs8dKseuv";
        System.out.println(faceBase);
        AipFace aipFace = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        String params = URLEncoder.encode("images", "UTF-8") + "=" + URLEncoder.encode(faceBase + "," + database, "UTF-8");

        String result = null;
        try {
            result = HttpUtil.post(MATH_URL, accessToken, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(111);
        JSONObject json = new JSONObject(result);
        JSONArray array = json.getJSONArray("result");
        if (array.length() < 1) {
            response.getWriter().print("0");
        } else {
            Double score = array.getJSONObject(0).getDouble("score");
            response.getWriter().print(score);
        }


    }
}
