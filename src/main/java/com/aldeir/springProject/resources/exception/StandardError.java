package com.aldeir.springProject.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
   //gera o erro  
	private static final long serialVersionUID = 1L;
	
private Integer  status;//status do erro
 private String msg;//msg do erro
 private Long timeStamp; //e quando aconteceu o erro
 
public StandardError(Integer status, String msg, Long timeStamp) {
	super();
	this.status = status;
	this.msg = msg;
	this.timeStamp = timeStamp;
}

public Integer getStatus() {
	return status;
}

public void setStatus(Integer status) {
	this.status = status;
}

public String getMsg() {
	return msg;
}

public void setMsg(String msg) {
	this.msg = msg;
}

public Long getTimeStamp() {
	return timeStamp;
}

public void setTimeStamp(Long timeStamp) {
	this.timeStamp = timeStamp;
}
 
 
}
