package com.retro.launcher;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
public class Alerts extends NotificationListenerService {
 public static volatile Alerts current;
 static long lastRebind;
 static boolean permitted(android.content.Context c){android.content.ComponentName name=new android.content.ComponentName(c,Alerts.class);if(android.os.Build.VERSION.SDK_INT>=27)return c.getSystemService(android.app.NotificationManager.class).isNotificationListenerAccessGranted(name);String enabled=android.provider.Settings.Secure.getString(c.getContentResolver(),"enabled_notification_listeners");return enabled!=null&&java.util.Arrays.asList(enabled.split(":")).contains(name.flattenToString());}
 static void reconnect(android.content.Context c){if(current!=null||!permitted(c))return;long now=android.os.SystemClock.elapsedRealtime();if(lastRebind!=0&&now-lastRebind<10000)return;lastRebind=now;try{requestRebind(new android.content.ComponentName(c,Alerts.class));android.util.Log.i("OrbitAlerts","Requested listener reconnect");}catch(RuntimeException e){android.util.Log.w("OrbitAlerts","Listener reconnect failed",e);}}
 static String connectionLabel(android.content.Context c){return permitted(c)?"Connecting…":"Access disabled";}
 static volatile StatusBarNotification[] snapshot=new StatusBarNotification[0];
 final java.util.concurrent.ExecutorService reader=java.util.concurrent.Executors.newSingleThreadExecutor();
 void refresh(){if(reader.isShutdown())return;try{reader.execute(()->{try{StatusBarNotification[] result=getActiveNotifications();snapshot=result==null?new StatusBarNotification[0]:result;update();}catch(RuntimeException e){android.util.Log.w("OrbitAlerts","Read failed",e);}});}catch(java.util.concurrent.RejectedExecutionException ignored){}}
 static final java.util.concurrent.ConcurrentHashMap<String,String> labels=new java.util.concurrent.ConcurrentHashMap<>();
 void update(){MainActivity a=MainActivity.foreground;if(a!=null)a.runOnUiThread(()->a.view.invalidate());}
 public void onNotificationPosted(StatusBarNotification n){refresh();}
 public void onNotificationRemoved(StatusBarNotification n){refresh();}
 public void onListenerConnected(){current=this;lastRebind=0;android.util.Log.i("OrbitAlerts","Listener connected");refresh();}
 public void onListenerDisconnected(){if(current==this)current=null;update();reconnect(this);}
 public void onDestroy(){if(current==this)current=null;reader.shutdownNow();super.onDestroy();}
 public static java.util.List<StatusBarNotification> members(String pkg){java.util.ArrayList<StatusBarNotification> out=new java.util.ArrayList<>();for(StatusBarNotification n:items())if(n.getPackageName().equals(pkg)&&(n.getNotification().flags&android.app.Notification.FLAG_GROUP_SUMMARY)==0)out.add(n);out.sort((a,b)->Long.compare(b.getPostTime(),a.getPostTime()));return out;}
 public static StatusBarNotification[] groups(){java.util.ArrayList<StatusBarNotification> all=new java.util.ArrayList<>(java.util.Arrays.asList(items()));all.sort((a,b)->Long.compare(b.getPostTime(),a.getPostTime()));java.util.LinkedHashMap<String,StatusBarNotification> out=new java.util.LinkedHashMap<>();for(StatusBarNotification n:all)if((n.getNotification().flags&android.app.Notification.FLAG_GROUP_SUMMARY)==0&&!out.containsKey(n.getPackageName()))out.put(n.getPackageName(),n);return out.values().toArray(new StatusBarNotification[0]);}
 public static String label(android.content.Context c,String pkg){try{String cached=labels.get(pkg);if(cached!=null)return cached;String label=c.getPackageManager().getApplicationLabel(c.getPackageManager().getApplicationInfo(pkg,0)).toString();labels.put(pkg,label);return label;}catch(Exception e){return pkg;}}
 public static void dismissGroup(String pkg){if(current!=null)for(StatusBarNotification n:members(pkg))if(n.isClearable())current.cancelNotification(n.getKey());}
 public static StatusBarNotification[] items(){return snapshot;}
}
