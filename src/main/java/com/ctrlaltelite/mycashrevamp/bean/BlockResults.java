package com.ctrlaltelite.mycashrevamp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlockResults {
    private String blockHash;
    private String previousHash;
}
