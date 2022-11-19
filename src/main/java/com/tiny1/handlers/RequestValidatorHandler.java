package com.tiny1.handlers;

import com.tiny1.exception.BadRequestException;
import com.tiny1.model.Handler;
import com.tiny1.model.Request;
import com.tiny1.util.HttpResponseUtils;

import java.io.IOException;
import java.util.regex.Pattern;

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
    public boolean handleImpl(String request, Request requestObject) throws IOException {
//        if (!request.matches("[A-Z]{3,7} [/a-zA-Z0-9\\-@:%._+~#=]+ HTTP/\\d.\\d\r\n[\\s\\S]*"))
        Pattern pat = Pattern.compile("^[A-Z]{3,7} \\S+ HTTP/\\d.\\d\r\n");
        if (!pat.matcher(request).find()) {
            HttpResponseUtils.sendBadRequest(requestObject.getOutput());
            return false;
        }
        return true;
    }

}
