package com.lpras.schedulingtasks;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer implements Serializable{
	private static final long serialVersionUID = -2025107613919203913L;
	private int id;
	private String name;
	private String email;

}
