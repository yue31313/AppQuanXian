package com.yarin.android.FileManager;

import java.text.AttributedCharacterIterator.Attribute;
import java.util.jar.Attributes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class IconifiedTextView extends LinearLayout
{
	private TextView	mText	= null;
	private ImageView	mIcon	= null;
	private Context context;
	public IconifiedTextView(Context context, IconifiedText aIconifiedText) 
	{
		super(context);
		this.context=context;
		this.setOrientation(HORIZONTAL);
		mIcon = new ImageView(context);
		mIcon.setImageDrawable(aIconifiedText.getIcon());
		mIcon.setPadding(8, 12, 8, 12); //左边框，上边框，右边框，下边框
		addView(mIcon,  new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mText = new TextView(context);
		mText.setText(aIconifiedText.getText());
		mText.setPadding(8, 6, 6, 10); 
		mText.setTextSize(26);
		//将文件名添加到布局中
		addView(mText, new LinearLayout.LayoutParams(
		LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		
	}
	//设置文件名
	public void setText(String words)
	{
		mText.setText(words);
	}
	//设置图标
	public void setIcon(Drawable bullet)
	{
		mIcon.setImageDrawable(bullet);
	}
	public View xiangdui(){
		RelativeLayout xd=new RelativeLayout(context);
		return xd;
	}
	
}

