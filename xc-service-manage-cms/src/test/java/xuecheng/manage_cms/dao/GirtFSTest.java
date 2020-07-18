package xuecheng.manage_cms.dao;

import com.mongodb.client.gridfs.GridFSBucket;
import org.bson.types.ObjectId;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsCriteria;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GirtFSTest {

    @Autowired
    private GridFSBucket gridFSBucket;
    @Autowired
    private GridFsTemplate gridFsTemplate;

    //存入文件名字
    @Test
    public  void testStore() throws FileNotFoundException {
        File file = new File("文件路径");
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectId store = gridFsTemplate.store(fileInputStream, "存入文件名字");
    }

    //取出文件
    @Test
    public  void queryFile(){
       // gridFsTemplate.findOne(Query.query(Crica))
    }
}
