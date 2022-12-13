package com.abnamro.reciepe.Model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@MappedSuperclass
public abstract class BaseModel implements Serializable {
    private Long id;

}
