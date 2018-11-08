package com.cnpc.myweb.domain;

public class FusionCharts{
private String name;
private Double value;
private String color;
public FusionCharts() {
	 super();
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public Double getValue() {
	return value;
}
public void setValue(Double value) {
	this.value = value;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}

public FusionCharts(String label, Double value) {
	super();
	this.name = label;
	this.value = value;
}


public FusionCharts(String label, String color) {
	super();
	this.name = label;
	this.color = color;
}


public FusionCharts(String label, Double value, String color) {
	super();
	this.name = label;
	this.value = value;
	this.color = color;
}
@Override
public String toString() {
	return "Product [label=" + name + ", value=" + value + "]";
}
}
