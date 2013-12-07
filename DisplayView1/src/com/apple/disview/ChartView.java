package com.apple.disview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.apple.asset.ChartEntity;
import com.apple.asset.DeviceInfo;

/**
 * 
 * ��״����ʾUI���
 * 
 * @author ����ƽ
 * 
 */

public class ChartView extends View {

	private ChartEntity entity;

	private Paint paint;
	// ÿ�����ο��
	private int zWith = 0;
	private int zPadding = 0;
	private int startX = 0;
	private int totalHeight = 0;
	private int lineHeight = 0;
	private int zHeight = 0;
	// ����x������ʾ����
	private int wSize = 0;
	// ����y������ʾ����
	private int hSize = 0;

	public ChartView(Context context, ChartEntity entity) {
		super(context);
		paint = new Paint();
		paint.setAntiAlias(true);
		this.entity = entity;
		zWith = DeviceInfo.DipToPixels(this.getContext(), entity.row_weight);
		zPadding = DeviceInfo.DipToPixels(this.getContext(),
				entity.padding_weight);
		startX = DeviceInfo.DipToPixels(this.getContext(), entity.startX);
		zHeight = DeviceInfo.DipToPixels(this.getContext(), entity.row_height);
		totalHeight = DeviceInfo
				.DipToPixels(this.getContext(), entity.row_height
						* entity.hList.size() + entity.padding_height);
		lineHeight = DeviceInfo.DipToPixels(this.getContext(),
				entity.row_height * entity.hList.size());
		wSize = entity != null && entity.wList != null
				&& entity.wList.size() > 0 ? entity.wList.size() : 0;
		hSize = entity != null && entity.hList != null
				&& entity.hList.size() > 0 ? entity.hList.size() : 0;
	}

	public void drawAxis(Canvas canvas) {
		//���û�������
		paint.setColor(Color.RED);
		paint.setStrokeWidth(2);
		paint.setTextSize(DeviceInfo.DipToPixels(this.getContext(), 12));
		//���α�滭����
		canvas.drawLine(startX, lineHeight, wSize * zWith + wSize * zPadding
				+ startX, lineHeight, paint);
		canvas.drawLine(startX, 0, startX, lineHeight, paint);
		int x = 0;
		int y = 0;
		for (int i = 0; i < wSize; i++) {
			x = startX + i * zPadding + (i) * zWith;
			if (entity != null && entity.wList != null) {
				String wVal = entity.wList.get(i);
				String[] wArray = wVal.split(",");
				if (wArray[1].equals("true")) {
					canvas.drawText(wArray[0], x, totalHeight, paint);
				}
			}
		}
		for (int i = 0; i < hSize; i++) {
			y = lineHeight - i * zHeight;
			if (entity != null && entity.hList != null) {
				String wVal = entity.hList.get(i);
				String[] wArray = wVal.split(",");
				if (i == 0) {
					canvas.drawText(wArray[0],
							DeviceInfo.DipToPixels(this.getContext(), 5), y,
							paint);
				} else {
					//���α�滭�ı�
					canvas.drawText(
							wArray[0],
							startX
									- DeviceInfo.DipToPixels(this.getContext(),
											15), y, paint);
				}
			}
		}
	}

	public void drawChart(Canvas canvas) {
		paint.setColor(Color.RED);
		if (entity != null && entity.wList.size() > 0) {
			for (int i = 0; i < wSize; i++) {
				int total = (int) (entity.map.get(entity.wList.get(i)) == null ? 0
						: entity.map.get(entity.wList.get(i)) * entity.scale);
				//���α껭һ������
				canvas.drawRect((i + 1) * zPadding + (i) * zWith + startX,
						lineHeight - total, i * zPadding + i * zWith + startX,
						lineHeight, paint);
			}
		}
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		final int widthSize = MeasureSpec.getSize(widthMeasureSpec);
		final int heightMode = MeasureSpec.getMode(heightMeasureSpec);
		final int heightSize = MeasureSpec.getSize(totalHeight);
		setMeasuredDimension(widthSize, heightSize);
	}

	@Override
	public void onDraw(Canvas canvas) {
		drawAxis(canvas);
		drawChart(canvas);
	}

}