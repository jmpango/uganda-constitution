package org.uganda.constitution.api.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Represents a rule of procedure under an Act in a constitution
 *
 * @author Jonathan
 */

@Entity
@Table(name = "rule_of_procedure")
public class RuleOfProcedure extends BaseData{
    private String tile;
    private String sINo;
    private List<Rule> rules;
    private Act act;

    public RuleOfProcedure(){}

    @ManyToOne()
    @JoinColumn(name = "act_id", nullable = true)
    public Act getAct() {
        return act;
    }

    public void setAct(Act act) {
        this.act = act;
    }

     @OneToMany(mappedBy = "ruleOfProcedure", cascade = { CascadeType.ALL })
    public List<Rule> getRules() {
        return rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    @Column(name = "sINo", nullable = true)
    public String getsINo() {
        return sINo;
    }

    public void setsINo(String sINo) {
        this.sINo = sINo;
    }

    @Column(name = "title", nullable = true)
    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
    }
}
