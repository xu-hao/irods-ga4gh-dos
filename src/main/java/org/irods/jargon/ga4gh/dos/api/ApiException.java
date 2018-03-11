package org.irods.jargon.ga4gh.dos.api;

@javax.annotation.Generated(value = "org.irods.jargon.ga4gh.dos.codegen.languages.SpringCodegen", date = "2018-02-03T00:47:18.655Z")

public class ApiException extends Exception{
    private int code;
    public ApiException (int code, String msg) {
        super(msg);
        this.code = code;
    }
}
