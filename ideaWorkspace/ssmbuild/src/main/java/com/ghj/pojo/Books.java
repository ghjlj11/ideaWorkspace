package com.ghj.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

/**
 * @author 86187
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Alias("books")
public class Books {
    private int bookID;
    private String bookName;
    private int bookCounts;
    private String detail;
}
