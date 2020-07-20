package com.xuecheng.manage_cms_client.service;

import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class PageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PageService.class);

    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private CmsSiteRepository cmsSiteRepository;
    @Autowired
    private CmsPageRepository cmsPageRepository;

    //保存页面到物理路径
    public void savePageToServerPath(String pageId) {

        //得到html文件Id 从cmsPage中获取
        CmsPage cmsPage = this.findPageById(pageId);
        //从gridFS中查询html文件
        String htmlFileId = cmsPage.getHtmlFileId();
        InputStream inputStream = this.getFileById(htmlFileId);
        if (inputStream == null) {
            LOGGER.error(htmlFileId);
            return;
        }
        //将html文件保存到服务器物理路径上

        //得到站点id
        String siteId = cmsPage.getSiteId();
        //得到站点信息
        CmsSite cmsSite = this.findCmsSiteBySiteId(siteId);
        //得到站点物理路径
        String sitePhysicalPath = cmsSite.getItePhysicalPath();
        //得到页面物理路径
        String pagePath = sitePhysicalPath + cmsPage.getPagePhysicalPath() + cmsPage.getPageName();
        //将html保存到物理服务器上
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(pagePath));
            IOUtils.copy(inputStream, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //从gridFS中查询html文件
    private InputStream getFileById(String htmlId) {
        //文件对象
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("id").is(htmlId)));
        //获得下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile, gridFSDownloadStream);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据页面id查询页面信息
    public CmsPage findPageById(String pageId) {
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    //根据站点idchaxun zhandian xinxi
    public CmsSite findCmsSiteBySiteId(String siteId) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (optional.isPresent()) {
            CmsSite cmsSite = optional.get();
            return cmsSite;
        }
        return null;
    }
}
