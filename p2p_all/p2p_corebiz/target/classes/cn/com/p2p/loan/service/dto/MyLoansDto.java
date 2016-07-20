package cn.com.p2p.loan.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.p2p.domain.loan.entity.Loan;

/**
 * <p>我的融资申请</p>
 * @author 
 * @date 2015-09-16 14:28
 */

public class MyLoansDto implements Serializable{

    private static final long serialVersionUID = -4202108197492380184L;

    /** 界面循环总List,此属性不具有实际意义，仅仅为了方便界面获取数据 */
    private List<List<Loan>> allList;
    
    /** 全部融资记录，此属性的值不是查询数据库而是其他五个状态的集合 */
    private List<Loan> all;
    
    /** 被驳回的融资记录 */
    private List<Loan> rejected;
    
    /** 审核中的融资记录 */
    private List<Loan> checking;
    
    /** 待生效的融资记录 */
    private List<Loan> effective;
    
    /** 还款中的融资记录 */
    private List<Loan> replaying;
    
    /** 已结束的融资记录 */
    private List<Loan> over;
    

    public MyLoansDto() {
        
        // 初始话数据传输对象时，所有融资状态下的全部记录
        // 全部的融资状态包括：被驳回、审核中、待生效、还款中、已结束
        this.all = new ArrayList<Loan>();
    }

    public List<Loan> getAll() {
        return all;
    }

    public void setAll(List<Loan> all) {
        this.all = all;
    }

    public List<Loan> getRejected() {
        return rejected;
    }

    public void setRejected(List<Loan> rejected) {
        this.rejected = rejected;
    }

    public List<Loan> getChecking() {
        return checking;
    }

    public void setChecking(List<Loan> checking) {
        this.checking = checking;
    }

    public List<Loan> getEffective() {
        return effective;
    }

    public void setEffective(List<Loan> effective) {
        this.effective = effective;
    }

    public List<Loan> getReplaying() {
        return replaying;
    }

    public void setReplaying(List<Loan> replaying) {
        this.replaying = replaying;
    }

    public List<Loan> getOver() {
        return over;
    }

    public void setOver(List<Loan> over) {
        this.over = over;
    }

    public List<List<Loan>> getAllList() {
        return allList;
    }

    public void setAllList(List<List<Loan>> allList) {
        this.allList = allList;
    }
    
}
