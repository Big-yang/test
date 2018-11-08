package com.cnpc.myweb.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cnpc.myweb.domain.Well;

public class WellService {
	private Random rnd=new Random();
	public WellService(){
		
	}
	public List<Well> getWellList(){
		List<Well> result = new ArrayList<Well>();
	for(int i=0;i<5;i++){
		Well item=new Well();
		item.setName("»¨ÍÁ¹µ"+i+"¾®ºÅ");
		item.setId("10001"+i);
		item.setValue(100+rnd.nextInt(100));
		result.add(item);
	}
	return result;
	}
}

