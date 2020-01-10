package parser.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Some Parameters are Invalid")  // 404
public class badRequestException extends RuntimeException {

}
