package com.xuecheng.manage_cms_client.mq;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.manage_cms_client.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CustomerPostPage {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerPostPage.class);
    @Autowired
    private PageService pageService;
    @RabbitListener(queues = "${xuecheng.mq.queue}")
    public void postPage(String msg){
        //解析信息
        Map map = JSON.parseObject(msg, Map.class);
        String pageId = (String) map.get("pageId");
        CmsPage cmsPage = pageService.findPageById(pageId);
        if(cmsPage == null){
            LOGGER.error(pageId);
        }
        pageService.savePageToServerPath(pageId);
    }
}
