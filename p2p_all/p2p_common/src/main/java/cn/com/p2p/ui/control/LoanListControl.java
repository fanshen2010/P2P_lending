package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.loan.criteria.LoanCommSelCriteria;
import cn.com.p2p.domain.loan.entity.Loan;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.util.DateUtils;
import cn.com.p2p.framework.util.DirectiveUtils;
import cn.com.p2p.loan.service.LoanSearchService;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取前台投资列表UI组件
 * @author 
 *
 */
public class LoanListControl implements TemplateDirectiveModel {

    /** 指定融资的所有信息查询 */
    @Autowired
    private LoanSearchService loanSearchService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
            throws TemplateException, IOException {
        int pageSize=4;
        try {
            pageSize = DirectiveUtils.getInt("pageSize", params);
        } catch (Exception e) {
            pageSize=4;
        }
      //投资列表
        List<String> lstStatus = new ArrayList<String>();
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_07.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_10.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_11.getCode());
        lstStatus.add(LoanStatusEnum.LOAN_STATUS_12.getCode());
        // lstStatus.add(LoanStatusEnum.LOAN_STATUS_13.getCode());
        /** 融资共通查询参数 */
        LoanCommSelCriteria criteria = new LoanCommSelCriteria();
        criteria.setStatus(lstStatus);
        criteria.setLoanPostTime(DateUtils.getCurrentDateTime());
        criteria.getPage().setDefalutPageRows(pageSize);
        List<Loan> loans = loanSearchService.getPageLoanObjectList(criteria);

        env.setVariable("loans", DEFAULT_WRAPPER.wrap(loans));
        env.setVariable("sysDate", DEFAULT_WRAPPER.wrap(DateUtils.getCurrentDateTime()));

        body.render(env.getOut());
        
    }

}
