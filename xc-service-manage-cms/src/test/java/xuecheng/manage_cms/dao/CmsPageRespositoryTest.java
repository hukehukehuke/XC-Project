package xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CmsPageRespositoryTest {

    @Autowired
    private CmsPageRepository cmsPageRepository;


    @Test
    public void testFindAllByExmaple(){
        int page = 10;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("站点ID");
        cmsPage.setTemplateId("模板ID");
        cmsPage.setPageAliase("页面别名");
        ExampleMatcher exampleMatcher = ExampleMatcher.matching();
        exampleMatcher = exampleMatcher.withMatcher("pageAliase",ExampleMatcher.GenericPropertyMatchers.contains());
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);
        Page<CmsPage> all = cmsPageRepository.findAll(example, pageable);
        List<CmsPage> content = all.getContent();

    }

    @Test
    public void testFindAll() {
        List<CmsPage> all = cmsPageRepository.findAll();
    }

    @Test
    public void findPage(){
        int size = 10;
        int page = 0; //页码从0开始
        Pageable pageable = PageRequest.of(page, size);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
    }
    @Test
    public void testUpdate(){
        Optional<CmsPage> optional = cmsPageRepository.findById("22222222");
        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageAliase("修改");
            cmsPageRepository.save(cmsPage);
        }
    }
    @Test
    public void findByPageName(){
        CmsPage cmsPage = cmsPageRepository.findByPageName("测试名字");
    }
}
