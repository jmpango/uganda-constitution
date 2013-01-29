package org.uganda.constitution.api.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a rule under Rule of Procedure in a constitution
 *
 * @author Jonathan
 */
@Entity
@Table(name = "rule")
public class Rule extends BaseData {
    private String ruleNumber;
    private String sINo;
    private RuleOfProcedure ruleOfProcedure;
    private List<Rule> subRules;

    public Rule(){}

    @Column(name = "rule_number", nullable = true)
    public String getRuleNumber() {
        return ruleNumber;
    }

    public void setRuleNumber(String ruleNumber) {
        this.ruleNumber = ruleNumber;
    }

    @ManyToOne()
    @JoinColumn(name = "rule_of_procedure_id", nullable = true)
    public RuleOfProcedure getRuleOfProcedure() {
        return ruleOfProcedure;
    }

    public void setRuleOfProcedure(RuleOfProcedure ruleOfProcedure) {
        this.ruleOfProcedure = ruleOfProcedure;
    }

    @Column(name = "sINo", nullable = true)
    public String getsINo() {
        return sINo;
    }

    public void setsINo(String sINo) {
        this.sINo = sINo;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "sub_rule", joinColumns = @JoinColumn(name = "rule_id"),
    inverseJoinColumns = @JoinColumn(name = "sub_rule_id"))
    public List<Rule> getSubRules() {
        return subRules;
    }

    public void setSubRules(List<Rule> subRules) {
        this.subRules = subRules;
    }
}
