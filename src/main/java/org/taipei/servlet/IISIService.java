package org.taipei.servlet;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin
public class IISIService {
    @RequestMapping(value = "/iisiservice", method = RequestMethod.POST)
    @ResponseBody
    public String iisiservice() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONObject obj = new JSONObject();
        obj.append("code", 0);
        obj.append("reason", "成功");
        obj.append("time", sdf.format(date));

        return obj.toString();

    }
}
