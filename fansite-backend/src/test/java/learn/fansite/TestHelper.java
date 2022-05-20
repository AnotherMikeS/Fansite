package learn.fansite;

import learn.fansite.domain.Result;
import learn.fansite.domain.ResultType;

public class TestHelper {

    public static Result makeResult(String... messages) {
        Result result = new Result();
        for (String message : messages) {
            result.addMessage(message, ResultType.INVALID);
        }
        return result;
    }
}
