package com.example.administrator.kotlintest.channel;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.kotlintest.R;

public class DragGrid extends GridView {
	/** ���ʱ���Xλ�� */
	public int downX;
	/** ���ʱ���Yλ�� */
	public int downY;
	/** ���ʱ���Ӧ��������Xλ�� */
	public int windowX;
	/** ���ʱ���Ӧ��������Yλ�� */
	public int windowY;
	/** ��Ļ�ϵ�X */
	private int win_view_x;
	/** ��Ļ�ϵ�Y*/
	private int win_view_y;
	/** �϶�����x�ľ���  */
	int dragOffsetX;
	/** �϶�����Y�ľ���  */
	int dragOffsetY;
	/** ����ʱ���Ӧpostion */
	public int dragPosition;
	/** Up���Ӧ��ITEM��Position */
	private int dropPosition;
	/** ��ʼ�϶���ITEM��Position*/
	private int startPosition;
	/** item�� */
	private int itemHeight;
	/** item�� */
	private int itemWidth;
	/** �϶���ʱ���ӦITEM��VIEW */
	private View dragImageView = null;
	/** ������ʱ��ITEM��VIEW*/
	private ViewGroup dragItemView = null;
	/** WindowManager������ */
	private WindowManager windowManager = null;
	/** */
	private WindowManager.LayoutParams windowParams = null;
	/** item����*/
	private int itemTotalCount;
	/** һ�е�ITEM����*/
	private int nColumns = 4;
	/** ���� */
	private int nRows;
	/** ʣ�ಿ�� */
	private int Remainder;
	/** �Ƿ����ƶ� */
	private boolean isMoving = false;
	/** */
	private int holdPosition;
	/** �϶���ʱ��Ŵ�ı��� */
	private double dragScale = 1.2D;
	/** ����  */
	private Vibrator mVibrator;
	/** ÿ��ITEM֮���ˮƽ��� */
	private int mHorizontalSpacing = 15;
	/** ÿ��ITEM֮�����ֱ��� */
	private int mVerticalSpacing = 15;
	/* �ƶ�ʱ������������ID */
	private String LastAnimationID;
	
	public DragGrid(Context context) {
		super(context);
		init(context);
	}

