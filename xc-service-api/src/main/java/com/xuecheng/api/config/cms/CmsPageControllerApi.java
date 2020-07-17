package com.xuecheng.api.config.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author 32456
 */
@Api(value = "cms页面管理接口",description = "ddd ")
public interface CmsPageControllerApi {

    @ApiOperation(value = "分页查询页面列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name ="size",value = "每页数量",required = true,paramType = "path",dataType = "int")
    })
    public QueryResponseResult findList(int page,int size,  QueryPageRequest queryPageRequest);

    @ApiOperation(value = "新增页面")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "")
    })
    public CmsPageResult add(CmsPage cmsPage);

    @ApiOperation(value = "根据页面ID查询页面ID")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "")
    })
    public CmsPage findById(String id);

    @ApiOperation(value = "根据页面Id修改页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "")
    })
    public CmsPageResult edit(String id,CmsPage cmsPage);

    @ApiOperation(value = "根据页面Id页面信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "")
    })
    public ResponseResult delete(String id);

}
