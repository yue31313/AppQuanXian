package com.phone.shadu;

import android.graphics.drawable.Drawable;

public class applicationInfo {
/**
 * 来保存应用程序的类
 */
public String appLabel;
public String appName;
public Drawable appIcon;
public String app;
public String packname;
public String getPackname() {
	return packname;
}
public void setPackname(String packname) {
	this.packname = packname;
}
public String getApp() {
	return app;
}
public void setApp(String app) {
	this.app = app;
}
public String getAppLabel() {
	return appLabel;
}
public void setAppLabel(String appLabel) {
	this.appLabel = appLabel;
}
public String getAppName() {
	return appName;
}
public void setAppName(String appName) {
	this.appName = appName;
}
public Drawable getAppIcon() {
	return appIcon;
}
public void setAppIcon(Drawable appIcon) {
	this.appIcon = appIcon;
}

}
