package com.example.administrator.mygankio;

/**
 * Created by tdfz on 2017/10/17.
 */

public class MyException extends Exception {
    private Type type;

    public MyException( Type type )
    {
        super();
        this.type = type;
    }

    public MyException( Throwable t, Type type )
    {
        super( t );
        this.type = type;
    }

    public String toString() {
        return super.toString() + "<" + getErrorType().getErrorCode() + ">";
    }

    public Type getErrorType()
    {
        return type;
    }

    public enum Type
    {
        //
        SYSTEM_ERROR( "99999" ),

        // 用户认证错误
        USER_AUTH( "03003" );

        private String errorCode;

        Type( String errorCode )
        {
            this.errorCode = errorCode;
        }

        public String getErrorCode()
        {
            return this.errorCode;
        }
    }
}
