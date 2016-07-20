package cn.com.p2p.ui.control;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.p2p.domain.loan.criteria.RepayDetailCriteria;
import cn.com.p2p.domain.loan.entity.RepayDetail;
import cn.com.p2p.domain.loan.repository.RepayDetailRepository;
import cn.com.p2p.framework.dao.BaseCriteria.Operator;
import cn.com.p2p.framework.enumpack.ApproveTypeEnum;
import cn.com.p2p.framework.enumpack.LoanStatusEnum;
import cn.com.p2p.framework.util.DirectiveUtils;
import cn.com.p2p.framework.util.StringUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 根据融资编号获得审批类型
 * @author 
 *
 */
public class ApproveTypeControl implements TemplateDirectiveModel {

    
    
    @Autowired
    private RepayDetailRepository repayDetailRepository;
    
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws  TemplateException, IOException {
            // 融资编号
            String loanCode = DirectiveUtils.getString("loanCode", params);
               
                // 融资状态
                String loanStatus = DirectiveUtils.getString("loanStatus", params);
                
             // 获取common路径
                String commonResultPath = env.getVariable("commonResultPath").toString();
                if (StringUtils.isNotEmpty(loanCode)) {
                    String approveType="";
                    if (LoanStatusEnum.LOAN_STATUS_07.getCode().equals(loanStatus)) {
                        approveType= ApproveTypeEnum.APPROVE_TYPE_1.getValue();

                    } else if (LoanStatusEnum.LOAN_STATUS_10.getCode().equals(loanStatus)) {
                     // 当前还款期次
                        int curNum = 0;
                        try {
                            curNum = DirectiveUtils.getInt("curNum", params);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        RepayDetailCriteria criteria=new RepayDetailCriteria();
                        criteria.setLoanCode(loanCode, Operator.equal);
                        criteria.setNum(curNum, Operator.equal);
                        List<RepayDetail> list=repayDetailRepository.findByCriteria(criteria);
                        if(list!=null &&list.size()>0){
                            RepayDetail detail=list.get(0);
                            
                            String accountType=detail.getAccountType();
                            approveType= ApproveTypeEnum.getEnumByKey(accountType).getValue();                            
                        }else{
                        }
                    }
                    env.setVariable("status", DEFAULT_WRAPPER.wrap(approveType));
                    env.include("/" + commonResultPath + "/common/bizTag/statusTag.ftl", "UTF-8", true);

                }
    }
}
