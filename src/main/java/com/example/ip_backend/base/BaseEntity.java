package com.example.ip_backend.base;

import java.io.Serializable;

public interface BaseEntity <ID extends Serializable>{
    void setId(ID id);
    ID getId();
}
