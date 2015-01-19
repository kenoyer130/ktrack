package com.jkenoyer.ktrack.commands;

import java.util.ArrayList;
import java.util.List;

/***
 * Captures validation results. By default success is true. If a result is added success is set to
 * false.
 */
public class ValidationResult {

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean success;

    private List<String> results;

    public ValidationResult() {
        results = new ArrayList<String>();
        success = true;
    }

    public void addResult(String result) {
        success  = false;
        results.add(result);
    }

    public List<String> getResults() {
        return results;
    }
}
