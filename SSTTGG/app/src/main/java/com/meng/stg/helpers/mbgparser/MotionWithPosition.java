package com.meng.stg.helpers.mbgparser;

import java.io.Serializable;

public class MotionWithPosition<T, U> implements Serializable {
	private static final long serialVersionUID = 1621713239065088260L;
	public Motion<T> motion;
	public Position<U>
		speedDirectionPosition,
		accelerationDirectionPosition;
}