	public DragGrid(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public DragGrid(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public void init(Context context){
		mVibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		mHorizontalSpacing = DataTools.dip2px(context, mHorizontalSpacing);
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {
			downX = (int) ev.getX();
			downY = (int) ev.getY();
			windowX = (int) ev.getX();
			windowY = (int) ev.getY();
			setOnItemClickListener(ev);
		}
		return super.onInterceptTouchEvent(ev);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		boolean bool = true;
		if (dragImageView != null && dragPosition != AdapterView.INVALID_POSITION) {
			bool = super.onTouchEvent(ev);
			int x = (int) ev.getX();
			int y = (int) ev.getY();
			switch (ev.getAction()) {
			case MotionEvent.ACTION_DOWN:
				downX = (int) ev.getX();
				windowX = (int) ev.getX();
				downY = (int) ev.getY();
				windowY = (int) ev.getY();
				break;
			case MotionEvent.ACTION_MOVE:
				onDrag(x, y ,(int) ev.getRawX() , (int) ev.getRawY());
				if (!isMoving){
					OnMove(x, y);
				}
				if (pointToPosition(x, y) != AdapterView.INVALID_POSITION){
					break;
				}
				break;
			case MotionEvent.ACTION_UP:
				stopDrag();
				onDrop(x, y);
				requestDisallowInterceptTouchEvent(false);
				break;

			default:
				break;
			}
		}
		return super.onTouchEvent(ev);
	}
	
	private void onDrag(int x, int y , int rawx , int rawy) {
		if (dragImageView != null) {
			windowParams.alpha = 0.6f;
			windowParams.x = rawx - win_view_x;
			windowParams.y = rawy - win_view_y;
			windowManager.updateViewLayout(dragImageView, windowParams);
		}
	}

	private void onDrop(int x, int y) {
		int tempPostion = pointToPosition(x, y);
			dropPosition = tempPostion;
			DragAdapter mDragAdapter = (DragAdapter) getAdapter();
			mDragAdapter.setShowDropItem(true);
			mDragAdapter.notifyDataSetChanged();
	}
	
	public void setOnItemClickListener(final MotionEvent ev) {
		setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				int x = (int) ev.getX();
				int y = (int) ev.getY();

				startPosition = position;
				dragPosition = position;
				if (startPosition <= 1) {
					return false;
				}
				ViewGroup dragViewGroup = (ViewGroup) getChildAt(dragPosition - getFirstVisiblePosition());
				TextView dragTextView = (TextView)dragViewGroup.findViewById(R.id.text_item);
				dragTextView.setSelected(true);
				dragTextView.setEnabled(false);
				itemHeight = dragViewGroup.getHeight();
				itemWidth = dragViewGroup.getWidth();
				itemTotalCount = DragGrid.this.getCount();
				int row = itemTotalCount / nColumns;// �������
				Remainder = (itemTotalCount % nColumns);// ������һ�ж��������
				if (Remainder != 0) {
					nRows = row + 1;
				} else {
					nRows = row;
				}
				
				if (dragPosition != AdapterView.INVALID_POSITION) {
					win_view_x = windowX - dragViewGroup.getLeft();//VIEW����Լ���X�����
					win_view_y = windowY - dragViewGroup.getTop();//VIEW����Լ���y�����
					dragOffsetX = (int) (ev.getRawX() - x);//��ָ����Ļ����Xλ��-��ָ�ڿؼ��е�λ�þ��Ǿ�������ߵľ���
					dragOffsetY = (int) (ev.getRawY() - y);//��ָ����Ļ����yλ��-��ָ�ڿؼ��е�λ�þ��Ǿ������ϱߵľ���
					dragItemView = dragViewGroup;
					dragViewGroup.destroyDrawingCache();
					dragViewGroup.setDrawingCacheEnabled(true);
					Bitmap dragBitmap = Bitmap.createBitmap(dragViewGroup.getDrawingCache());
					mVibrator.vibrate(50);//������ʱ��
					startDrag(dragBitmap, (int)ev.getRawX(),  (int)ev.getRawY());
					hideDropItem();
					dragViewGroup.setVisibility(View.INVISIBLE);
					isMoving = false;
					requestDisallowInterceptTouchEvent(true);
					return true;
				}
				return false;
			}
		});
	}

	public void startDrag(Bitmap dragBitmap, int x, int y) {
		stopDrag();
		windowParams = new WindowManager.LayoutParams();// ��ȡWINDOW�����
		windowParams.gravity = Gravity.TOP | Gravity.LEFT;
		windowParams.x = x - win_view_x;
		windowParams.y = y  - win_view_y; 
		windowParams.width = (int) (dragScale * dragBitmap.getWidth());// �Ŵ�dragScale�������������϶���ı���
		windowParams.height = (int) (dragScale * dragBitmap.getHeight());// �Ŵ�dragScale�������������϶���ı���
		this.windowParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE                           
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE                           
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON                           
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;
		this.windowParams.format = PixelFormat.TRANSLUCENT;
		this.windowParams.windowAnimations = 0;
		ImageView iv = new ImageView(getContext());
		iv.setImageBitmap(dragBitmap);
		windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);// "window"
		windowManager.addView(iv, windowParams);
		dragImageView = iv;
	}

	private void stopDrag() {
		if (dragImageView != null) {
			windowManager.removeView(dragImageView);
			dragImageView = null;
		}
	}

	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
	
	private void hideDropItem() {
		((DragAdapter) getAdapter()).setShowDropItem(false);
	}
	
	/** ��ȡ�ƶ����� */
	public Animation getMoveAnimation(float toXValue, float toYValue) {
		TranslateAnimation mTranslateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, 0.0F,
				Animation.RELATIVE_TO_SELF,toXValue, 
				Animation.RELATIVE_TO_SELF, 0.0F,
				Animation.RELATIVE_TO_SELF, toYValue);// ��ǰλ���ƶ���ָ��λ��
		mTranslateAnimation.setFillAfter(true);// ����һ������Ч��ִ����Ϻ�View����������ֹ��λ�á�
		mTranslateAnimation.setDuration(300L);
		return mTranslateAnimation;
	}
	
	/** �ƶ���ʱ�򴥷�*/
	public void OnMove(int x, int y) {
		// �϶���VIEW�·���POSTION
		int dPosition = pointToPosition(x, y);
		// �ж��·���POSTION�Ƿ����ʼ2�������϶���
		if (dPosition > 1) {
	        if ((dPosition == -1) || (dPosition == dragPosition)){
	        	return;
	        }
		    dropPosition = dPosition;
		    if (dragPosition != startPosition){
		    	dragPosition = startPosition;
		    }
			int movecount;
			//�϶���=��ʼ�ϵģ����� �϶��� �����ڷ��µ�
		    if ((dragPosition == startPosition) || (dragPosition != dropPosition)){
		    	//����Ҫ�ƶ��Ķ�ITEM����
		    	movecount = dropPosition - dragPosition;
		    }else{
		    	//����Ҫ�ƶ��Ķ�ITEM����Ϊ0
		    	movecount = 0;
		    }
		    if(movecount == 0){
		    	return;
		    }
		    
		    int movecount_abs = Math.abs(movecount);
		    
			if (dPosition != dragPosition) {
				//dragGroup����Ϊ���ɼ�
				ViewGroup dragGroup = (ViewGroup) getChildAt(dragPosition);
				dragGroup.setVisibility(View.INVISIBLE);
				float to_x = 1;// ��ǰ�·�positon
				float to_y;// ��ǰ�·��ұ�positon
				//x_vlaue�ƶ��ľ���ٷֱȣ�������Լ����ȵİٷֱȣ�
				float x_vlaue = ((float) mHorizontalSpacing / (float) itemWidth) + 1.0f;
				//y_vlaue�ƶ��ľ���ٷֱȣ�������Լ���ȵİٷֱȣ�
				float y_vlaue = ((float) mVerticalSpacing / (float) itemHeight) + 1.0f;
				Log.d("x_vlaue", "x_vlaue = " + x_vlaue);
				for (int i = 0; i < movecount_abs; i++) {
					 to_x = x_vlaue;
					 to_y = y_vlaue;
					//����
					if (movecount > 0) {
						// �ж��ǲ���ͬһ�е�
						holdPosition = dragPosition + i + 1;
						if (dragPosition / nColumns == holdPosition / nColumns) {
							to_x = - x_vlaue;
							to_y = 0;
						} else if (holdPosition % 4 == 0) {
							to_x = 3 * x_vlaue;
							to_y = - y_vlaue;
						} else {
							to_x = - x_vlaue;
							to_y = 0;
						}
					}else{
						//����,���Ƶ��ϣ����Ƶ���
						holdPosition = dragPosition - i - 1;
						if (dragPosition / nColumns == holdPosition / nColumns) {
							to_x = x_vlaue;
							to_y = 0;
						} else if((holdPosition + 1) % 4 == 0){
							to_x = -3 * x_vlaue;
							to_y = y_vlaue;
						}else{
							to_x = x_vlaue;
							to_y = 0;
						}
					}
					ViewGroup moveViewGroup = (ViewGroup) getChildAt(holdPosition);
					Animation moveAnimation = getMoveAnimation(to_x, to_y);
					moveViewGroup.startAnimation(moveAnimation);
					//��������һ���ƶ��ģ���ô���������������IDΪLastAnimationID
					if (holdPosition == dropPosition) {
						LastAnimationID = moveAnimation.toString();
					}
					moveAnimation.setAnimationListener(new AnimationListener() {

						@Override
						public void onAnimationStart(Animation animation) {
							// TODO Auto-generated method stub
							isMoving = true;
						}

						@Override
						public void onAnimationRepeat(Animation animation) {
							// TODO Auto-generated method stub

						}

						@Override
						public void onAnimationEnd(Animation animation) {
							// TODO Auto-generated method stub
							// ���Ϊ��������������ִ������ķ���
							if (animation.toString().equalsIgnoreCase(LastAnimationID)) {
								DragAdapter mDragAdapter = (DragAdapter) getAdapter();
								mDragAdapter.exchange(startPosition,dropPosition);
								startPosition = dropPosition;
								dragPosition = dropPosition;
								isMoving = false;
							}
						}
					});
				}
			}
		}
	}
}