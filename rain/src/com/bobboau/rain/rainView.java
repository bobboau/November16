package com.bobboau.rain;

import java.util.Vector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class rainView extends View {

	Vector<Drop> drops;
	Vector<Drop> kill_list;

	public rainView(Context context) {
		super(context);
		drops = new Vector<Drop>();
		kill_list = new Vector<Drop>();//remove an item within the loop traversing it? You didn't think it was gonna be that easy, did you?
	}
	
	public rainView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		drops = new Vector<Drop>();
		kill_list = new Vector<Drop>();//remove an item within the loop traversing it? You didn't think it was gonna be that easy, did you?
	} 
	
	@Override
	protected void onDraw(Canvas canvas) {
		Paint p = new Paint();
		p.setStyle(Style.STROKE);
				
        for(Drop drop : drops){
        	if(drop.isAlive())
        	{
				Log.d("drawing", "X: "+drop.getPosition().x+", Y:"+drop.getPosition().y+", rad:"+drop.getRadius());
        		p.setColor(drop.getColor());
        		canvas.drawCircle(drop.getPosition().x, drop.getPosition().y, drop.getRadius(), p);
        	}
        	else
        	{
        		//the drop is dead so remove it from the list
        		kill_list.add(drop);
        	}
        }
        
        for(Drop drop : kill_list){
        	drops.remove(drop);
        }
        kill_list.clear();
        
        //we still have drops keep drawing
        if(!drops.isEmpty())
        {
        	invalidate();
        }
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
        switch (event.getAction()) {
        	case MotionEvent.ACTION_DOWN:
        		drops.add(
        				new Drop(
        					new PointF(event.getX(), event.getY()),
							Integer.valueOf(super.getContext().getString(R.string.max_life)),
							Float.valueOf(super.getContext().getString(R.string.max_size)),
							(float) Math.random()
						)
        		);
        		invalidate();
        	break;
        }

        return true;
	} 
}
