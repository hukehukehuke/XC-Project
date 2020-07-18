package xuecheng.manage_cms.controller;


import com.xuecheng.api.config.cms.CmsConfigControllerApi;
import com.xuecheng.framework.domain.cms.CmsConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xuecheng.manage_cms.service.PageService;

/**
 * @author Administrator
 * @version 1.0
 * @create 2018-09-15 14:22
 **/
@RestController
@RequestMapping("/cms/config")
public class CmsConfigController implements CmsConfigControllerApi {

    @Autowired
    PageService pageService;

    @Override
    @GetMapping("/getmodel/{id}")
    public CmsConfig getmodel(@PathVariable("id") String id) {
        return pageService.getConfigById(id);
    }
}
