package com.example.postgresdemo.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VcapServices {
    @Override
    public String toString() {
        return "VcapServices{" +
                "pPostgres=" + pPostgres +
                '}';
    }

@SerializedName(value = "p.postgres")
private List<PPostgres> pPostgres;

    public List<PPostgres> getPPostgres() {
        return pPostgres;
    }

    public void setPPostgres(List<PPostgres> value) {
        this.pPostgres = value;
    }


}

// PPostgres.java


// Credentials.java


