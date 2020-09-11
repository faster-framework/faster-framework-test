package cn.org.faster.framework.test.elasticsearch;

import cn.org.faster.framework.test.elasticsearch.entity.UserEntity;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhangbowen
 * @since 2019/3/8
 */
@SpringBootTest(classes = TestApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexDocumentTest {
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void testIndex() {
        elasticsearchTemplate.createIndex(UserEntity.class);
        UserEntity test = new UserEntity();
        test.setPhone("18764050615");
        test.setName("张三");
        elasticsearchTemplate.index(new IndexQueryBuilder().withObject(test).build());
        elasticsearchTemplate.refresh(UserEntity.class);
        List<UserEntity> list = elasticsearchTemplate.queryForList(new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchAllQuery())
                .build(),UserEntity.class);
        System.out.println(list);
    }
}
