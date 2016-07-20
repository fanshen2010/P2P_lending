package cn.com.p2p.loan.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.code.EnumCodeList;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.dao.BaseCriteria.SortType;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.enumpack.LoanTimeLimitUnitEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.StringUtils;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LoanSearchServiceTest {
	
	@Autowired
	LoanSearchService loanSearchService;
	
    @Test
    public void Test(){
    	LoanCommSelCriteria criteria = new LoanCommSelCriteria();
        List<String> lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_07.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_11.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_12.getCode());
        criteria.setStatus(lstStatus);
        criteria.setLoanPostTime(DateUtils.getCurrentDateTime());
        List<Loan>lstLoan = loanSearchService.getPageLoanObjectList(criteria);
        
        assertNotNull(lstLoan);
    }
    
    
    public void Test3() throws Exception{
        EnumCodeList enumCodeList = new EnumCodeList(
                (Class<? extends Enum<?>>) Class.forName("cn.com.p2p.framework.enumpack.LoanInterestMannerEnum"));
        Map<String,String> rstList = enumCodeList.toMap();
        
        assertNotNull(rstList);
    }
    
    public void Test4() throws Exception{
    	EnumCodeList enumCodeList = new EnumCodeList(
                (Class<? extends Enum<?>>) Class.forName("cn.com.p2p.framework.enumpack.LoanTimeLimitEnum"));
        Map<String,String> loanTimeLimitList = enumCodeList.toMap();
        
        assertNotNull(loanTimeLimitList);
    }
    
}

