package xuecheng.manage_cms.controller;

import com.xuecheng.framework.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import xuecheng.manage_cms.service.PageService;

import javax.servlet.ServletOutputStream;
import java.io.IOException;

@Controller
public class CmsPagePrivewController extends BaseController {


    @Autowired
    private PageService pageService;

    //页面预览
    @GetMapping(value = "view/{pageId}")
    public void preview(@PathVariable(value = "pageId") String pageId) {
        String pageHtml = pageService.getPageHtml(pageId);
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(pageHtml.getBytes("UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
