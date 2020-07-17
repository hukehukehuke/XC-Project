package xuecheng.manage_cms.controller;

import com.xuecheng.api.config.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xuecheng.manage_cms.service.PageService;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping(value = "/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private PageService pageService;

    @GetMapping(value = "/list{page}/{size}")
    @Override
    public QueryResponseResult findList(@PathVariable(value = "page") int page,
                                        @PathVariable(value = "size") int size, QueryPageRequest queryPageRequest) {
//
//        QueryResult<CmsPage> queryResult = new QueryResult<>();
//        List<CmsPage> list = new ArrayList<>();
//        CmsPage cmsPage = new CmsPage();
//        cmsPage.setPageName("测试页面");
//        list.add(cmsPage);
//        queryResult.setList(list);
//        queryResult.setTotal(1);
//        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return pageService.findList(page,size,queryPageRequest);
    }

    @Override
    @PostMapping(value = "add")
    public CmsPageResult add(@RequestBody CmsPage cmsPage){
        return pageService.add(cmsPage);
    }

    @Override
    @GetMapping(value = "findById/{id}")
    public CmsPage findById(@PathVariable(value = "id") String id) {
        return null;
    }

    @Override
    @PutMapping(value = "/editById")
    public CmsPageResult edit(@PathVariable(value = "id") String id, @RequestBody CmsPage cmsPage) {
        return null;
    }

    @Override
    @DeleteMapping(value = "del/{id}")
    public ResponseResult delete(@PathVariable(value = "id") String id) {
        return pageService.delete(id);
    }
}
