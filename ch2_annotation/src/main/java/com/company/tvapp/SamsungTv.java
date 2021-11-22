package com.company.tvapp;

import org.springframework.stereotype.Component;
//@Component samsungTv 객체 생성

@Component("samsung") // samsung 객체 생성
public class SamsungTv implements TV{
	
	public SamsungTv() {
		System.out.println("SamsungTv 객체 생성");
	}
	
	
	@Override
	public void turnOn() {
		System.out.println("SamsungTv - 파워 On");
		
	}
	@Override
	public void turnOff() {
		System.out.println("SamsungTv - 파워 Off");
		
	}
	@Override
	public void soundUp() {
		System.out.println("SamsungTv - 볼륨 Up");
		
	}
	@Override
	public void soundDown() {
		System.out.println("SamsungTv - 볼륨 Down");
		
	}
}
