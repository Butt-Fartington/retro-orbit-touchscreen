package com.retro.launcher;
import android.graphics.*;
import android.graphics.drawable.Drawable;
/** Lightweight glass plaque shared by native footer controls and canvas headings. */
final class GlassOval extends Drawable {
 final Paint paint=new Paint(3);final float density;boolean lit;
 GlassOval(float density){this.density=density;}
 protected void onBoundsChange(Rect bounds){invalidateSelf();}
 public boolean isStateful(){return true;}
 protected boolean onStateChange(int[] states){boolean next=false;for(int state:states)if(state==android.R.attr.state_pressed||state==android.R.attr.state_focused)next=true;if(next==lit)return false;lit=next;invalidateSelf();return true;}
 public void draw(Canvas c){Rect b=getBounds();draw(c,new RectF(b.left+2*density,b.top+10*density,b.right-2*density,b.bottom-10*density),paint,lit,density);}
 static void draw(Canvas c,RectF r,Paint p,boolean lit,float unit){
 p.setStyle(Paint.Style.FILL);p.setColor(Color.WHITE);p.setShader(new LinearGradient(0,r.top,0,r.bottom,new int[]{lit?0xff315970:0xff284357,0xff0a2134,0xff041323,lit?0xff214b66:0xff142f43},new float[]{0,.28f,.65f,1},Shader.TileMode.CLAMP));c.drawOval(r,p);p.setShader(null);
 p.setStyle(Paint.Style.STROKE);p.setStrokeWidth(.65f*unit);p.setColor(lit?0xff5ce4ff:0xffb8d6e5);c.drawOval(r,p);
 RectF inner=new RectF(r);inner.inset(1.1f*unit,1.1f*unit);p.setColor(0x774b7a97);p.setStrokeWidth(.6f*unit);c.drawOval(inner,p);
 p.setShader(new LinearGradient(r.left,r.top,r.right,r.top,new int[]{0x005c8bab,0xcce7faff,0x005c8bab},new float[]{0,.5f,1},Shader.TileMode.CLAMP));p.setStrokeWidth(1.1f*unit);c.drawArc(inner,213,114,false,p);p.setShader(null);
 p.setStyle(Paint.Style.FILL);p.setShader(new RadialGradient(r.centerX(),r.top+1.5f*unit,8*unit,new int[]{0xaadef8ff,0x00a6eaff},null,Shader.TileMode.CLAMP));c.drawOval(r.centerX()-9*unit,r.top-1*unit,r.centerX()+9*unit,r.top+4*unit,p);p.setShader(null);p.setColor(Color.WHITE);
 }
 public void setAlpha(int a){}public void setColorFilter(ColorFilter f){}public int getOpacity(){return PixelFormat.TRANSLUCENT;}
}
