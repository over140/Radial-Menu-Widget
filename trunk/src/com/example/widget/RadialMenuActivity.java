package com.example.widget;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.widget.RadialMenuWidget.RadialMenuEntry;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.Toast;

public class RadialMenuActivity extends Activity {

	private RadialMenuWidget PieMenu;
	private LinearLayout ll;
	//private TextView tv;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

      //------------------------------------------------------------------------------------------
        // Generating Pie view
        //------------------------------------------------------------------------------------------
		setContentView(R.layout.main);
	
		
/*		There are 3 ways to add the view to also make it removeable.
 * 		1.)  Dialog box.  Use the .show() and .dismiss() commands
 * 		2.)  Just add to an existing layer using the addView() and removeView() commands
 * 		3.)  Add new layout using the addContentView; then use the addView() and removeView()
 */

		ll = new LinearLayout(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( 
	             LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT); 
		addContentView(ll, params);

		
/*		final LinearLayout ll = new LinearLayout(this);
		LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);
		final TextView tv = new TextView(this);
		tv.setText("Hello");
		tv.setTextColor(Color.WHITE); 
		ll.addView(tv);
		addContentView(ll, p);*/


		Button testButton = (Button) this.findViewById(R.id.button1);
		testButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Toast.makeText(RadialMenuActivity.this, "Button Clicked!",
						Toast.LENGTH_SHORT).show();
				/*
				 * Dialog dialog = new Dialog(RadialMenuActivity.this);
				 * dialog.setContentView(new
				 * RadialMenuWidget(RadialMenuActivity.this)); dialog.show();
				 */
				// Dialog dialog = new Dialog(RadialMenuActivity.this);
				// dialog.setContentView(new
				// RadialMenuWidget(RadialMenuActivity.this));
				// dialog.setCanceledOnTouchOutside(true);
				// dialog.show();

				PieMenu = new RadialMenuWidget(getBaseContext());

				PieMenu.setAnimationSpeed(0L);
				//PieMenu.setSourceLocation(100,100);
				//PieMenu.setCenterLocation(240,400);
				//PieMenu.setInnerRingRadius(50, 120);
				//PieMenu.setInnerRingColor(Color.LTGRAY, 255);
				//PieMenu.setHeader("Menu Header", 20);

				int xLayoutSize = ll.getWidth();
				int yLayoutSize = ll.getHeight();				
				PieMenu.setSourceLocation(xLayoutSize,yLayoutSize);
				PieMenu.setIconSize(15, 30);
				PieMenu.setTextSize(13);				
				
				PieMenu.setCenterCircle(new Close());
				PieMenu.addMenuEntry(new Menu1());
				PieMenu.addMenuEntry(new NewTestMenu());
				PieMenu.addMenuEntry(new CircleOptions());
				PieMenu.addMenuEntry(new Menu2());
				PieMenu.addMenuEntry(new Menu3());

				
/*				try {
					Class<drawable> res = R.drawable.class;
					Field field = res.getField("icon");
					int drawableId = field.getInt(null);
				} catch (Exception e) {
					Log.e("MyTag", "Failure to get drawable id.", e);
				}*/
				//tv = new TextView(getBaseContext());
				//tv.setText("Hello");
				//tv.setTextColor(Color.WHITE); 
				//ll.addView(tv);
				
				ll.addView(PieMenu);

				
				// PieMenuImpl pieMenu = new PieMenuImpl();

				// pieMenu.addMenuEntry( new FileMenu() );

				// pieMenu.show();

			}
		
			});
 

		}


		public boolean onTouchEvent(MotionEvent e) {
			int state = e.getAction();
			int eventX = (int) e.getX();
			int eventY = (int) e.getY();
			if (state == MotionEvent.ACTION_DOWN) {


				System.out.println( "Button Pressed");
				Toast.makeText(RadialMenuActivity.this, "Screen Touched!",
						Toast.LENGTH_SHORT).show();

				//Screen Sizes
				int xScreenSize = (getResources().getDisplayMetrics().widthPixels);
				int yScreenSize = (getResources().getDisplayMetrics().heightPixels);
				int xLayoutSize = ll.getWidth();
				int yLayoutSize = ll.getHeight();
				int xCenter = xScreenSize/2;
				int xSource = eventX;
				int yCenter = yScreenSize/2;
				int ySource = eventY;
				
				if (xScreenSize != xLayoutSize) {
					xCenter = xLayoutSize/2;
					xSource = eventX-(xScreenSize-xLayoutSize);
				}
				if (yScreenSize != yLayoutSize) {
					yCenter = yLayoutSize/2;
					ySource = eventY-(yScreenSize-yLayoutSize);
				}				
				
				PieMenu = new RadialMenuWidget(getBaseContext());

				PieMenu.setSourceLocation(xSource,ySource);
				PieMenu.setShowSourceLocation(true);
				PieMenu.setCenterLocation(xCenter,yCenter);

				PieMenu.setHeader("X:"+xSource+" Y:"+ySource, 20);
				
				PieMenu.setCenterCircle(new Close());
				PieMenu.addMenuEntry(new CircleOptions());
				PieMenu.addMenuEntry(new Menu1());
				PieMenu.addMenuEntry(new Menu2());
				PieMenu.addMenuEntry(new Menu3());

				
				
				
				
				ll.addView(PieMenu);
			}
			return true;
		}

		
		
	
	   public class Close implements RadialMenuEntry
	   {

		  public String getName() { return "Close"; } 
		  public String getLabel() { return null; } 
	      public int getIcon() { return android.R.drawable.ic_menu_close_clear_cancel; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	  
	    	  System.out.println( "Close Menu Activated");
	    	  //Need to figure out how to to the layout.removeView(PieMenu)
	    	  //ll.removeView(PieMenu);
	    	  ((LinearLayout)PieMenu.getParent()).removeView(PieMenu); 

	    	  
	      }
	   }	
	
	   
	   public static class Menu1 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu1 - No Children"; } 
		  public String getLabel() { return "Menu1\nTest"; } 
		  public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	    	  System.out.println( "Menu #1 Activated - No Children");
	      }
	   }	
	   

	   public static class Menu2 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu2 - Children"; }
		  public String getLabel() { return "Menu2"; }
	      public int getIcon() { return R.drawable.icon; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new StringOnly(), new IconOnly(), new StringAndIcon() ) );
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Menu #2 Activated - Children");
	      }
	   }	
	   
	   
	   
	   public static class Menu3 implements RadialMenuEntry
	   {
	      public String getName() { return "Menu3 - No Children"; }
		  public String getLabel() { return null; } 
	      public int getIcon() { return R.drawable.icon; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Menu #3 Activated - No Children");
	      }
	   }

   


	   
	   
	   public static class IconOnly implements RadialMenuEntry
	   {
	      public String getName() { return "IconOnly"; }
		  public String getLabel() { return null; } 
	      public int getIcon() { return R.drawable.icon; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "IconOnly Menu Activated");
	      }
	   }
	   
	   
	   public static class StringAndIcon implements RadialMenuEntry
	   {
	      public String getName() { return "StringAndIcon"; }
		  public String getLabel() { return "String"; } 
	      public int getIcon() { return R.drawable.icon; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "StringAndIcon Menu Activated");
	      }
	   }
	   
	   public static class StringOnly implements RadialMenuEntry
	   {
	      public String getName() { return "StringOnly"; } 
		  public String getLabel() { return "String\nOnly"; }
	      public int getIcon() { return 0; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "StringOnly Menu Activated");
	      }
	   }

	   public static class NewTestMenu implements RadialMenuEntry
	   {
	      public String getName() { return "NewTestMenu"; } 
		  public String getLabel() { return "New\nTest\nMenu"; }
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new StringOnly(), new IconOnly() ) );
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "New Test Menu Activated");
	      }
	   }


	   
	   
	   
	   public static class CircleOptions implements RadialMenuEntry
	   {
	      public String getName() { return "CircleOptions"; } 
		  public String getLabel() { return "Circle\nSymbols"; }
	      public int getIcon() { return 0; }
	      private List<RadialMenuEntry> children = new ArrayList<RadialMenuEntry>( Arrays.asList( new RedCircle(), new YellowCircle(), new GreenCircle(), new BlueCircle() ) );
	      public List<RadialMenuEntry> getChildren() { return children; }
	      public void menuActiviated()
	      {
	         System.out.println( "Circle Options Activated");
	      }
	   }	
	   public static class RedCircle implements RadialMenuEntry
	   {
	      public String getName() { return "RedCircle"; } 
		  public String getLabel() { return "Red"; }
	      public int getIcon() { return R.drawable.red_circle; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Red Circle Activated");
	      }
	   }	   
	   public static class YellowCircle implements RadialMenuEntry
	   {
	      public String getName() { return "YellowCircle"; } 
		  public String getLabel() { return "Yellow"; }
	      public int getIcon() { return R.drawable.yellow_circle; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Yellow Circle Activated");
	      }
	   }
	   public static class GreenCircle implements RadialMenuEntry
	   {
	      public String getName() { return "GreenCircle"; } 
		  public String getLabel() { return "Green"; }
	      public int getIcon() { return R.drawable.green_circle; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Green Circle Activated");
	      }
	   }
	   public static class BlueCircle implements RadialMenuEntry
	   {
	      public String getName() { return "BlueCircle"; } 
		  public String getLabel() { return "Blue"; }
	      public int getIcon() { return R.drawable.blue_circle; }
	      public List<RadialMenuEntry> getChildren() { return null; }
	      public void menuActiviated()
	      {
	         System.out.println( "Blue Circle Activated");
	      }
	   }
	   
	   
	   
	   
	   
	   
}

