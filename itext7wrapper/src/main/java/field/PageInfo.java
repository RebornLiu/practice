package field;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * 对应一页模板
 * */
@Getter
@Setter
@ToString
public class PageInfo {
    /*页码*/
    private int pageNumber;

    /*模板域填充信息*/
    List<FieldInfo> fieldInfos;
}
