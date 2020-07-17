package xuecheng.manage_cms.service;

import com.sun.webkit.PageCache;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.PathVariable;
import xuecheng.manage_cms.dao.CmsPageRepository;

import java.util.Optional;

public class PageService {

    @Autowired
    private CmsPageRepository cmsPageRepository;

    /**
     * 页面查询方法
     *
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest == null) {
            queryPageRequest = new QueryPageRequest();
        }
        ExampleMatcher exampleMatcher = ExampleMatcher.matching
                ().withMatcher("pageAliase", ExampleMatcher.GenericPropertyMatchers.contains());
        CmsPage cmsPage = new CmsPage();
        if (StringUtils.isNotEmpty(queryPageRequest.getSiteId())) {
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getTemplateId())) {
            cmsPage.setTemplateId(queryPageRequest.getTemplateId());
        }
        if (StringUtils.isNotEmpty(queryPageRequest.getPageAliase())) {
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }
        Example<CmsPage> example = Example.of(cmsPage, exampleMatcher);
        if (page <= 0) {
            page = 1;
        }
        page = page - 1;

        Pageable pageCache = PageRequest.of(page, size);
        Page<CmsPage> cmsPages = cmsPageRepository.findAll(example, pageCache);
        QueryResult queryResult = new QueryResult();
        queryResult.setList(cmsPages.getContent());
        queryResult.setTotal(cmsPages.getTotalPages());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS, queryResult);
        return queryResponseResult;
    }

    public CmsPageResult add(CmsPage cmsPage) {
        CmsPage cmsPage1 = cmsPageRepository.findByPageNameAndSiteIdAndWebPath(cmsPage.getPageName(),
                cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (cmsPage1 != null) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_SAVEHTMLERROR);
        }
        cmsPage.setPageId(null);
        cmsPageRepository.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
    }

    public CmsPage getById(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            CmsPage cmsPage = optional.get();
            return cmsPage;
        }
        return null;
    }

    public CmsPageResult update(String id, CmsPage cmsPage) {
        CmsPage cmsPage1 = this.getById(id);
        if (cmsPage1 != null) {
            cmsPage1.setPageAliase(cmsPage.getPageAliase());
            cmsPage1.setPageParameter(cmsPage.getPageParameter());
            cmsPage1.setPageName(cmsPage.getPageName());
            cmsPageRepository.save(cmsPage1);
            return new CmsPageResult(CommonCode.SUCCESS, cmsPage);
        }
        return null;
    }

    public ResponseResult delete(String id) {
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()) {
            cmsPageRepository.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }
}
