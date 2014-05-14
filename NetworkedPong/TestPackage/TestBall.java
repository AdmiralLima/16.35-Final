package TestPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import GameObjects.Ball;
import GameObjects.Paddle;

public class TestBall {

	@Test
	public void testBallConstructor() {
		Ball b;
		//inbounds x and y, theta
		try{
			b = new Ball(250,250,1);
			assertArrayEquals(new int[]{250,250},b.getPosition());
			assertEquals(1,b.getOrientation(),1e-4);
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
		//out of bounds (lower) x and in bounds y, theta
		try{
			b = new Ball(99,250,1);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
			//pass
		}
		
		//out of bounds (upper) x and in bounds y, theta
		try{
			b = new Ball(501,250,1);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
			//pass
		}
		
		//out of bounds (lower) y and in bounds x, theta
		try{
			b = new Ball(250,99,1);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
			//pass
		}

		//out of bounds (upper) y and in bounds x, theta
		try{
			b = new Ball(250,501,1);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
			//pass
		}
		
		//out of bounds (lower) theta and in bounds x, y
		try{
			b = new Ball(250,250,-1);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
			//pass
		}
		
		//out of bounds (upper) theta and in bounds x, y
		try{
			b = new Ball(250,250, 7);
			fail("Failed requirement: ");
		}
		catch(IllegalArgumentException e){
			//pass
		}
		
		//upper bound theta and in bounds x, y
		try{
			b = new Ball(250,250, 2*Math.PI);
			assertEquals(0,b.getOrientation(),1e-4);
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
	}

	@Test
	public void testAdvance() {
		fail("Not yet implemented");
	}

	@Test
	public void testCollision() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPosition() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOrientation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testWrapTheta() {
		Ball b = new Ball(250,250,0);
		b.setPosition(250,250,-1);
		b.wrapTheta();
		
	}

}
