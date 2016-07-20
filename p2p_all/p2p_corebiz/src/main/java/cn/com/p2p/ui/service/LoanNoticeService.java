package cn.com.p2p.ui.service;

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

public class LoanNoticeService implements TemplateDirectiveModel {

	@Autowired
	private LoanSearchService loanSearchService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		int pageSize=1;
		try {
			pageSize = DirectiveUtils.getInt("pageSize", params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Loan> loans = new ArrayList<Loan>();

		LoanCommSelCriteria loanParameter = new LoanCommSelCriteria();
		loanParameter.setLoanPostTime(DateUtils.getCurrentDateTime());
		List<String> status = new ArrayList<String>();
		status.add(LoanStatusEnum.LOAN_STATUS_04.getCode());
		loanParameter.setStatus(status);
		loanParameter.getPage().setDefalutPageRows(pageSize);
		loans = loanSearchService.getLoanNoticeList(loanParameter);

		env.setVariable("loans", DEFAULT_WRAPPER.wrap(loans));

		body.render(env.getOut());

	}

}
