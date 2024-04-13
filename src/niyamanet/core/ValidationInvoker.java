package niyamanet.core;

import java.util.Map;
import java.util.Set;

public class ValidationInvoker {
    public Map<String, Set<String>> executeCommand(Validator validator) {
        return validator.analyseRecord();
    }
}
