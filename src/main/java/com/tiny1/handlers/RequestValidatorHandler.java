package com.tiny1.handlers;

import com.tiny1.exception.BadRequestException;
import com.tiny1.model.Handler;
import com.tiny1.model.Request;

public class RequestValidatorHandler extends Handler {

    public RequestValidatorHandler(Handler next) {
        super(next);
    }

    /**
     * Checks if
     * Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
     * @param request The request String
     * @throws BadRequestException The request is invalid
     */
    @Override
    public void handleImpl(String request, Request requestObject) throws BadRequestException {
        if (!request.matches("[A-Z]{3,7} [/a-zA-Z0-9\\-@:%._+~#=]+ HTTP/\\d.\\d\r\n[\\s\\S]*"))
            throw new BadRequestException();
    }

}
