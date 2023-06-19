package com.company.userandadminauthorities.util;

import com.company.userandadminauthorities.dto.ResponseDto;

public abstract class CRUD<T, ID> {

    public abstract ResponseDto<T> create(T dto);

    public abstract ResponseDto<T> get(ID id);

    public abstract ResponseDto<T> update(T dto, ID id);

    public abstract ResponseDto<T> delete(ID id);

}
