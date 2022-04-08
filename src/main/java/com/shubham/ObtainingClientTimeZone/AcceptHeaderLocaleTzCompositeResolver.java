package com.shubham.ObtainingClientTimeZone;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.web.servlet.LocaleContextResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class AcceptHeaderLocaleTzCompositeResolver implements LocaleContextResolver {

    private final LocaleContextResolver localeContextResolver;
    private final AcceptHeaderLocaleResolver acceptHeaderLocaleContext;

    public AcceptHeaderLocaleTzCompositeResolver(LocaleContextResolver localeContextResolver) {
        this.localeContextResolver = localeContextResolver;
        acceptHeaderLocaleContext = new AcceptHeaderLocaleResolver();
        acceptHeaderLocaleContext.setDefaultLocale(Locale.getDefault());
    }

    @Override
    public LocaleContext resolveLocaleContext(HttpServletRequest request) {
        return localeContextResolver.resolveLocaleContext(request);
    }

    @Override
    public void setLocaleContext(HttpServletRequest request,
                                 HttpServletResponse response,
                                 LocaleContext localeContext) {
        localeContextResolver.setLocaleContext(request, response, localeContext);
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return acceptHeaderLocaleContext.resolveLocale(request);
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response,
                          Locale locale) {
        acceptHeaderLocaleContext.setLocale(request, response, locale);
    }
}
