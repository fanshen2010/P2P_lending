package cn.com.p2p.contentmanagent.sevice;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.com.p2p.contentmanagent.service.AboutUsService;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria;
import cn.com.p2p.domain.cms.criteria.ArticleCriteria.OrderField;
import cn.com.p2p.domain.cms.dto.AboutUsDto;
import cn.com.p2p.domain.cms.entity.Article;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AboutUsServiceTest {
	
	@Autowired
	AboutUsService aboutUsService;
	
	private ArticleCriteria criteria = new ArticleCriteria();
	
    @Test
    public void printTest(){
    	criteria.setSortFields(OrderField.orderNum, SortType.ASC);
    	criteria.setSortFields(OrderField.postAt, SortType.DESC);
    	criteria.setSortFields(OrderField.updateTime, SortType.DESC);
    	criteria.setStatus("1", Operator.equal);
    	AboutUsDto aboutUsDto = null;
    	AboutUsDto result = aboutUsService.findCategoryArticle(aboutUsDto, criteria);
    	assertNotNull(result);
    	assertEquals("ArticleTest4", result.getArticles().get(0).getTitle());
    }

}
