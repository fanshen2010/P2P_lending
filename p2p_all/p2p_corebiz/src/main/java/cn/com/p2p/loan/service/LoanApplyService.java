package cn.com.p2p.loan.service;


/**
 * 融资接口 <p> 融资的主要实现接口
 * 
 * @作者 何生
 * @创建时间 2015-09-13 10:14
 */

import java.util.List;
import java.util.Map;

import org.json.JSONException;

import cn.com.p2p.domain.system.entity.UploadFile;
import cn.com.p2p.framework.context.UserContext;
import cn.com.p2p.loan.service.dto.LoanDto;


public interface LoanApplyService {

    /**
     * <p>
     * 融资申请操作 *
     * </p>
     * .<br>
     * 
     * author：<br>
     * ===================================
     * 
     * @param loanDto 融资基本信息
     * @param loginUser 当前登录用户信息
     * @throws Exception
     */
    public void apply(LoanDto loanDto,UserContext userInfo) throws Exception;

    /**
     * <p>
     * 根据ID删除融资
     * </p>
     * .<br>
     * author：<br>
     * ===================================
     * 
     * @param pstrLoanCode 融资编号
     */
    public void doDeletetLoanById(String pstrLoanCode);
    
}
