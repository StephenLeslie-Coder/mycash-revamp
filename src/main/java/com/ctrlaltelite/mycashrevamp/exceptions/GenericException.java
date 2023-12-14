package com.ctrlaltelite.mycashrevamp.exceptions;

import lombok.Getter;

public class GenericException extends Exception{
        private static final long serialVersionUID = 1L;
        private int statusCode;

        public GenericException(String message) {
            super(message);
        }
        public GenericException(String message,int statusCode) {
            super(message);
            this.statusCode = statusCode;
        }

        public void setStatusCode(int statusCode) {
            this.statusCode = statusCode;
        }

    public int getStatusCode() {
        return statusCode;
    }
}

