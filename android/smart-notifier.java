
package fr.julienmarcou.smartnotifier;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.NotificationCompat;
import android.app.PendingIntent;
import android.app.NotificationManager;
import android.content.Context;

public class Notifier extends CordovaPlugin {

	private int currentNotificationId = 1;

	@override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		JSONObject result = new JSONObject();
		if(action.equals("notify")) {
			this.notify(args.getString(0), args.getString(1), args.getString(2));
			callbackContext.success(result);
			return true;
		}
		return false;
	}

	private void notify(String title, String message, String icon) {
		// Notification content
		NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
		builder.setTitle(title);
		builder.setContentText(message);
		builder.setSmallIcon(R.drawable.notification_icon);

		// Notification behavior
		builder.setContentIntent(PendingIntent.getActivity(
			this, 0, new Intent(this, ResultActivity.class), PendingIntent.FLAG_ONE_SHOT
		));

		// Notification issue
		NotificationManager notifier = (NotificationManager) Context.getSystemService(NOTIFICATION_SERVICE);
		notifier.notify(currentNotificationId, builder.build());
		currentNotificationId++;
	}

}
