package com.reborn.parse.source;

import com.reborn.parse.annotation.DDD;
import com.reborn.parse.entity.ParameterEntity;
import com.reborn.parse.entity.ReturnEntity;

public interface Api {

    @DDD(value = "echo_method",
         restName = "echo_rest")
    ReturnEntity echo(ParameterEntity parameterEntity);
}
