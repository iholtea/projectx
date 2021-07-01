package org.projectx.rest.jersey.resource.other;

import java.util.Date;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/demo")
public class DemoResource {
	
	@GET
	@Path("header_param")
	@Produces(MediaType.TEXT_PLAIN)
	public String getHeaderParams(@HeaderParam("headerParamA") String headerParamA,
			@HeaderParam("headerParamB") String headerParamB) {
		
		String str = "headerParamA: " + headerParamA +
				" headerParamB: " + headerParamB;
		return str;
	}
	
	@GET
	@Path("cookie_param")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCookieParams(@CookieParam("cookieParamA") String cookieParamA,
			@CookieParam("cookieParamB") String cookieParamB) {
		
		String str = "cookieParamA: " + cookieParamA +
				" cookieParamB: " + cookieParamB;
		return str;
	}
	
	@GET
	@Path("/context/uri_info")
	@Produces(MediaType.TEXT_PLAIN)
	public String getContextUri(@Context UriInfo uriInfo) {
		String path = uriInfo.getPath();
		String absPath = uriInfo.getAbsolutePath().toString();
		String baseUri = uriInfo.getBaseUri().toString();
		StringBuilder sb = new StringBuilder();
		sb.append("Path: ").append(path).append("\n");
		sb.append("AbsolutePath: ").append(absPath).append("\n");
		sb.append("Base URI: ").append(baseUri).append("\n");
		uriInfo.getQueryParameters().entrySet().forEach(entry -> {
			sb.append( entry.getKey() + ": " + entry.getValue().get(0) + "\n" );
		});
		return sb.toString();
	}
	
	@GET
	@Path("/context/headers")
	@Produces(MediaType.TEXT_PLAIN)
	public String getContextHeader(@Context HttpHeaders headers) {
		Date date = headers.getDate();
		String dateStr =  date != null ? date.toString() : "null";
		MediaType mediaType = headers.getMediaType();
		String mediaTypeStr = mediaType != null ? mediaType.getType() : "null";
		StringBuilder sb = new StringBuilder();
		sb.append("Date from Http Headers: ").append(dateStr).append("\n");
		sb.append("Media Type: ").append(mediaTypeStr).append("\n");
		headers.getRequestHeaders().entrySet().forEach(entry -> {
			sb.append( entry.getKey() + ": " + entry.getValue().get(0) + "\n" );
		});
		return sb.toString();
	}
}
