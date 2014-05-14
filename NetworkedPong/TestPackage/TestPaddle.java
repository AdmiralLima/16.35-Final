package TestPackage;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

import GameObjects.Paddle;

public class TestPaddle {
	
	
	/**
	 * Traces to Requirements:
	 * -has constructor that takes two integer parameters
	 * -takes input integer
	 */
	@Test
	public void testConstructorPaddle() {
		Paddle p;
		//inbounds x and lower y
		try{
			p = new Paddle(250,90);
			assertArrayEquals(new int[]{250,90},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
		//inbounds x and upper y
		try{
			p = new Paddle(250,500);
			assertArrayEquals(new int[]{250,500},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
		//max x
		try{
			p = new Paddle(150,90);
			assertArrayEquals(new int[]{150,90},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
		//min x
		try{
			p = new Paddle(350,90);
			assertArrayEquals(new int[]{350,90},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
		//out of bounds max x
		try{
			p = new Paddle(351,90);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
		}
		
		//out of bounds min x
		try{
			p = new Paddle(149,90);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
		}
		
		//out of bounds y
		try{
			p = new Paddle(250,250);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
		}
	}
	
	/*
	 * Position changes to the right by click
	 */
	@Test
	public void testMoveRight() {
		int x = 200; int y = 90;
		Paddle p = new Paddle(x,y);
		int vel = 50;
		
		// normal case
		p.moveRight();
		int[] expectedPos = new int[]{x+vel,y}; 
		assertArrayEquals(expectedPos,p.getPosition());
		
		//max bound
		p.setPosition(320, y);
		expectedPos[0] = 350;
		p.moveRight();
		assertArrayEquals(expectedPos,p.getPosition());
	}

	@Test
	public void testMoveLeft() {
		int x = 250; int y = 90;
		Paddle p = new Paddle(x,y);
		int vel = 50;
		
		// normal case
		p.moveRight();
		int[] expectedPos = new int[]{x-vel,y}; 
		assertArrayEquals(expectedPos,p.getPosition());
		
		//min bound
		p.setPosition(170, y);
		expectedPos[0] = 150;
		p.moveRight();
		assertArrayEquals(expectedPos,p.getPosition());
	}

	@Test
	public void testSetPosition() {
		Paddle p = new Paddle(250,90);
		
		//inbounds x and lower y
		try{
			p.setPosition(190,90);
			assertArrayEquals(new int[]{190,90},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail();
		}
		
		//inbounds x and upper y
		try{
			p.setPosition(250,500);
			assertArrayEquals(new int[]{250,500},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail();
		}
		
		//max x
		try{
			p.setPosition(150,90);
			assertArrayEquals(new int[]{150,90},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail();
		}
		
		//min x
		try{
			p.setPosition(350,90);
			assertArrayEquals(new int[]{350,90},p.getPosition());
		}
		catch(IllegalArgumentException e){
			fail();
		}
		
		//out of bounds max x
		try{
			p.setPosition(351,90);
			fail();
		}
		catch(IllegalArgumentException e){
		}
		
		//out of bounds min x
		try{
			p.setPosition(149,90);
			fail();
		}
		catch(IllegalArgumentException e){
		}
		
		//out of bounds y
		try{
			p.setPosition(250,250);
			fail();
		}
		catch(IllegalArgumentException e){
		}
	}

	@Test
	public void testGetPosition() {
		final Paddle p = new Paddle(245,90);
		assertArrayEquals(new int[]{245,90},p.getPosition());		
	}

	@Test
	public void testGetSize() {
		assertArrayEquals(new int[]{100,10},Paddle.getSize());
	}

}
