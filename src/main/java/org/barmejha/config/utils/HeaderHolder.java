package org.barmejha.config.utils;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import org.barmejha.config.Constants;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.List;
import java.util.Map;

@RequestScoped
public class HeaderHolder {

  @Context
  private HttpHeaders httpHeaders;

  public HttpHeaders getRequestHeader() {
    return httpHeaders;
  }

  public String getHeader(String name) {
    return httpHeaders.getHeaderString(name);
  }

  public String getLang() {
    return httpHeaders.getHeaderString(Constants.X_LANG);
  }

  public String getMsisdn() {
    return httpHeaders.getHeaderString(Constants.X_MSISDN);
  }

  public String getUser() {
    return httpHeaders.getHeaderString(Constants.X_USER);
  }

  public String getVersionApp() {
    return httpHeaders.getHeaderString(Constants.X_VERSION);
  }

  public String getTenant() {
    return httpHeaders.getHeaderString(Constants.X_TENANT);
  }

  public int getRequestApiVersion() {
    String header = getHeader(Constants.X_API_VERSION);
    if (header != null && header.toLowerCase().startsWith("v")) {
      try {
        return Integer.parseInt(header.substring(1));
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Invalid version format: " + header, e);
      }
    }
    return ConfigProvider.getConfig().getOptionalValue("maxit.bff.v2.headers.api-version.default", Integer.class).orElse(1);
  }

  public Map<String, List<String>> getAllHeaders() {
    return httpHeaders.getRequestHeaders();
  }
}
