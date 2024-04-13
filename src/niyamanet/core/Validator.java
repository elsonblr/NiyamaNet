package niyamanet.core;

import java.util.Map;
import java.util.Set;

public interface Validator {
    //public Map<String, String> analyseRecord(String record);
    public Map<String, Set<String>> analyseRecord();
}