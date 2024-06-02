package com.kimseungjin.block_file_extensions.support.web;

import com.kimseungjin.block_file_extensions.global.web.UserIdArgumentResolver;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Primary;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

@Primary
@TestComponent
public class UserIdArgumentResolverImpl extends UserIdArgumentResolver {

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return super.supportsParameter(parameter);
    }

    @Override
    public Object resolveArgument(
            final MethodParameter parameter,
            final ModelAndViewContainer mavContainer,
            final NativeWebRequest webRequest,
            final WebDataBinderFactory binderFactory)
            throws Exception {
        return 1L;
    }
}
