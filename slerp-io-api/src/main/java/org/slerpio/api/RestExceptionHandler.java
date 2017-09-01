package org.slerpio.api;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.slerp.core.CoreException;
import org.slerp.core.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

@RestControllerAdvice
public class RestExceptionHandler {
	@Autowired
	MessageSource messageSource;
	// @Autowired
	// @Qualifier("removeUser")
	// BusinessTransaction removeUser;

	@ExceptionHandler(HttpServerErrorException.class)
	public ResponseEntity<ErrorItem> handle(HttpServerErrorException e) {
		// System.out.println(e.getResponseBodyAsString());
		ErrorItem item = new ErrorItem();
		String errResult = e.getResponseBodyAsString();
		System.out.println(errResult);
		if (!errResult.isEmpty()) {
			Domain errorDomain = new Domain(errResult);
			// System.out.println(errorDomain);
			item.setStatus(1);
			String message = messageSource.getMessage(errorDomain.getString("message"), null,
					LocaleContextHolder.getLocale());
			if (message != null) {
				item.setBody(message);
				if (message.equals("data.has.been.exist")) {

				}
			} else
				item.setBody(errResult);
		}
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<ErrorItem> handle(ResourceAccessException e) {
		// System.out.println(e.getResponseBodyAsString());
		ErrorItem item = new ErrorItem();
		String errResult = "server.fault";
		System.out.println(errResult);
		if (!errResult.isEmpty()) {
			// Domain errorDomain = new Domain(errResult);
			// System.out.println(errorDomain);
			item.setStatus(1);
			String message = messageSource.getMessage(errResult, null, LocaleContextHolder.getLocale());
			if (message != null) {
				item.setBody(message);
			} else
				item.setBody(errResult);
		}
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	// private void removeUser(Domain userDomain) {
	// Domain removeDomain = removeUser.handle(userDomain);
	// if (!removeDomain.getBoolean("success")) {
	// log.info("The user registration failed shold be handle manualy {}",
	// removeDomain);
	// }
	// }
	@SuppressWarnings("rawtypes")
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> handle(ConstraintViolationException e) {
		ErrorResponse errors = new ErrorResponse();
		for (ConstraintViolation violation : e.getConstraintViolations()) {
			ErrorItem error = new ErrorItem();
			error.setStatus(2);
			error.setBody(violation.getMessage());
			errors.addError(error);
		}
		return new ResponseEntity<>(errors, HttpStatus.OK);
	}

	@ExceptionHandler(CoreException.class)
	public ResponseEntity<ErrorItem> coreHandle(CoreException e) {
		e.printStackTrace();
		ErrorItem item = new ErrorItem();
		item.setStatus(1);
		String body = messageSource.getMessage(e.getMessage(), null, LocaleContextHolder.getLocale());
		item.setStatus(1);
		if (body != null) {

			item.setBody(body);
		} else {
			item.setBody(e.getMessage());
		}
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorItem> coreHandle(HttpMessageNotReadableException e) {
		ErrorItem item = new ErrorItem(2,
				messageSource.getMessage("failed.to.read.json.data", null, LocaleContextHolder.getLocale()));
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErrorItem> httpMethod(HttpRequestMethodNotSupportedException e) {
		ErrorItem item = new ErrorItem(2, "should.be.use.method." + e.getSupportedMethods()[0] + ".for.this.operation");
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<ErrorItem> unsupportMediaType(HttpMediaTypeNotSupportedException e) {
		ErrorItem item = new ErrorItem(2, "failed.to.handle.media.type");
		return new ResponseEntity<>(item, HttpStatus.OK);
	}

	public static class ErrorItem {
		private int status;
		private String body;

		public ErrorItem() {
		}

		public ErrorItem(int status, String body) {
			super();
			this.status = status;
			this.body = body;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

	}

	@XmlRootElement(name = "errors")
	public static class ErrorResponse {
		private List<ErrorItem> errors = new ArrayList<>();

		@XmlElement(name = "error")
		public List<ErrorItem> getErrors() {
			return errors;
		}

		public void setErrors(List<ErrorItem> errors) {
			this.errors = errors;
		}

		public void addError(ErrorItem error) {
			this.errors.add(error);
		}
	}
}