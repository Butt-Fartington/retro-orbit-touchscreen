package com.retro.launcher;
import android.accessibilityservice.AccessibilityService;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.accessibility.AccessibilityEvent;
import java.lang.ref.WeakReference;
import java.util.HashMap;

public class KeyAccess extends AccessibilityService {
 public static KeyAccess current;
 final Handler actions=new Handler(Looper.getMainLooper());
 static class Press {final long downTime;final WeakReference<MainActivity> owner;Runnable longAction;boolean longFired;Press(KeyEvent event,MainActivity a){downTime=event.getDownTime();owner=new WeakReference<>(a);}}
 final HashMap<Integer,Press> held=new HashMap<>();
 protected void onServiceConnected(){super.onServiceConnected();current=this;}
 public void onDestroy(){held.clear();actions.removeCallbacksAndMessages(null);if(current==this)current=null;super.onDestroy();}
 public void onAccessibilityEvent(AccessibilityEvent event){}
 public void onInterrupt(){held.clear();}
 static boolean function(int key){return key==KeyEvent.KEYCODE_APP_SWITCH||key==KeyEvent.KEYCODE_F11||key==KeyEvent.KEYCODE_F12;}
 protected boolean onKeyEvent(KeyEvent event){int key=event.getKeyCode();if(!function(key))return false;
  Press press=held.get(key);
  if(press!=null&&press.downTime!=event.getDownTime()){held.remove(key);press=null;}
  if(event.getAction()==KeyEvent.ACTION_DOWN){if(press!=null)return true;MainActivity owner=MainActivity.foreground;if(owner==null||event.getRepeatCount()!=0)return false;Press captured=new Press(event,owner);held.put(key,captured);if(key==KeyEvent.KEYCODE_F12){captured.longAction=()->{if(held.get(key)!=captured||MainActivity.foreground!=owner)return;captured.longFired=true;PointerSupport.toggle(owner);};actions.postDelayed(captured.longAction,650);}return true;}
  if(event.getAction()==KeyEvent.ACTION_UP&&press!=null){held.remove(key);if(press.longAction!=null)actions.removeCallbacks(press.longAction);if(press.longFired)return true;final boolean longPress=key==KeyEvent.KEYCODE_F12&&event.getEventTime()-press.downTime>=650;final MainActivity owner=press.owner.get();if(!event.isCanceled()&&owner!=null)actions.post(()->{if(MainActivity.foreground!=owner||owner.isFinishing()||owner.isDestroyed())return;if(longPress){PointerSupport.toggle(owner);return;}if(owner.screen.equals("Keys")){owner.lastKey=KeyEvent.keyCodeToString(key)+" ("+key+")";owner.view.invalidate();}else if(key==KeyEvent.KEYCODE_APP_SWITCH)owner.leftSoft();else if(key==KeyEvent.KEYCODE_F11)owner.middleSoft();else owner.rightSoft();});return true;}
  return false;
 }
}
