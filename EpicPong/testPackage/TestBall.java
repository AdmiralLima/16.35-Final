package EpicPong.testPackage;

import static org.junit.Assert.*;

import org.junit.Test;

import EpicPong.GameObjects.Ball;

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
		
		//upper bound theta and in bounds x, y
		try{
			b = new Ball(250,250, 0);
			assertEquals(0,b.getOrientation(),1e-4);
		}
		catch(IllegalArgumentException e){
			fail("Failed requirement: ");
		}
		
	}

	@Test
	public void testAdvance() {
		//normal values
		
		//x direction only
		Ball b = new Ball(250,250,0);
		b.advance(100);
		assertArrayEquals(new int[]{310,250},b.getPosition());
		
		//y direction only
		b.setPosition(250,250,Math.PI/2);
		b.advance(100);
		assertArrayEquals(new int[]{250,310},b.getPosition());
		
		//negative parameter
		try{
			b.advance(-1);
			fail("Failed Trace: ");
		}
		catch(IllegalArgumentException e){}
		
		//parameter = 0;
		b.setPosition(250, 250, 0);
		b.advance(0);
		assertArrayEquals(new int[]{250,250},b.getPosition());
	}

	@Test
	public void testCollision() {
		//not implemented
	}

	@Test
	public void testGetPosition() {
		//assumed correct because it returns internal variables and works based off results
		//of other tests
	}

	@Test
	public void testGetOrientation() {
		//assumed correct because it returns internal variables and works based off results
		//of other tests
	}

	@Test
	public void testGetSize() {
		//assumed correct because it returns internal variables and works based off results
		//of other tests
	}

	@Test
	public void testWrapTheta() {
		
		//theta < 0
		assertEquals(Math.PI/2,Ball.wrapTheta(-3*Math.PI/2.0),1e-4);
		
		//theta > 2*pi
		assertEquals(Math.PI/2,Ball.wrapTheta(13*Math.PI/2.0),1e-4);
		
		//0<=theta<=2*pi
		assertEquals(Math.PI/2,Ball.wrapTheta(Math.PI/2.0),1e-4);
		assertEquals(0,Ball.wrapTheta(0),1e-4);
		assertEquals(2*Math.PI,Ball.wrapTheta(2*Math.PI),1e-4);
		
	}

}
