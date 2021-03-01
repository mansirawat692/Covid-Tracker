package com.example.covidtracker;

class Model {
    public String flag, country,active,cases,recovered,death,today_case,today_recoverd,today_death,critical;

    public Model() {
    }

    public Model(String flag, String country, String active, String cases, String recovered, String death, String today_case, String today_recoverd, String today_death, String critical) {
        this.flag = flag;
        this.country = country;
        this.active = active;
        this.cases = cases;
        this.recovered = recovered;
        this.death = death;
        this.today_case = today_case;
        this.today_recoverd = today_recoverd;
        this.today_death = today_death;
        this.critical = critical;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    public String getToday_case() {
        return today_case;
    }

    public void setToday_case(String today_case) {
        this.today_case = today_case;
    }

    public String getToday_recoverd() {
        return today_recoverd;
    }

    public void setToday_recoverd(String today_recoverd) {
        this.today_recoverd = today_recoverd;
    }

    public String getToday_death() {
        return today_death;
    }

    public void setToday_death(String today_death) {
        this.today_death = today_death;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
