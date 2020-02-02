package com.thomasvitale.application.multitenancy;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Provides Hibernate with a strategy to resolve the tenant schema.
 */
@Component
public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver {

    @Value("${multitenant.default.schema}")
    private String defaultTenantSchema;

    @Override
    public String resolveCurrentTenantIdentifier() {
        return Objects.requireNonNullElse(TenantContext.getCurrentTenant(), defaultTenantSchema);
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
